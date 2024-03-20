package no.nav.dagpenger.kontrakter.oppdrag

import no.nav.dagpenger.kontrakter.felles.Fagsystem
import no.nav.dagpenger.kontrakter.felles.GyldigStringId
import no.nav.dagpenger.kontrakter.felles.validerBehandlingId
import no.nav.dagpenger.kontrakter.felles.validerSakId

@Suppress("unused")
data class OppdragIdDto(
    val fagsystem: Fagsystem,
    @GyldigStringId
    val sakId: String,
    @GyldigStringId
    val behandlingId: String,
    val iverksettingId: String?,
) {
    init {
        validerSakId(sakId)
        validerBehandlingId(behandlingId)
    }

    override fun toString(): String =
        "OppdragId(fagsystem=$fagsystem, sakId=$sakId, behandlingsId=$behandlingId, iverksettingId=$iverksettingId)"
}
