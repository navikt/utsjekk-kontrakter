package no.nav.dagpenger.kontrakter.felles

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class OrganisasjonsnummerTest {

    @Test
    internal fun `validerer organisasjonsnumre`() {
        assertDoesNotThrow {
            Organisasjonsnummer("701730496")
        }
        assertThrows<Exception> {
            Organisasjonsnummer("701730495")
        }
    }
}