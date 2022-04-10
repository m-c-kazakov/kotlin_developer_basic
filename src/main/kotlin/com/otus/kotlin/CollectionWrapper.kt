package com.otus.kotlin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

class CollectionWrapper(
    private val collection: MutableList<Animal<*>> = mutableListOf()
) {

    val animals: List<Animal<*>>
        get() = collection.toList()

    fun add(animal: Animal<*>) {
        collection.add(animal)
    }

    fun addAll(sequence: Sequence<Animal<*>>) {
        collection.addAll(sequence)
    }

    suspend fun addAll(flow: Flow<Human>) {
        flow.collect {
            collection.add(it)
        }
    }

    fun getHumanWithUsingSequence(): List<Human> {
        val sequence = (1..10000000)
            .asSequence()
            .filter { it % 7 == 0 }
            .map { Human(name = "Человек_$it", number = it, children = emptyList(), specific = VoiceCommand()) }
            .filter { it.name.contains("3") }
            .drop(1)
            .take(3).toList()
        return sequence
    }

    suspend fun getHumanWithUsingFlow(): List<Human> {
        val flow = (1..10000000)
            .asFlow()
            .filter { it % 7 == 0 }
            .map { Human(name = "Человек_$it", number = it, children = emptyList(), specific = VoiceCommand()) }
            .filter { it.name.contains("3") }
            .drop(1)
            .take(3)
        return flow.toList()
    }

    suspend fun getHumanWithUsingFlowAndDelay(): List<Human> = runBlocking{
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
        return@runBlocking flow.toList()
    }

    suspend fun getHumanWithUsingFlowAndThreadSleep(): List<Human> = runBlocking{
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
        return@runBlocking flow.toList()
    }
}