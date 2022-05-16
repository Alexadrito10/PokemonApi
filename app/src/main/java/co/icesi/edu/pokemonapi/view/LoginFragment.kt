package co.icesi.edu.pokemonapi.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import co.icesi.edu.pokemonapi.R
import co.icesi.edu.pokemonapi.databinding.ActivityMainBinding
import co.icesi.edu.pokemonapi.databinding.FragmentLoginBinding
import co.icesi.edu.pokemonapi.model.Pokemon
import co.icesi.edu.pokemonapi.model.User
import co.icesi.edu.pokemonapi.serviceManagement.Session
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.login.setOnClickListener { connect(binding.login) }
    }

    private fun connect(view: View){
        var username= binding.username.text.toString()
        username= username.trim()

        if(username!= ""){

            Firebase.firestore.collection("users").whereEqualTo("username", username)
                .get().addOnCompleteListener { task ->

                    if(task.result?.size()==0) {
                        val collection = Firebase.firestore.collection("users")
                        val sessionUser = collection.document()

                        Session.sessionId = sessionUser.id
                        val userCreated = User(sessionUser.id,username, emptyList())

                        print("id de recien creado : "+Session.sessionId+"")
                        sessionUser.set(userCreated)


                        }

                    else{
                        if (task.isSuccessful){
                            task.result.documents.forEach{
                                val user = it.toObject(User::class.java)
                                Session.sessionId = user!!.id
                            }


                            print("id de uno  prev creado: "+Session.sessionId+"---------------------------------" )


                        }
                    }



                    print("id de recien creado fin : "+Session.sessionId+"")
                    Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_dexFragment)
                }
        }else{
            Toast.makeText(requireContext(), "Empty username", Toast.LENGTH_SHORT).show()
        }
    }
}