package com.dinadurykina.skylexicon.ui

import android.media.AudioAttributes
import android.media.MediaPlayer

//val http = "https://firebasestorage.googleapis.com/v0/b/nmixer-97a91.appspot.com/o/musics%2FDark%20World.mp3?alt=media&token=f8564ca6-cf59-468d-bd98-13ff646a1752"
//val http = "https://d2fmfepycn0xw0.cloudfront.net/?gender=male&accent=british&text=chair"
fun playSound(soundUrl: String?) {
    val mediaPlayer = MediaPlayer()

    val http = "https:$soundUrl"
    mediaPlayer.setDataSource(http)
    // необязательно:
    mediaPlayer.setAudioAttributes(
        AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()
    )
    mediaPlayer.prepare()
    mediaPlayer.start()
}
