package no.nav.utsjekk.kontrakter.iverksett

import io.swagger.v3.oas.annotations.media.Schema
import no.nav.utsjekk.kontrakter.felles.GyldigBehandlingId
import no.nav.utsjekk.kontrakter.felles.GyldigSakId
import no.nav.utsjekk.kontrakter.felles.Personident
import java.time.LocalDateTime

data class IverksettTilleggsstønaderDto(
    @Schema(
        required = true,
        minLength = 1,
        maxLength = GyldigSakId.MAKSLENGDE,
        description = GyldigSakId.BESKRIVELSE,
        type = "String",
    )
    val sakId: String,
    @Schema(
        required = true,
        minLength = 1,
        maxLength = GyldigBehandlingId.MAKSLENGDE,
        description = GyldigBehandlingId.BESKRIVELSE,
        type = "String",
    )
    val behandlingId: String,
    val iverksettingId: String?,
    @Schema(required = true, description = "Fødselsnummer eller D-nummer", example = "15507600333", type = "string")
    val personident: Personident,
    @Schema(required = true)
    val vedtak: VedtaksdetaljerTilleggsstønaderDto,
    @Schema(description = "Må være satt hvis det ikke er første iverksetting på saken")
    val forrigeIverksetting: ForrigeIverksettingTilleggsstønaderDto? = null,
)

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
    @Schema(
        required = true,
        minLength = 1,
        maxLength = GyldigBehandlingId.MAKSLENGDE,
        description = GyldigBehandlingId.BESKRIVELSE,
        type = "String",
    )
    val behandlingId: String,
    val iverksettingId: String? = null,
)
