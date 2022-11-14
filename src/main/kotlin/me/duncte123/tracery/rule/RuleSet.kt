package me.duncte123.tracery.rule

class RuleSet(val rules: Array<Rule>) {

    fun addRule(rule: Rule) {
        // wat?
    }

    fun get(seed: Int): Rule? {
        TODO("Not implemented")
    }

    /// Methods
    // parseAll
    fun mapRules(fxn: Any): RuleSet {
        return this
    }
    fun applyToRules(fxn: Any) {
        //
    }
    // get
    // getRandomIndex
    // getIndex
    // decayUses
    // testRandom
    // getSaveRules
}