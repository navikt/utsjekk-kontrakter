package no.nav.dagpenger.kontrakter.iverksett.journalføring

data class DokumentInfo(
    val dokumentInfoId: String,
    val tittel: String? = null,
    val brevkode: String? = null,
    val dokumentstatus: Dokumentstatus? = null,
    val dokumentvarianter: List<Dokumentvariant>? = null,
    val logiskeVedlegg: List<LogiskVedlegg>? = null
)
