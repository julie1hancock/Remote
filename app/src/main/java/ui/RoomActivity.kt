package ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hancock.julie.remote.R
import kotlinx.android.synthetic.main.activity_room.*

class RoomActivity : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
//        roomText.text = intent.getStringExtra("roomKey")
    }
}