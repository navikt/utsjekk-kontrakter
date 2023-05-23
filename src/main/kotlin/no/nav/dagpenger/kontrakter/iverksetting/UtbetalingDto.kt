package no.nav.dagpenger.kontrakter.iverksetting

import no.nav.dagpenger.kontrakter.utbetaling.StønadType
import java.time.LocalDate

data class UtbetalingDto(
    val beløp: Int,
    val fraOgMedDato: LocalDate,
    val tilOgMedDato: LocalDate,
    val stønadType: StønadType
)
