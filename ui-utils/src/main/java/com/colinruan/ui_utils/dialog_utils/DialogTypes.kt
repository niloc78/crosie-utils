package com.colinruan.ui_utils.dialog_utils

sealed class DialogTypes {
    val EXCEPTION_MESSAGE : String = "must implement MultipurposeDialogListener"

    object AddDialog : DialogTypes() {
        const val POSITIVE_BUTTON : String = "Add"
        const val NEGATIVE_BUTTON : String = "Cancel"
    }

    data class ConfirmDialog(var setName : String, var content : String) : DialogTypes() {
        val POSITIVE_BUTTON : String = "Yes"
        val NEGATIVE_BUTTON : String = "No"
        val CONTENT : String = content
    }

    data class EnableDisableDialog(var isEnabled : Boolean = false, val contentDisabled : String, val contentEnabled : String) : DialogTypes() {
        val POSITIVE_BUTTON__ENABLED : String = "Disable"
        val POSITIVE_BUTTON__DISABLED : String = "Enable"
        val NEGATIVE_BUTTON : String = "Cancel"
        val CONTENT__ENABLED : String = contentEnabled
        val CONTENT__DISABLED : String = contentDisabled

    }

    object SaveDialog : DialogTypes() {
        const val POSITIVE_BUTTON : String = "Save"
        const val NEGATIVE_BUTTON : String = "Cancel"
    }


}
