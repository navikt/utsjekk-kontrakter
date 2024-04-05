package no.nav.utsjekk.kontrakter.felles

import java.lang.IllegalArgumentException

enum class Fagsystem(val kode: String) {
    DAGPENGER("DP"),
    TILTAKSPENGER("TILTPENG"),
    TILLEGGSSTØNADER("TILLST"),
}

fun String.tilFagsystem(): Fagsystem =
    Fagsystem.values().find { it.kode == this }
        ?: throw IllegalArgumentException("$this er ukjent fagsystem")
