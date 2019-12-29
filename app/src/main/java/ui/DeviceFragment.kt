package ui

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hancock.julie.remote.R
import kotlinx.android.synthetic.main.fragment_device.*
import kotlinx.android.synthetic.main.single_button.view.*
import kotlinx.android.synthetic.main.single_room_row.view.*
import models.Button
import models.Cache
import models.Device
import android.graphics.drawable.Drawable
import android.widget.ImageView
import models.Parser


class DeviceFragment : Fragment() {

    lateinit var presenter : DevicePresenter

    companion object {
        fun createFragment(deviceKey: String, roomKey: String): Fragment {
            val bundle = Bundle()
            val toRet = DeviceFragment()
            bundle.putString("roomKey", roomKey)
            bundle.putString("deviceKey", deviceKey)
            toRet.arguments = bundle
            return toRet
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_device, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var roomKey = arguments?.getString("roomKey")
        var deviceKey = arguments?.getString("deviceKey")
        val device :Device = Cache.remote.getDevice(roomKey = roomKey, deviceKey = deviceKey)
        presenter = DevicePresenter(device)
        displayButtons()
    }

    private fun displayButtons() {
        deviceButtons.layoutManager = GridLayoutManager(requireContext(), 3)
        val adapter = DeviceAdapter(presenter.device.buttons, requireContext())
        deviceButtons.adapter = adapter
    }

}


class DeviceAdapter (
    private val buttons: List<Button>,
    private val context: Context
): RecyclerView.Adapter<DeviceAdapter.ButtonHolder>(){

    class ButtonHolder(v: View): RecyclerView.ViewHolder(v){
        private var view: View = v

        fun bind(remoteButton: Button, context: Context) {
            if(remoteButton.buttonKey.isNullOrBlank()){
                view.remoteButton.setBackgroundColor(context.resources.getColor(R.color.darkGray))
                return
            }


            view.remoteButton.setOnClickListener {
                Toast.makeText(context, remoteButton.getURL(), Toast.LENGTH_SHORT).show()
            }


            view.remoteButtonText.text = remoteButton.buttonText
            view.remoteButtonImage.setImage(remoteButton)
            view.remoteButtonText.visibility = View.VISIBLE
            view.remoteButtonImage.visibility = View.VISIBLE

            when{
                !remoteButton.buttonText.isNullOrBlank() && !remoteButton.imagePath.isNullOrBlank()-> {
                    /*both image & text */
                    view.remoteButtonText.visibility = View.GONE
                    view.remoteButton.setBackgroundColor(context.resources.getColor(R.color.darkGray))
                }
                remoteButton.buttonText.isNullOrBlank() && remoteButton.imagePath.isNullOrBlank() -> { //neither image, nor text
                    view.remoteButtonImage.visibility = View.GONE
                    view.remoteButtonText.text = remoteButton.buttonKey
                }
                remoteButton.buttonText.isNullOrBlank() -> { //only image
                    view.remoteButtonText.visibility = View.GONE
                    view.remoteButton.setBackgroundColor(context.resources.getColor(R.color.darkGray))
                }
                remoteButton.imagePath.isNullOrBlank() -> { //only text
                    view.remoteButtonImage.visibility = View.GONE
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_button, parent, false)
        return ButtonHolder(view)
    }

    override fun getItemCount(): Int = buttons.size

    override fun onBindViewHolder(holder: ButtonHolder, position: Int) {
        holder.bind(buttons[position], context)
    }
}

private fun ImageView.setImage(remoteButton: Button) {
    if(remoteButton.imagePath.isNullOrBlank()) return

    val id = when(remoteButton.imagePath){
        "ok.png" -> R.raw.ok
        "left_arrow.png" -> R.raw.left_arrow
        "right_arrow.png" -> R.raw.right_arrow
        "up_arrow.png" -> R.raw.up_arrow
        "down_arrow.png" -> R.raw.down_arrow

        "back.png" -> R.raw.back
        "settings.png" -> R.raw.settings
        "home.png" -> R.raw.home


        else -> null
    }

    if(id!=null){
        val bitmap = BitmapFactory.decodeResource(context.resources, id)
        setImageBitmap(bitmap)
    }
}
