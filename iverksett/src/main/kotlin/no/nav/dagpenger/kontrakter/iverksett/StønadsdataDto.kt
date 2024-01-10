package no.nav.dagpenger.kontrakter.iverksett

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.databind.JsonNode
import no.nav.dagpenger.kontrakter.felles.StønadType
import no.nav.dagpenger.kontrakter.felles.StønadTypeDagpenger
import no.nav.dagpenger.kontrakter.felles.StønadTypeTiltakspenger

enum class Ferietillegg {
    ORDINÆR,
    AVDØD,
}

sealed class StønadsdataDto(open val stønadstype: StønadType) {
    companion object {
        @JsonCreator
        @JvmStatic
        fun deserialize(json: JsonNode): StønadsdataDto {
            val stønadstype = json.findValue("stønadstype").asText()
            return Result.runCatching { StønadTypeDagpenger.valueOf(stønadstype) }.fold(
                onSuccess = {
                    val ferietillegg = json.findValue("ferietillegg")?.asText()
                    if (ferietillegg != null && ferietillegg != "null") {
                        StønadsdataDagpengerDto(it, Ferietillegg.valueOf(ferietillegg))
                    } else {
                        StønadsdataDagpengerDto(it)
                    }
                },
                onFailure = {
                    val stønadTypeTiltakspenger = StønadTypeTiltakspenger.valueOf(stønadstype)
                    val barnetillegg = json.findValue("barnetillegg")?.asBoolean()
                    StønadsdataTiltakspengerDto(stønadTypeTiltakspenger, barnetillegg ?: false)
                },
            )
        }
    }
}

data class StønadsdataDagpengerDto(override val stønadstype: StønadTypeDagpenger, val ferietillegg: Ferietillegg? = null) :
    StønadsdataDto(stønadstype)

data class StønadsdataTiltakspengerDto(
    override val stønadstype: StønadTypeTiltakspenger,
    val barnetillegg: Boolean = false,
) :
    StønadsdataDto(stønadstype)
