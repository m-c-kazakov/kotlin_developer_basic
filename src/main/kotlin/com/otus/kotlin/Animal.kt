package com.otus.kotlin

sealed class Animal(
    val name: String,
    val number: Int,
    val children: List<Animal> = emptyList()
)

abstract class AnimalDsl {
    var name: String = ""
    var number: Int = 0
    // Наличие обобщенных типов в DSL
    var children: MutableList<Animal> = mutableListOf()

    fun add(animal: Animal) {
        children.add(animal)
    }

    // Наличие infix-функций
    infix fun add(animalDsl: AnimalDsl) {
        add(animalDsl.build())
    }

    abstract fun build(): Animal

}