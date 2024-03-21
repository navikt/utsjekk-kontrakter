package no.nav.dagpenger.kontrakter.felles

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    required = true,
    minLength = 1,
    maxLength = GyldigSakId.MAKSLENGDE,
    description = "På grunn av tekniske begrensninger hos OS/UR er det en lengdebegrensning på ${GyldigSakId.MAKSLENGDE} for sakId",
    type = "String",
)
annotation class GyldigSakId {
    companion object {
        const val MAKSLENGDE = 25

        fun valider(string: String) {
            require(string.length in 1..MAKSLENGDE)
        }
    }
}

@Schema(
    required = true,
    minLength = 1,
    maxLength = GyldigBehandlingId.MAKSLENGDE,
    description =
        "På grunn av tekniske begrensninger hos OS/UR er det en lengdebegrensning på ${GyldigBehandlingId.MAKSLENGDE} for behandlingId",
    type = "String",
)
annotation class GyldigBehandlingId {
    companion object {
        const val MAKSLENGDE = 30

        fun valider(string: String) {
            require(string.length in 1..MAKSLENGDE)
        }
    }
}
