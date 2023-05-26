package no.nav.dagpenger.kontrakter.iverksett

import no.nav.dagpenger.kontrakter.felles.Opplysningskilde
import no.nav.dagpenger.kontrakter.felles.Revurderingsårsak

data class ÅrsakRevurderingDto(
    val opplysningskilde: Opplysningskilde,
    val årsak: Revurderingsårsak,
)
