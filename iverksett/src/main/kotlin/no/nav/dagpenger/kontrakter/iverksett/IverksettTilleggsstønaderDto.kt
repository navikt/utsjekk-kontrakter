package no.nav.dagpenger.kontrakter.iverksett

import io.swagger.v3.oas.annotations.media.Schema
import no.nav.dagpenger.kontrakter.felles.GyldigBehandlingId
import no.nav.dagpenger.kontrakter.felles.GyldigSakId
import no.nav.dagpenger.kontrakter.felles.Personident
import java.time.LocalDateTime

data class IverksettTilleggsstønaderDto(
    @GyldigSakId
    val sakId: String,
    @GyldigBehandlingId
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
        GyldigSakId.valider(sakId)
        GyldigBehandlingId.valider(behandlingId)
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
    @GyldigBehandlingId
    val behandlingId: String,
    val iverksettingId: String? = null,
) {
    init {
        GyldigBehandlingId.valider(behandlingId)
    }
}
