package com.example.tiktactoerepo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class PlayingActivity : AppCompatActivity(), View.OnClickListener {

    private val dimention = 3
    private val buttons = Array(dimention) {
        arrayOfNulls<ImageView>(
            dimention
        )
    }

    private val texts = Array(dimention) {
        arrayOfNulls<String>(
            dimention
        )
    }

    private var p1Turn = true

    var p1TotalWins = 0
    var p2TotalWins = 0

    private var runder = 0

    private var p1Points = 0
    private var p2Points = 0

//    var line

    private var p1Score: TextView? = null
    private var p2Score: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

//        line = findViewById<View>(R.id.line)

//        p1Score = findViewById(R.id.text_view_p1)
//        p2Score = findViewById(R.id.text_view_p2)

        for (i in 0 until dimention) {
            for (j in 0 until dimention) {
                val buttonID = "button_$i$j"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                buttons[i][j] = findViewById(resID)
                buttons[i][j]?.setOnClickListener(this)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        for (i in 0 until dimention) {
            for (j in 0 until dimention) {
                texts[i][j] = "ABC"
                Log.e("AAA", texts[i][j] + " ss " + i + " j : " + j)
            }
        }
    }

    override fun onClick(view: View?) {
        if (p1Turn) {
            (view as ImageView).setImageResource(R.drawable.x)
        } else {
            (view as ImageView).setImageResource(R.drawable.oo)
        }

        when (view.id) {
            R.id.button_00 -> {
                Log.e("AAA", " R.id.button_00 click  ")
                if (p1Turn) {
                    texts[0][0] = "x"
                } else {
                    texts[0][0] = "0"
                }
            }
            R.id.button_01 -> {
                Log.e("AAA", " R.id.button_01 click  ")
                if (p1Turn) {
                    texts[0][1] = "x"
                } else {
                    texts[0][1] = "0"
                }
            }
            R.id.button_02 -> {
                Log.e("AAA", " R.id.button_02 click  ")
                if (p1Turn) {
                    texts[0][2] = "x"
                } else {
                    texts[0][2] = "0"
                }
            }
            R.id.button_10 -> {
                if (p1Turn) {
                    texts[1][0] = "x"
                } else {
                    texts[1][0] = "0"
                }
            }
            R.id.button_11 -> {
                if (p1Turn) {
                    texts[1][1] = "x"
                } else {
                    texts[1][1] = "0"
                }
            }
            R.id.button_12 -> {
                if (p1Turn) {
                    texts[1][2] = "x"
                } else {
                    texts[1][2] = "0"
                }
            }
            R.id.button_20 -> {
                if (p1Turn) {
                    texts[2][0] = "x"
                } else {
                    texts[2][0] = "0"
                }
            }
            R.id.button_21 -> {
                if (p1Turn) {
                    texts[2][1] = "x"
                } else {
                    texts[2][1] = "0"
                }
            }
            R.id.button_22 -> {
                if (p1Turn) {
                    texts[2][2] = "x"
                } else {
                    texts[2][2] = "0"
                }
            }
        }

        runder++

        if (checkForWin()) {
            if (p1Turn) {
                p1Win()
            } else {
                p2Win()
            }
        } else if (runder == 9) {
            draw()
        } else {
            p1Turn = !p1Turn
        }
    }

    private fun checkForWin(): Boolean {
        val field = Array(dimention) {
            arrayOfNulls<String>(
                dimention
            )
        }
//        val horizontalLineAnimation = TranslateAnimation( buttons[0][0]!!.pivotX, buttons[0][0]!!.pivotY, buttons[2][2]!!.pivotX, buttons[2][2]!!.pivotY)
//        horizontalLineAnimation.duration = 1000
//        findViewById<View>(R.id.line).startAnimation(horizontalLineAnimation)

        for (i in 0 until dimention) {
            for (j in 0 until dimention) {
                field[i][j] = texts[i][j]
                Log.e("AAA", texts[i][j] + " ss " + i + " j : " + j)
            }
        }
        for (i in 0 until dimention) {
            if (field[i][0] == field[i][1] && field[i][0] == field[i][2] && field[i][0] != "ABC") {
                Log.e("AAA", "FOR 1 call ")
                return true
            }
        }
        for (i in 0 until dimention) {
            if (field[0][i] == field[1][i] && field[0][i] == field[2][i] && field[0][i] != "ABC") {
                Log.e("AAA", "FOR 2 call ")
                return true
            }
        }
        if (field[0][0] == field[1][1] && field[0][0] == field[2][2] && field[0][0] != "ABC") {
            Log.e("AAA", "FOR 3 call ")

            return true
        }
        Log.e("AAA", "FOR 4  call ")
        return field[0][2] == field[1][1] && field[0][2] == field[2][0] && field[0][2] != "ABC"
    }

    private fun p1Win() {
        p1Points++
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show()
        p1TotalWins++
        //  ScoreText()
        CleanBoard()
    }

    private fun p2Win() {
        p2Points++
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show()
        p2TotalWins++
        //  ScoreText()
        CleanBoard()
    }

    private fun draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()
        CleanBoard()
    }

    private fun Reset() {
        p1Points = 0
        p2Points = 0
        // ScoreText()
        CleanBoard()
    }

    private fun CleanBoard() {
        for (i in 0 until dimention) {
            for (j in 0 until dimention) {
                buttons[i][j]?.setImageDrawable(null)
            }
        }
        for (i in 0 until dimention) {
            for (j in 0 until dimention) {
                texts[i][j] = "ABC"
                Log.e("AAA", texts[i][j] + " ss " + i + " j : " + j)
            }
        }
        runder = 0
        p1Turn = true
    }
}