package co.icesi.edu.pokemonapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import co.icesi.edu.pokemonapi.databinding.ActivityMainBinding
import co.icesi.edu.pokemonapi.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()

        setContentView(binding.root)
        binding.login.setOnClickListener(::connect)
    }

    private fun connect(view: View){
        var username= binding.username.text.toString()
        username= username.trim()

        if(username!= ""){
            //Creando el intent para los dos casos e ir a la pagina donde se atrapa el pokemon
            val intent = Intent(this, dex::class.java).apply{
                putExtra("username", username)
            }
            Firebase.firestore.collection("users").whereEqualTo("username", username)
                .get().addOnCompleteListener { task ->

                    if(task.result?.size()==0){
                        Firebase.firestore.collection("users").add(User(username))
                        startActivity(intent)
                    }else{
                        startActivity(intent)
                    }
                }
        }else{
            Toast.makeText(this, "Empty username", Toast.LENGTH_SHORT).show()
        }
    }
}