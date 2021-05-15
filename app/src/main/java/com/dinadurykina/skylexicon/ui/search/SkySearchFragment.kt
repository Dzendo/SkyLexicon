package com.dinadurykina.skylexicon.ui.search

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dinadurykina.skylexicon.databinding.FragmentSkySearchBinding
import com.dinadurykina.skylexicon.launcher.SkyConstants


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SkySearchFragment : Fragment() {

    private val args: SkySearchFragmentArgs by navArgs()
    private lateinit var binding: FragmentSkySearchBinding
    /**
     * Lazily initialize our [SkySearchViewModel].
     */
    private val skySearchViewModel: SkySearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentSkySearchBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = viewLifecycleOwner
        // Giving the binding access to the OverviewViewModel
        binding.viewModel = skySearchViewModel

        binding.recyclerViewSky.adapter = skySearchViewModel.skySearchAdapter

        val nStolbov =when (resources.configuration.orientation){
            Configuration.ORIENTATION_PORTRAIT ->  2
            Configuration.ORIENTATION_LANDSCAPE ->  3
            else ->  5
        }
        val staggeredGridLayoutManager  =
            StaggeredGridLayoutManager(nStolbov,StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerViewSky.layoutManager = staggeredGridLayoutManager

        SkyConstants.slovobinding.setVisibility(View.VISIBLE)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SkyConstants.slovo.observe(viewLifecycleOwner) {skySearchViewModel.searchSlovo(it)}
        //skySearchViewModel.slovo.observe(viewLifecycleOwner) {skySearchViewModel.searchSlovo(it)}
        SkyConstants.slovo.value = args.slovo

        // событие нажатия на картинку -> показ большой картинки в окошке alert
        skySearchViewModel.showImage.observe(viewLifecycleOwner) { imageUri ->
            imageUri?.let { uri ->
                this.findNavController().navigate(
                    SkySearchFragmentDirections.actionSkySearchFragmentToDialogImageFragment(uri)
                )
                skySearchViewModel.onShowImageNavigated()
            }
        }

        // событие нажатия на item -> переход к второму экрану meanings
        skySearchViewModel.navigateToSkyMeanings.observe(viewLifecycleOwner) { id ->
            id?.let {
                this.findNavController().navigate(
                    SkySearchFragmentDirections.actionSkySearchFragmentToSkyMeaningsFragment(id)
                )
                skySearchViewModel.onSkyMeaningsNavigated()
            }
        }
    }
}
