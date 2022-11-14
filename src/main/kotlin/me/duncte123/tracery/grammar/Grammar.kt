package me.duncte123.tracery.grammar

import me.duncte123.tracery.TraceryJson
import me.duncte123.tracery.node.RootNode
import me.duncte123.tracery.rule.Rule
import me.duncte123.tracery.rule.RuleSet

class Grammar {
    val symbolNames = mutableListOf<String>()
    val symbols = mutableMapOf<String, Symbol>()
    val errors = mutableListOf<String>()
    val modifiers = mutableMapOf<String, ModifierFunction>()
    var selected = false

    init {
        clear()
    }

    fun clear() {
        symbols.clear()
        errors.clear()
        modifiers.clear()

        modifiers.putAll(UniversalModifiers.getAll())
    }

    fun loadFrom(obj: TraceryJson) {
        val symbolSrc: TraceryJson = if (obj.has("symbols")) {
            obj["symbols"]
        } else {
            obj
        }

        val keys = obj.getKeys()

        symbolNames.clear()

        for (key in keys) {
            symbolNames.add(key)

            val symbol = Symbol(this, key)

            symbol.loadFrom(symbolSrc[key])

            symbols[key] = symbol
        }
    }

    fun select() {
        selected = true
    }

    fun deselect() {
        selected = false
    }

    fun <T> mapSymbols(fxn: (Symbol, String) -> T): List<T> {
        return symbolNames.map {
                key -> fxn(symbols[key]!!, key)
        }
    }

    fun applyToSymbols(fxn: (Symbol, String) -> Unit) {
        for (name in symbolNames) {
            fxn(symbols[name]!!, name)
        }
    }

    fun addOrGetSymbol(key: String): Symbol {
        if (key !in symbols) {
            symbolNames.add(key);
            symbols[key] = Symbol(this, key)
        }

        return symbols[key]!!
    }

    // TODO: load from array of rules and string as well
    // turn top-level into ruleset and then call this
    fun pushRules(key: String, rules: RuleSet) {
        val symbol = addOrGetSymbol(key)
        symbol.pushRules(rules)
    }

    fun getRule(key: String, index: Int): Rule {
        // TODO: this is weird af
        if (key !in symbols) {
            val rule = Rule("{{$key}}")

            rule.error = "Missing symbol $key"

            return rule
        }

        val sRule = symbols[key]!!.getRule(index)

        if (sRule == null) {
            val rule = Rule("[$key]")

            rule.error = "Symbol $key has no rule"

            return rule
        }

        return sRule
    }

    fun expand(raw: TraceryJson): RootNode {
        val root = RootNode(this, raw)

        root.expand()

        return root
    }

    fun flatten(raw: TraceryJson): String {
        return expand(raw).childText
    }

    // TODO: analyze is next

    // TODO: toHTML, toJSON
}