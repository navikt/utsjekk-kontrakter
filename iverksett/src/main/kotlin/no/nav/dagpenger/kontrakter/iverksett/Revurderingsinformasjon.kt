package no.nav.dagpenger.kontrakter.iverksett

import no.nav.dagpenger.kontrakter.felles.StønadType
import no.nav.dagpenger.kontrakter.felles.StønadTypeDagpenger

enum class Opplysningskilde {
    INNSENDT_SØKNAD,
    MELDING_MODIA,
    INNSENDT_MELDEKORT
}

@Suppress("EnumEntryName", "unused")
enum class Revurderingsårsak(
    vararg stønadstyper: StønadType = arrayOf(
        StønadTypeDagpenger.DAGPENGER_ARBEIDSSOKER_ORDINAER
    ),
) {
    ENDRING_INNTEKT(StønadTypeDagpenger.DAGPENGER_ARBEIDSSOKER_ORDINAER),
    SYKDOM(StønadTypeDagpenger.DAGPENGER_ARBEIDSSOKER_ORDINAER),
    ANNET,
    KLAGE_OMGJØRING,
    ANKE_OMGJØRING,
    ;

    val gjelderStønadstyper = stønadstyper.toSet()

    fun erGyldigForStønadstype(stønadType: StønadType): Boolean {
        return gjelderStønadstyper.contains(stønadType)
    }
}
