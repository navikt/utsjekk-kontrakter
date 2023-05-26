package no.nav.dagpenger.kontrakter.iverksett.iverksett

import no.nav.dagpenger.kontrakter.iverksett.felles.Opplysningskilde
import no.nav.dagpenger.kontrakter.iverksett.felles.Revurderingsårsak

data class ÅrsakRevurderingDto(
    val opplysningskilde: Opplysningskilde,
    val årsak: Revurderingsårsak,
)
