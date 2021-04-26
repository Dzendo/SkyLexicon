package com.dinadurykina.skylexicon.ui.search

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dinadurykina.skylexicon.databinding.FragmentSkySearchBinding
import com.dinadurykina.skylexicon.launcher.SkyActivity
import com.dinadurykina.skylexicon.ui.share.DialogImageFragment
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SkySearchFragment : Fragment() {
    private lateinit var thisContext: Context
    private val args: SkySearchFragmentArgs by navArgs()
    lateinit var binding: FragmentSkySearchBinding
    lateinit var skySearchViewModel: SkySearchViewModel
    private var mediaPlayer: MediaPlayer? = null

    /**
     * Lazily initialize our [SkySearchViewModel].
     */
  //  private val viewModel: SkySearchViewModel by lazy {
  //      ViewModelProvider(this).get(SkySearchViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (container != null) thisContext = container.context
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

        //<!--Вариант SkySearchListener-->
        /*val skySearchAdapter = SkySearchAdapter(SkySearchListener { id ->
            Toast.makeText(thisContext, id, Toast.LENGTH_LONG).show()
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

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        skySearchViewModel.navigateToSkyMeanings.observe(viewLifecycleOwner){ id ->
            id?.let {
                this.findNavController().navigate(
                    SkySearchFragmentDirections.actionSkySearchFragmentToSkyMeaningsFragment(id)
                )
                skySearchViewModel.onSkyMeaningsNavigated()
            }
        }
        skySearchViewModel.showImage.observe(viewLifecycleOwner){ imageUri ->
            imageUri?.let { uri ->
                this.findNavController().navigate(
                    SkySearchFragmentDirections.actionSkySearchFragmentToDialogImageFragment(uri)
                )
            }
        }

        skySearchViewModel.listenSound.observe(viewLifecycleOwner) { soundUrl ->
            soundUrl?.let {
                val toast = Toast.makeText(thisContext, "Sound: $it", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()

                // работает Виноградов
                mediaPlayer?.stop()
                mediaPlayer?.release()
                //val http = "https://firebasestorage.googleapis.com/v0/b/nmixer-97a91.appspot.com/o/musics%2FDark%20World.mp3?alt=media&token=f8564ca6-cf59-468d-bd98-13ff646a1752"
                //val http = "https://d2fmfepycn0xw0.cloudfront.net/?gender=male&accent=british&text=chair"
                val http = "https://$soundUrl"
                mediaPlayer = MediaPlayer()
                mediaPlayer?.setDataSource(http)
                // необязательно:
                mediaPlayer?.setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                mediaPlayer?.prepare()
                mediaPlayer?.start()

                skySearchViewModel.onSkySoundNavigated()
            }
        }
    }
}