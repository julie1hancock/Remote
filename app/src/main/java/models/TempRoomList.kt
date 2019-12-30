package models

data class TempRoomList(
    val rooms: List<TempRoom>
) {
    fun toRoomList(): RoomList {
        return RoomList(rooms = rooms.map {
            it.toRoom()
        })
    }
}