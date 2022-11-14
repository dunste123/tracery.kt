package me.duncte123.tracery.parser

class ParseTag(
    val preActions: List<String>,
    val postActions: List<String>,
    val symbol: String,
    val mods: List<String>,
    raw: String
): ParseItem(raw)