package com.example.sabbir.pixabaysearch.ui

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sabbir.pixabaysearch.R
import com.example.sabbir.pixabaysearch.databinding.FragmentFirstBinding
import com.example.sabbir.pixabaysearch.models.ImageHit
import com.example.sabbir.pixabaysearch.utils.EventObserver
import com.example.sabbir.pixabaysearch.utils.isInternetAvailable
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.NonCancellable.cancel
import javax.inject.Inject

/**
 * Created by himelhimu on ${DATE}
 * Md Sabbir Ahmed (Himel)
 * This is a free of charge whatever you want to do with it code,
 * However the individul librays used in here might haver their own licensing.
 */
class ImageListFragment : DaggerFragment() {

    private var _binding: FragmentFirstBinding? = null

    @Inject lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MainViewModel> { viewModelFactory }
    private lateinit var  adapter : ImageListAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            val query = binding.editQuery.text.toString()
            if (query.isEmpty()){
                binding.editQuery.error = "Provide input"
                Toast.makeText(requireContext(),"Please provide some input",Toast.LENGTH_SHORT).show()
            } else{
                if (isInternetAvailable(requireContext())){
                    mainViewModel.searchForImages(query)
                }else Toast.makeText(requireContext(),"Internet not available",Toast.LENGTH_SHORT).show()
            }
        }

        mainViewModel.dataLoading.observe(viewLifecycleOwner,{
            if (it){
                binding.progress.visibility = View.VISIBLE
            }else binding.progress.visibility = View.GONE
        })

        mainViewModel.openTaskEvent.observe(viewLifecycleOwner,EventObserver{ imageHit->

            val alertDialog: AlertDialog? = activity?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton(
                        R.string.ok,
                        DialogInterface.OnClickListener { dialog, id ->
                            openImageHitDetails(imageHit)
                        })
                    setNegativeButton(R.string.cancel,
                        DialogInterface.OnClickListener { dialog, id ->
                            dialog.dismiss()
                        })
                    setTitle("Open Details")
                    setMessage("Do you want to see the details of the Image?")
                }

                // Set other dialog properties

                // Create the AlertDialog
                builder.create()
            }

            alertDialog?.show()


        })

        mainViewModel.items.observe(viewLifecycleOwner,{
            binding.progress.visibility = View.GONE
            if (it.imageHitList.isNotEmpty()){
                binding.epmtyTxt.visibility = View.GONE
                binding.imageRecylrView.visibility = View.VISIBLE
                setUpAdapter(it.imageHitList)
                val preferences  = context?.getSharedPreferences("pixaby", Context.MODE_PRIVATE)
                if (preferences!=null){
                    mainViewModel.cacheTheLastData(it.imageHitList,binding.editQuery.text.toString(),preferences)
                }

            }else {
                if (it.total==0){
                    binding.imageRecylrView.visibility = View.GONE
                    binding.epmtyTxt.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        if (binding.imageRecylrView.adapter==null && isInternetAvailable(requireContext())){
            mainViewModel.searchForImages("fruits")
        }else if(!isInternetAvailable(requireContext())) Toast.makeText(requireContext(),"Internet not available",Toast.LENGTH_SHORT).show()
    }


    private fun openImageHitDetails(imageHit: ImageHit){
        val action = ImageListFragmentDirections.actionFirstFragmentToSecondFragment(imageHit)
        findNavController().navigate(action)
    }

    private fun setUpAdapter(list: List<ImageHit>){
        adapter = ImageListAdapter(mainViewModel,list)
        binding.imageRecylrView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}