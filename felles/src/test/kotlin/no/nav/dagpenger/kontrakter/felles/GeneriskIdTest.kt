package no.nav.dagpenger.kontrakter.felles

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


class GeneriskIdTest {

    @Test
    fun `skal kaste feil hvis id som string er tom`() {
        assertThrows<IllegalArgumentException> { GeneriskIdSomString("") }
    }

    @Test
    fun `skal kaste feil hvis id som string er over 20 tegn`() {
        val id = "abcdabcdabcdabcdabcdabcdabcdabcd"
        assertThrows<IllegalArgumentException> { GeneriskIdSomString(id) }
    }

    @Test
    fun `skal lage generisk id med gyldig string`() {
        val id = "abcdabcdabcd"
        assertDoesNotThrow { GeneriskIdSomString(id) }
    }

    @Test
    fun `skal deserialisere ren string til GeneriskId`() {
        val id = "abc"
        val generiskId = objectMapper.readValue("\"$id\"", GeneriskId::class.java)

        assertNotNull(generiskId)
        assertTrue(generiskId is GeneriskIdSomString)
        assertEquals(id, generiskId.somString)
    }

    @Test
    fun `skal deserialisere uuid-string til GeneriskId`() {
        val id = "894d8db6-b535-11ee-bc98-325096b39f47"
        val generiskId = objectMapper.readValue("\"$id\"", GeneriskId::class.java)

        assertNotNull(generiskId)
        assertTrue(generiskId is GeneriskIdSomUUID)
        assertEquals(id, generiskId.somString)
    }

    @Test
    fun `skal deserialisere GeneriskId-json med UUID til GeneriskId`() {
        val id = GeneriskIdSomUUID(id = UUID.randomUUID())
        val idSomJson = objectMapper.writeValueAsString(id)
        val generiskId = objectMapper.readValue(idSomJson, GeneriskId::class.java)

        assertNotNull(generiskId)
        assertTrue(generiskId is GeneriskIdSomUUID)
        assertEquals(id.id, generiskId.somUUID)
    }

    @Test
    fun `skal deserialisere GeneriskId-json med String til GeneriskId`() {
        val id = GeneriskIdSomString(id = "abc")
        val idSomJson = objectMapper.writeValueAsString(id)
        val generiskId = objectMapper.readValue(idSomJson, GeneriskId::class.java)

        assertNotNull(generiskId)
        assertTrue(generiskId is GeneriskIdSomString)
        assertEquals(id.id, generiskId.somString)
    }
}