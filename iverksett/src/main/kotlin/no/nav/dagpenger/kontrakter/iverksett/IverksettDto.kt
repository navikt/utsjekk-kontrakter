package no.nav.dagpenger.kontrakter.iverksett

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size
import no.nav.dagpenger.kontrakter.felles.BrevmottakerDto
import no.nav.dagpenger.kontrakter.felles.Datoperiode
import no.nav.dagpenger.kontrakter.felles.SakIdentifikator
import no.nav.dagpenger.kontrakter.felles.Tilbakekrevingsvalg
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID


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
    @Deprecated("Bruk forrigeIverksetting")
    @Schema(hidden = true)
    val utbetalingerPaaForrigeVedtak: List<UtbetalingDto> = emptyList(),
    @Schema(required = false, description = "Må vøre satt hvis det ikke er første iverksetting")
    val forrigeIverksetting: ForrigeIverksettingDto? = null
) {
    init {
        SakIdentifikator.valider(sakId, saksreferanse)
    }
}

data class VedtaksdetaljerDto(
    @Schema(required = true)
    val vedtakstype: VedtakType,
    @Schema(required = true,)
    val vedtakstidspunkt: LocalDateTime,
    @Schema(required = true,)
    val resultat: Vedtaksresultat,
    @Schema(required = true,)
    val saksbehandlerId: String,
    @Schema(required = true,)
    val beslutterId: String,
    @Schema(hidden = true)
    val opphorAarsak: OpphørÅrsak? = null,
    @Schema(hidden = true)
    val avslagAarsak: AvslagÅrsak? = null,
    @Schema(required = false)
    val utbetalinger: List<UtbetalingDto> = emptyList(),
    @Schema(hidden = true)
    val vedtaksperioder: List<VedtaksperiodeDto> = emptyList(),
    @Schema(hidden = true)
    val tilbakekreving: TilbakekrevingDto? = null,
    @Schema(hidden = true)
    val brevmottakere: List<BrevmottakerDto> = emptyList(),
)

data class VedtaksstatusDto(
    val vedtakstype: VedtakType = VedtakType.RAMMEVEDTAK,
    val vedtakstidspunkt: LocalDateTime,
    val resultat: Vedtaksresultat,
    val vedtaksperioder: List<VedtaksperiodeDto> = emptyList(),
)

data class VedtaksperiodeDto(
    val fraOgMedDato: LocalDate,
    val tilOgMedDato: LocalDate? = null,
    val periodeType: VedtaksperiodeType = VedtaksperiodeType.HOVEDPERIODE,
)

data class TilbakekrevingDto(
    val tilbakekrevingsvalg: Tilbakekrevingsvalg,
    val tilbakekrevingMedVarsel: TilbakekrevingMedVarselDto?,
)

data class TilbakekrevingMedVarselDto(
    val varseltekst: String,
    val sumFeilutbetaling: BigDecimal? = null, // Hentes fra simulering hvis det mangler
    val fellesperioder: List<Datoperiode> = emptyList()
) // Hentes fra simulering hvis det mangler

enum class IverksettStatus {
    SENDT_TIL_OPPDRAG,
    FEILET_MOT_OPPDRAG,
    OK_MOT_OPPDRAG,
    JOURNALFORT,
    OK,
    IKKE_PAABEGYNT,
}

enum class VedtaksperiodeType {
    MIGRERING,
    FORLENGELSE,
    HOVEDPERIODE,
    UTVIDELSE,
    SANKSJON
}

data class ForrigeIverksettingDto (
    val behandlingId: UUID,
    @Schema(required = false, description = "Må være identisk med utbetalingene som ble iverksatt forrige gang")
    val utbetalinger: List<UtbetalingDto> = emptyList()
)



