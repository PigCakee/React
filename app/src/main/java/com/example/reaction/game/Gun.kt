package com.example.reaction.game

class Gun (val name: String) {
    var delay = 0.0
    var load = 0
    var icon = 0 //TODO реализовать подгрузку иконок

    companion object Factory{
        fun makeGun(name: String): Gun {
            val gun = Gun(name)
            when (name){
               "default" -> {
                   gun.delay = 0.1
                   //gun.icon =
                   gun.load = 5
               }
            }
            return gun
        }
    }

    override fun toString(): String {
        return this.name
    }
}