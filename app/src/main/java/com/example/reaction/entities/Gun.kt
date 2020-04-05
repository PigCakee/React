package com.example.reaction.entities

class Gun (val name: String) {
    var delay = 0L
    var load = 0
    var cost = 0

    companion object Factory{
        fun makeGun(name: String): Gun {
            val gun = Gun(name)
            when (name){
               "revolver0" -> {
                   gun.delay = 100L
                   //gun.icon =
                   gun.load = 5
                   gun.cost = 100
               }
                "revolver1" -> {
                    gun.delay = 75L
                    //gun.icon =
                    gun.load = 5
                    gun.cost = 300
                }
                "revolver2" -> {
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
                0 -> "revolver0"
                1 -> "revolver1"
                2 -> "revolver2"
                else -> "revolver0"
            }
            return makeGun(name)
        }
    }

    override fun toString(): String {
        return this.name
    }
}
