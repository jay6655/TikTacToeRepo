package com.example.tiktactoerepo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tiktactoerepo.util.GameEnd
import com.example.tiktactoerepo.util.GameState
import com.example.tiktactoerepo.util.Poi
import java.util.*

class ComputerPlayingActivity : AppCompatActivity(), View.OnClickListener {

    private var gs: GameState? = null
    private var player = 0

    private val dimention = 3
    private val buttons = Array(dimention) {
        arrayOfNulls<ImageView>(
            dimention
        )
    }
    private var firstline: View? = null
    private var secondline  = null
    private var thirdline = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        for (i in 0 until dimention) {
            for (j in 0 until dimention) {
                val buttonID = "button_$i$j"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                buttons[i][j] = findViewById(resID)
                buttons[i][j]?.setOnClickListener(this)
            }
        }

        firstline = findViewById(R.id.firstline)
//        secondline = findViewById(R.id.secondline)
//        thirdline = findViewById(R.id.thirdline)


    }

    override fun onStart() {
        super.onStart()
        gs = GameState(3)

        val rand: Random = Random()
        if (rand.nextInt() % 2 == 0) {
            player = 1
            gs!!.computerStart()
            updateButton(Poi(0, 0), -1)
        } else {
            player = 1
        }
    }

    private fun updateButton(p: Poi, pl: Int) {
        val ib: ImageView
        val r = p.getX()
        val c = p.getY()
        ib =
            if (r == 0 && c == 0) findViewById<View>(R.id.button_00) as ImageView
            else if (r == 0 && c == 1) findViewById<View>(R.id.button_01) as ImageView
            else if (r == 0 && c == 2) findViewById<View>(R.id.button_02) as ImageView
            else if (r == 1 && c == 0) findViewById<View>(R.id.button_10) as ImageView
            else if (r == 1 && c == 1) findViewById<View>(R.id.button_11) as ImageView
            else if (r == 1 && c == 2) findViewById<View>(R.id.button_12) as ImageView
            else if (r == 2 && c == 0) findViewById<View>(R.id.button_20) as ImageView
            else if (r == 2 && c == 1) findViewById<View>(R.id.button_21) as ImageView
            else findViewById<View>(R.id.button_22) as ImageView

        if (pl == 1) {
            ib.setImageResource(R.drawable.x)
        } else {
            ib.setImageResource(R.drawable.oo)
        }
    }

    private fun input(r: Int, c: Int) {
        val p = Poi(r, c)
        val q = gs!!.next(p, player, 1)
        Log.e("DBG", " p = ( " + p.getX() + ", " + p.getY() + " )")
        Log.e("DBG", " q = ( " + q.getX() + ", " + q.getY() + " )")
        if (!(p.getX() == q.getX() && p.getY() == q.getY())) {
            if (p.getX() in 0..2 && p.getY() >= 0 && p.getY() < 3)
                updateButton(p, player)
            if (q.getX() in 0..2 && q.getY() >= 0 && q.getY() < 3)
                updateButton(q, player * -1)
        }
        Log.e("AAA", " gs!!.check1() " + gs!!.check1())
        if (gs!!.check1() != 0) { // win
            val ge: GameEnd = gs!!.endState()
//            val point: Array<Poi?> = ge.getArr()
            for (i in 0..2) {
//                setButtonDisable()
//                resetBoard()
                if (ge.getPlayer() == 1) {
                    Toast.makeText(this, "YOU win", Toast.LENGTH_SHORT).show()
//                    txtres.setText("You Win!")
                    firstline!!.visibility = View.VISIBLE
                } else {
                    Toast.makeText(this, "YOU loss ", Toast.LENGTH_SHORT).show()
//                    txtres.setText("You Lose!")
//                    resetBoard()
                }
            }

            return
        } else if (q.getX() == 3 && q.getY() == 3 || gs!!.getMoveCount() >= 3 * 3) { // draw
            Toast.makeText(this, "Draw Game", Toast.LENGTH_SHORT).show()
//            setButtonDisable()
//            txtres.setText("Match Tie")
//            resetBoard()
        }
    }

    private fun setButtonDisable() {
        for (i in 0 until dimention) {
            for (j in 0 until dimention) {
                buttons[i][j]?.setImageDrawable(null)
            }
        }
        gs = GameState(3)
//        for (i in 0 until dimention) {
//            for (j in 0 until dimention) {
//                texts[i][j] = "ABC"
//                Log.e("AAA", texts[i][j] + " ss " + i + " j : " + j)
//            }
//        }
//        runder = 0
//        p1Turn = true
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.button_00 -> {
                Log.e("AAA", " R.id.button_00 click  ")
                input(0, 0)
            }
            R.id.button_01 -> {
                Log.e("AAA", " R.id.button_01 click  ")
                input(0, 1)
            }
            R.id.button_02 -> {
                Log.e("AAA", " R.id.button_02 click  ")
                input(0, 2)
            }
            R.id.button_10 -> {
                input(1, 0)
            }
            R.id.button_11 -> {
                input(1, 1)
            }
            R.id.button_12 -> {
                input(1, 2)
            }
            R.id.button_20 -> {
                input(2, 0)
            }
            R.id.button_21 -> {
                input(2, 1)
            }
            R.id.button_22 -> {
                input(2, 2)
            }
        }
    }
}