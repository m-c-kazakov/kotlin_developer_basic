package com.otus.kotlin

class Flea(name: String, number: Int, children: List<Animal> = emptyList()) : Animal(name, number, children)

class FleaDsl : AnimalDsl() {
    override fun build(): Dog = Dog(name, number, children.toList())
}