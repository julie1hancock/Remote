package models

class Room ( //list of tabs - pick one on start
    val roomKey: String,
    val roomLabel: String,
    val devices: List<Device>
) {
    fun getDevice(deviceKey: String): Device {
        devices.forEach {
            if(it.deviceKey == deviceKey) return it
        }
        throw Error("Could not find device with matching key: $deviceKey")
    }
}