package com.example.reaction.game

class Gun (val name: String) {
    var delay = 0L
    var load = 0
    var cost = 0
    var icon = 0 //TODO реализовать подгрузку иконок

    companion object Factory{
        fun makeGun(name: String): Gun {
            val gun = Gun(name)
            when (name){
               "default" -> {
                   gun.delay = 100L
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