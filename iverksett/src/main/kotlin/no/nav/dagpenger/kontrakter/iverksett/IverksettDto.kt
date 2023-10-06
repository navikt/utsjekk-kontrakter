package no.nav.dagpenger.kontrakter.iverksett

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size
import java.time.LocalDateTime
import java.util.UUID
import no.nav.dagpenger.kontrakter.felles.SakIdentifikator


data class IverksettDto(
    @Schema(required = false, description = "Må være satt hvis saksreferanse ikke er satt")
    val sakId: UUID? = null,
    @Size(min = 1, max = 20)
    @Schema(required = false, description = "Må være satt hvis sakId ikke er satt")
    val saksreferanse: String? = null,
    @Schema(required = true)
    val behandlingId: UUID,
    @Schema(required = true, description = "Fødselsnummer eller D-nummer")
    val personIdent: String,
    @Schema(required = false, description = "Må være satt for utbetalingsvedtak")
    val vedtak: VedtaksdetaljerDto = VedtaksdetaljerDto(
        vedtakstype = VedtakType.RAMMEVEDTAK,
        vedtakstidspunkt = LocalDateTime.now(),
        resultat = Vedtaksresultat.INNVILGET,
        saksbehandlerId = "",
        beslutterId = ""
    ),
    @Schema(required = false, description = "Må være satt hvis det ikke er første iverksetting")
    val forrigeIverksetting: ForrigeIverksettingDto? = null
) {
    init {
        SakIdentifikator.valider(sakId, saksreferanse)
    }
}

data class VedtaksdetaljerDto(
    @Schema(required = true)
    val vedtakstype: VedtakType,
    @Schema(required = true)
    val vedtakstidspunkt: LocalDateTime,
    @Schema(required = true)
    val resultat: Vedtaksresultat,
    @Schema(required = true)
    val saksbehandlerId: String,
    @Schema(required = true)
    val beslutterId: String,
    @Schema(required = false)
    val utbetalinger: List<UtbetalingDto> = emptyList(),
    @Schema(hidden = true)
    val vedtaksperioder: List<VedtaksperiodeDto> = emptyList(),
)

enum class IverksettStatus {
    SENDT_TIL_OPPDRAG,
    FEILET_MOT_OPPDRAG,
    OK,
    IKKE_PAABEGYNT,
}

data class ForrigeIverksettingDto (
    val behandlingId: UUID,
)



