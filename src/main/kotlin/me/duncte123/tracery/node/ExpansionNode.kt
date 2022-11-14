package me.duncte123.tracery.node

import me.duncte123.tracery.grammar.Grammar

open class ExpansionNode(var grammar: Grammar) {
    protected var depth = 0
    protected var id = 0 // nodeCount
    var childText = "[[UNEXPANDED]]"
    protected var finalText = "[[UNEXPANDED]]"

    private var children = mutableListOf<ExpansionNode>()

    private var internalParent: ExpansionNode? = null

    var parent: ExpansionNode?
        get() = internalParent
        set(value) {
            internalParent = value
            if (value != null) {
                depth = value.depth + 1
                grammar = value.grammar
            }
        }

    open fun expand(): String {
        // do nothing
        return "???"
    }

    fun expandChildren() {
        if (children.isEmpty()) {
            return
        }

        childText = ""

        for (child in children) {
            child.expand()
            childText += child.finalText // TODO: stringbuilder
        }
    }

    // TODO: can either be string or ExpansionNode
    fun createChildrenFromSections(sections: List<Any>) {
        //
    }
}