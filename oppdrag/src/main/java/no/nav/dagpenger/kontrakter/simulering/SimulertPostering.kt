package no.nav.dagpenger.kontrakter.iverksett.simulering

import no.nav.dagpenger.kontrakter.iverksett.simulering.BetalingType
import no.nav.dagpenger.kontrakter.iverksett.simulering.FagOmrådeKode
import no.nav.dagpenger.kontrakter.iverksett.simulering.PosteringType
import java.math.BigDecimal
import java.time.LocalDate

data class SimulertPostering(
    val fagOmrådeKode: FagOmrådeKode,
    val erFeilkonto: Boolean? = null, // brukes for å skille manuelle korigeringer og reelle feilutbetalinger
    val fom: LocalDate,
    val tom: LocalDate,
    val betalingType: BetalingType,
    val beløp: BigDecimal,
    val posteringType: PosteringType,
    val forfallsdato: LocalDate,
    val utenInntrekk: Boolean = false
)