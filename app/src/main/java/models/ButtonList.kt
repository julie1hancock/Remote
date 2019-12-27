package models

import java.lang.NullPointerException

data class ButtonList (
    val buttons: List<Button>
) {
    fun find(buttonKey: String): Button {
        return buttons.filter {buttonKey == it.buttonKey}[0] ?: throw NullPointerException("Couldn't find button with key of $buttonKey")
    }
}