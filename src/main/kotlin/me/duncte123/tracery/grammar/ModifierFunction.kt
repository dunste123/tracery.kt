package me.duncte123.tracery.grammar

fun interface ModifierFunction {
    fun parse(s: String): String
}