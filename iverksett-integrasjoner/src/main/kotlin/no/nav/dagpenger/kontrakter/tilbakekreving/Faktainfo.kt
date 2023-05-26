package no.nav.dagpenger.kontrakter.iverksett.tilbakekreving

import no.nav.dagpenger.kontrakter.felles.Tilbakekrevingsvalg

data class Faktainfo(
    val revurderingsårsak: String,
    val revurderingsresultat: String,
    val tilbakekrevingsvalg: Tilbakekrevingsvalg? = null,
    val konsekvensForYtelser: Set<String> = emptySet(),
)

