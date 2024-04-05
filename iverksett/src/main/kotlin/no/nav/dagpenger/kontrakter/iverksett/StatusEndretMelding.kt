package no.nav.utsjekk.kontrakter.iverksett

import no.nav.utsjekk.kontrakter.felles.Fagsystem

@Suppress("unused")
data class StatusEndretMelding(
    val sakId: String,
    val behandlingId: String,
    val iverksettingId: String?,
    val fagsystem: Fagsystem,
    val status: IverksettStatus,
)
