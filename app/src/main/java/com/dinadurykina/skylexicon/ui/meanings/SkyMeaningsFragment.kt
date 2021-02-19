package com.dinadurykina.skylexicon.ui.meanings

import android.R
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dinadurykina.skylexicon.databinding.FragmentSkyMeaningsBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SkyMeaningsFragment : Fragment() {
         lateinit var thiscontext: Context
    private val args: SkyMeaningsFragmentArgs by navArgs()
    lateinit var binding: FragmentSkyMeaningsBinding
    /**
     * Lazily initialize our [SkyMeaningsViewModel].
     */
    private val viewModel: SkyMeaningsViewModel by lazy {
        ViewModelProvider(this).get(SkyMeaningsViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        if (container != null) thiscontext = container.context

        // Inflate the layout for this fragment
        binding = FragmentSkyMeaningsBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel
        binding.meaning = viewModel.meaning.value

        binding.ids.setText(args.id)
        viewModel.onIdsClicked(binding.ids)

        val examplesAdapter = ArrayAdapter<String?>(thiscontext, R.layout.simple_list_item_1, viewModel.examples)
        binding.examples.adapter = examplesAdapter

        val meaningsWithSimilarTranslationAdapter = ArrayAdapter<String?>(thiscontext, R.layout.simple_list_item_1, viewModel.meaningsWithSimilarTranslation)
        binding.meaningsWithSimilarTranslation.adapter = meaningsWithSimilarTranslationAdapter

        val alternativeTranslationsAdapter = ArrayAdapter<String?>(thiscontext, R.layout.simple_list_item_1, viewModel.alternativeTranslations)
        binding.alternativeTranslations.adapter = alternativeTranslationsAdapter

        val imagesAdapter = ArrayAdapter<String?>(thiscontext, R.layout.simple_list_item_1, viewModel.images)
        binding.images.adapter = imagesAdapter

        viewModel.refresh.observe(viewLifecycleOwner) {
            if (it == true) { // Observed state is true. Наблюдаемое состояние истинно.
                // Получив команду перейти мы зовем NavController это то же самое, что NavHostFragment
                // Мы ему говорим пошли, навигируй нас в назад Up
                // Он знает откуда пришел и грузит фрагмент который был до этого, откуда пришли
                // соответственно зовет SkyFragment.kt из указанного каталога
                // и говорит ему ты сюда давай размещайся и отдает ему управление
                examplesAdapter.notifyDataSetChanged()
                meaningsWithSimilarTranslationAdapter.notifyDataSetChanged()
                alternativeTranslationsAdapter.notifyDataSetChanged()
                imagesAdapter.notifyDataSetChanged()
                viewModel.refreshNull()
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.skyImage.setOnClickListener {
            val slovo = viewModel.meaning.value?.text?: "Table"
            findNavController().navigate(
                SkyMeaningsFragmentDirections.actionSkyMeaningsFragmentToSkySearchFragment(slovo))
        }
    }
}