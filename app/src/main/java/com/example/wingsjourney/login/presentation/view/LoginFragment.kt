package com.example.wingsjourney.login.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.wingsjourney.R
import com.example.wingsjourney.databinding.FragmentLoginBinding
import com.example.wingsjourney.login.presentation.viewmodel.LoginViewModel
import com.example.wingsjourney.usecase.base.ResultStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLoginBinding.inflate(
        inflater,
        container,
        false
    ).apply{
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservations()
        setClickListeners()
    }

    private fun setupObservations(){
        viewModel.loginResult.observe(viewLifecycleOwner){
            when(it){
                ResultStatus.Loading -> {

                }
                is ResultStatus.Success -> {
                    if(it.data.token.isNotEmpty()){
                        findNavController().navigate(R.id.games_list)
                    } else {
                        Toast.makeText(context, "CREDENCIAIS ERRADAS", Toast.LENGTH_SHORT).show()
                    }
                }
                is ResultStatus.Error -> {
                    Toast.makeText(context, "ERRO: "+it.throwable, Toast.LENGTH_SHORT).show()
                    Log.d("AAAAAA", it.throwable.message.toString())
                }
            }
        }
    }

    private fun setClickListeners(){
        binding.btnLogin.setOnClickListener{
            viewModel.performLogin(
                binding.etUser.text.toString(),
                binding.etPassword.text.toString()
            )
        }
    }
}