package no.nav.dagpenger.kontrakter.felles

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.databind.JsonNode

sealed class Ident(val verdi: String) {
    companion object {
        @JvmStatic
        @JsonCreator
        fun deserialize(json: String) =
            when (json.length) {
                9 -> Organisasjonsnummer(json)
                11 -> Personident(json)
                else -> throw Exception("Ugyldig ident")
            }

        @JvmStatic
        @JsonCreator
        fun deserializeObject(json: JsonNode) = deserialize(json.get("verdi").asText())
    }
}