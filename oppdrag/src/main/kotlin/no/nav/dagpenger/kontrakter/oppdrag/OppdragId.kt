package no.nav.dagpenger.kontrakter.oppdrag

import no.nav.dagpenger.kontrakter.felles.Fagsystem
import no.nav.dagpenger.kontrakter.felles.GeneriskId

@Suppress("unused")
data class OppdragId(
    val fagsystem: Fagsystem,
    val personIdent: String,
    val behandlingId: GeneriskId,
) {
    override fun toString(): String = "OppdragId(fagsystem=$fagsystem, behandlingsId=$behandlingId)"
}
