package me.duncte123.tracery

fun isConsonant(c: Char) = when (c.lowercase()) {
    "a", "e", "i", "o", "u" -> false
    else -> true
}

fun endsWithConY(s: String): Boolean {
    if (s[s.length - 1] == 'y') {
        return isConsonant(s[s.length - 2])
    }

    return false
}