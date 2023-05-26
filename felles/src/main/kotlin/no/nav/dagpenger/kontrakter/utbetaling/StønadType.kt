package no.nav.dagpenger.kontrakter.utbetaling

enum class StønadType {
    DAGPENGER_ARBEIDSSOKER_ORDINAER,
    DAGPENGER_PERMITTERING_ORDINAER,
    DAGPENGER_PERMITTERING_FISKEINDUSTRI,
    DAGPENGER_EOS,
}

fun StønadType.tilKlassifisering(ferietillegg: Ferietillegg? = null) = when (this) {
    StønadType.DAGPENGER_ARBEIDSSOKER_ORDINAER -> when(ferietillegg) {
        Ferietillegg.VANLIG -> "DPORASFE"
        Ferietillegg.AVDØD -> "DPORASFE-IOP"
        null -> "DPORAS"
    }
    StønadType.DAGPENGER_PERMITTERING_ORDINAER -> when(ferietillegg) {
        Ferietillegg.VANLIG -> "DPPEASFE1"
        Ferietillegg.AVDØD-> "DPPEASFE1-IOP"
        null -> "DPPEAS"
    }
    StønadType.DAGPENGER_PERMITTERING_FISKEINDUSTRI -> when(ferietillegg) {
        Ferietillegg.VANLIG -> "DPPEFIFE1"
        Ferietillegg.AVDØD -> "DPPEFIFE1-IOP"
        null-> "DPPEFI"
    }
    StønadType.DAGPENGER_EOS -> when(ferietillegg) {
        Ferietillegg.VANLIG -> "DPFEASISP"
        Ferietillegg.AVDØD -> throw IllegalArgumentException("Eksport-gruppen har ingen egen kode for ferietillegg til avdød")
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