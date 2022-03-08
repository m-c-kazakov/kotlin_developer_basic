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

        val sequence = (1..10000000)
            .asSequence()
            .filter { it % 7 == 0 }
            .map { Human(name = "Человек_$it", number = it, children = emptyList(), specific = VoiceCommand()) }
            .filter { it.name.contains("3") }
            .drop(1)
            .take(3)

        collectionWrapper.addAll(sequence)

        assertEquals(3, collectionWrapper.animals.size)
    }

    @Test
    fun `suspend-метод для обработки с использованием flow`() = runBlocking {
        val collectionWrapper = CollectionWrapper()

        val flow = (1..10000000)
            .asFlow()
            .filter { it % 7 == 0 }
            .map { Human(name = "Человек_$it", number = it, children = emptyList(), specific = VoiceCommand()) }
            .filter { it.name.contains("3") }
            .drop(1)
            .take(3)

        collectionWrapper.addAll(flow)

        assertEquals(3, collectionWrapper.animals.size)
    }

    @Test
    fun `delay внутри операций над flow`() = runBlocking {
        val collectionWrapper = CollectionWrapper()

        val flow = (1..10000000)
            .asFlow()
            .filter { it % 7 == 0 }
            .map {
                async {
                    delay(2.seconds)
                    Human(name = "Человек_$it", number = it, children = emptyList(), specific = VoiceCommand())
                }
            }
            .drop(1)
            .take(3)
            .buffer(3)
            .map { it.await() }

        collectionWrapper.addAll(flow)

        assertEquals(3, collectionWrapper.animals.size)
    }

    @Test
    fun `Thread_sleep внутри операций над flow`() = runBlocking {
        val collectionWrapper = CollectionWrapper()

        val flow = (1..10000000)
            .asFlow()
            .filter { it % 7 == 0 }
            .map {
                async(Dispatchers.IO) {
                    Thread.sleep(2000)
                    Human(name = "Человек_$it", number = it, children = emptyList(), specific = VoiceCommand())
                }
            }
            .drop(1)
            .take(3)
            .buffer(3)
            .map { it.await() }

        collectionWrapper.addAll(flow)

        assertEquals(3, collectionWrapper.animals.size)
    }


}

