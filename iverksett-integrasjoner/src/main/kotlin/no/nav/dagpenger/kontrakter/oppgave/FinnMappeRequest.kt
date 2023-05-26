package no.nav.dagpenger.kontrakter.iverksett.oppgave

data class FinnMappeRequest(
    val tema: List<String>? = null,
    val enhetsnr: String,
    val opprettetFom: String? = null,
    val limit: Int = 1000,
)
