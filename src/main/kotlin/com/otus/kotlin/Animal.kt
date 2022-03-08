package com.otus.kotlin

sealed class Animal<out T:Action>(
    val name: String,
    val number: Int,
    val children: List<Animal<*>> = emptyList(),
    // Наличие обобщенных типов в DSL
    val specific: T
)

abstract class AnimalDsl {
    var name: String = ""
    var number: Int = 0
    var children: MutableList<Animal<*>> = mutableListOf()
    lateinit var specific: Action

    fun add(animal: Animal<*>) {
        children.add(animal)
    }

    abstract fun build(): Animal<*>
    
    inline operator fun <reified T : AnimalDsl> plus(animalDsl: T) {
        println("Кто я? ${T::class.simpleName}")
        add(animalDsl.build())
    }

}