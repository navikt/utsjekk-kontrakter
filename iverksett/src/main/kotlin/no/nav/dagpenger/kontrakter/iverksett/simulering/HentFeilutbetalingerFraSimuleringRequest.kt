package no.nav.dagpenger.kontrakter.iverksett.simulering

import no.nav.dagpenger.kontrakter.iverksett.tilbakekreving.Ytelsestype
import java.math.BigDecimal
import java.time.LocalDate

data class HentFeilutbetalingerFraSimuleringRequest(
    val ytelsestype: Ytelsestype,
    val eksternFagsakId: String,
    val fagsystemsbehandlingId: String
)

data class FeilutbetalingerFraSimulering(val feilutbetaltePerioder: List<FeilutbetaltPeriode>)

data class FeilutbetaltPeriode(
    val fom: LocalDate,
    val tom: LocalDate,
    val nyttBeløp: BigDecimal,
    val tidligereUtbetaltBeløp: BigDecimal,
    val feilutbetaltBeløp: BigDecimal
)
