package no.nav.dagpenger.kontrakter.utbetaling

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class Utbetalingsoppdrag(
    val kodeEndring: KodeEndring,
    val fagSystem: Fagsystem = Fagsystem.Dagpenger,
    val saksnummer: UUID,
    val aktoer: String,
    val saksbehandlerId: String,
    val avstemmingTidspunkt: LocalDateTime = LocalDateTime.now(),
    val utbetalingsperiode: List<Utbetalingsperiode>,
    val gOmregning: Boolean = false
) {

    enum class KodeEndring {
        NY,
        ENDR,
        UEND
    }


}

@JsonIgnoreProperties(ignoreUnknown = true)
data class Utbetalingsperiode(
    val erEndringPåEksisterendePeriode: Boolean,
    val opphør: Opphør? = null,
    val periodeId: Long,
    val forrigePeriodeId: Long? = null,
    val datoForVedtak: LocalDate,
    val klassifisering: String,
    val vedtakdatoFom: LocalDate,
    val vedtakdatoTom: LocalDate,
    val sats: BigDecimal,
    val satsType: SatsType,
    val utbetalesTil: String,
    val behandlingId: UUID,
    val utbetalingsgrad: Int? = null
) {

    enum class SatsType {
        DAG,
        MND,
        ENG
    }
}

data class Opphør(val opphørDatoFom: LocalDate)

enum class UtbetalingType(val kode: String) {
    DAGPENGER_ARBEIDSSOKER_ORDINAER("DPORAS"),
    DAGPENGER_PERMITTERING_ORDINAER("DPPEAS"),
    DAGPENGER_PERMITTERING_FISKEINDUSTRI("DPPEFI"),
    DAGPENGER_EOS("DPDPASISP1"),
}