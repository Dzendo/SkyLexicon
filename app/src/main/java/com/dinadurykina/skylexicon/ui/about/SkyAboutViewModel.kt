package com.dinadurykina.skylexicon.ui.about

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SkyAboutViewModel(application: Application) : AndroidViewModel(application) {

    val myName: MyName = MyName("My Dzen DO Binding")

    /**
     *
     */
    private val _editNickname = MutableLiveData<Boolean?>(true)
    val editNickname: LiveData<Boolean?>
        get() = _editNickname

    private val _doneButton = MutableLiveData<Boolean?>(null)
    val doneButton: LiveData<Boolean?>
        get() = _doneButton
    private val _nicknameText = MutableLiveData<Boolean?>(null)
    val nicknameText: LiveData<Boolean?>
        get() = _nicknameText

    var testS:String  = "сестраБрат"

    fun onDoneButtonClick() {
        _editNickname.value = false
        _doneButton.value = true
        testS = "ПапаМама"
    }
    fun onDoneButtonClicked() {
        _doneButton.value = null
    }

    fun onNicknameTextClick() {
        _editNickname.value = true
        _nicknameText.value = true
    }
    fun onNicknameTextClicked() {
        _nicknameText.value = null
    }

}
