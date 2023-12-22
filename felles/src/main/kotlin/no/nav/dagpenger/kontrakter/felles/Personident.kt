package no.nav.dagpenger.kontrakter.felles

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.databind.JsonNode

class Personident(verdi: String) : Ident(verdi) {
    init {
        check(gyldig()) { "Personidenten er ugyldig" }
    }

    companion object {
        @JvmStatic
        @JsonCreator
        fun deserialize(json: String) = Personident(json)

        @JvmStatic
        @JsonCreator
        fun deserializeObject(json: JsonNode) = Personident(json.get("verdi").asText())
    }

    private fun gyldig(): Boolean {
        if (verdi.length != 11 || verdi.toLongOrNull() == null) {
            return false
        }

        val siffer = verdi.chunked(1).map { it.toInt() }
        val k1Vekting = intArrayOf(3, 7, 6, 1, 8, 9, 4, 5, 2)
        val k2Vekting = intArrayOf(5, 4, 3, 2, 7, 6, 5, 4, 3, 2)

        val kontrollMod1 = 11 - (0..8).sumOf { k1Vekting[it] * siffer[it] } % 11
        val kontrollMod2 = 11 - (0..9).sumOf { k2Vekting[it] * siffer[it] } % 11
        val kontrollsiffer1 = siffer[9]
        val kontrollsiffer2 = siffer[10]

        return gyldigKontrollSiffer(kontrollMod1, kontrollsiffer1) && gyldigKontrollSiffer(kontrollMod2, kontrollsiffer2)
    }

    private fun gyldigKontrollSiffer(kontrollMod: Int, kontrollsiffer: Int): Boolean {
        if (kontrollMod == kontrollsiffer) {
            return true
        }
        if (kontrollMod == 11 && kontrollsiffer == 0) {
            return true
        }
        return false
    }
}
