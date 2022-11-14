package me.duncte123.tracery.node

import me.duncte123.tracery.parser.TraceryParser
import me.duncte123.tracery.grammar.Grammar
import me.duncte123.tracery.rule.Rule

class RootNode(grammar: Grammar, rawRule: String): ExpansionNode(grammar) {

    var parsedRule: Rule? = null

    init {
        parsedRule = TraceryParser().parseRule(rawRule)
    }

}