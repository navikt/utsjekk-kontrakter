package no.nav.dagpenger.kontrakter.felles

enum class StønadType {
    DAGPENGER_ARBEIDSSOKER_ORDINAER,
    DAGPENGER_PERMITTERING_ORDINAER,
    DAGPENGER_PERMITTERING_FISKEINDUSTRI,
    DAGPENGER_EOS,
}


fun StønadType.tilFagsystem() = when (this) {
    StønadType.DAGPENGER_ARBEIDSSOKER_ORDINAER,
    StønadType.DAGPENGER_PERMITTERING_ORDINAER,
    StønadType.DAGPENGER_PERMITTERING_FISKEINDUSTRI,
    StønadType.DAGPENGER_EOS
    -> Fagsystem.Dagpenger
}