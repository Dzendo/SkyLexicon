package com.dinadurykina.skylexicon.ui.about

class SkyTimer (
    val timerWord:MutableList<SeansWord> = arrayListOf(),
    val timerNeaning:MutableList<SeansNeaning> = arrayListOf(),
    val timerFag:MutableList<SeansFag> = arrayListOf(),
) {
    // Номер числовой типа какой тфймер сейчас тикает
    companion object {
        enum class TIMER{
            TIMER_FAG,
            TIMER_WORD,
            TIMER_MEANING
        }
    }
    var nowTimer:Int = -1
    var nowStart:Long = -1
    var nowEnd:Long = -1

    fun clear():Boolean{
        timerWord.clear()
        timerNeaning.clear()
        timerFag.clear()
        return true
    }
    fun start(timerN:Int): Int{
        return -1
    }
    fun stop(timerN:Int): Int{
        return -1
    }
    fun pause(timerN:Int):Boolean = true
    fun run(timerN:Int):Boolean = true
}
//data class Seans(val dataStart:Long, val dataStop:Long, val duration:Long)
data class SeansFag(val dataStart:Long, val dataStop:Long, val duration:Long, val what:String)
data class SeansWord(val dataStart:Long, val dataStop:Long, val duration:Long, val slovo:String)
data class SeansNeaning(val dataStart:Long, val dataStop:Long, val duration:Long, val ids:String)


/*
sealed class DataItem {
    data class ExampleItem(val example: Example=Example()
    ) : DataItem()
    data class MeaningWithSimilarTranslationItem(
        val meaningWithSimilarTranslation: MeaningsWithSimilarTranslation= MeaningsWithSimilarTranslation()
    ) : DataItem()
    data class AlternativeTranslationsItem(
        val alternativeTranslations: AlternativeTranslations= AlternativeTranslations()
    ) : DataItem()
    enum class VIEW_TYPE {
        ITEM_VIEW_TYPE_EXAMPLE,
        ITEM_VIEW_TYPE_SIMILAR,
        ITEM_VIEW_TYPE_ALTERNATIVE
    }
}
 */