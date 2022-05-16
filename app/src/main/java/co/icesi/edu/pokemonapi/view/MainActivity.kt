package co.icesi.edu.pokemonapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import co.icesi.edu.pokemonapi.R
import co.icesi.edu.pokemonapi.databinding.ActivityMainBinding
import co.icesi.edu.pokemonapi.model.User
import co.icesi.edu.pokemonapi.serviceManagement.Session
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContentView(R.layout.activity_main)

        Session

//        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        supportActionBar?.hide()

    }

}