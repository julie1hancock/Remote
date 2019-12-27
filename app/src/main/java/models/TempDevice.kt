package models

data class TempDevice ( //page
    var numRows: Int = 7,
    var numCols: Int = 3,
    val deviceKey: String,
    val deviceLabel: String,
    val buttonKeys: List<String>
){
    override fun toString(): String {
        var ss = "$numRows x $numCols\n" +
                "KEY: $deviceKey, LABEL: $deviceLabel\n" +
                "BUTTONS [\n"
        buttonKeys.forEach {
            ss+=" "
            ss += it
            ss+=","
        }
        ss+="\n]"
        return ss
    }

    fun toDevice(): Device {
        return Device(
            deviceKey = deviceKey,
            deviceLabel = deviceLabel,
            numCols = numCols,
            numRows = numRows,
            buttons = buttonKeys.map{
                Button(buttonKey = it)
            }
        )
    }
}






















