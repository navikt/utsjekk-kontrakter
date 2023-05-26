package no.nav.dagpenger.kontrakter.iverksett.iverksett

import no.nav.dagpenger.iverksett.kontrakter.felles.Opplysningskilde
import no.nav.dagpenger.iverksett.kontrakter.felles.Revurderingsårsak

data class ÅrsakRevurderingDto(
    val opplysningskilde: Opplysningskilde,
    val årsak: Revurderingsårsak,
)
