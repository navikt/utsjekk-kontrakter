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
    DAGPENGER_ARBEIDSSOKER_ORDINAER,
    DAGPENGER_PERMITTERING_ORDINAER,
    DAGPENGER_PERMITTERING_FISKEINDUSTRI,
    DAGPENGER_EOS;

    override fun tilFagsystem(): Fagsystem = Fagsystem.Dagpenger
}

enum class StønadTypeTiltakspenger : StønadType {
    ARBEIDSFORBEREDENDE_TRENING,
    ARBEIDSRETTET_REHABILITERING,
    ARBEIDSTRENING,
    AVKLARING,
    DIGITAL_JOBBKLUBB,
    ENKELTPLASS_AMO,
    ENKELTPLASS_VGS_OG_HOYERE_YRKESFAG,
    FORSOK_OPPLAERING_LENGRE_VARIGHET,
    GRUPPE_AMO,
    GRUPPE_VGS_OG_HOYERE_YRKESFAG,
    HOYERE_UTDANNING,
    INDIVIDUELL_JOBBSTOTTE,
    INDIVIDUELL_KARRIERESTOTTE_UNG,
    JOBBKLUBB,
    OPPFOLGING,
    UTVIDET_OPPFOLGING_I_NAV,
    UTVIDET_OPPFOLGING_I_OPPLAERING;

    override fun tilFagsystem(): Fagsystem = Fagsystem.Tiltakspenger
}