package com.birdbraintech.android.finchbasicapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity(), Finch.FinchListener {

    /* You must put this in every activity that uses the Finch. Just trust me. */
    val finch: Finch?
        get() = (application as FinchApplication).finch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Make this the activity that is receiving Finch data */
        finch?.setFinchListener(this)

        /* Set up the seek bar. */
        val seekBar: SeekBar = findViewById(R.id.seekBar)
        seekBar?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed

            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
                when (seek.progress) {
                    in 0..12 -> {
                        finch?.setBeak(100,100,100)
                        finch?.setTail("all",100,100,100)
                    }
                    in 13..24 -> {
                        finch?.setBeak(100,100,0)
                        finch?.setTail("all",100,100,0)
                    }
                    in 25..36 -> {
                        finch?.setBeak(0,100,100)
                        finch?.setTail("all",0,100,100)
                    }
                    in 37..48 -> {
                        finch?.setBeak(0,100,0)
                        finch?.setTail("all",0,100,0)
                    }
                    in 49..60 -> {
                        finch?.setBeak(100,0,100)
                        finch?.setTail("all",100,0,100)
                    }
                    in 61..72 -> {
                        finch?.setBeak(100,0,0)
                        finch?.setTail("all",100,0,0)
                    }
                    in 73..84 -> {
                        finch?.setBeak(0,0,100)
                        finch?.setTail("all",0,0,100)
                    }
                    else -> {
                        finch?.setBeak(0,0,0)
                        finch?.setTail("all",0,0,0)
                    }
                }
            }
        })

        seekBar.progress = 100

    }

    /* This Bluetooth does not disconnect automatically, so I want to disconnect it if this activity stops (since there are no
    other activities in my app.
     */
    override fun onStop() {
        super.onStop()
        finch?.disconnect()
    }

    fun upButtonClicked(view: View) {
        finch?.setMove("F",20.0,50)
    }

    fun downButtonClicked(view: View) {
        finch?.setMove("B",20.0,50)
    }

    fun leftButtonClicked(view: View) {
        finch?.setTurn("L",90.0,50)
    }

    fun rightButtonClicked(view: View) {
        finch?.setTurn("R",90.0,50)
    }

    override fun onConnected() {
        /* This is where you would handle anything you want to do if the Finch connects (for instance, if you are handling
        reconnection).
         */
    }
    override fun onDisconnected() {
        /* This is where you would handle anything you want to do if the Finch disconnects. */
    }

    override fun onData() {
        this.runOnUiThread(java.lang.Runnable({
            this.distanceNumber.text = finch?.inputState?.distance.toString() + " cm"
            val lightSensors = finch?.correctLightSensorValues()
            this.lightNumbers.text = "(" + lightSensors?.get(0).toString() + ", " +
                    lightSensors?.get(1).toString() + ")"

            this.lineNumbers.text = "(" + finch?.inputState?.leftLine.toString() + ", " +
                    finch?.inputState?.rightLine.toString() + ")"
        }))

    }
}
