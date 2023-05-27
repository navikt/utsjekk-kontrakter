package no.nav.dagpenger.kontrakter.oppdrag

fun Utbetalingsoppdrag.behandlingsIdForFørsteUtbetalingsperiode(): String {

    return utbetalingsperiode[0].behandlingId.toString()
}

