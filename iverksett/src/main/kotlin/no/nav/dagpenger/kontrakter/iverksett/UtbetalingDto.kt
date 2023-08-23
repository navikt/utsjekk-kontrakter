package no.nav.dagpenger.kontrakter.iverksett

import io.swagger.v3.oas.annotations.media.Schema
import no.nav.dagpenger.kontrakter.felles.StønadType
import java.time.LocalDate

data class UtbetalingDto(
    @Schema(name = "beløp i kroner per dag")
    val belopPerDag: Int,
    val fraOgMedDato: LocalDate,
    val tilOgMedDato: LocalDate,
    @Schema(name = "Stønadstype",)
    val stonadstype: StønadType = StønadType.DAGPENGER_ARBEIDSSOKER_ORDINAER,
    @Schema(name = "ferietilleg", required = false, description = "Settes kun ved ferietillegg")
    val ferietillegg: Ferietillegg? = null
)

enum class Ferietillegg {
    ORDINAER,
    AVDOD
}
