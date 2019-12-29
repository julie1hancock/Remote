package models


data class ButtonList (
    val buttons: List<Button>
) {
    fun find(buttonKey: String): Button? {
        val list = buttons.filter {it!=null && buttonKey == it.buttonKey}
        if(list.isNullOrEmpty()) return null
        return list[0]
    }
}