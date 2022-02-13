package com.otus.kotlin

import org.junit.Test
import kotlin.test.assertEquals


internal class MonkeyTest {

    @Test
    fun faster() {
        val dog = Monkey("Monkey", 1)
        dog.faster()
        assertEquals(2, dog.speed)
    }
}