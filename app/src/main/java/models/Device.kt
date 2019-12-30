package models

data class Device ( //page
    var numRows: Int = 7,
    var numCols: Int = 3,
    val deviceKey: String,
    val deviceLabel: String,
    val buttons: List<Button>
){
    override fun toString(): String {
        var ss = "$numRows x $numCols\n" +
                "KEY: $deviceKey, LABEL: $deviceLabel\n" +
                "BUTTONS [\n"
        buttons.filterNotNull().forEach {
            ss+=" "
            ss += it.toString()
            ss+=","
        }
        ss+="\n]"
        return ss
    }
}