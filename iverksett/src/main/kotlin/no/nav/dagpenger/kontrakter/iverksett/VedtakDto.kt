package no.nav.dagpenger.kontrakter.iverksett

enum class Vedtaksresultat(val visningsnavn: String) {
    INNVILGET(visningsnavn = "Innvilget"),
    OPPHØRT(visningsnavn = "Opphørt"),
    AVSLÅTT(visningsnavn = "Avslått"),
}
