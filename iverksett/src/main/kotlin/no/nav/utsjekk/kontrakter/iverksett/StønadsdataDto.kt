package no.nav.utsjekk.kontrakter.iverksett

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.databind.JsonNode
import no.nav.utsjekk.kontrakter.felles.StønadType
import no.nav.utsjekk.kontrakter.felles.StønadTypeDagpenger
import no.nav.utsjekk.kontrakter.felles.StønadTypeTilleggsstønader
import no.nav.utsjekk.kontrakter.felles.StønadTypeTiltakspenger

enum class Ferietillegg {
    ORDINÆR,
    AVDØD,
}

/**
 * Stønadsdata representerer ytelsesspesifikke data for utbetalingsperioden. Se subklasser for detaljer.
 */
sealed class StønadsdataDto(open val stønadstype: StønadType) {
    companion object {
        @JsonCreator
        @JvmStatic
        fun deserialize(json: JsonNode) =
            listOf(
                StønadsdataDagpengerDto::deserialiser,
                StønadsdataTiltakspengerV2Dto::deserialiser,
                StønadsdataTilleggsstønaderDto::deserialiser
            )
                .map { it(json) }
                .firstOrNull { it != null } ?: error("Klarte ikke deserialisere stønadsdata")
    }
}

/**
 * @property stønadstype Stønadstypene for dagpenger representerer rettighetsgruppene Ordinær arbeidssøker, Permittert, Permittert fra fiskeindustri og EØS.
 * @property ferietillegg Settes når utbetalingen er et ferietillegg.
 */
data class StønadsdataDagpengerDto(
    override val stønadstype: StønadTypeDagpenger,
    val ferietillegg: Ferietillegg? = null
) :
    StønadsdataDto(stønadstype) {
    companion object {
        fun deserialiser(json: JsonNode) = try {
            StønadsdataDagpengerDto(
                stønadstype = StønadTypeDagpenger.valueOf(json["stønadstype"].asText()),
                ferietillegg = json["ferietillegg"]?.asText()
                    .takeIf { it != null && it != "null" }
                    ?.let { Ferietillegg.valueOf(it) }
            )
        } catch (_: Exception) {
            null
        }
    }
}

/**
 * @property stønadstype Stønadstypene for tiltakspenger representerer tiltakstypene.
 * @property barnetillegg Settes når utbetalingsperioden gjelder et barnetillegg.
 * @property brukersNavKontor Enhetsnummeret for NAV-kontoret som brukeren tilhører.
 */
data class StønadsdataTiltakspengerV2Dto(
    override val stønadstype: StønadTypeTiltakspenger,
    val barnetillegg: Boolean = false,
    val brukersNavKontor: String,
) : StønadsdataDto(stønadstype) {
    companion object {
        fun deserialiser(json: JsonNode) = try {
            StønadsdataTiltakspengerV2Dto(
                stønadstype = StønadTypeTiltakspenger.valueOf(json["stønadstype"].asText()),
                barnetillegg = json["barnetillegg"]?.asBoolean() ?: false,
                brukersNavKontor = json["brukersNavKontor"].asText(),
            )
        } catch (_: Exception) {
            null
        }
    }
}

/**
 * @property stønadstype Stønadstypene for tilleggsstønader representerer både hvilken stønad utbetalingen gjelder samt visse undergrupper
 * for den enkelte stønad (hva disse representerer kan variere)
 * @property brukersNavKontor Enhetsnummeret for NAV-kontoret som brukeren tilhører. Settes kun for reisestønadene.
 */
data class StønadsdataTilleggsstønaderDto(
    override val stønadstype: StønadTypeTilleggsstønader,
    val brukersNavKontor: String? = null,
) : StønadsdataDto(stønadstype) {
    companion object {
        fun deserialiser(json: JsonNode) = try {
            StønadsdataTilleggsstønaderDto(
                stønadstype = StønadTypeTilleggsstønader.valueOf(json["stønadstype"].asText()),
                brukersNavKontor = json["brukersNavKontor"]?.asText().takeIf { it != null && it != "null" },
            )
        } catch (_: Exception) {
            null
        }
    }
}