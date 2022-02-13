package com.otus.kotlin

class Monkey(var name: String, var speed: Int): Animals(){
    fun faster() {
        this.speed +=1
    }
}
