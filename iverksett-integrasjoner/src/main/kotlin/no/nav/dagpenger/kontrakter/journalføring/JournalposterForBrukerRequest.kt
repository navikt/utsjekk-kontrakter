package no.nav.dagpenger.kontrakter.iverksett.journalføring

import no.nav.dagpenger.kontrakter.felles.Tema

data class JournalposterForBrukerRequest(
    val brukerId: Bruker,
    val antall: Int,
    val tema: List<Tema>? = null,
    val journalposttype: List<Journalposttype>? = null
)
