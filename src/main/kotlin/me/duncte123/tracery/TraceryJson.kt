package me.duncte123.tracery

class TraceryJson {
    // TODO: parse text to json with jackson

    operator fun get(key: String): TraceryJson {
        return this
    }

    operator fun get(key: Int): TraceryJson {
        return this
    }

    fun has(key: String): Boolean {
        return false
    }

    fun getKeys(): Set<String> {
        return setOf()
    }
}