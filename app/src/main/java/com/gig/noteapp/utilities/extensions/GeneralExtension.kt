package com.gig.noteapp.utilities.extensions

fun <T> T?.default(default: T): T {
    return this ?: default
}
