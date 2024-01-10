package no.nav.dagpenger.kontrakter.oppdrag

fun Utbetalingsoppdrag.behandlingsIdForFørsteUtbetalingsperiode() = utbetalingsperiode[0].behandlingId.toString()
