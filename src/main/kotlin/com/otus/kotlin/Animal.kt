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

//    operator fun Animal<*>.plus(animal: Animal<*>){
////        children.add(animal)
//        children.add(animal)
//    }

    // Наличие infix-функций
//    infix fun add(animalDsl: AnimalDsl) {
//        add(animalDsl.build())
//    }

//    operator fun Animal<*>.plus(animalDsl: AnimalDsl){
//        add(animalDsl.build())
//    }

    abstract fun build(): Animal<*>
    
    operator fun <T : AnimalDsl> plus(animalDsl: T) {
        add(animalDsl.build())
    }

}