package com.dinadurykina.skylexicon.ui.about

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dinadurykina.skylexicon.ui.Event

class SkyAboutViewModel(application: Application) : AndroidViewModel(application) {

    val myName: MyName = MyName("My Dzen DO Binding","Dinosaur")

    /**
     *
     */
    private val _starClicked = MutableLiveData<Event<String?>>()
    val starClicked: LiveData<Event<String?>>
        get() = _starClicked

    private val _keyBoard =  MutableLiveData<Event<Boolean?>>(Event(true))
    val keyBoard: LiveData<Event<Boolean?>>
        get() = _keyBoard

    fun onDoneButtonClick() {
        _keyBoard.value = Event(false)
    }

    fun onNicknameTextClick() {
        _keyBoard.value = Event(true)
    }

    fun onStarClick() {
        _starClicked.value = Event("on Star Clicked")
    }

}
/*
  private val _editNickname = MutableLiveData<Boolean?>(true)
    val editNickname: LiveData<Boolean?>
        get() = _editNickname
    private val _doneButton = MutableLiveData<Boolean?>(null)
    val doneButton: LiveData<Boolean?>
        get() = _doneButton
    private val _nicknameText = MutableLiveData<Boolean?>(null)
    val nicknameText: LiveData<Boolean?>
        get() = _nicknameText

    fun onDoneButtonClick() {
        _editNickname.value = false
        _doneButton.value = true
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
 */