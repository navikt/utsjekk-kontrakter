package no.nav.utsjekk.kontrakter.felles

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.databind.JsonNode
import jakarta.validation.constraints.Size
import java.util.UUID

sealed interface GeneriskId {
    companion object {
        @JsonCreator
        @JvmStatic
        fun deserialize(json: String): GeneriskId {
            return Result.runCatching { UUID.fromString(json) }.fold(
                onSuccess = { GeneriskIdSomUUID(UUID.fromString(json)) },
                onFailure = { GeneriskIdSomString(json) },
            )
        }

        @JsonCreator
        @JvmStatic
        fun deserializeObject(json: JsonNode): GeneriskId {
            val id = json.findValue("id").asText()
            return Result.runCatching { UUID.fromString(id) }.fold(
                onSuccess = { GeneriskIdSomUUID(UUID.fromString(id)) },
                onFailure = { GeneriskIdSomString(id) },
            )
        }
    }
}

data class GeneriskIdSomUUID(val id: UUID) : GeneriskId {
    override fun toString() = id.toString()
}

data class GeneriskIdSomString(
    @Size(min = 1, max = 20) val id: String,
) : GeneriskId {
    init {
        if (id.isEmpty() || id.length > 20) {
            throw IllegalArgumentException("Id-en må være mellom 1 og 20 tegn lang")
        }
    }

    override fun toString() = id
}

val GeneriskId.somUUID
    get(): UUID =
        when (this) {
            is GeneriskIdSomString ->
                UUID.nameUUIDFromBytes(
                    this.id.toByteArray(),
                ) // TODO denne genererer en versjon 3-UUID. Bør generere versjon 5
            is GeneriskIdSomUUID -> this.id
        }

val GeneriskId.somString
    get(): String =
        when (this) {
            is GeneriskIdSomString -> this.id
            is GeneriskIdSomUUID -> this.id.toString()
        }
