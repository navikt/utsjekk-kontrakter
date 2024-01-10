package no.nav.dagpenger.kontrakter.iverksett

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size
import no.nav.dagpenger.kontrakter.felles.BrukersNavKontor
import no.nav.dagpenger.kontrakter.felles.Personident
import no.nav.dagpenger.kontrakter.felles.SakIdentifikator
import java.time.LocalDateTime
import java.util.*

data class IverksettDto(
    @Schema(description = "Må være satt hvis saksreferanse ikke er satt")
    val sakId: UUID? = null,
    @Size(min = 1, max = 20)
    @Schema(description = "Må være satt hvis sakId ikke er satt")
    val saksreferanse: String? = null,
    @Schema(required = true)
    val behandlingId: UUID,
    @Schema(required = true, description = "Fødselsnummer eller D-nummer", example = "15507600333", type = "string")
    val personident: Personident,
    @Schema(description = "Må være satt for utbetalingsvedtak")
    val vedtak: VedtaksdetaljerDto =
        VedtaksdetaljerDto(
            vedtakstidspunkt = LocalDateTime.now(),
            resultat = Vedtaksresultat.INNVILGET,
            saksbehandlerId = "",
            beslutterId = "",
        ),
    @Schema(description = "Må være satt hvis det ikke er første iverksetting")
    val forrigeIverksetting: ForrigeIverksettingDto? = null,
) {
    init {
        SakIdentifikator.valider(sakId, saksreferanse)
    }
}

data class VedtaksdetaljerDto(
    @Schema(required = true)
    val vedtakstidspunkt: LocalDateTime,
    @Schema(required = true)
    val resultat: Vedtaksresultat,
    @Schema(
        required = true,
        description = "NAV-ident til saksbehandler, eller servicebruker til applikasjon dersom vedtaket er fattet fullautomatisk",
        pattern = "^[A-Z]\\d{6}\$",
        example = "Z123456",
    )
    val saksbehandlerId: String,
    @Schema(
        required = true,
        description = "NAV-ident til saksbehandler, eller servicebruker til applikasjon dersom vedtaket er fattet fullautomatisk",
        pattern = "^[A-Z]\\d{6}\$",
        example = "Z123456",
    )
    val beslutterId: String,
    @Schema(
        required = false,
        description = "Settes kun for tiltakspenger",
    )
    val brukersNavKontor: BrukersNavKontor? = null,
    @Schema(required = false)
    val utbetalinger: List<UtbetalingDto> = emptyList(),
    @Schema(hidden = true)
    val vedtaksperioder: List<VedtaksperiodeDto> = emptyList(),
)

@Suppress("unused")
enum class IverksettStatus {
    SENDT_TIL_OPPDRAG,
    FEILET_MOT_OPPDRAG,
    OK,
    IKKE_PAABEGYNT,
}

data class ForrigeIverksettingDto(
    val behandlingId: UUID,
)
