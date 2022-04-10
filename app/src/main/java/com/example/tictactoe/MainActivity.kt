package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener
{
    var PLAYER=true
    //Player
    var TURNCOUNT=0
    var Rarr= Array(3){ IntArray(3)}
    lateinit var board:Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       board = arrayOf(arrayOf(btn1,btn2,btn3),arrayOf(btn4,btn5,btn6),arrayOf(btn7,btn8,btn9))
        for(i in board){
            for(button in i)
            {
                button.setOnClickListener(this)
            }
        }
        initialiseRarr()
        resetbtn.setOnClickListener{
                PLAYER=true
                TURNCOUNT=0
             initialiseRarr()
                }
    }
    private fun initialiseRarr() {
        for(i in 0..2)
            for(j in 0..2)
                Rarr[i][j]=-1
        for(i in board)
            for(button in i)
            {
                button.isEnabled=true
                "".also { button.text = it }
            }
    }
    override fun onClick(v: View) {
        when(v.id){
            R.id.btn1->{
                updateValue(row=0,col=0,player=PLAYER)
            }
            R.id.btn2->{
                updateValue(row=0,col=1,player=PLAYER)
            }
            R.id.btn3->{
                updateValue(row=0,col=2,player=PLAYER)
            }
            R.id.btn4->{
                updateValue(row=1,col=0,player=PLAYER)
            }
            R.id.btn5->{
                updateValue(row=1,col=1,player=PLAYER)
            }
            R.id.btn6->{
                updateValue(row=1,col=2,player=PLAYER)
            }
            R.id.btn7->{
                updateValue(row=2,col=0,player=PLAYER)
            }
            R.id.btn8->{
                updateValue(row=2,col=1,player=PLAYER)
            }
            R.id.btn9->{
                updateValue(row=2,col=2,player=PLAYER)
            }
        }
            TURNCOUNT++
            PLAYER=!PLAYER
            if(PLAYER)
            {
                updatedisplay("Player X Turn")
            }
            else
            {
                updatedisplay("Player 0 Turn")
            }
            if(TURNCOUNT==9)
            {
                updatedisplay("Game Draw")
            }
        checkwinner()
    }
//Horizontal
    private fun checkwinner() {
        for(i in 0..2) {
            if(Rarr[i][0]==Rarr[i][1] && Rarr[i][0]==Rarr[i][2])
            {
                if(Rarr[i][0]==1)
                {
                    updatedisplay("Player X is Winner")
                    break
                }
                else if(Rarr[i][0]==0)
                {
                    updatedisplay("Player 0 is Winner")
                    break
                }
            }
        }
    //vertical
    for(i in 0..2) {
        if (Rarr[0][i] == Rarr[1][i] && Rarr[0][i] == Rarr[2][i]) {
            if (Rarr[0][i] == 1) {
                updatedisplay("Player X is Winner")
                break
            } else if (Rarr[0][i] == 0) {
                updatedisplay("Player 0 is Winner")
                break
            }
        }
    }
    //First Diagonal
    if(Rarr[0][0]==Rarr[1][1] && Rarr[0][0]==Rarr[2][2])
    {
        if(Rarr[0][0]==1)
        {
            updatedisplay("Player X is Winner")
        }
        else if(Rarr[0][0]==0)
        {
            updatedisplay("Player 0 is Winner")
        }
    }
    //second Diagonal
    if(Rarr[0][2]==Rarr[1][1] && Rarr[0][2]==Rarr[2][0])
    {
        if(Rarr[0][2]==1)
        {
            updatedisplay("Player X is Winner")
        }
        else if(Rarr[0][2]==0)
        {
            updatedisplay("Player 0 is Winner")
        }
    }
}
    private fun updatedisplay(text: String) {
        displaytv.text=text
        if(text.contains("Winner")){
        disblebutton()}
    }
    private fun disblebutton() {
        for(i in board)
        {
            for(button in i)
            {
                button.isEnabled=false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text=if(player) "X" else "0"
        val value=if(player) 1 else 0
        board[row][col].apply {
            isEnabled=true
            setText(text)
        }
        Rarr[row][col]=value
    }

}