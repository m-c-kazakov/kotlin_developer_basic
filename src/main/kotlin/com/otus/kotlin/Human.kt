package com.otus.kotlin

class Human(name: String, number: Int, children: List<Animal<*>> = emptyList(), specific: VoiceCommand) :
    Animal<VoiceCommand>(name, number, children, specific)

fun human(function: HumanDsl.() -> Unit): Animal<*> = HumanDsl().apply(function).build()

class HumanDsl : AnimalDsl() {
    // Перегрузка операторов
    override fun build(): Human = Human(name, number, children.toList(), specific as VoiceCommand)


    fun dog(dog: Dog) = add(dog)
    fun dog(function: DogDsl.() -> Unit) = this + DogDsl().apply(function)
}

class VoiceCommand : Action {
    override fun doIt() {
        println("Собака - Голос!")
    }
}