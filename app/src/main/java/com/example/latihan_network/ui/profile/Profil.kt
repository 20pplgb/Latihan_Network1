package com.example.latihan_network.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.latihan_network.R
import com.example.latihan_network.databinding.FragmentProfilBinding
import com.example.latihan_network.model.TweetRequest


class Profil : Fragment() {

    private lateinit var binding : FragmentProfilBinding
    private lateinit var viewModel : ProfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profil , container , false)
        viewModel = ViewModelProvider(this).get(ProfilViewModel::class.java)

        binding.btnCreate.setOnClickListener{

            val author = binding.edtAuthor.text.toString()
            val tweetTeks = binding.edtAuthor.text.toString()


            if(author.isNotEmpty()   && tweetTeks.isNotEmpty())
            {
                var request = TweetRequest()
                request.teks = tweetTeks
                request.author = author



                viewModel.post(request)

                binding.edtWriteTweet.clearFocus()
                binding.edtAuthor.clearFocus()

                viewModel.response.observe(viewLifecycleOwner){
                    response ->
                    Toast.makeText(requireContext(), response , Toast.LENGTH_SHORT).show()

                    viewModel.response.removeObservers(viewLifecycleOwner)
                }
            }else{
                Toast.makeText(requireContext(), "Data Tidak Lengkap", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

}