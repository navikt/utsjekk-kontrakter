package no.nav.dagpenger.kontrakter.iverksett

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class UtbetalingDto(
    @Schema(description = "Må være et positivt heltall")
    val beløpPerDag: Int,
    val fraOgMedDato: LocalDate,
    val tilOgMedDato: LocalDate,
    @Schema(oneOf = [StønadsdataDagpengerDto::class, StønadsdataTiltakspengerDto::class])
    val stønadsdata: StønadsdataDto,
)
