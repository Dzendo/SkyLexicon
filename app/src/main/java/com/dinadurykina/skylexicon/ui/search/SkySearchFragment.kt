package com.dinadurykina.skylexicon.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.dinadurykina.skylexicon.databinding.FragmentSkySearchBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SkySearchFragment : Fragment() {
    private lateinit var thiscontext: Context
    private val args: SkySearchFragmentArgs by navArgs()
    lateinit var binding: FragmentSkySearchBinding
    lateinit var viewModel: SkySearchViewModel

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
         viewModel = ViewModelProvider(
             this,
             SkySearchViewModelFactory(args.slovo)
         ).get(SkySearchViewModel::class.java)

        // Inflate the layout for this fragment
        binding = FragmentSkySearchBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = viewLifecycleOwner
        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        binding.slovo.setText(args.slovo)
        viewModel.searchSlovo(args.slovo)



        /*binding.ids.setOnClickListener {
            val id = viewModel.word.value?.meanings?.get(0)?.id?:"1938"
            findNavController().navigate(
                SkySearchFragmentDirections.actionSkySearchFragmentToSkyMeaningsFragment(id)
            )
        }*/

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SkySearchAdapter()
        binding.recyclerViewSky.adapter = adapter

        viewModel.wordsListRecycler.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }
    }
}