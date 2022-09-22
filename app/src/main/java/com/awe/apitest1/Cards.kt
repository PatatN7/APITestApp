package com.awe.apitest1

import kotlin.collections.ArrayList

class Cards {
    var data : ArrayList<Data>? = null
    var count : Int? = 0
}

class Data{
    var name : String? = null
    var images : Images? = null
    var set : Set? = null
    var rarity : String? = null
    var tcgplayer : TcgPlayer? = null
}

class Images{
    var small : String? = null
}

class Set{
    var name : String? = null
}

class TcgPlayer{
    var updatedAt : String? = null
    var prices : Prices? = null
}

class Prices{
    var normal : Normal? = null
    var holofoil : Holofoil? = null
    var reverseHolofoil : ReverseHolofoil? = null
}

class Normal{
    var market : Double? = null
}

class Holofoil{
    var market : Double? = null
}

class ReverseHolofoil{
    var market : Double? = null
}