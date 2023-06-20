package no.nav.dagpenger.kontrakter.iverksett

import no.nav.dagpenger.kontrakter.felles.StønadType
import java.time.LocalDate

data class UtbetalingDto(
    val belopPerDag: Int,
    val fraOgMedDato: LocalDate,
    val tilOgMedDato: LocalDate,
    val stonadstype: StønadType = StønadType.DAGPENGER_ARBEIDSSOKER_ORDINAER,
    val ferietillegg: Ferietillegg? = null
)

enum class Ferietillegg {
    ORDINAER,
    AVDOD
}
