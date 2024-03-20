package no.nav.dagpenger.kontrakter.felles

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    description =
        "På grunn av tekniske begrensninger hos OS/UR er det en lengdebegrensning på ${Makslengde.SAK_ID} for sakId" +
            " og ${Makslengde.BEHANDLING_ID} for behandlingId",
    type = "String",
)
annotation class GyldigStringId {
    companion object {
        fun valider(
            string: String,
            makslengde: Int,
        ) {
            require(string.length in 1..makslengde)
        }
    }
}

object Makslengde {
    const val BEHANDLING_ID = 30
    const val SAK_ID = 25
}

fun validerSakId(sakId: String) = GyldigStringId.valider(sakId, Makslengde.SAK_ID)

fun validerBehandlingId(behandlingId: String) = GyldigStringId.valider(behandlingId, Makslengde.BEHANDLING_ID)
