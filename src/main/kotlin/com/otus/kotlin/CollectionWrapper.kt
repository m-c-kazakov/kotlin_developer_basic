package com.otus.kotlin

import kotlinx.coroutines.flow.Flow

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
}