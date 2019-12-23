package models

data class Remote(
    val rooms: List<Room>
){

    fun getRoomFromKey(key: String):Room {
        val room = rooms.filter { it.roomKey == key }
        if(room.isEmpty()) throw Error("No room with matching key!")
        return room[0]
    }

    fun getDevice(roomKey: String?, deviceKey: String?): Device {
        val room = getRoomFromKey(roomKey.orEmpty())
        return room.getDevice(deviceKey.orEmpty())
    }
}