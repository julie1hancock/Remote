package models

data class Device ( //page
    var numRows: Int = 7,
    var numCols: Int = 3,
    val deviceKey: String,
    val deviceLabel: String,
    val buttons: List<Button>
)