package no.nav.utsjekk.kontrakter.iverksett

import com.fasterxml.jackson.databind.exc.ValueInstantiationException
import com.fasterxml.jackson.module.kotlin.readValue
import no.nav.utsjekk.kontrakter.felles.Satstype
import no.nav.utsjekk.kontrakter.felles.StønadTypeTilleggsstønader
import no.nav.utsjekk.kontrakter.felles.objectMapper
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class IverksettTilleggsstønaderDtoTest {
    @Test
    fun `serialiserer og deserialiserer`() {
        val rawJson = json()

        assertDoesNotThrow {
            objectMapper.readValue<IverksettTilleggsstønaderDto>(rawJson)
        }
    }

    @Test
    fun `gyldig validering når månedssats starter den første i måneden og slutter den siste i måneden`() {
        val rawJson =
            json(
                fom = LocalDate.of(2021, 5, 1),
                tom = LocalDate.of(2021, 5, 31),
            )

        assertDoesNotThrow {
            objectMapper.readValue<IverksettTilleggsstønaderDto>(rawJson)
        }
    }

    @Test
    fun `ugyldig validering når månedssats ikke starter den første i måneden`() {
        val rawJson =
            json(
                fom = LocalDate.of(2021, 5, 2),
                tom = LocalDate.of(2021, 5, 31),
            )

        assertThrows<ValueInstantiationException> {
            objectMapper.readValue<IverksettTilleggsstønaderDto>(rawJson)
        }
    }

    @Test
    fun `ugyldig validering når månedssats ikke slutter den siste i måneden`() {
        val rawJson =
            json(
                fom = LocalDate.of(2021, 5, 1),
                tom = LocalDate.of(2021, 5, 30),
            )

        assertThrows<ValueInstantiationException> {
            objectMapper.readValue<IverksettTilleggsstønaderDto>(rawJson)
        }
    }

    @Language("json")
    private fun json(
        stønadType: StønadTypeTilleggsstønader = StønadTypeTilleggsstønader.TILSYN_BARN_AAP,
        satstype: Satstype = Satstype.MÅNEDLIG,
        fom: LocalDate = LocalDate.of(2021, 5, 1),
        tom: LocalDate = LocalDate.of(2021, 5, 31),
    ) = """
        {
          "sakId": "ASDNOUDBOWQD12",
          "behandlingId": "1",
          "personident": "15507600333",
          "vedtak": {
            "vedtakstidspunkt": "2021-05-10T01:00:00",
            "saksbehandlerId": "A123456",
            "beslutterId": "B123456",
            "utbetalinger": [
              {
                "beløp": 1000,
                "satstype": "$satstype",
                "fraOgMedDato": "$fom",
                "tilOgMedDato": "$tom",
                "stønadstype": "$stønadType"
              }
            ]
          }
        }
        """.trimIndent()
}
