package no.nav.dagpenger.kontrakter.iverksett

import io.swagger.v3.oas.annotations.media.Schema
import no.nav.dagpenger.kontrakter.felles.GyldigStringId
import no.nav.dagpenger.kontrakter.felles.Personident
import no.nav.dagpenger.kontrakter.felles.validerBehandlingId
import no.nav.dagpenger.kontrakter.felles.validerSakId
import java.time.LocalDateTime

data class IverksettTilleggsstønaderDto(
    @GyldigStringId
    @Schema(required = true)
    val sakId: String,
    @GyldigStringId
    @Schema(required = true)
    val behandlingId: String,
    val iverksettingId: String?,
    @Schema(required = true, description = "Fødselsnummer eller D-nummer", example = "15507600333", type = "string")
    val personident: Personident,
    @Schema(required = true)
    val vedtak: VedtaksdetaljerTilleggsstønaderDto,
    @Schema(description = "Må være satt hvis det ikke er første iverksetting på saken")
    val forrigeIverksetting: ForrigeIverksettingTilleggsstønaderDto? = null,
) {
    init {
        validerSakId(sakId)
        validerBehandlingId(behandlingId)
    }
}

data class VedtaksdetaljerTilleggsstønaderDto(
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
    @Schema(required = false)
    val utbetalinger: List<UtbetalingTilleggsstønaderDto> = emptyList(),
)

data class ForrigeIverksettingTilleggsstønaderDto(
    @GyldigStringId
    @Schema(required = true)
    val behandlingId: String,
    val iverksettingId: String? = null,
) {
    init {
        validerBehandlingId(behandlingId)
    }
}
