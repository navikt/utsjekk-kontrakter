package no.nav.dagpenger.kontrakter.utbetaling

import java.time.LocalDateTime


data class GrensesnittavstemmingRequest(
    val fagsystem: Fagsystem,
    val fra: LocalDateTime,
    val til: LocalDateTime
)
