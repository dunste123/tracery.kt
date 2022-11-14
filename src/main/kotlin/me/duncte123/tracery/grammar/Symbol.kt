package me.duncte123.tracery.grammar

import me.duncte123.tracery.TraceryJson
import me.duncte123.tracery.rule.Rule
import me.duncte123.tracery.rule.RuleSet

class Symbol(val grammar: Grammar, val key: String) {
    val ruleSets = mutableListOf<RuleSet>()
    var baseRules: RuleSet? = null
    var currentRules: RuleSet? = null
    var selected = false

    // TODO: load from array of rules and string as well
    fun loadFrom(rules: RuleSet) {
        baseRules = rules
        ruleSets.add(rules)
        currentRules = ruleSets[ruleSets.size - 1]
    }

    fun loadFrom(rules: TraceryJson) {
        TODO("Implement")
        /*baseRules = rules
        ruleSets.add(rules)
        currentRules = ruleSets[ruleSets.size - 1]*/
    }

    // function?
    fun mapRules(fxn: Any): RuleSet {
        return currentRules!!.mapRules(fxn)
    }

    fun applyToRules(fxn: Any) {
        currentRules!!.applyToRules(fxn)
    }

    // TODO: do we need this?
    fun wrapRules() {
        /*
       if (rules.prototype !== RuleSet) {
            if (Array.isArray(rules)) {
                return new RuleSet(rules);
            } else if ( typeof rules == 'string' || rules instanceof String) {
                return new RuleSet(rules);
            } else {
                throw ("Unknown rules type: " + rules);
            }
        }
        // already a ruleset
        return rules;
        */
    }

    // TODO: load from array of rules and string as well
    fun pushRules(rules: RuleSet) {
        ruleSets.add(rules)
        currentRules = ruleSets[ruleSets.size - 1]
    }

    fun popRules() {
        if (ruleSets.isEmpty()) {
            // TODO: warn?
            return
        }

        // NOTE: pop removes and returns last element
        val lastIndex = ruleSets.size - 1
        val lastItem = ruleSets[lastIndex]
        ruleSets.removeAt(lastIndex)

        // did we clear everything out?
        if (ruleSets.isEmpty()) {
            return
        }

        currentRules = ruleSets[ruleSets.size - 1]
    }

    // TODO: load from array of rules and string as well
    fun setRules(rules: RuleSet) {
        ruleSets.clear()
        ruleSets.add(rules)
        currentRules = ruleSets[ruleSets.size - 1]
    }

    // TODO: wat?
    fun addRule(rule: Rule) {
        currentRules?.addRule(rule)
    }

    fun select() {
        selected = true
    }

    fun deselect() {
        selected = false
    }

    fun getRule(index: Int): Rule? {
        return currentRules!!.get(index)
    }

    override fun toString(): String {
        return "$key: $currentRules (overlaying ${ruleSets.size - 1})"
    }

    fun toJSON(): String {
        /*
        var rules = this.baseRules.rules.map(function(rule) {
          return '"' + rule.raw + '"';
        });
        return '"' + this.key + '"' + ": [" + rules.join(", ") + "]";
        */

        TODO("not yet implemented")
    }

    fun toHTML(useSpans: Boolean): String {
        /*
        var keySpan = '"' + this.key + '"';
        if (useSpans)
            keySpan = "<span class='symbol symbol_" + this.key + "'>" + keySpan + "</span>";

        var rules = this.baseRules.rules.map(function(rule) {
            // replace any anglebrackets for html
            var cleaned = rule.raw.replace(/&/g, "&amp;");
            cleaned = cleaned.replace(/>/g, "&gt;");
            cleaned = cleaned.replace(/</g, "&lt;");

            var s = '"' + cleaned + '"';
            if (useSpans)
                s = "<span class='rule'>" + s + "</span>";
            return s;
        });
        return keySpan + ": [" + rules.join(", ") + "]";
        */

        TODO("not yet implemented")
    }
}