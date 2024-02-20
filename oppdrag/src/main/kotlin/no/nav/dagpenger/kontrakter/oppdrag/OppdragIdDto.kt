package no.nav.dagpenger.kontrakter.oppdrag

import no.nav.dagpenger.kontrakter.felles.Fagsystem
import no.nav.dagpenger.kontrakter.felles.GeneriskId
import no.nav.dagpenger.kontrakter.felles.somString

@Suppress("unused")
data class OppdragIdDto(
    val fagsystem: Fagsystem,
    val sakId: GeneriskId,
    val behandlingId: GeneriskId,
    val iverksettingId: String?,
) {
    override fun toString(): String =
        "OppdragId(fagsystem=$fagsystem, sakId=${sakId.somString}, behandlingsId=${behandlingId.somString}, iverksettingId=$iverksettingId)"
}
