package no.nav.utsjekk.kontrakter.iverksett

import io.swagger.v3.oas.annotations.media.Schema
import no.nav.utsjekk.kontrakter.felles.GyldigBehandlingId
import no.nav.utsjekk.kontrakter.felles.GyldigSakId
import no.nav.utsjekk.kontrakter.felles.Personident
import no.nav.utsjekk.kontrakter.felles.Satstype
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Representerer en iverksetting.
 * @property sakId Id på saken i saksbehandlingsløsningen
 * @property behandlingId Id på behandlingen i saksbehandlingsløsningen
 * @property iverksettingId Id som unikt identifiserer iverksettingen. Brukes når konsument må iverksette flere ganger for samme behandling
 */
data class IverksettV2Dto(
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
    val iverksettingId: String? = null,
    @Schema(required = true, description = "Fødselsnummer eller D-nummer", example = "15507600333", type = "string")
    val personident: Personident,
    @Schema(description = "Må være satt for utbetalingsvedtak")
    val vedtak: VedtaksdetaljerV2Dto =
        VedtaksdetaljerV2Dto(
            vedtakstidspunkt = LocalDateTime.now(),
            saksbehandlerId = "",
            beslutterId = "",
        ),
    @Schema(description = "Må være satt hvis det ikke er første iverksetting på saken")
    val forrigeIverksetting: ForrigeIverksettingV2Dto? = null,
)

data class VedtaksdetaljerV2Dto(
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
    val utbetalinger: List<UtbetalingV2Dto> = emptyList(),
)

data class UtbetalingV2Dto(
    val beløp: UInt,
    val satstype: Satstype,
    val fraOgMedDato: LocalDate,
    val tilOgMedDato: LocalDate,
    @Schema(oneOf = [
        StønadsdataDagpengerDto::class,
        StønadsdataTiltakspengerDto::class,
        StønadsdataTilleggsstønaderDto::class
    ])
    val stønadsdata: StønadsdataDto,
)

data class ForrigeIverksettingV2Dto(
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
