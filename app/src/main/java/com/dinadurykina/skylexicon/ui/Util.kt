package com.dinadurykina.skylexicon.ui

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.core.net.toUri

//class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)
// Существует как простейшая заглушка для первого вызова
// RecyclerView без него не может просто подставить textView
// ViewHolder дает что именно где и как надо отображать RecyclerView
// еще через его методы доступ ко всей информации о элементе отображения
// номер, позиция, итд

//val http = "https://firebasestorage.googleapis.com/v0/b/nmixer-97a91.appspot.com/o/musics%2FDark%20World.mp3?alt=media&token=f8564ca6-cf59-468d-bd98-13ff646a1752"
//val http = "https://d2fmfepycn0xw0.cloudfront.net/?gender=male&accent=british&text=chair"
fun playSound(soundUrl: String?) {
    val mediaPlayer = MediaPlayer()

    val http = "https:$soundUrl"
    mediaPlayer.setDataSource(http)

    //val soundUri = soundUrl?.toUri().buildUpon().scheme("https").build()
    //mediaPlayer.setDataSource(applicationContext,soundUri)

    // необязательно:
    mediaPlayer.setAudioAttributes(
        AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()
    )
    mediaPlayer.prepare()
    mediaPlayer.start()
}
