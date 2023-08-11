package no.nav.dagpenger.kontrakter.datadeling

import no.nav.dagpenger.kontrakter.felles.StønadType
import java.time.LocalDate

data class DatadelingResponse(
    val personIdent: String,
    val perioder: List<Periode>
)

data class Periode(
    val fraOgMedDato: LocalDate,
    val tilOgMedDato: LocalDate,
    val ytelseType: StønadType,
    val gjenståendeDager: Int
)
