package com.example.pointcontest

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // this keeps the score
    var score = 0

    // sound players
    var clickSound: MediaPlayer? = null
    var winSound: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("PointContest", "App started")

        // text views
        val textScore = findViewById<TextView>(R.id.textScore)
        val textMessage = findViewById<TextView>(R.id.textMessage)

        // buttons
        val buttonOne = findViewById<Button>(R.id.buttonOne)
        val buttonTwo = findViewById<Button>(R.id.buttonTwo)
        val buttonReset = findViewById<Button>(R.id.buttonReset)

        // load sounds
        clickSound = MediaPlayer.create(this, R.raw.click_sound)
        winSound = MediaPlayer.create(this, R.raw.win_sound)

        // load saved score if screen rotated
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("score_key")
            textScore.text = score.toString()
            Log.d("PointContest", "Score restored: $score")

            if (score >= 15) {
                textMessage.text = getString(R.string.win_message)
            } else {
                textMessage.text = getString(R.string.progress_message)
            }
        }

        // +1 button
        buttonOne.setOnClickListener {
            Log.d("PointContest", "+1 button clicked")

            score = score + 1
            textScore.text = score.toString()

            clickSound?.start()

            if (score >= 15) {
                textMessage.text = getString(R.string.win_message)
                winSound?.start()
                Log.d("PointContest", "Player won")
            } else {
                textMessage.text = getString(R.string.progress_message)
            }
        }

        // +2 button
        buttonTwo.setOnClickListener {
            Log.d("PointContest", "+2 button clicked")

            score = score + 2
            textScore.text = score.toString()

            clickSound?.start()

            if (score >= 15) {
                textMessage.text = getString(R.string.win_message)
                winSound?.start()
                Log.d("PointContest", "Player won")
            } else {
                textMessage.text = getString(R.string.progress_message)
            }
        }

        // reset button
        buttonReset.setOnClickListener {
            Log.d("PointContest", "Reset button clicked")

            score = 0
            textScore.text = getString(R.string.initial_score)
            textMessage.text = getString(R.string.game_message)

            clickSound?.start()
        }
    }

    // save score when screen changes
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("score_key", score)
        Log.d("PointContest", "Score saved: $score")
    }

    // clear sounds when app closes
    override fun onDestroy() {
        super.onDestroy()
        clickSound?.release()
        winSound?.release()
        Log.d("PointContest", "Sounds released")
    }
}

