package no.nav.utsjekk.kontrakter.iverksett

import com.fasterxml.jackson.module.kotlin.readValue
import no.nav.utsjekk.kontrakter.felles.Personident
import no.nav.utsjekk.kontrakter.felles.StønadType
import no.nav.utsjekk.kontrakter.felles.StønadTypeDagpenger
import no.nav.utsjekk.kontrakter.felles.StønadTypeTiltakspenger
import no.nav.utsjekk.kontrakter.felles.objectMapper
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class IverksettDtoTest {

    @Test
    fun `serialiserer og deserialiserer`() {
        assertDoesNotThrow {
            objectMapper.readValue<IverksettDto>(objectMapper.writeValueAsString(enIverksettDto()))
        }
    }

    @Test
    fun `deserialiserer dto med stønadstype for dagpenger`() {
        assertDoesNotThrow { objectMapper.readValue<IverksettDto>(json(StønadTypeDagpenger.DAGPENGER_ARBEIDSSØKER_ORDINÆR)) }
    }

    @Test
    fun `deserialiserer dto med stønadstype for tiltakspenger`() {
        assertDoesNotThrow { objectMapper.readValue<IverksettDto>(json(StønadTypeTiltakspenger.JOBBKLUBB)) }
    }

    private fun enIverksettDto(sakId: String = "S-123456") =
        IverksettDto(
            sakId = sakId,
            behandlingId = "AOSCOBo1u12dOASUCNB",
            personident = Personident("15507600333"),
        )

    @Language("json")
    private fun json(stønadType: StønadType): String {
        val brukersNavKontor = if (stønadType is StønadTypeTiltakspenger) "{ \"enhet\": \"4400\" }" else null
        return """
        {
          "sakId": "1234",
          "behandlingId": "1",
          "personident": "15507600333",
          "vedtak": {
            "vedtakstidspunkt": "2021-05-10T01:00:00",
            "saksbehandlerId": "A123456",
            "beslutterId": "B123456",
            "brukersNavKontor": $brukersNavKontor,
            "utbetalinger": [
              {
                "belopPerDag": 10,
                "fraOgMedDato": "2021-05-01",
                "tilOgMedDato": "2021-05-31",
                "stønadsdata": {
                  "stønadstype": "$stønadType"
                }
              }
            ]
          },
          "forrigeIverksetting": null
        }
        """.trimIndent()
    }
}
