package no.nav.dagpenger.kontrakter.iverksett.samordning

import no.nav.dagpenger.kontrakter.felles.StønadType
import java.util.UUID

data class DagpengerVedtakhendelse(
    val behandlingId: UUID,
    val personIdent: String,
    val stønadType: StønadType
)
