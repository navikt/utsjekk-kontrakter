package no.nav.dagpenger.kontrakter.felles

// Brukes også fra [FrittståendeBrevDto]
data class BrevmottakerDto(
    val ident: String,
    val navn: String,
    val mottakerRolle: MottakerRolle,
    val identType: IdentType,
) {

    enum class MottakerRolle {
        BRUKER,
        VERGE,
        FULLMEKTIG,
    }

    enum class IdentType {
        PERSONIDENT,
        ORGANISASJONSNUMMER,
    }
}