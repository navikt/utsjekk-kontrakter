package no.nav.dagpenger.kontrakter.iverksett

import no.nav.dagpenger.kontrakter.felles.Fagsystem

@Suppress("unused")
data class StatusEndretMelding(
    val sakId: String,
    val behandlingId: String,
    val iverksettingId: String?,
    val fagsystem: Fagsystem,
    val status: IverksettStatus,
)
