package no.nav.dagpenger.kontrakter.iverksett

import java.time.LocalDate

data class TilkjentYtelseDto(
    val utbetalinger: List<UtbetalingDto>,
)
