package com.example.tiktactoerepo.util

class GameEnd {

    private var arr: Array<Poi?> = arrayOfNulls(3)
    private var player = 0
    private var key: Int = 0
    private var isDraw = false
    private var isResult: Boolean = false

    init {
        arr = arrayOfNulls(3)
    }

    fun getPlayer(): Int {
        return player
    }

    fun getArr(): Array<Poi?> {
        return arr
    }

    fun getIsDraw(): Boolean {
        return isDraw
    }

    fun getIsResult(): Boolean {
        return isResult
    }

    fun setIsDraw(isDraw: Boolean) {
        this.isDraw = isDraw
    }

    fun setIsResult(isResult: Boolean) {
        this.isResult = isResult
    }

    fun Push(p: Poi?, player: Int) {
        arr[key++] = p
        this.player = player
    }

    fun Reset() {
        key = 0
        player = 0
        arr = arrayOfNulls(3)

    }

    fun print() {
        println("isDraw = $isDraw")
        println("isResult = $isResult")
        println("arr[0] = " + arr[0]!!.getX().toString() + " " + arr[0]!!.getY())
        println("arr[1] = " + arr[1]!!.getX().toString() + " " + arr[1]!!.getY())
        println("arr[2] = " + arr[2]!!.getX().toString() + " " + arr[2]!!.getY())
        println("player $player")
    }

}
