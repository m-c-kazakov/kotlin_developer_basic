package com.otus.kotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DslTest {

    @Test
    fun `Домашнее задание по DSL`() {

        val animalGroup = human {
            name = "Олег"
            number = 1
            specific = VoiceCommand()
            dog {
                name = "Пес"
                number = 1
                specific = Woof()
                flea {
                    name = "Блохи"
                    number = 3
                    specific = Bit()
                }
            }
        }

        assertEquals("Олег", animalGroup.name)
        assertTrue(animalGroup.children.first {
                it.name == "Пес"
            } != null, "dog contains лайка"
        )
        assertTrue(
            animalGroup.children.first {
                it.name == "Пес"
            }.children.first{
                it.name == "Блохи"
            }.number == 3,
            "У собаки 3 блохи"
        )

    }
}
















