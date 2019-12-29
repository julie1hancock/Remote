package models

data class TempRemote(
    val rooms: List<TempRoom>,
    val buttons: List<Button>
) {
    fun toRoomList(): RoomList {
        val buttonList = ButtonList(buttons)
        val completeRoomList = rooms.map{
            it.toRoom()
        }


        completeRoomList.forEach { room ->
            room.devices.forEach { device ->
                device.buttons.forEach { button ->
                    val buttonToCopy = buttonList.find(button.buttonKey)

                    button.buttonText = buttonToCopy?.buttonText
                    button.imagePath = buttonToCopy?.imagePath
                    button.url = buttonToCopy?.url
                }
            }
        }

        return RoomList(completeRoomList)
    }
}