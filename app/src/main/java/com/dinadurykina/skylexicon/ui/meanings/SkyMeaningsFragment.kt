package com.dinadurykina.skylexicon.ui.meanings

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dinadurykina.skylexicon.databinding.FragmentSkyMeaningsBinding
import com.dinadurykina.skylexicon.launcher.SkyActivity
import com.dinadurykina.skylexicon.launcher.SkyConstants

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
        container?.let { thisContext = it.context }

        // Inflate the layout for this fragment
        binding = FragmentSkyMeaningsBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        // Giving the binding access to the OverviewViewModel
        binding.viewModel = skyMeaningsViewModel

        skyMeaningsViewModel.ids.observe(viewLifecycleOwner) {skyMeaningsViewModel.meaningsIds(it)}

        skyMeaningsViewModel.ids.value= args.id
        // сделал вренменно
        // пока не знаю как поместить поле для ввода слова в ActionBar
        // а достать его из фрагмента
        // Удалить
        SkyConstants.ids.observe(viewLifecycleOwner) {skyMeaningsViewModel.meaningsIds(it)}
        SkyConstants.ids.value= args.id
        binding.constants = SkyConstants

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.skyImage.adapter = SkyMeaningImageAdapter(skyMeaningsViewModel)

        binding.recyclerMeaningSky.adapter = SkyMeaningAdapter(skyMeaningsViewModel)


        skyMeaningsViewModel.navigateToSkySearch.observe(viewLifecycleOwner){
            it?.let{ slovo ->
                // сделал вренменно
                // пока не знаю как поместить поле для ввода слова в ActionBar
                // а достать его из фрагмента
                SkyConstants.slovo.value = slovo
            this.findNavController().navigate(
                SkyMeaningsFragmentDirections.actionSkyMeaningsFragmentToSkySearchFragment(slovo)
            )
            skyMeaningsViewModel.onSkySearchNavigated()
            }
        }
        // сделал вренменно
        // пока не знаю как поместить поле для ввода слова в ActionBar
        // а достать его из фрагмента
        SkyConstants.slovobinding.setVisibility(View.GONE)
    }
}