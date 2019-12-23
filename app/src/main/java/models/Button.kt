package models

data class Button (
    val buttonKey: String,

    //null initially
    val buttonText: String?,
    val imagePath: String?,
    val reqString: String?,
    val ipType: IPType?
){
    override fun toString(): String {
        return "KEY : $buttonKey"
    }
}