package me.duncte123.tracery.rule

import me.duncte123.tracery.parser.TraceryParser

class Rule(val raw: String) {
    var error: String? = null
    private val sections = TraceryParser().parseRule(raw)


    fun getParsed() = sections

    override fun toString() = raw
    fun toJSONString() = raw
}