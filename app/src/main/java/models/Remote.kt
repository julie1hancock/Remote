package models

data class Remote(
    val rooms: List<Room>
){
    fun roomsAsStrings():List<Pair<String,String>> {
        val list = mutableListOf<Pair<String, String>>()
        rooms.map {
            list.add(Pair(it.roomKey, it.roomLabel))
        }
        return list
    }
    fun getRoomFromKey(key: String):Room {
        val room = rooms.filter { it.roomKey == key }
        if(room.isEmpty()) throw Error("No room with matching key!")
        return room[0]
    }
}