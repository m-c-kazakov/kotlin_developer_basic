package com.otus.kotlin

class Flea(name: String, number: Int, children: List<Animal<*>> = emptyList(), specific:Bit) : Animal<Bit>(name, number, children, specific)

class FleaDsl : AnimalDsl() {
    override fun build(): Flea = Flea(name, number, children.toList(), specific as Bit)
}

class Bit : Action {
    override fun doIt() {
        println("Не могу терпеть - Кусаю!")
    }
}