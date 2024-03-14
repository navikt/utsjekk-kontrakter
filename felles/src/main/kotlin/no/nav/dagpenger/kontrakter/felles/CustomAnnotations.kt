package no.nav.dagpenger.kontrakter.felles

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    minLength = 1,
    maxLength = 20,
    description = "På grunn av tekniske begrensninger hos OS/UR må denne IDen være en streng på mellom 1 og 20 karakterer",
    type = "String",
)
annotation class GyldigStringId {
    companion object {
        fun validate(string: String) {
            require(string.length in 1..20)
        }
    }
}
