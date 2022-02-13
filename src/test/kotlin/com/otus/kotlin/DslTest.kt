package com.otus.kotlin

import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.test.assertEquals

class DslTest {

    @Test
    fun `Домашнее задание по DSL`() {

        val animalGroup = human {
            name = "Олег"
            number = 1
            dog {
                name = "Пес"
                number = 1
                flea {
                    name = "Блохи"
                    number = 3
                }
            }
        }

        assertEquals("Олег", animalGroup.name)
        assertTrue(
            "dog contains лайка",
            animalGroup.children.first {
                it.name == "Пес"
            } != null
        )
        assertTrue(
            "У собаки 3 блохи",
            animalGroup.children.first {
                it.name == "Пес"
            }.children.first{
                it.name == "Блохи"
            }.number == 3
        )

    }
}
















