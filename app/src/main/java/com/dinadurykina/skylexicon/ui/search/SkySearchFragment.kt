package com.dinadurykina.skylexicon.ui.search

import android.content.Context
import android.net.Uri.parse
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dinadurykina.skylexicon.R
import com.dinadurykina.skylexicon.databinding.FragmentSkySearchBinding
import com.squareup.picasso.Picasso
import java.net.URI
import java.net.URI.*



/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SkySearchFragment : Fragment() {
    private lateinit var thiscontext: Context
    private val args: SkySearchFragmentArgs by navArgs()
    lateinit var binding: FragmentSkySearchBinding
    lateinit var skySearchViewModel: SkySearchViewModel

    /**
     * Lazily initialize our [SkySearchViewMode].
     */
  //  private val viewModel: SkySearchViewModel by lazy {
  //      ViewModelProvider(this).get(SkySearchViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (container != null) thiscontext = container.context
        skySearchViewModel = ViewModelProvider(
             this,
             SkySearchViewModelFactory(args.slovo)
         ).get(SkySearchViewModel::class.java)

        // Inflate the layout for this fragment
        binding = FragmentSkySearchBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = viewLifecycleOwner
        // Giving the binding access to the OverviewViewModel
        binding.viewModel = skySearchViewModel

        binding.slovo.setText(args.slovo)
        skySearchViewModel.searchSlovo(args.slovo)

        skySearchViewModel.navigateToSkyMeanings.observe(viewLifecycleOwner){ id ->
            id?.let {
                this.findNavController().navigate(
                    SkySearchFragmentDirections.actionSkySearchFragmentToSkyMeaningsFragment(id)
                )
                skySearchViewModel.onSkyMeaningsNavigated()
            }
        }

        skySearchViewModel.showImage.observe(viewLifecycleOwner){ imageUrl ->
            imageUrl?.let {
                val toast = Toast.makeText(thiscontext,"Image: $it", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)

               /* val toastContainer = toast.view as LinearLayout
                val imageView = ImageView(thiscontext)
                Picasso.with(thiscontext).load(it).into(imageView)
                //imageView.setImageURI(URI.parse(it))
                toastContainer.addView(imageView, 0)
                */
                toast.show()

                skySearchViewModel.onSkyImageNavigated()


            }
        }

        skySearchViewModel.listenSound.observe(viewLifecycleOwner){ soundUrl ->
            soundUrl?.let {
                val toast = Toast.makeText(thiscontext,"Sound: $it", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)

                /* val toastContainer = toast.view as LinearLayout
                 val imageView = ImageView(thiscontext)
                 Picasso.with(thiscontext).load(it).into(imageView)
                 //imageView.setImageURI(URI.parse(it))
                 toastContainer.addView(imageView, 0)
                 */
                toast.show()

                skySearchViewModel.onSkySoundNavigated()


            }
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //<!--Вариант SkySearchListener-->
        /*val skySearchAdapter = SkySearchAdapter(SkySearchListener { id ->
            Toast.makeText(context, id, Toast.LENGTH_LONG).show()
            skySearchViewModel.onSkySearchClicked(id)
        })*/

        //<!--Вариант SkySearchViewModel-->
        val skySearchAdapter = SkySearchAdapter(skySearchViewModel)
        binding.recyclerViewSky.adapter = skySearchAdapter

        skySearchViewModel.wordsListRecycler.observe(viewLifecycleOwner) {
            it?.let {
                skySearchAdapter.submitList(it)
            }
        }
    }
}