package no.nav.dagpenger.kontrakter.oppdrag

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import no.nav.dagpenger.kontrakter.felles.BrukersNavKontor
import no.nav.dagpenger.kontrakter.felles.Fagsystem
import no.nav.dagpenger.kontrakter.felles.GeneriskId
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class Utbetalingsoppdrag(
    val kodeEndring: KodeEndring,
    val fagSystem: Fagsystem = Fagsystem.Dagpenger,
    val saksnummer: GeneriskId,
    val aktør: String,
    val saksbehandlerId: String,
    val avstemmingTidspunkt: LocalDateTime = LocalDateTime.now(),
    val utbetalingsperiode: List<Utbetalingsperiode>,
    val brukersNavKontor: BrukersNavKontor? = null,
    val gOmregning: Boolean = false,
) {

    @Suppress("unused")
    enum class KodeEndring {
        NY,
        ENDR,
        UEND,
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
    val behandlingId: GeneriskId,
    val utbetalingsgrad: Int? = null,
) {
    @Suppress("unused")
    enum class SatsType {
        DAG,
        MND,
        ENG,
    }
}

data class Opphør(val opphørDatoFom: LocalDate)

@Suppress("unused")
enum class UtbetalingType(val kode: String) {
    DAGPENGER_ARBEIDSSØKER_ORDINÆR("DPORAS"),
    DAGPENGER_PERMITTERING_ORDINÆR("DPPEAS"),
    DAGPENGER_PERMITTERING_FISKEINDUSTRI("DPPEFI"),
    DAGPENGER_EØS("DPDPASISP1"),
}
