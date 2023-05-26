package no.nav.dagpenger.kontrakter.iverksett.tilbakekreving

import java.math.BigDecimal

data class Varsel(
    val varseltekst: String,
    val sumFeilutbetaling: BigDecimal,
    val perioder: List<Periode>,
)
