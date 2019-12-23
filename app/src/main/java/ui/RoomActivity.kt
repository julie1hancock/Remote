package ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hancock.julie.remote.R
import kotlinx.android.synthetic.main.activity_room.*
import models.Cache
import models.Room

class RoomActivity : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        val room : Room = Cache.remote.getRoomFromKey(intent.getStringExtra("roomKey") ?: "")
        val adapter = RoomTabAdapter(supportFragmentManager,room)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        changeRoom.setOnClickListener {
            finish()
        }
        enableDebug.setOnClickListener {
            Toast.makeText(this, "Debug cannot be turned on currently", Toast.LENGTH_SHORT).show()
        }
    }

    class RoomTabAdapter(
        fragmentManager: FragmentManager,
        private val room: Room
    ) : FragmentPagerAdapter(fragmentManager) {

        override fun getItem(position: Int): Fragment {
            return DeviceFragment.createFragment(room.devices[position].deviceKey, room.roomKey)
        }

        override fun getCount(): Int = room.devices.size

        override fun getPageTitle(position: Int): CharSequence? {
            return room.devices[position].deviceLabel
        }

    }
}