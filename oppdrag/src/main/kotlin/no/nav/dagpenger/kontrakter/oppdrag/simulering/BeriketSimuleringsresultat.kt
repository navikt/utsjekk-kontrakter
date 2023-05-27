package no.nav.dagpenger.kontrakter.oppdrag.simulering

data class BeriketSimuleringsresultat(
    val detaljer: DetaljertSimuleringResultat,
    val oppsummering: Simuleringsoppsummering
)
