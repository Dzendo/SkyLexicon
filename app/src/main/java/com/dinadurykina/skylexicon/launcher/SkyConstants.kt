package com.dinadurykina.skylexicon.launcher

import android.widget.EditText
import androidx.lifecycle.MutableLiveData

//  Do not place Android context classes in static fields
//  (static reference to SkyConstants which has field slovobinding pointing to EditText);
//  this is a memory leak
// Не помещайте классы контекста Android в статические поля
// (статическая ссылка на SkyConstants, у которой есть привязка поля, указывающая на EditText);
// это утечка памяти
// т.е. Не соххранять ВНЕ или в Static поле ссфлку на View - она при разрушении макета мин потеряется
// сделал вренменно пока не знаю как поместить поле для ввода слова в ActionBar а достать его из фрагмента
object SkyConstants{
    //public val Slovo: String = "Table"
    //public val Ids:String = "132398"
    public val slovo: MutableLiveData<String> = MutableLiveData<String>("Face")
    public val ids: MutableLiveData<String> = MutableLiveData<String>("132398")
    public lateinit var slovobinding: EditText
}
// TODO
// 0кк. Запомить ссылку на поле в константах статик и достать его из фрагментов
// 1к. разместить поле ЗА - НАД границей Фрагмента (КАК?)
// 2. Добавить поле для ввода слова в меню три точки из фрагмента (Как добавить Edit?)
// 3. Строить action bar со словом в Фрагменте и другой action bar в Втором фрагменте
// 4к. Перестраивать ВСЮ архитектуру приложения


// https://classroom.udacity.com/courses/ud9012/lessons/2be0ed85-721d-4a8d-a484-909b5c98336c/concepts/b36af08e-1bce-48e8-9a31-2e268907d2f0
// https://developer.android.com/codelabs/kotlin-android-training-internet-data?index=..%2F..android-kotlin-fundamentals#3
//  https://dictionary.skyeng.ru/doc/api/external

// https://metanit.com/kotlin/tutorial/4.13.php
// Файл для описания констант и временных переменных вместо setting
// String representation of a part of speech
// Строковое представление части речи
// TODO пока не задействовано
enum class PartOfSpeech(val partOfSpeechEng: String = " ", val partOfSpeechRus: String = " "){
    n ("noun","существительное"),
    v ("verb","глагол"),
    j ("adjective","прилагательное"),
    r ("adverb","наречие"),
    prp ("preposition","предлог"),
    prn ("pronoun","местоимение"),
    crd ("cardinal number","кардинальное число"),
    cjc ("conjunction","связи"),
    exc ("interjection","междометие"),
    det ("article","статьи"),
    abb ("abbreviation","аббревиатура"),
    x ("particle","частица"),
    ord ("ordinal number","порядковый номер"),
    md ("modal verb","модальный глагол"),
    ph ("phrase","фразы"),
    phi ("idiom","идиома");

}

// Создайте область сопрограммы для использования в вашем приложении чтобы не блокировать экраны
// идея создания такой области сопрограмм что она будет работать до остановки app не обращая внимания на экраны
// пока не используется ( кстати можно создать подобную в application)
//val APPlicationScope = CoroutineScope(Dispatchers.Default)

