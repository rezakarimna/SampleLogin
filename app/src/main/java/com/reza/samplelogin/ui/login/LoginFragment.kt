package com.reza.samplelogin.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.reza.samplelogin.databinding.FragmentLoginBinding

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.reza.appmovies.viewmodel.LoginViewModel
import com.reza.samplelogin.R
import com.reza.samplelogin.data.models.BodyRegister
import com.reza.samplelogin.utils.StoreUserData
import com.reza.samplelogin.utils.showInVisible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 7

    @Inject
    lateinit var bodyRegister: BodyRegister

    @Inject
    lateinit var storeUserData: StoreUserData

    //Other
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgGmail.setOnClickListener {
            signIn()
        }
        binding.submitBtn.setOnClickListener {
            initAndValidationField()
            //sendData
            viewModel.sendRegisterUser(bodyRegister)
            //loading
            observeLoading()
            //save user
            observeAndSaveUserRegister()
        }
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            // Signed in successfully, show authenticated UI.
            val acct: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
            Toast.makeText(requireContext(), acct.displayName, Toast.LENGTH_LONG).show()
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toast.makeText(requireContext(), "no", Toast.LENGTH_LONG).show()
        }
    }

    private fun initAndValidationField() {
        binding.apply {
            val name = nameEdt.text.toString()
            val email = gmailEdt.text.toString()
            val password = passwordEdt.text.toString()
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                validationBodyRegister(name, email, password)
            } else {
                Snackbar.make(
                    binding.submitBtn,
                    getString(R.string.fillAllFields),
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    private fun validationBodyRegister(name: String, email: String, password: String) {
        bodyRegister.name = name
        bodyRegister.email = email
        bodyRegister.password = password
    }

    private fun observeLoading() {
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.submitLoading.showInVisible(true)
                binding.submitBtn.showInVisible(false)
            } else {
                binding.submitLoading.showInVisible(false)
                binding.submitBtn.showInVisible(true)
            }
        }
    }

    private fun observeAndSaveUserRegister() {
        viewModel.registerUser.observe(viewLifecycleOwner) { response ->
            lifecycle.coroutineScope.launchWhenCreated {
                storeUserData.saveUserToken(response.name.toString())
                Snackbar.make(
                    binding.submitBtn,
                    getString(R.string.welcome),
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

}