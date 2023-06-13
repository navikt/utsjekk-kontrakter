package no.nav.dagpenger.kontrakter.iverksett

import no.nav.dagpenger.kontrakter.felles.BrevmottakerDto
import no.nav.dagpenger.kontrakter.felles.Datoperiode
import no.nav.dagpenger.kontrakter.felles.Tilbakekrevingsvalg
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID


data class IverksettDto(
    val sakId: UUID,
    val behandlingId: UUID,
    val personIdent: String,
    val vedtak: VedtaksdetaljerDto,
    @Deprecated("Bruk forrigeIverksetting") val utbetalingerPaaForrigeVedtak: List<UtbetalingDto> = emptyList(),
    val forrigeIverksetting: ForrigeIverksettingDto? = null
)

data class VedtaksdetaljerDto(
    val vedtakstype: VedtakType = VedtakType.RAMMEVEDTAK,
    val vedtakstidspunkt: LocalDateTime,
    val resultat: Vedtaksresultat,
    val saksbehandlerId: String,
    val beslutterId: String,
    val opphorAarsak: OpphørÅrsak? = null,
    val avslagAarsak: AvslagÅrsak? = null,
    val utbetalinger: List<UtbetalingDto> = emptyList(),
    val vedtaksperioder: List<VedtaksperiodeDto> = emptyList(),
    val tilbakekreving: TilbakekrevingDto? = null,
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
    val utbetalinger: List<UtbetalingDto> = emptyList()
)



