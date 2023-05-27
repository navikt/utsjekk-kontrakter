package no.nav.dagpenger.kontrakter.oppdrag

import no.nav.dagpenger.kontrakter.felles.Fagsystem
import java.util.UUID

data class OppdragId(
    val fagsystem: Fagsystem,
    val personIdent: String,
    val behandlingsId: UUID
) {
    override fun toString(): String = "OppdragId(fagsystem=$fagsystem, behandlingsId=$behandlingsId)"
}
