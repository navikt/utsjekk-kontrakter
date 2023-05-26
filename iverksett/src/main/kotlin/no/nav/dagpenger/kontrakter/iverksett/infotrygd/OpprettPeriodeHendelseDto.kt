package no.nav.dagpenger.kontrakter.iverksett.infotrygd

import java.time.LocalDate

data class Periode(val startdato: LocalDate, val sluttdato: LocalDate, val fulleDagpenger: Boolean)
