package no.nav.dagpenger.kontrakter.felles

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.databind.JsonNode

data class Organisasjonsnummer(val verdi: String) {
    init {
        check(gyldig()) { "Organisasjonsnummeret er ugyldig" }
    }

    companion object {
        @JvmStatic
        @JsonCreator
        fun deserialize(json: String) = Organisasjonsnummer(json)

        @JvmStatic
        @JsonCreator
        fun deserializeObject(json: JsonNode) = Organisasjonsnummer(json.get("verdi").asText())
    }

    private fun gyldig(): Boolean {
        val sifre = verdi.chunked(1).map(String::toInt)
        val vekttall = intArrayOf(3, 2, 7, 6, 5, 4, 3, 2)
        val kontrolltall = 11 - (0..7).sumOf { vekttall[it] * sifre[it] } % 11

        return kontrolltall == sifre.last() || kontrolltall == 11 && sifre.last() == 0
    }
}
