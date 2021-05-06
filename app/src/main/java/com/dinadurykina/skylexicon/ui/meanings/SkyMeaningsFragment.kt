package com.dinadurykina.skylexicon.ui.meanings

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.dinadurykina.skylexicon.databinding.FragmentSkyMeaningsBinding
import com.dinadurykina.skylexicon.ui.playSound

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SkyMeaningsFragment : Fragment() {
    private lateinit var thisContext: Context
    private val args: SkyMeaningsFragmentArgs by navArgs()
    private lateinit var binding: FragmentSkyMeaningsBinding
     val skyMeaningsViewModel: SkyMeaningsViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        container?.let{ thisContext = it.context }

        // Inflate the layout for this fragment
        binding = FragmentSkyMeaningsBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        // Giving the binding access to the OverviewViewModel
        binding.viewModel = skyMeaningsViewModel
        binding.meaning = skyMeaningsViewModel.meaning.value

       // binding.ids.text = args.id
        skyMeaningsViewModel.ids = args.id
        skyMeaningsViewModel.meaningsIds(args.id)

        //<!--Вариант SkySearchListener-->
        /*val skySearchAdapter = SkySearchAdapter(SkySearchListener { id ->
            Toast.makeText(thisContext, id, Toast.LENGTH_LONG).show()
            skySearchViewModel.onSkySearchClicked(id)
        })*/

        //<!--Вариант SkySearchViewModel-->
        binding.skyImage.adapter = SkyMeaningImageAdapter(skyMeaningsViewModel)
        // обновление списка skySearchAdapter.submitList(it) вынесено
        // в fragment_sky_search.xml через BindingAdapters.kt

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val examplesAdapter = ArrayAdapter<String?>(thisContext, android.R.layout.simple_list_item_1, skyMeaningsViewModel.examples)
        binding.examples.adapter = examplesAdapter

        val meaningsWithSimilarTranslationAdapter = ArrayAdapter<String?>(thisContext, android.R.layout.simple_list_item_1, skyMeaningsViewModel.meaningsWithSimilarTranslation)
        binding.meaningsWithSimilarTranslation.adapter = meaningsWithSimilarTranslationAdapter

        val alternativeTranslationsAdapter = ArrayAdapter<String?>(thisContext, android.R.layout.simple_list_item_1, skyMeaningsViewModel.alternativeTranslations)
        binding.alternativeTranslations.adapter = alternativeTranslationsAdapter

        skyMeaningsViewModel.refresh.observe(viewLifecycleOwner) {
            if (it == true) { // Observed state is true. Наблюдаемое состояние истинно.

                examplesAdapter.notifyDataSetChanged()
                meaningsWithSimilarTranslationAdapter.notifyDataSetChanged()
                alternativeTranslationsAdapter.notifyDataSetChanged()
                skyMeaningsViewModel.refreshNull()
            }
        }
        binding.alternativeTranslations.setOnItemClickListener { parent, itemClicked, position, id ->
            val engSlovo = skyMeaningsViewModel.meanings.value?.get(0)?.alternativeTranslations?.get(position)?.text ?: "NoNoNo"
            val rusSlovo = skyMeaningsViewModel.meanings.value?.get(0)?.alternativeTranslations?.get(position)?.translation?.text ?: "Нет"
            Toast.makeText(
                thisContext,
                "Вы нажали $engSlovo -->  $rusSlovo",
                Toast.LENGTH_SHORT
            ).show()
            //val soundUri = viewModel.meanings.value?.get(0)?.alternativeTranslations?.get(position)?.
         //   findNavController().navigate(
         //       SkyMeaningsFragmentDirections.actionSkyMeaningsFragmentToSkySearchFragment(slovo))
        }
        binding.meaningsWithSimilarTranslation.setOnItemClickListener { _, itemClicked, position, id ->
            val ids = skyMeaningsViewModel.meanings.value?.get(0)?.meaningsWithSimilarTranslation?.get(position)?.meaningId.toString()
            binding.ids.text = ids
            skyMeaningsViewModel.meaningsIds(ids)
            skyMeaningsViewModel.ids = ids
            Toast.makeText(
                thisContext,
                "Переход с ${(itemClicked as TextView).text} на $ids",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.examples.setOnItemClickListener { _, itemClicked, position, id ->
            val soundUri = skyMeaningsViewModel.meanings.value?.get(0)?.examples?.get(position)?.soundUrl
            playSound(soundUri)
            Toast.makeText(
                thisContext,
                soundUri,
                Toast.LENGTH_SHORT
            ).show()
        }


        skyMeaningsViewModel.listenSound.observe(viewLifecycleOwner) { soundUri ->
            soundUri?.let { sounduri ->
                val toast = Toast.makeText(thisContext, "[\b ${skyMeaningsViewModel.meanings.value?.get(0)?.transcription} ]", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                playSound(sounduri)
                skyMeaningsViewModel.onSkySoundNavigated()
            }
        }
    }
}