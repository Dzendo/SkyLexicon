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

        binding.slovo.setText(skySearchViewModel.slovo)
        // skySearchViewModel.searchSlovo(args.slovo)

        //<!--Вариант SkySearchListener-->
        /*val skySearchAdapter = SkySearchAdapter(SkySearchListener { id ->
            Toast.makeText(thisContext, id, Toast.LENGTH_LONG).show()
            skySearchViewModel.onSkySearchClicked(id)
        })*/

        //<!--Вариант SkySearchViewModel-->
        binding.recyclerViewSky.adapter = SkySearchAdapter(skySearchViewModel)
        // обновление списка skySearchAdapter.submitList(it) вынесено
        // в fragment_sky_search.xml через BindingAdapters.kt

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        skySearchViewModel.navigateToSkyMeanings.observe(viewLifecycleOwner) { id ->
            id?.let {
                this.findNavController().navigate(
                    SkySearchFragmentDirections.actionSkySearchFragmentToSkyMeaningsFragment(id)
                )
                skySearchViewModel.onSkyMeaningsNavigated()
            }
        }
        skySearchViewModel.showImage.observe(viewLifecycleOwner) { imageUri ->
            imageUri?.let { uri ->
                this.findNavController().navigate(
                    SkySearchFragmentDirections.actionSkySearchFragmentToDialogImageFragment(uri)
                )
                skySearchViewModel.onShowImageNavigated()
            }
        }

        skySearchViewModel.listenSound.observe(viewLifecycleOwner) { soundUri ->
            soundUri?.let { sounduri ->
                val toast = Toast.makeText(thisContext, "Sound: $sounduri", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                playSound(sounduri)
                skySearchViewModel.onSkySoundNavigated()
            }
        }
    }
}
