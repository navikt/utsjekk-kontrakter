package no.nav.dagpenger.kontrakter.oppdrag

@Suppress("unused")
data class OppdragStatusDto(
    val status: OppdragStatus,
    val feilmelding: String?,
)

@Suppress("unused")
enum class OppdragStatus {
    LAGT_PÅ_KØ,
    KVITTERT_OK,
    KVITTERT_MED_MANGLER,
    KVITTERT_FUNKSJONELL_FEIL,
    KVITTERT_TEKNISK_FEIL,
    KVITTERT_UKJENT,
}
