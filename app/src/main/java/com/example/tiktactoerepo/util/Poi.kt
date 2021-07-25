package com.example.tiktactoerepo.util

class Poi {
    private var x = 0
    private  var y:Int = 0
    private  var h:Int = 0
    private  var d:Int = 0

    fun Poi() {}

    constructor(x: Int, y: Int, h: Int) {
        this.x = x
        this.y = y
        this.h = h
    }

    constructor(x: Int, y: Int, h: Int, d: Int) {
        this.x = x
        this.y = y
        this.h = h
        this.d = d
    }

    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    constructor(h: Int) {
        this.h = h
    }

    constructor(d: Int, f: Boolean) {
        this.d = d
    }

    fun setD(d: Int) {
        this.d = d
    }

    fun getX(): Int {
        return x
    }

    fun getY(): Int {
        return y
    }

    fun getH(): Int {
        return h
    }

    fun getD(): Int {
        return d
    }

}
