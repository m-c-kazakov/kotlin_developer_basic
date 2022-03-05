package com.otus.kotlin

class Dog(name: String, number: Int, children: List<Animal<*>> = emptyList(), specific: Woof) : Animal<Woof>(name, number, children, specific)

class DogDsl : AnimalDsl() {
    override fun build(): Dog = Dog(name, number, children.toList(), specific as Woof,)

    fun flea(flea: Flea) = add(flea)

    // Наличие inline reified функций
    inline fun flea(flea: FleaDsl.() -> Unit) = this + FleaDsl().apply(flea)
}

class Woof : Action {
    override fun doIt() {
        println("Гав мазафака")
    }

}