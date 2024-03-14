package no.nav.dagpenger.kontrakter.oppdrag

import no.nav.dagpenger.kontrakter.felles.GyldigStringId

/**
 * Perioder er idn for perioder, eks 1,2,3
 */
data class PerioderForBehandling(
    @GyldigStringId
    val behandlingId: String,
    val perioder: Set<Long>,
    val aktivFødselsnummer: String,
) {
    init {
        GyldigStringId.validate(behandlingId)
    }
}
