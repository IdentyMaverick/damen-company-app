package org.example.nazlican

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform