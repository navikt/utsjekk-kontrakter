package no.nav.utsjekk.kontrakter.oppdrag

import no.nav.utsjekk.kontrakter.felles.Fagsystem
import no.nav.utsjekk.kontrakter.felles.GyldigBehandlingId
import no.nav.utsjekk.kontrakter.felles.GyldigSakId

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
