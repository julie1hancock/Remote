package models

data class Button (
    val buttonKey: String,

    //null initially
    var buttonText: String? = null,
    var imagePath: String? = null,
    var url: String? = null

//    val ipType: IPType?
){


    fun getURL(): String {
        return url ?: throw Error("null url for button $buttonKey")
    }

    override fun toString(): String {
        return "Button(buttonKey='$buttonKey', buttonText=$buttonText, imagePath=$imagePath, url=$url)"
    }
}