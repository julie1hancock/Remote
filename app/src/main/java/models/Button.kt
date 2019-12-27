package models

data class Button (
    val buttonKey: String,

    //null initially
    var buttonText: String? = null,
    var imagePath: String? = null,
    var urlPrefix: String? = null,
    var urlMiddle: String? = null,
    var urlSuffix: String? = null
//    val ipType: IPType?
){
    override fun toString(): String {
        return "Button(buttonKey='$buttonKey', buttonText=$buttonText, imagePath=$imagePath, urlPrefix=$urlPrefix, urlMiddle=$urlMiddle, urlSuffix=$urlSuffix)"
    }

    fun getURL(): String {
        return "$urlPrefix/$urlMiddle/$urlSuffix"
    }
}