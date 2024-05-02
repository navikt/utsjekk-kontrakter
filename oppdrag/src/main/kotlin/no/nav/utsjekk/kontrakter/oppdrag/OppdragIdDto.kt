package no.nav.utsjekk.kontrakter.oppdrag

import no.nav.utsjekk.kontrakter.felles.Fagsystem

@Suppress("unused")
data class OppdragIdDto(
    val fagsystem: Fagsystem,
    val sakId: String,
    val behandlingId: String,
    val iverksettingId: String?,
) {
    override fun toString(): String =
        "OppdragId(fagsystem=$fagsystem, sakId=$sakId, behandlingsId=$behandlingId, iverksettingId=$iverksettingId)"
}
