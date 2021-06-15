package com.dinadurykina.skylexicon.ui.about

class SkyTimer (
    val timerWord:MutableList<SeansWord> = arrayListOf(),
    val timerNeaning:MutableList<SeansNeaning> = arrayListOf(),
    val timerFag:MutableList<SeansFag> = arrayListOf(),
) {
    // Номер числовой типа какой тфймер сейчас тикает
    companion object {
        const val TIMER_FAG = 0
        const val TIMER_WORD = 1
        const val TIMER_MEANING = 2
        enum class TIMER{
            TIMER_FAG,
            TIMER_WORD,
            TIMER_MEANING
        }
    }
    var nowTimer:Int = TIMER_FAG
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
data class SeansWord(val dataStart:Long, val dataStop:Long, val duration:Long, val slovo:String)
data class SeansNeaning(val dataStart:Long, val dataStop:Long, val duration:Long, val ids:String)
data class SeansFag(val dataStart:Long, val dataStop:Long, val duration:Long, val what:String)

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
    // Номер числовой типа данных для вывода в RecyclerView
    companion object {
        const val ITEM_VIEW_TYPE_EXAMPLE = 0
        const val ITEM_VIEW_TYPE_SIMILAR = 1
        const val ITEM_VIEW_TYPE_ALTERNATIVE = 2
    }
}
 */