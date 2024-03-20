package no.nav.dagpenger.kontrakter.iverksett

import io.swagger.v3.oas.annotations.media.Schema
import no.nav.dagpenger.kontrakter.felles.BrukersNavKontor
import no.nav.dagpenger.kontrakter.felles.GyldigStringId
import no.nav.dagpenger.kontrakter.felles.Makslengde
import no.nav.dagpenger.kontrakter.felles.Personident
import no.nav.dagpenger.kontrakter.felles.validerBehandlingId
import no.nav.dagpenger.kontrakter.felles.validerSakId
import java.time.LocalDateTime

data class IverksettDto(
    @GyldigStringId
    @Schema(
        required = true,
        minLength = 1,
        maxLength = Makslengde.SAK_ID,
    )
    val sakId: String,
    @GyldigStringId
    @Schema(
        required = true,
        minLength = 1,
        maxLength = Makslengde.BEHANDLING_ID,
    )
    val behandlingId: String,
    @Schema(required = true, description = "Fødselsnummer eller D-nummer", example = "15507600333", type = "string")
    val personident: Personident,
    @Schema(description = "Må være satt for utbetalingsvedtak")
    val vedtak: VedtaksdetaljerDto =
        VedtaksdetaljerDto(
            vedtakstidspunkt = LocalDateTime.now(),
            saksbehandlerId = "",
            beslutterId = "",
        ),
    @Schema(description = "Må være satt hvis det ikke er første iverksetting på saken")
    val forrigeIverksetting: ForrigeIverksettingDto? = null,
) {
    init {
        validerSakId(sakId)
        validerBehandlingId(behandlingId)
    }
}

data class VedtaksdetaljerDto(
    @Schema(required = true)
    val vedtakstidspunkt: LocalDateTime,
    @Schema(
        required = true,
        description = "NAV-ident til saksbehandler, eller servicebruker til applikasjon dersom vedtaket er fattet fullautomatisk",
        pattern = "^[A-Z]\\d{6}\$",
        example = "Z123456",
    )
    val saksbehandlerId: String,
    @Schema(
        required = true,
        description = "NAV-ident til beslutter, eller servicebruker til applikasjon dersom vedtaket er fattet fullautomatisk",
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
)

@Suppress("unused")
enum class IverksettStatus {
    SENDT_TIL_OPPDRAG,
    FEILET_MOT_OPPDRAG,
    OK,
    IKKE_PÅBEGYNT,
    OK_UTEN_UTBETALING,
}

data class ForrigeIverksettingDto(
    @GyldigStringId
    val behandlingId: String,
) {
    init {
        validerBehandlingId(behandlingId)
    }
}
