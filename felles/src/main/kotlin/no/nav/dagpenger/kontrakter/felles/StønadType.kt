package no.nav.dagpenger.kontrakter.felles

import com.fasterxml.jackson.annotation.JsonCreator

sealed interface StønadType {
    fun tilFagsystem(): Fagsystem

    fun tilEnum(): Enum<*> {
        return if (this is StønadTypeDagpenger) {
            this
        } else {
            this as StønadTypeTiltakspenger
        }
    }

    companion object {
        @JsonCreator
        @JvmStatic
        fun deserialize(json: String): StønadType? {
            return Result.runCatching { StønadTypeDagpenger.valueOf(json) }
                .getOrElse {
                    Result.runCatching { StønadTypeTiltakspenger.valueOf(json) }
                        .getOrNull()
                }
        }
    }
}

enum class StønadTypeDagpenger : StønadType {
    DAGPENGER_ARBEIDSSØKER_ORDINÆR,
    DAGPENGER_PERMITTERING_ORDINÆR,
    DAGPENGER_PERMITTERING_FISKEINDUSTRI,
    DAGPENGER_EØS,
    ;

    override fun tilFagsystem(): Fagsystem = Fagsystem.Dagpenger
}

enum class StønadTypeTiltakspenger : StønadType {
    ARBEIDSFORBEREDENDE_TRENING,
    ARBEIDSRETTET_REHABILITERING,
    ARBEIDSTRENING,
    AVKLARING,
    DIGITAL_JOBBKLUBB,
    ENKELTPLASS_AMO,
    ENKELTPLASS_VGS_OG_HØYERE_YRKESFAG,
    FORSØK_OPPLÆRING_LENGRE_VARIGHET,
    GRUPPE_AMO,
    GRUPPE_VGS_OG_HØYERE_YRKESFAG,
    HØYERE_UTDANNING,
    INDIVIDUELL_JOBBSTØTTE,
    INDIVIDUELL_KARRIERESTØTTE_UNG,
    JOBBKLUBB,
    OPPFØLGING,
    UTVIDET_OPPFØLGING_I_NAV,
    UTVIDET_OPPFØLGING_I_OPPLÆRING,
    ;

    override fun tilFagsystem(): Fagsystem = Fagsystem.Tiltakspenger
}
