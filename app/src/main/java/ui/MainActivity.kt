package ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hancock.julie.remote.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.single_room_row.view.*
import models.Cache
import models.Parser
import models.Room


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ChooseRoomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Parser().parse(resources.openRawResource(R.raw.parse))
        if(Cache.remote.size == 1){
            RoomActivity.start(this, Cache.remote.get(0).roomKey)
        }
        roomsList.layoutManager = LinearLayoutManager(this)
        adapter = ChooseRoomAdapter(Cache.remote.rooms, this)
        roomsList.adapter = adapter
    }

}


class ChooseRoomAdapter (
    private val rooms: List<Room>,
    private val context: Context
): RecyclerView.Adapter<ChooseRoomAdapter.RoomHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_room_row, parent, false)
        return RoomHolder(view)
    }

    override fun getItemCount(): Int = rooms.size

    override fun onBindViewHolder(holder: RoomHolder, position: Int) {
        holder.bind(rooms[position], context)
    }

    class RoomHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun bind(room: Room, context: Context){
            view.singleRoomButton.text = room.roomLabel
            view.singleRoomButton.setOnClickListener {
                RoomActivity.start(context, room.roomKey)
            }
        }
    }

}