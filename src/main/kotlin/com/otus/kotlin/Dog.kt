package com.otus.kotlin

class Dog(name: String, number: Int, children: List<Animal> = emptyList()) : Animal(name, number, children)

class DogDsl : AnimalDsl() {
    override fun build(): Dog = Dog(name, number, children.toList())

    fun flea(flea: Flea) = add(flea)

    // Наличие inline reified функций
    inline fun flea(flea: FleaDsl.() -> Unit) = this add FleaDsl().apply(flea)
}