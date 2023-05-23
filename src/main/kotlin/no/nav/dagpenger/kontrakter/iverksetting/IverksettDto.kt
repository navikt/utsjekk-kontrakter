package no.nav.dagpenger.kontrakter.iverksetting

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class IverksettDagpengerdDto(
    val sakId: UUID,
    val personIdent: String,
    val vedtak: VedtaksdetaljerDagpengerDto,
    val forrigeVedtak: VedtaksdetaljerDagpengerDto? = null
)

data class VedtaksdetaljerDagpengerDto(
    val vedtakstype: VedtakType = VedtakType.RAMMEVEDTAK,
    val vedtakstidspunkt: LocalDateTime,
    val resultat: Vedtaksresultat,
    val behandlingId: UUID,
    val saksbehandlerId: String,
    val beslutterId: String,
    val opphørÅrsak: OpphørÅrsak? = null,
    val utbetalinger: List<UtbetalingDto> = emptyList(),
    val vedtaksperioder: List<VedtaksperiodeDagpengerDto> = emptyList(),
)

data class VedtaksperiodeDagpengerDto(
    val fraOgMedDato: LocalDate,
    val tilOgMedDato: LocalDate? = null,
)

enum class VedtakType {
    RAMMEVEDTAK,
    UTBETALINGSVEDTAK
}

enum class Vedtaksresultat(val visningsnavn: String) {
    INNVILGET(visningsnavn = "Innvilget"),
    OPPHØRT(visningsnavn = "Opphørt"),
    AVSLÅTT(visningsnavn = "Avslått"),
}

enum class OpphørÅrsak {
    PERIODE_UTLØPT,
}
