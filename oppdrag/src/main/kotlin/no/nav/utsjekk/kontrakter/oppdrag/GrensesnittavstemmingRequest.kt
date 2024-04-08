package no.nav.utsjekk.kontrakter.oppdrag

import no.nav.utsjekk.kontrakter.felles.Fagsystem
import java.time.LocalDateTime


data class GrensesnittavstemmingRequest(
    val fagsystem: Fagsystem,
    val fra: LocalDateTime,
    val til: LocalDateTime
)
