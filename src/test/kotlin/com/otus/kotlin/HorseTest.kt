package com.otus.kotlin

import org.junit.Test
import kotlin.test.assertEquals


internal class HorseTest {

    @Test
    fun faster() {
        val dog = Horse("Horse", 1)
        dog.faster()
        assertEquals(2, dog.speed)
    }
}