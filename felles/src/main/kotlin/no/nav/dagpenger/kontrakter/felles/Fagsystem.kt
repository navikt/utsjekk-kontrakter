package no.nav.dagpenger.kontrakter.felles

import java.lang.IllegalArgumentException

enum class Fagsystem(val kode: String) {
    Dagpenger("DP"),
    Tiltakspenger("TP")
}

fun String.tilFagsystem(): Fagsystem = Fagsystem.values().find { it.kode == this }
    ?: throw IllegalArgumentException("$this er ukjent fagsystem")