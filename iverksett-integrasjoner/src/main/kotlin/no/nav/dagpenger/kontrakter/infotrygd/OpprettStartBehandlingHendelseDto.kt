package no.nav.dagpenger.kontrakter.iverksett.infotrygd

import no.nav.dagpenger.kontrakter.felles.StønadType

/**
 * @param personIdenter alle identer til personen
 */
data class OpprettStartBehandlingHendelseDto(
    val personIdenter: Set<String>,
    val type: StønadType,
)
