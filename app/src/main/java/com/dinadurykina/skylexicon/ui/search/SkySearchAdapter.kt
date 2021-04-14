package com.dinadurykina.skylexicon.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dinadurykina.skylexicon.R

/** An adapter
 * The adapter connects your data to the RecyclerView.
 * It adapts the data so that it can be displayed in a ViewHolder.
 * A RecyclerView uses the adapter to figure out how to display the data on the screen.
 * Адаптер подключает ваши данные к RecyclerView.
 * Он адаптирует данные так, чтобы их можно было отображать в формате ViewHolder.
 * A RecyclerView использует адаптер, чтобы выяснить, как отображать данные на экране.
 */

class SkySearchAdapter(private val dataSet: MutableList<String?>) :
    RecyclerView.Adapter<SkySearchAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     * The view holder extends the ViewHolder class.
     * It contains the view information for displaying one item from the item's layout.
     * View holders also add information that RecyclerView uses to efficiently move views around the screen
     * Он содержит информацию о просмотре для отображения одного элемента из макета элемента.
     * Держатели представлений также добавляют информацию, которая RecyclerView используется
     * для эффективного перемещения представлений по экрану.
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            // Определите прослушиватель щелчков для представления Viewholder.
            textView = itemView.findViewById(R.id.textView)
        }
    }

    // Create new views (invoked by the layout manager)
    // вызывается, когда RecyclerView требуется держатель представления
    // Эта функция принимает два параметра и возвращает ViewHolder.
    // parent Параметр, который является вид группы , которая удерживает держатель вида, всегда RecyclerView.
    // viewType Параметр используется , когда есть несколько представлений в том же самом RecyclerView.
    // Например, если вы поместите список текстовых представлений, изображение и видео
    // в одно и то же RecyclerView, onCreateViewHolder()функция должна будет знать,
    // какой тип представления использовать.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val view = layoutInflater
            .inflate(R.layout.text_row_item_sky_search, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    // Замените содержимое представления (вызывается менеджером компоновки)
    // Функция вызывается RecyclerView для отображения данных для одного элемента списка в заданном положении.
    // Таким образом, onBindViewHolder()метод принимает два аргумента:
    // держатель представления и позицию данных для привязки.
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        // Получить элемент из вашего набора данных в этой позиции и заменить
        // содержимое представления этим элементом
        viewHolder.textView.text = dataSet[position]
        // здесь же устанавливаются значения других полей и рисунков
    }

    // Return the size of your dataset (invoked by the layout manager)
    // Возвращает размер вашего набора данных (вызывается менеджером компоновки)
    override fun getItemCount() = dataSet.size

}
