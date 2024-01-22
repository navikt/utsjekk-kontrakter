package no.nav.dagpenger.kontrakter.felles

import com.fasterxml.jackson.annotation.JsonCreator

sealed interface StønadType {
    fun tilFagsystem(): Fagsystem

    companion object {
        @JsonCreator
        @JvmStatic
        fun deserialize(json: String): StønadType? {
            val stønadstypeDagpenger = StønadTypeDagpenger.values().find { it.name == json }
            val stønadstypeTiltakspenger = StønadTypeTiltakspenger.values().find { it.name == json }
            val stønadstypeTilleggsstønader = StønadTypeTilleggsstønader.values().find { it.name == json }
            return stønadstypeDagpenger ?: stønadstypeTiltakspenger ?: stønadstypeTilleggsstønader
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

enum class StønadTypeTilleggsstønader : StønadType {
    TILSYN_BARN_ENSLIG_FORSØRGER,
    TILSYN_BARN_AAP,
    TILSYN_BARN_ETTERLATTE,
    ;

    override fun tilFagsystem(): Fagsystem = Fagsystem.Tilleggsstønader
}
