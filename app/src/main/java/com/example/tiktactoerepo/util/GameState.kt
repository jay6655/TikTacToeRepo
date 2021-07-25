package com.example.tiktactoerepo.util

import android.util.Log
import java.util.*

class GameState// board[i][j] = -1 for O move and +1 for X move
    (private var size: Int) {

    private var board: Array<IntArray?>? = null
    private var moveCount: Int = 0
    var endCross: Boolean = false
    var startCross: Boolean =  false
    private var rnd: Random? = null

    init {
        this.moveCount = 0
        board = Array(size) { IntArray(size) }
        for (i in 0 until size) {
            for (j in 0 until size) {
                board!![i]!![j] = 0 // board[i][j] = -1 for O move and +1 for X move
            }
        }
        rnd = Random()
    }

    fun getMoveCount(): Int {
        return moveCount
    }

    fun setMoveCount(moveCount: Int) {
        this.moveCount = moveCount
    }

    fun printBoard() {
        for (i in 0 until size) {
            for (j in 0 until size) {
                print(board!![i]!![j].toString() + " ")
            }
            println("")
        }
    }

    private fun drop(p: Poi, player: Int) {
        this.board!![p.getX()]!![p.getY()] = player
    }

    private fun check(grid: Array<IntArray?>?): Int {
        var cnt: Int
        for (i in 0 until size) {
            for (j in 0 until size) {
                val `val` = grid!![i]!![j]
                Log.e("QWE" , " val 50 : $`val`")
                if (`val` == 0) continue
                cnt = 0
                run {
                    var k = 0
                    while (k < size && j + k < size) {
                        val y = j + k
                        if (grid[i]!![y] == `val`) cnt++
                        k++
                    }
                }
                Log.e("QWE " , " val 61 : $`val`")
                if (cnt == size) return `val`
                cnt = 0
                run {
                    var k = 0
                    while (k < size && i + k < size) {
                        val x = i + k
                        if (grid[x]!![j] == `val`) cnt++
                        k++
                    }
                }
                Log.e("QWE " , " val 72 : $`val`")
                if (cnt == size) return `val`
                cnt = 0
                run {
                    var k = 0
                    while (k < size && i + k < size && j + k < size) {
                        val x = i + k
                        val y = j + k
                        if (grid[x]!![y] == `val`) cnt++
                        k++
                    }
                }
                Log.e("QWE " , " val 84 : $`val`")
                if (cnt == size) {
                    endCross = true
                    return `val`
                }
                cnt = 0
                var k = 0
                while (k < size && i + k < size && j - k >= 0) {
                    val x = i + k
                    val y = j - k
                    if (grid[x]!![y] == `val`) cnt++
                    k++
                }
                Log.e("QWE " , " val 94  : $`val`")
                if (cnt == size) {
                    startCross = true;
                    return `val`
                }
            }
        }
        Log.e("QWE " , " val 98 : 0 ")
        return 0
    }

    private fun minimax(grid: Array<IntArray>, currentPlayer: Int, depth: Int): Poi {
//        val here = check(grid)
//        if (here != 0) {
//            val retHere = Poi(here)
////            val retHere = Poi.new
//            retHere.setD(depth)
//            return retHere
//        }
        return if (currentPlayer == 1) {
            var nRet = Poi(0, true)
            var pRet = Poi(100, true)
            var dRet = Poi(0, true)
            var n = 0
            var p = 0
            var d = 0
            var ret = 0
            for (i in 0 until size) {
                for (j in 0 until size) {
                    if (grid[i][j] == 0) {
                        grid[i][j] = currentPlayer
                        val pt: Poi = minimax(grid, currentPlayer * -1, depth + 1)
                        if (pt.getH() === -1) {
                            n++
                            if (pt.getD() >= nRet.getD()) nRet = Poi(i, j, -1, pt.getD())
                        } else if (pt.getH() === 1) {
                            p++
                            if (pt.getD() <= pRet.getD()) pRet = Poi(i, j, 1, pt.getD())
                        } else {
                            d++
                            if (pt.getD() >= dRet.getD()) dRet = Poi(i, j, 0, pt.getD())
                        }
                        grid[i][j] = 0
                    }
                }
            }
            if (p != 0) ret = 1 else if (n != 0 && d == 0) ret = -1
            if (ret == 1) pRet else if (ret == -1) nRet else dRet
        } else { // currentPLayer = -1;
            var nRet = Poi(100, true)
            var pRet = Poi(0, true)
            var dRet = Poi(0, true)
            var n = 0
            var p = 0
            var d = 0
            var ret = 0
            for (i in 0 until size) {
                for (j in 0 until size) {
                    if (grid[i][j] == 0) {
                        grid[i][j] = currentPlayer
                        val pt: Poi = minimax(grid, currentPlayer * -1, depth + 1)
                        if (pt.getH() === -1) {
                            n++
                            if (pt.getD() <= nRet.getD()) nRet = Poi(i, j, -1, pt.getD())
                        } else if (pt.getH() === 1) {
                            p++
                            if (pt.getD() >= pRet.getD()) pRet = Poi(i, j, 1, pt.getD())
                        } else {
                            d++
                            if (pt.getD() >= dRet.getD()) dRet = Poi(i, j, 0, pt.getD())
                        }
                        grid[i][j] = 0
                    }
                }
            }
            if (n != 0) ret = -1 else if (p != 0 && d == 0) ret = +1
            if (ret == 1) pRet else if (ret == -1) nRet else dRet
        }
    }

//    fun generateNextMove(player: Int): Poi {
//        val p: Poi = minimax(board, player, 0)
//        this.drop(p, player)
//        return p
//    }

    private fun randomMove(player: Int): Poi {
        moveCount++
        while (true) {
            val x = (rnd!!.nextInt() % size + size) % size
            val y = (rnd!!.nextInt() % size + size) % size
            if (board!![x]!![y] == 0) {
                val p = Poi(x, y)
                drop(p, player)
                return p
            }
        }
    }

    private fun properMove(player: Int): Poi {
        moveCount++
        val arr: Array<IntArray> = Array(size) { IntArray(size) }
        for (i in 0 until size) {
            for (j in 0 until size) {
                arr[i][j] = board!![i]!![j]
            }
        }
        val p: Poi = minimax(arr, player, 0)
        drop(p, player)
        return p
    }

    fun next(p: Poi, player: Int, level: Int): Poi {
        var player = player
        if (board!![p.getX()]!![p.getY()] != 0) return p
        drop(p, player)
        player *= -1
        moveCount++
        val ge: GameEnd = checkGameEnd()
        if (ge.getIsDraw()) {
            return Poi(size, size)
        }
        if (ge.getIsResult()) {
            return Poi(size + 1, size + 1)
        }
        return when (level) {
            0 -> { // easy
                randomMove(player)
            }
            1 -> { // medium
                val x = rnd!!.nextInt()
                if (x % 10 == 0)
                    randomMove(player)
                else
                    properMove(player)
            }
            2 -> { // hard
                properMove(player)
            }
            else -> p
        }
    }


    fun endState(): GameEnd {
        val ge = GameEnd()
        ge.setIsResult(true)
        var cnt: Int
        for (i in 0 until size) {
            for (j in 0 until size) {
                val `val`: Int = board!![i]!![j]
                if (`val` == 0) continue
                ge.Reset()
                cnt = 0
                run {
                    var k = 0
                    while (k < size && j + k < size) {
                        val y = j + k
                        ge.Push(Poi(i, y), `val`)
                        if (board!![i]!![y] == `val`) cnt++
                        k++
                    }
                }
                if (cnt == size) return ge
                ge.Reset()
                cnt = 0
                run {
                    var k = 0
                    while (k < size && i + k < size) {
                        val x = i + k
                        ge.Push(Poi(x, j), `val`)
                        if (board!![x]!![j] == `val`) cnt++
                        k++
                    }
                }
                if (cnt == size) return ge
                ge.Reset()
                cnt = 0
                run {
                    var k = 0
                    while (k < size && i + k < size && j + k < size) {
                        val x = i + k
                        val y = j + k
                        ge.Push(Poi(x, y), `val`)
                        if (board!![x]!![y] == `val`) cnt++
                        k++
                    }
                }
                if (cnt == size) return ge
                ge.Reset()
                cnt = 0
                var k = 0
                while (k < size && i + k < size && j - k >= 0) {
                    val x = i + k
                    val y = j - k
                    ge.Push(Poi(x, y), `val`)
                    if (board!![x]!![y] == `val`) cnt++
                    k++
                }
                if (cnt == size) return ge
            }
        }
        return ge
    }

    private fun checkGameEnd(): GameEnd {
        val ge = GameEnd()
        //		System.out.println("checkGameEnd moveCount = " + moveCount);
        return if (check(this.board) == 0 && moveCount == size * size) {
            ge.setIsDraw(true)
            ge
        } else {
            val winner = check(board)
            if (winner == 0) ge else endState()
        }
    }


    fun check1(): Int {
        return check(this.board)
    }

    fun computerStart() {
        drop(Poi(0, 0), -1)
        moveCount++
        //properMove(-1);
    }
}