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
import co.icesi.edu.pokemonapi.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


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
            //Creando el intent para los dos casos e ir a la pagina donde se atrapa el pokemon
//            val intent = Intent(this, dex::class.java).apply{
//                putExtra("username", username)
//            }


            Firebase.firestore.collection("users").whereEqualTo("username", username)
                .get().addOnCompleteListener { task ->

                    if(task.result?.size()==0){
                        Firebase.firestore.collection("users").add(User(username))
//                        startActivity(intent)

                    }else{
//                        startActivity(intent)
                    }
                    Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_dexFragment)
                }
        }else{
            Toast.makeText(requireContext(), "Empty username", Toast.LENGTH_SHORT).show()
        }
    }
}