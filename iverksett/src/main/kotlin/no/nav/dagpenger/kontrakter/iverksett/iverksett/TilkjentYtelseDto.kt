package no.nav.dagpenger.kontrakter.iverksett.iverksett

import java.time.LocalDate

data class TilkjentYtelseDto(
    val utbetalinger: List<UtbetalingDto>,
    val startdato: LocalDate,
)
