package com.otus.kotlin

class Human(name: String, number: Int, children: List<Animal> = emptyList()) : Animal(name, number, children)

fun human(function: HumanDsl.() -> Unit): Animal = HumanDsl().apply(function).build()

class HumanDsl : AnimalDsl() {
    // Перегрузка операторов
    override fun build(): Dog = Dog(name, number, children.toList())


    fun dog(dog: Dog) = add(dog)
    fun dog(function: DogDsl.() -> Unit) = add(DogDsl().apply(function).build())
}