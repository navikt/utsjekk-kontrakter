package no.nav.dagpenger.kontrakter.oppdrag

import no.nav.dagpenger.kontrakter.felles.Fagsystem
import no.nav.dagpenger.kontrakter.felles.GyldigBehandlingId
import no.nav.dagpenger.kontrakter.felles.GyldigSakId

@Suppress("unused")
data class OppdragIdDto(
    val fagsystem: Fagsystem,
    @GyldigSakId
    val sakId: String,
    @GyldigBehandlingId
    val behandlingId: String,
    val iverksettingId: String?,
) {
    init {
        GyldigSakId.valider(sakId)
        GyldigBehandlingId.valider(behandlingId)
    }

    override fun toString(): String =
        "OppdragId(fagsystem=$fagsystem, sakId=$sakId, behandlingsId=$behandlingId, iverksettingId=$iverksettingId)"
}
