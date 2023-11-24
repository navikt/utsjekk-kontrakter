package no.nav.dagpenger.kontrakter.felles

import java.lang.IllegalArgumentException
import java.util.UUID

data class SakIdentifikator(
    val sakId:  UUID? = null,
    val saksreferanse: String? = null
) {
    init {
        if (this.sakId == null && this.saksreferanse == null) {
            throw IllegalArgumentException("SakIdentifikator må ha enten sakId eller saksreferanse")
        }

        if (this.saksreferanse != null && this.saksreferanse.length > 20) {
            throw IllegalArgumentException("saksreferanse kan ikke være mer enn 20 tegn")
        }

        if (this.saksreferanse != null && this.saksreferanse.isEmpty()) {
            throw IllegalArgumentException("saksreferanse kan ikke være en tom streng")
        }
    }

    companion object{
        fun valider(sakId: UUID?, saksreferanse: String?) {
            SakIdentifikator(sakId, saksreferanse)
        }
    }
}
