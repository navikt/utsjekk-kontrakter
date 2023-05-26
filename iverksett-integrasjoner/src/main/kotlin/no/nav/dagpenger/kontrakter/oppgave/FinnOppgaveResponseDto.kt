package no.nav.dagpenger.kontrakter.iverksett.oppgave

data class FinnOppgaveResponseDto(
    val antallTreffTotalt: Long,
    val oppgaver: List<Oppgave>,
)
