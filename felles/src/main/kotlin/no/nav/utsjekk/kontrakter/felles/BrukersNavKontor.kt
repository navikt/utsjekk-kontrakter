package no.nav.utsjekk.kontrakter.felles

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class BrukersNavKontor(
    @Schema(
        required = true,
        pattern = "^\\d{4}\$",
        example = "4444",
    )
    val enhet: String,
    @Schema(required = false)
    @Deprecated("Ikke lenger i bruk, settes nå internt")
    val gjelderFom: LocalDate? = null,
)
