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
//        setSupportActionBar(toolbar)

        Parser().parse(resources.openRawResource(R.raw.parse))
        roomsList.layoutManager = LinearLayoutManager(this)
        adapter = ChooseRoomAdapter(Cache.remote.rooms, this)
        roomsList.adapter = adapter
    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
////        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
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
                val intent = Intent(context, RoomActivity::class.java)
                intent.putExtra("roomKey",room.roomKey)
                context.startActivity(intent)
            }
        }
    }

}