package models

class TempRoom ( //list of tabs - pick one on start
    val roomKey: String,
    val roomLabel: String,
    val devices: List<TempDevice>
) {
    fun toRoom(): Room {
        return Room(
            roomKey = roomKey,
            roomLabel = roomLabel,
            devices = devices.map{
                it.toDevice()
            }
        )
    }
}