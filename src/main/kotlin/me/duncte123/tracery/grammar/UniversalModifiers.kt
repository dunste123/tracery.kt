package me.duncte123.tracery.grammar

import me.duncte123.tracery.isConsonant

object UniversalModifiers {
    @JvmStatic
    fun getAll(): Map<String, ModifierFunction> = mapOf(
        "capitalizeAll" to ModifierFunction { s ->
            s.replace("/(?:^|\\s)\\S/g".toRegex()) {
                it.value.uppercase()
            }
        },

        "capitalize" to ModifierFunction { s ->
            s[0].uppercase() + s.substring(1)
        },

        "inQuotes" to ModifierFunction { s ->
            "\"$s\""
        },

        "comma" to ModifierFunction { s ->
            when (s[s.length - 1]) {
                ',', '.', '?', '!' -> s
                else -> "$s,"
            }
        },

        "beeSpeak" to ModifierFunction { s ->
            s.replace("s", "zzz")
        },

        "a" to ModifierFunction { s ->
            if (!isConsonant(s[0])) {
                "an $s"
            } else {
                "a $s"
            }
        },

        "s" to ModifierFunction { s ->
            val last = s[s.length - 1]
            val minLast = s.subSequence(0, s.length - 1)

            when (last) {
                'y' -> if (isConsonant(s[s.length - 2])) {
                    "${minLast}ies" // harpies, cries
                } else {
                    "${s}s" // rays, convoys
                }
                'x' -> "${minLast}xen" // oxen, boxen, foxen
                'z' -> "${minLast}zes"
                'h' -> "${minLast}hes"
                else -> "${s}s"
            }
        },

        "ed" to ModifierFunction { s ->
            val spaceIndex = s.indexOf(' ')
            var str = s
            var rest = ""

            if (spaceIndex > 0) {
                rest = str.substring(spaceIndex, str.length)
                str = str.substring(0, spaceIndex)
            }

            val last = str[str.length - 1]

            when (last) {
                'y' -> if (isConsonant(s[s.length - 2])) {
                    "${str.substring(0, str.length - 1)}ied$rest"
                } else {
                    "${str}ed$rest"
                }
                'e' -> "${str}d$rest"
                else -> "${str}ed$rest"
            }
        }
    )
}