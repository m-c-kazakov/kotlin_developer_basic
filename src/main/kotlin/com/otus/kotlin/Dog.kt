package com.otus.kotlin

class Dog(var name: String, var speed: Int): Animals() {
    fun faster() {
        this.speed +=1
    }
}
