package com.otus.kotlin

class Horse(var name: String, var speed: Int): Animals(){
    fun faster() {
        this.speed +=1
    }
}
