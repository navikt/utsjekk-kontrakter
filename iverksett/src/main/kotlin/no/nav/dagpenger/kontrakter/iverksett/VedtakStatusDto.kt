package no.nav.dagpenger.kontrakter.iverksett

import java.time.LocalDate
import java.time.LocalDateTime

data class VedtaksstatusDto(
    val vedtakstidspunkt: LocalDateTime,
    val resultat: Vedtaksresultat,
    val vedtaksperioder: List<VedtaksperiodeDto> = emptyList(),
)

data class VedtaksperiodeDto(
    val fraOgMedDato: LocalDate,
    val tilOgMedDato: LocalDate? = null,
    val periodeType: VedtaksperiodeType = VedtaksperiodeType.HOVEDPERIODE,
)

enum class VedtaksperiodeType {
    MIGRERING,
    FORLENGELSE,
    HOVEDPERIODE,
    UTVIDELSE,
    SANKSJON,
}
