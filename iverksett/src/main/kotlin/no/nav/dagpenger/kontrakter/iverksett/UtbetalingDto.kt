package no.nav.dagpenger.kontrakter.iverksett

import io.swagger.v3.oas.annotations.media.Schema
import no.nav.dagpenger.kontrakter.felles.StønadType
import java.time.LocalDate

data class UtbetalingDto(

    val belopPerDag: Int,
    val fraOgMedDato: LocalDate,
    val tilOgMedDato: LocalDate,
    @Schema(required = false, defaultValue = "DAGPENGER_ARBEIDSSOKER_ORDINAER")
    val stonadstype: StønadType = StønadType.DAGPENGER_ARBEIDSSOKER_ORDINAER,
    @Schema(required = false, description = "Settes kun ved ferietillegg")
    val ferietillegg: Ferietillegg? = null
)

enum class Ferietillegg {
    ORDINAER,
    AVDOD
}
