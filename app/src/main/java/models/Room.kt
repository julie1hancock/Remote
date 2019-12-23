package models

class Room ( //list of tabs - pick one on start
    val roomKey: String,
    val roomLabel: String,
    val devices: List<Device>
)