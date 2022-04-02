package com.otus.kotlin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.time.Duration.Companion.seconds

class CoroutineTest {

    @Test
    fun wrapperTest() {
        val collectionWrapper = CollectionWrapper()
        val fleaAnimal = flea {
            name = "Блохи"
            number = 3
            specific = Bit()
        }

        val dogAnimal = dog {
            name = "Пес"
            number = 1
            specific = Woof()
            children = mutableListOf(fleaAnimal)
        }
        val humanAnimal = human {
            name = "Олег"
            number = 1
            specific = VoiceCommand()
            children = mutableListOf(dogAnimal)
        }

        collectionWrapper.add(humanAnimal)
        collectionWrapper.add(dogAnimal)
        collectionWrapper.add(fleaAnimal)
        assertEquals("Олег", collectionWrapper.animals.first().name)
    }

    @Test
    fun `suspend-метод для обработки с использованием sequence`() {
        val collectionWrapper = CollectionWrapper()
        val human = collectionWrapper.getHumanWithUsingSequence()
        assertEquals(3, human.size)
    }

    @Test
    fun `suspend-метод для обработки с использованием flow`() = runBlocking {
        val collectionWrapper = CollectionWrapper()
        val humanWithUsingFlow = collectionWrapper.getHumanWithUsingFlow()
        assertEquals(3, humanWithUsingFlow.size)
    }

    @Test
    fun `delay внутри операций над flow`() = runBlocking {
        val collectionWrapper = CollectionWrapper()
        val humanWithUsingFlowAndDelay = collectionWrapper.getHumanWithUsingFlowAndDelay()
        assertEquals(3, humanWithUsingFlowAndDelay.size)
    }

    @Test
    fun `Thread_sleep внутри операций над flow`() = runBlocking {
        val collectionWrapper = CollectionWrapper()
        val humanWithUsingFlowAndThreadSleep = collectionWrapper.getHumanWithUsingFlowAndThreadSleep()
        assertEquals(3, humanWithUsingFlowAndThreadSleep.size)
    }


}

