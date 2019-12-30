package models

data class RoomList(
    val rooms: List<Room>
){

    val size: Int
        get() = rooms.size

    fun getRoomFromKey(key: String):Room {
        val room = rooms.filter { it.roomKey == key }
        if(room.isEmpty()) throw Error("No room with matching key!")
        return room[0]
    }

    fun getDevice(roomKey: String?, deviceKey: String?): Device {
        val room = getRoomFromKey(roomKey.orEmpty())
        return room.getDevice(deviceKey.orEmpty())
    }

    fun get(index: Int): Room {
        if (size < index) throw Error("invalid room index!")
        return rooms[index]
    }
}


