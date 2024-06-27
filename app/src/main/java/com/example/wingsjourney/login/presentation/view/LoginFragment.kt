package com.example.wingsjourney.login.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.wingsjourney.R
import com.example.wingsjourney.databinding.FragmentLoginBinding
import com.example.wingsjourney.games.TestActivity
import com.example.wingsjourney.gameslist.presentation.MainActivity
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
                    showProgressBar(true)
                }
                is ResultStatus.Success -> {
                    if(it.data.token.isNotEmpty()){
                        val sharedPref = activity?.getSharedPreferences(
                            getString(R.string.jwt_shared_pref_key),
                            Context.MODE_PRIVATE
                        ) ?: return@observe
                        with(sharedPref.edit()){
                            putString(getString(R.string.jwt_shared_pref_key), "Bearer "+it.data.token)
                            apply()
                        }

                        goToGamesList()
                    } else {
                        Toast.makeText(context, "CREDENCIAIS ERRADAS", Toast.LENGTH_SHORT).show()
                        showProgressBar(false)
                    }
                }
                is ResultStatus.Error -> {
                    Toast.makeText(context, "ERRO: "+it.throwable, Toast.LENGTH_SHORT).show()
                    showProgressBar(false)
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

    private fun goToGamesList(){
        startActivity(Intent(context, TestActivity::class.java))
        requireActivity().finish()
    }

    private fun showProgressBar(show: Boolean){
        binding.pbLogin.visibility = if(show) View.VISIBLE else View.GONE
        binding.btnLogin.visibility = if(show) View.GONE else View.VISIBLE
    }
}