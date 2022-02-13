package com.otus.kotlin

import org.junit.Test
import kotlin.test.assertEquals


internal class DogTest {

    @Test
    fun faster() {
        val dog = Dog("dog", 1)
        dog.faster()
        assertEquals(2, dog.speed)
    }
}