package no.nav.dagpenger.kontrakter.iverksett.tilbakekreving

import no.nav.dagpenger.kontrakter.felles.Regelverk
import no.nav.dagpenger.kontrakter.felles.SakIdentifikator
import no.nav.dagpenger.kontrakter.felles.Språkkode
import java.time.LocalDate
import java.util.UUID

data class HentFagsystemsbehandlingRequest(
    val sakId: UUID? = null,
    val saksreferanse: String? = null,
    val ytelsestype: Ytelsestype,
    val behandlingId: UUID,
){
    init {
        SakIdentifikator.valider(sakId, saksreferanse)
    }
}

data class HentFagsystemsbehandlingRespons(
    val feilMelding: String? = null,
    val hentFagsystemsbehandling: HentFagsystemsbehandling? = null,
)

data class HentFagsystemsbehandling(
    // Én av sakId og saksreferanse må være satt
    val sakId: UUID? = null,
    val saksreferanse: String? = null,
    val ytelsestype: Ytelsestype,
    val regelverk: Regelverk? = null,
    val behandlingId: UUID,
    val personIdent: String,
    val språkkode: Språkkode = Språkkode.NB,
    val enhetId: String,
    val enhetsnavn: String,
    val revurderingsvedtaksdato: LocalDate,
    val verge: Verge? = null,
    val faktainfo: Faktainfo,
    val institusjon: Institusjon? = null,
){
    init {
        SakIdentifikator.valider(sakId, saksreferanse)
    }
}
