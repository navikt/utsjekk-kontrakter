package no.nav.dagpenger.kontrakter.iverksett.journalføring.dokarkiv

import no.nav.dagpenger.kontrakter.felles.Fagsystem

data class Sak(
    val arkivsaksnummer: String? = null,
    val arkivsaksystem: String? = null,
    val fagsakId: String? = null,
    val fagsaksystem: Fagsystem? = null,
    val sakstype: String? = null
)
