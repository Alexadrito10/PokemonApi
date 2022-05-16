package co.icesi.edu.pokemonapi.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.navArgs
import co.icesi.edu.pokemonapi.R
import co.icesi.edu.pokemonapi.databinding.FragmentPokeInfoBinding
import co.icesi.edu.pokemonapi.model.Pokemon
import co.icesi.edu.pokemonapi.model.User
import co.icesi.edu.pokemonapi.viewmodel.DexViewModel
import co.icesi.edu.pokemonapi.viewmodel.PokeInfoViewModel
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class PokeInfoFragment : Fragment() {
    private lateinit var binding : FragmentPokeInfoBinding
    private lateinit var  viewModel: PokeInfoViewModel
    private lateinit var viewModelDexFragment: DexViewModel

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
        if (args.date== null){
            binding.seeLiberate.isVisible= false
        }else{
            binding.seeCatch.isVisible= false
        }
        viewModel = ViewModelProvider(requireActivity())[PokeInfoViewModel::class.java]
        viewModelDexFragment = ViewModelProvider(requireActivity())[DexViewModel::class.java]
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

            binding.seeCatch.setOnClickListener {
                viewModelDexFragment.getPokemon(binding.seePokeName.text.toString())
            }


        }
    }
}