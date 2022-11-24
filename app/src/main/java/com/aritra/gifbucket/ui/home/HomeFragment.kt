package com.aritra.gifbucket.ui.home

import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aritra.gifbucket.R
import com.aritra.gifbucket.data.models.GifUIModel
import com.aritra.gifbucket.data.remote.dtos.GifSearchResponse
import com.aritra.gifbucket.data.utils.Resource
import com.aritra.gifbucket.data.utils.Status
import com.aritra.gifbucket.databinding.FragmentHomeBinding
import com.aritra.gifbucket.presentation.adapters.GifAdapter
import com.aritra.gifbucket.presentation.viewmodels.vm.GifSearchViewModel
import com.aritra.gifbucket.ui.utils.observeDebounceCheck
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private val viewModel by activityViewModels<GifSearchViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private lateinit var navBtn: Button // binding gets recreated on onviwcreated so views cant be val as they has to be reassigned on viewcreated
    private lateinit var gifRecyclerView:RecyclerView
    private lateinit var gifAdapter:GifAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        navBtn = binding.homeNavBtn
        gifRecyclerView = binding.gifRv
        gifAdapter = GifAdapter()

        prepareRecyclerView()
        setClickListener()



        return root
    }

    private fun setClickListener() {
        navBtn.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_gallery)
        }
    }

    private fun prepareRecyclerView() {

        gifRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = gifAdapter
        }
    }

    override fun onResume() {
        super.onResume()


        binding.gifEt.observeDebounceCheck()
            .filterNot { it.isNullOrBlank() }
            .debounce(600)
            .flatMapLatest {startGifSearch(it.toString()) }
            .onEach { updateUIOnGifSearch(it) }
            .launchIn(lifecycleScope)


//        lifecycleScope.launch{
//            viewModel.getGifSearchResult("beach").observe(viewLifecycleOwner){
//                when(it.status){
//                    Status.SUCCESS ->{
//                        Toast.makeText(requireActivity(),"successfully loaded ${it.data?.gifData?.size} number of data",
//                            Toast.LENGTH_SHORT).show()
//                        var listOfGifs:List<GifUIModel>?  = it.data?.gifData?.map {
//                            it->
//                            GifUIModel(it.images.downsized_large.url,it.title)
//                        }
//                        listOfGifs?.let { submitListToRV(it)}
//                        lifecycleScope.launch(Dispatchers.IO){
//                            val numberOfGifs = gifDao.getAllGifs().size.toString()
//                            withContext(Dispatchers.Main){
//                                Toast.makeText(this@ContainerActivity,"numbers in db  $numberOfGifs",
//                                    Toast.LENGTH_SHORT).show()
//                            }
//                        }
//
//                    }
//                    Status.ERROR ->{
//                        Toast.makeText(requireActivity(),"ERROR! ${it.message}", Toast.LENGTH_SHORT).show()
//                    }
//                    Status.LOADING ->{
//                        Toast.makeText(requireActivity(),"loading.....", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
    }

    private fun startGifSearch(it: String): Flow<Resource<GifSearchResponse>> {
        return viewModel.getGifSearchResultFlow(it)
    }

    private fun submitListToRV(it: List<GifUIModel>) {
        gifAdapter.submitList(it.toMutableList())
    }

    private fun updateUIOnGifSearch(it: Resource<GifSearchResponse>) {
        when (it.status) {
            Status.SUCCESS -> {
                Toast.makeText(requireActivity(),
                    "successfully loaded ${it.data?.gifData?.size} number of data",
                    Toast.LENGTH_SHORT).show()
                binding.gifSearchProgress.visibility = View.GONE
                var listOfGifs: List<GifUIModel>? = it.data?.gifData?.map { it ->
                    GifUIModel(it.images.downsized_large.url, it.title)
                }
                listOfGifs?.let { submitListToRV(it) }
            }
            Status.ERROR -> {
                Toast.makeText(requireActivity(), "ERROR! ${it.message}", Toast.LENGTH_SHORT)
                    .show()
                binding.gifSearchProgress.visibility = View.GONE

            }
            Status.LOADING -> {
                binding.gifSearchProgress.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}