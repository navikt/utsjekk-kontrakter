package no.nav.utsjekk.kontrakter.iverksett

import com.fasterxml.jackson.databind.exc.ValueInstantiationException
import com.fasterxml.jackson.module.kotlin.readValue
import no.nav.utsjekk.kontrakter.felles.Personident
import no.nav.utsjekk.kontrakter.felles.StønadTypeDagpenger
import no.nav.utsjekk.kontrakter.felles.StønadTypeTilleggsstønader
import no.nav.utsjekk.kontrakter.felles.StønadTypeTiltakspenger
import no.nav.utsjekk.kontrakter.felles.objectMapper
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class IverksettDtoV2Test {

    @Test
    fun `serialiserer og deserialiserer`() {
        assertDoesNotThrow {
            objectMapper.readValue<IverksettV2Dto>(objectMapper.writeValueAsString(enIverksettDto()))
        }
    }

    @Test
    fun `deserialiserer dto med stønadsdata for dagpenger`() {
        val iverksettV2 = assertDoesNotThrow { objectMapper.readValue<IverksettV2Dto>(json(StønadsdataDagpengerDto(StønadTypeDagpenger.DAGPENGER_ARBEIDSSØKER_ORDINÆR))) }

        assertTrue(iverksettV2.vedtak.utbetalinger.first().stønadsdata is StønadsdataDagpengerDto)
    }

    @Test
    fun `deserialiserer dto med stønadsdata med ferietillegg for dagpenger`() {
        val iverksettV2 = assertDoesNotThrow { objectMapper.readValue<IverksettV2Dto>(json(StønadsdataDagpengerDto(stønadstype = StønadTypeDagpenger.DAGPENGER_ARBEIDSSØKER_ORDINÆR, ferietillegg = Ferietillegg.AVDØD))) }

        assertTrue(iverksettV2.vedtak.utbetalinger.first().stønadsdata is StønadsdataDagpengerDto)
    }

    @Test
    fun `deserialiserer dto med stønadsdata for tiltakspenger`() {
        val iverksettV2 = assertDoesNotThrow {
             objectMapper.readValue<IverksettV2Dto>(
                json(
                    StønadsdataTiltakspengerV2Dto(
                        stønadstype = StønadTypeTiltakspenger.JOBBKLUBB,
                        brukersNavKontor = "4401"
                    )
                )
            )
        }

        assertTrue(iverksettV2.vedtak.utbetalinger.first().stønadsdata is StønadsdataTiltakspengerV2Dto)
    }


    @Test
    fun `deserialiserer dto med stønadsdata med barnetillegg for tiltakspenger`() {
        val iverksettV2 = assertDoesNotThrow {
            objectMapper.readValue<IverksettV2Dto>(
                json(
                    StønadsdataTiltakspengerV2Dto(
                        stønadstype = StønadTypeTiltakspenger.JOBBKLUBB,
                        barnetillegg = true,
                        brukersNavKontor = "4401"
                    )
                )
            )
        }

        assertTrue(iverksettV2.vedtak.utbetalinger.first().stønadsdata is StønadsdataTiltakspengerV2Dto)
    }

    @Test
    fun `deserialiserer dto med stønadsdata for tilleggsstønader`() {
        val iverksettV2 = assertDoesNotThrow {
            objectMapper.readValue<IverksettV2Dto>(
                json(
                    StønadsdataTilleggsstønaderDto(
                        stønadstype = StønadTypeTilleggsstønader.TILSYN_BARN_AAP
                    )
                )
            )
        }

        assertTrue(iverksettV2.vedtak.utbetalinger.first().stønadsdata is StønadsdataTilleggsstønaderDto)
    }

    @Test
    fun `deserialiserer dto med stønadsdata med brukersNavKontor for tilleggsstønader`() {
        val iverksettV2 = assertDoesNotThrow {
            objectMapper.readValue<IverksettV2Dto>(
                json(
                    StønadsdataTilleggsstønaderDto(
                        stønadstype = StønadTypeTilleggsstønader.TILSYN_BARN_AAP,
                        brukersNavKontor = "4401"
                    )
                )
            )
        }

        assertTrue(iverksettV2.vedtak.utbetalinger.first().stønadsdata is StønadsdataTilleggsstønaderDto)
    }

    @Test
    fun `skal feile når den ikke klarer å deserialisere Stønadsdata`() {
        val json = """
        {
          "sakId": "1234",
          "behandlingId": "1",
          "personident": "15507600333",
          "vedtak": {
            "vedtakstidspunkt": "2021-05-10T01:00:00",
            "saksbehandlerId": "A123456",
            "beslutterId": "B123456",
            "utbetalinger": [
              {
                "beløp": 10,
                "satstype": "DAGLIG",
                "fraOgMedDato": "2021-05-01",
                "tilOgMedDato": "2021-05-31",
                "stønadsdata": {
                    "stønadstype": "UKJENT"
                }
              }
            ]
          },
          "forrigeIverksetting": null
        }
        """.trimIndent()

        val exception = assertThrows<ValueInstantiationException> {
            objectMapper.readValue<IverksettV2Dto>(json)
        }

        assertEquals("Klarte ikke deserialisere stønadsdata", exception.cause?.message)
    }

    private fun enIverksettDto(sakId: String = "S-123456") =
        IverksettV2Dto(
            sakId = sakId,
            behandlingId = "AOSCOBo1u12dOASUCNB",
            personident = Personident("15507600333"),
        )

    @Language("json")
    private fun json(stønadsdataDto: StønadsdataDto): String {
        val stønadsdata = objectMapper.writeValueAsString(stønadsdataDto)
        return """
        {
          "sakId": "1234",
          "behandlingId": "1",
          "personident": "15507600333",
          "vedtak": {
            "vedtakstidspunkt": "2021-05-10T01:00:00",
            "saksbehandlerId": "A123456",
            "beslutterId": "B123456",
            "utbetalinger": [
              {
                "beløp": 10,
                "satstype": "DAGLIG",
                "fraOgMedDato": "2021-05-01",
                "tilOgMedDato": "2021-05-31",
                "stønadsdata": $stønadsdata
              }
            ]
          },
          "forrigeIverksetting": null
        }
        """.trimIndent()
    }
}
