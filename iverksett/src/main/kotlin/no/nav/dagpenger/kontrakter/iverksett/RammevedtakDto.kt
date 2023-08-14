package no.nav.dagpenger.kontrakter.iverksett

import java.util.UUID

data class RammevedtakDto(
    val sakId: UUID,
    val behandlingId: UUID,
    val personIdent: String
)
