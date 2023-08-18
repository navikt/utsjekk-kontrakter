package no.nav.dagpenger.kontrakter.datadeling

import java.time.LocalDate

data class DatadelingRequest(
    val personIdent: String,
    val fraOgMedDato: LocalDate,
    val tilOgMedDato: LocalDate? = null
)
