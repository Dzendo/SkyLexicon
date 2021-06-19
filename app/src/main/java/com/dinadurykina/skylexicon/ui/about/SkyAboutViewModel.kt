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
    private val _keyBoard =  MutableLiveData<Event<Boolean?>>(Event(true))
    val keyBoard: LiveData<Event<Boolean?>>
        get() = _keyBoard

    private val _starClicked = MutableLiveData<Event<String?>>()
    val starClicked: LiveData<Event<String?>>
        get() = _starClicked

    fun onStarClick() {
        _starClicked.value = Event("on Star ONCE Clicked")
    }

    fun onDoneButtonClick() {
        _keyBoard.value = Event(false)
    }

    fun onNicknameTextClick() {
        _keyBoard.value = Event(true)
    }



}
