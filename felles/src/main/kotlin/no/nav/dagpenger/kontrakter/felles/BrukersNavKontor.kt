package no.nav.dagpenger.kontrakter.felles

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class BrukersNavKontor(
    @Schema(
        required = true,
        pattern = "^\\d{4}\$",
        example = "4444"
    )
    val enhet: String,
    @Schema(required = true)
    val gjelderFom: LocalDate,
)
