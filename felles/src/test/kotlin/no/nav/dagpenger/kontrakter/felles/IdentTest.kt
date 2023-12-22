package no.nav.dagpenger.kontrakter.felles

import com.fasterxml.jackson.databind.exc.ValueInstantiationException
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class IdentTest {

    @Test
    fun `deserialiserer til organisasjonsnummer`() {
        val json = """{ "ident": "701730496" }""".trimIndent()
        val dto = objectMapper.readValue<TestDto>(json)

        assertEquals(Organisasjonsnummer::class, dto.ident::class)
    }

    @Test
    fun `deserialiserer til personnummer`() {
        val json = """{ "ident": "15507600333" }""".trimIndent()
        val dto = objectMapper.readValue<TestDto>(json)

        assertEquals(Personident::class, dto.ident::class)
    }

    @Test
    fun `kaster feil om lengden på identen er feil`() {
        val json = """{ "ident": "1550760033" }""".trimIndent()

        assertThrows<ValueInstantiationException> {
            objectMapper.readValue<TestDto>(json)
        }
    }
}

private data class TestDto(
    val ident: Ident
)

