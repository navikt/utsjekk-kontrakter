package no.nav.dagpenger.kontrakter.iverksett

import com.fasterxml.jackson.module.kotlin.readValue
import no.nav.dagpenger.kontrakter.felles.*
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.util.*

class IverksettDtoTest {

    @Test
    fun `serialiserer og deserialiserer`() {
        val dto = IverksettDto(
            sakId = UUID.randomUUID(),
            saksreferanse = "S-123456",
            behandlingId = UUID.randomUUID(),
            personident = Personident("15507600333"),
        )
        assertDoesNotThrow {
            objectMapper.readValue<IverksettDto>(objectMapper.writeValueAsString(dto))
        }
    }

    @Test
    fun `deserialiserer dto med stønadstype for dagpenger`() {
        assertDoesNotThrow { objectMapper.readValue<IverksettDto>(json(StønadTypeDagpenger.DAGPENGER_ARBEIDSSOKER_ORDINAER)) }
    }

    @Test
    fun `deserialiserer dto med stønadstype for tiltakspenger`() {
        assertDoesNotThrow { objectMapper.readValue<IverksettDto>(json(StønadTypeTiltakspenger.TILTAKSPENGER)) }
    }

    @Language("json")
    private fun json(stønadType: StønadType) = """
        {
          "sakId": "234bed7c-b1d3-11eb-9999-0242ac130003",
          "saksreferanse": "S-123456",
          "behandlingId": "234bed7c-b1d3-11eb-8529-0242ac130003",
          "personident": "15507600333",
          "vedtak": {
            "vedtakstype": "RAMMEVEDTAK",
            "vedtakstidspunkt": "2021-05-10T01:00:00",
            "resultat": "AVSLÅTT",
            "saksbehandlerId": "A123456",
            "beslutterId": "B123456",
            "opphorAarsak": "PERIODE_UTLØPT",
            "avslagAarsak": null,
            "utbetalinger": [
              {
                "belopPerDag": 10,
                "fraOgMedDato": "2021-05-01",
                "tilOgMedDato": "2021-05-31",
                "stonadstype": "$stønadType",
                "ferietillegg": "ORDINAER"
              }
            ],
            "vedtaksperioder": [
              {
                "fraOgMedDato": "2021-06-01",
                "tilOgMedDato": null,
                "periodeType": "HOVEDPERIODE"
              }
            ],
            "tilbakekreving": {
              "tilbakekrevingsvalg": "OPPRETT_TILBAKEKREVING_MED_VARSEL",
              "tilbakekrevingMedVarsel": {
                "varseltekst": "varsel",
                "sumFeilutbetaling": 1,
                "fellesperioder": []
              }
            },
            "brevmottakere": []
          },
          "utbetalingerPaaForrigeVedtak": [],
          "forrigeIverksetting": null
        }
    """.trimIndent()
}