package no.nav.dagpenger.kontrakter.iverksett.journalføring.dokarkiv

import no.nav.dagpenger.kontrakter.felles.Språkkode

data class Førsteside(
    val språkkode: Språkkode = Språkkode.NB,
    val navSkjemaId: String,
    val overskriftstittel: String
)
