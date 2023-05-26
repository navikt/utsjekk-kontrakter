package no.nav.dagpenger.kontrakter.iverksett.simulering

data class BeriketSimuleringsresultat(
    val detaljer: DetaljertSimuleringResultat,
    val oppsummering: Simuleringsoppsummering
)
