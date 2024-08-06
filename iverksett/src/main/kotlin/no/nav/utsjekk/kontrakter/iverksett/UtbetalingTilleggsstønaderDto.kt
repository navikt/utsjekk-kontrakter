package no.nav.utsjekk.kontrakter.iverksett

import io.swagger.v3.oas.annotations.media.Schema
import no.nav.utsjekk.kontrakter.felles.BrukersNavKontor
import no.nav.utsjekk.kontrakter.felles.Satstype
import no.nav.utsjekk.kontrakter.felles.StønadTypeTilleggsstønader
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import java.util.Date

data class UtbetalingTilleggsstønaderDto(
    @Schema(description = "Må være et positivt heltall")
    val beløp: Int,
    val satstype: Satstype,
    val fraOgMedDato: LocalDate,
    val tilOgMedDato: LocalDate,
    val stønadstype: StønadTypeTilleggsstønader,
    val brukersNavKontor: BrukersNavKontor? = null,
) {
    init {
        validerSatstype()
    }

    private fun validerSatstype() {
        when (satstype) {
            Satstype.MÅNEDLIG -> validerMånedssats()
            else -> return
        }
    }

    private fun validerMånedssats() {
        require(fraOgMedDato.dayOfMonth == 1 && tilOgMedDato == sisteDagIMåneden) {
            "Utbetalinger med satstype ${satstype.name} må starte den første i måneden og slutte den siste i måneden"
        }
    }

    private val sisteDagIMåneden
        get(): LocalDate {
            val defaultZoneId = ZoneId.systemDefault()
            val calendar =
                Calendar.getInstance().apply {
                    time = Date.from(tilOgMedDato.atStartOfDay(defaultZoneId).toInstant())
                    set(Calendar.DAY_OF_MONTH, this.getActualMaximum(Calendar.DAY_OF_MONTH))
                }

            return LocalDate.ofInstant(calendar.toInstant(), defaultZoneId)
        }
}
