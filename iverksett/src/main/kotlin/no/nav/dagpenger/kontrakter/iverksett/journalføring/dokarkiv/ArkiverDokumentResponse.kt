package no.nav.dagpenger.kontrakter.iverksett.journalføring.dokarkiv

data class ArkiverDokumentResponse(
    val journalpostId: String,
    val ferdigstilt: Boolean,
    val dokumenter: List<DokumentInfo>? = null
)
