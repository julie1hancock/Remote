package ui

import android.content.Context
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
            view.remoteButton.setOnClickListener {
                Toast.makeText(context, remoteButton.getURL(), Toast.LENGTH_SHORT).show()
            }


            if (remoteButton.buttonText.isNullOrBlank()) {
                view.remoteButtonText.visibility = View.GONE
                if (remoteButton.imagePath.isNullOrBlank()) {
                    view.remoteButtonText.visibility = View.VISIBLE
                    view.remoteButtonText.text = remoteButton.buttonKey

                }
            }
            else {
                view.remoteButtonText.visibility = View.VISIBLE
                view.remoteButtonText.text = remoteButton.buttonText ?: "A BUTTON HERE!"
            }



            if (remoteButton.imagePath.isNullOrBlank()) {
                view.remoteButtonImage.visibility = View.GONE
            }
            else {
                view.remoteButtonImage.visibility = View.VISIBLE
                //TODO: display image @ URL path
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_button, parent, false)
        return ButtonHolder(view)
    }

    override fun getItemCount(): Int = buttons.size

    override fun onBindViewHolder(holder: ButtonHolder, position: Int) {
//        if(buttons[position] == null) return

        holder.bind(buttons[position], context)
    }
}