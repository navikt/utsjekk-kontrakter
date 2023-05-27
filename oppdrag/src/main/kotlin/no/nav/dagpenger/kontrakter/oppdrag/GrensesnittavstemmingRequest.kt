package no.nav.dagpenger.kontrakter.oppdrag

import no.nav.dagpenger.kontrakter.felles.Fagsystem
import java.time.LocalDateTime


data class GrensesnittavstemmingRequest(
    val fagsystem: Fagsystem,
    val fra: LocalDateTime,
    val til: LocalDateTime
)
