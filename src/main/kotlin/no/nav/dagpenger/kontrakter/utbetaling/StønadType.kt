package no.nav.dagpenger.kontrakter.utbetaling

enum class StønadType {
    DAGPENGER_ARBEIDSSOKER_ORDINAER,
    DAGPENGER_PERMITTERING_ORDINAER,
    DAGPENGER_PERMITTERING_FISKEINDUSTRI,
    DAGPENGER_EOS,
}

fun StønadType.tilKlassifisering() = when (this) {
    StønadType.DAGPENGER_ARBEIDSSOKER_ORDINAER -> "DPORAS"
    StønadType.DAGPENGER_PERMITTERING_ORDINAER -> "DPPEAS"
    StønadType.DAGPENGER_PERMITTERING_FISKEINDUSTRI -> "DPPEFI"
    StønadType.DAGPENGER_EOS -> "DPDPASISP1"
}

fun StønadType.tilFagsystem() = when (this) {
    StønadType.DAGPENGER_ARBEIDSSOKER_ORDINAER,
    StønadType.DAGPENGER_PERMITTERING_ORDINAER,
    StønadType.DAGPENGER_PERMITTERING_FISKEINDUSTRI,
    StønadType.DAGPENGER_EOS
    -> Fagsystem.Dagpenger
}