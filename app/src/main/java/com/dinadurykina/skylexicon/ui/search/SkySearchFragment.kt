package com.dinadurykina.skylexicon.ui.search

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dinadurykina.skylexicon.databinding.FragmentSkySearchBinding
import com.dinadurykina.skylexicon.ui.playSound

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SkySearchFragment : Fragment() {
    private lateinit var thisContext: Context
    //private val args: SkySearchFragmentArgs by navArgs()
    private lateinit var binding: FragmentSkySearchBinding
    /**
     * Lazily initialize our [SkySearchViewModel].
     */
    private val skySearchViewModel: SkySearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.let { thisContext = it.context }

        // Inflate the layout for this fragment
        binding = FragmentSkySearchBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = viewLifecycleOwner
        // Giving the binding access to the OverviewViewModel
        binding.viewModel = skySearchViewModel

        // TODO надо поменять на двухсторонний адаптер прямо из viewmodel
        binding.slovo.setText(skySearchViewModel.slovo)
        // skySearchViewModel.searchSlovo(args.slovo)

        //<!--Вариант SkySearchListener-->
        /*val skySearchAdapter = SkySearchAdapter(SkySearchListener { id ->
            Toast.makeText(thisContext, id, Toast.LENGTH_LONG).show()
            skySearchViewModel.onSkySearchClicked(id)
        })*/

        //<!--Вариант SkySearchViewModel-->
        binding.recyclerViewSky.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerViewSky.adapter = SkySearchAdapter(skySearchViewModel)
        // обновление списка skySearchAdapter.submitList(it) вынесено
        // в fragment_sky_search.xml через BindingAdapters.kt

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // событие нажатия на item -> переход к второму экрану meanings
        skySearchViewModel.navigateToSkyMeanings.observe(viewLifecycleOwner) { id ->
            id?.let {
                this.findNavController().navigate(
                    SkySearchFragmentDirections.actionSkySearchFragmentToSkyMeaningsFragment(id)
                )
                skySearchViewModel.onSkyMeaningsNavigated()
            }
        }
        // событие нажатия на картинку -> показ большой картинки в окошке alert
        skySearchViewModel.showImage.observe(viewLifecycleOwner) { imageUri ->
            imageUri?.let { uri ->
                this.findNavController().navigate(
                    SkySearchFragmentDirections.actionSkySearchFragmentToDialogImageFragment(uri)
                )
                skySearchViewModel.onShowImageNavigated()
            }
        }
        // событие нажатия на динамик -> вызывапет говорилку с soundUri
        skySearchViewModel.listenSound.observe(viewLifecycleOwner) { soundUri ->
            soundUri?.let { sounduri ->
                playSound(sounduri)
                skySearchViewModel.onSkySoundNavigated()
            }
        }
    }
}
