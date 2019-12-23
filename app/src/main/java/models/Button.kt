package models

data class Button (
    val buttonKey: String,

    //null initially
    val buttonText: String?,
    val imagePath: String?,
    val reqString: String?,
    val ipType: IPType?
)