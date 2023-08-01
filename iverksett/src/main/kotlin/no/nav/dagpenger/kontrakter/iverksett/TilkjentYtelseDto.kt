package no.nav.dagpenger.kontrakter.iverksett

import java.time.LocalDate

data class TilkjentYtelseDto(
    val utbetalinger: List<UtbetalingDto>,
    @Deprecated("Ikke i bruk") val startdato: LocalDate = LocalDate.now()
)
