package no.nav.dagpenger.kontrakter.utbetaling

enum class StønadType {
    DAGPENGER_ARBEIDSSOKER_ORDINAER,
    DAGPENGER_PERMITTERING_ORDINAER,
    DAGPENGER_PERMITTERING_FISKEINDUSTRI,
    DAGPENGER_EOS,
}

fun StønadType.tilKlassifisering(ferietilllegg: Ferietilllegg? = null) = when (this) {
    StønadType.DAGPENGER_ARBEIDSSOKER_ORDINAER -> when(ferietilllegg) {
        Ferietilllegg.VANLIG -> "DPORASFE"
        Ferietilllegg.AVDØD -> "DPORASFE-IOP"
        null -> "DPORAS"
    }
    StønadType.DAGPENGER_PERMITTERING_ORDINAER -> when(ferietilllegg) {
        Ferietilllegg.VANLIG -> "DPPEASFE1"
        Ferietilllegg.AVDØD-> "DPPEASFE1-IOP"
        null -> "DPPEAS"
    }
    StønadType.DAGPENGER_PERMITTERING_FISKEINDUSTRI -> when(ferietilllegg) {
        Ferietilllegg.VANLIG -> "DPPEFIFE1"
        Ferietilllegg.AVDØD -> "DPPEFIFE1-IOP"
        null-> "DPPEFI"
    }
    StønadType.DAGPENGER_EOS -> when(ferietilllegg) {
        Ferietilllegg.VANLIG -> "DPFEASISP"
        Ferietilllegg.AVDØD -> throw IllegalArgumentException("Eksport-gruppen har ingen egen kode for ferietillegg til avdød")
        null -> "DPDPASISP1"
    }
}

fun StønadType.tilFagsystem() = when (this) {
    StønadType.DAGPENGER_ARBEIDSSOKER_ORDINAER,
    StønadType.DAGPENGER_PERMITTERING_ORDINAER,
    StønadType.DAGPENGER_PERMITTERING_FISKEINDUSTRI,
    StønadType.DAGPENGER_EOS
    -> Fagsystem.Dagpenger
}