package co.icesi.edu.pokemonapi.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import co.icesi.edu.pokemonapi.R
import co.icesi.edu.pokemonapi.databinding.FragmentPokeInfoBinding
import co.icesi.edu.pokemonapi.model.Pokemon
import co.icesi.edu.pokemonapi.model.User
import co.icesi.edu.pokemonapi.viewmodel.PokeInfoViewModel
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class PokeInfoFragment : Fragment() {
    private lateinit var binding : FragmentPokeInfoBinding
    private lateinit var  viewModel: PokeInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokeInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args:PokeInfoFragmentArgs by navArgs()
        viewModel = ViewModelProvider(requireActivity())[PokeInfoViewModel::class.java]
        viewModel.getPokemon(args.namePoke)
        viewModel.pokemon.observe(viewLifecycleOwner){
            Log.e("pokemon",it.toString())

            it.apply { val defense = stats.filter {
                    mystat -> mystat.stat?.name == "defense" }[0].base_stat
                val attack = stats.filter { mystat -> mystat.stat?.name == "attack" }[0].base_stat
                val speed = stats.filter { mystat -> mystat.stat?.name == "speed" }[0].base_stat
                val health = stats.filter { mystat -> mystat.stat?.name == "hp" }[0].base_stat
                val name = name
                var type =""
                types.forEach{
                    if (type.length != 0){
                        type += " - "
                    }
                    type += "${it.type?.name}"
                }
                val image = sprites?.front_default

                binding.apply {
                    seeDefenseNum.text = defense.toString()
                    seeAtkNum.text = attack.toString()
                    seeSpeedNum.text = speed.toString()
                    seeLifeNum.text = health.toString()
                    val pokename = "$name ( $type )"
                    seePokeName.text = pokename
                }
                Glide.with(binding.seePokeImg.context).load(image).into(binding.seePokeImg)

            }

        }



    }

    fun whatever(){
//        user = intent.extras?.get("user") as User
//        pokemon = intent.extras?.get("pokemon") as Pokemon
//
//        val defense = pokemon.stats.filter { mystat -> mystat.stat.name == "defense" }[0].base_stat
//        val attack = pokemon.stats.filter { mystat -> mystat.stat.name == "attack" }[0].base_stat
//        val speed = pokemon.stats.filter { mystat -> mystat.stat.name == "speed" }[0].base_stat
//        val health = pokemon.stats.filter { mystat -> mystat.stat.name == "hp" }[0].base_stat
//        val name = pokemon.name
//        var types:String =""
//        pokemon.type.forEach{
//            types += "${it.type.name}-"
//
//        }
//        val image = pokemon.sprites.front_default
//
//
//
//        binding.seeDefenseNum.text = defense.toString()
//        binding.seeAtkNum.text = attack.toString()
//        binding.seeSpeedNum.text = speed.toString()
//        binding.seeLifeNum.text = health.toString()
//        binding.seePokeName.text = "$name ( $types )"
//        Glide.with(binding.seePokeImg.context).load(image).into(binding.seePokeImg)
//
//        binding.seeCatch.setOnClickListener {
//            Firebase.firestore.collection("users").document(user?.username).collection("pokemons").document(pokemon.id.toString()).set(pokemon)
//            val intent = Intent(this, dex::class.java).apply {
//                putExtra("user",user)
//            }
//            startActivity(intent)
//
//        }
    }

}