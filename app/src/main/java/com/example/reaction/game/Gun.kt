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
               "revolver1" -> {
                   gun.delay = 100L
                   //gun.icon =
                   gun.load = 5
                   gun.cost = 100
               }
                "revolver2" -> {
                    gun.delay = 75L
                    //gun.icon =
                    gun.load = 5
                    gun.cost = 300
                }
                "revolver3" -> {
                    gun.delay = 50L
                    //gun.icon =
                    gun.load = 5
                    gun.cost = 500
                }
            }
            return gun
        }

        fun makeGunByNumber(number: Int): Gun{
            val name = when(number){
                0 -> "revolver1"
                1 -> "revolver2"
                2 -> "revolver3"
                else -> "revolver1"
            }
            return makeGun(name)
        }
    }

    override fun toString(): String {
        return this.name
    }
}
