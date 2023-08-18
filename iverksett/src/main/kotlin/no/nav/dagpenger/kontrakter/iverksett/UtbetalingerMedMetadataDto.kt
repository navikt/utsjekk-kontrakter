package no.nav.dagpenger.kontrakter.iverksett

import no.nav.dagpenger.kontrakter.felles.SakIdentifikator
import no.nav.dagpenger.kontrakter.felles.StønadType
import java.time.LocalDate
import java.util.UUID

data class UtbetalingerMedMetadataDto(
    val utbetalinger: List<UtbetalingDto>,
    val saksbehandlerId: String,
    val stønadstype: StønadType,
    val sakId: UUID? = null,
    val saksreferanse: String? =  null,
    val personIdent: String,
    val behandlingId: UUID,
    val vedtaksdato: LocalDate,
){
    init {
        SakIdentifikator.valider(sakId, saksreferanse)
    }
}
