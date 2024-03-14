package no.nav.dagpenger.kontrakter.iverksett

import com.fasterxml.jackson.module.kotlin.readValue
import no.nav.dagpenger.kontrakter.felles.Personident
import no.nav.dagpenger.kontrakter.felles.StønadType
import no.nav.dagpenger.kontrakter.felles.StønadTypeDagpenger
import no.nav.dagpenger.kontrakter.felles.StønadTypeTiltakspenger
import no.nav.dagpenger.kontrakter.felles.objectMapper
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class IverksettDtoTest {
    @Test
    fun `tillater ikke tomme saksreferanser`() {
        assertThrows<IllegalArgumentException> {
            objectMapper.readValue<IverksettDto>(objectMapper.writeValueAsString(enIverksettDto(saksreferanse = "")))
        }
    }

    @Test
    fun `tillater ikke saksreferanser over 20 tegn`() {
        assertThrows<IllegalArgumentException> {
            objectMapper.readValue<IverksettDto>(objectMapper.writeValueAsString(enIverksettDto(saksreferanse = "aaaaaaaaaaaaaaaaaaaaa")))
        }
    }

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

    private fun enIverksettDto(saksreferanse: String = "S-123456") =
        IverksettDto(
            sakId = saksreferanse,
            behandlingId = "AOSCOBo1u12dOASUCNB",
            personident = Personident("15507600333"),
        )

    @Language("json")
    private fun json(stønadType: StønadType) =
        """
        {
          "sakId": "1234",
          "behandlingId": "1",
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
                "stønadsdata": {
                  "stønadstype": "$stønadType"
                }
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
