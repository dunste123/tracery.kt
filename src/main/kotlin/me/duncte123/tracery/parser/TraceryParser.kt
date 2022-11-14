package me.duncte123.tracery.parser

class TraceryParser {
    fun parseRule(rule: String): List<ParseItem> {
        if (rule.isBlank()) {
            throw Exception("Rule cannot be blank")
        }

        val sections = mutableListOf<ParseItem>()

        var lvl = 0
        var start = 0
        var inTag = false

        fun createSection(end: Int) {
            val section = rule.subSequence(start, end).toString()

            if (section.isNotBlank()) {
                if (inTag) {
                    sections.add(parseTag(section))
                } else {
                    sections.add(ParseSection(section))
                }
            }

            inTag = !inTag
            start = end + 1
        }

        for (i in rule.indices) {
            val c = rule[i]

            when (c) {
                '[' -> lvl++
                ']' -> lvl--
                '#' -> {
                    if (lvl == 0) {
                        createSection(i)
                    }
                }
                else -> { /* NOOP */ }
            }
        }

        if (lvl > 0) {
            throw Exception("Too many '[' in rule \"$rule\"")
        }

        if (lvl < 0) {
            throw Exception("Too many ']' in rule \"$rule\"")
        }

        if (inTag) {
            throw Exception("Odd number of '#' in rule \"$rule\"")
        }

        createSection(rule.length)
        return sections
    }

    fun parseTag(tag: String): ParseTag {

        val prefixNs = mutableListOf<String>()
        val postfixNs = mutableListOf<String>()
        var lvl = 0
        var start = 0
        var inPre = false

        var symbol = ""
        var mods: List<String> = listOf()

        fun nonAction(end: Int) {
            if (start != end) {
                if (!inPre) {
                    throw Exception("Multiple possible expansion symbols in tag! $tag")
                }

                inPre = false

                val section = tag.substring(start, end)
                val split = section.split(".")

                symbol = split[0]
                mods = split.subList(1, split.size)
            }

            start = end
        }

        var outI = 0
        for (i in tag.indices) {
            val c = tag[i]

            when (c) {
                '[' -> {
                    if (lvl == 0) {
                        nonAction(i)
                    }

                    lvl++
                }
                ']' -> {
                    if (lvl == 0) {
                        val section = tag.substring(start + 1, i)

                        if (inPre) {
                            prefixNs.add(section)
                        } else {
                            postfixNs.add(section)
                        }

                        start = i + 1
                    }
                }
                else -> { /* NOOP */ }
            }

            outI = i
        }

        nonAction(outI)

        return ParseTag(
            prefixNs,
            postfixNs,
            symbol,
            mods,
            tag
        )
    }
}