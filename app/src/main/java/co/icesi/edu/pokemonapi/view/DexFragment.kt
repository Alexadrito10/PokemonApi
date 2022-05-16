package co.icesi.edu.pokemonapi.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import co.icesi.edu.pokemonapi.R
import co.icesi.edu.pokemonapi.adapter.PokemonAdapter
import co.icesi.edu.pokemonapi.databinding.FragmentDexBinding
import co.icesi.edu.pokemonapi.model.Pokemon
import co.icesi.edu.pokemonapi.serviceManagement.Session
import co.icesi.edu.pokemonapi.viewmodel.DexViewModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DexFragment : Fragment(), PokemonAdapter.OnClickListener {

    private lateinit var binding: FragmentDexBinding
    private lateinit var viewModel: DexViewModel
    private lateinit var adapter: PokemonAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDexBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DexViewModel::class.java]
        viewModel.getPokemons()
        adapter = PokemonAdapter(ArrayList(), this)

        listener()
        viewModel.pokemon.observe(viewLifecycleOwner) {
            Firebase.firestore.collection("users").document(Session.sessionId).update("pokemons",
                FieldValue.arrayUnion(it))
            adapter.addPokemon(it)
            adapter.notifyDataSetChanged()
        }

        viewModel.listPokemons.observe(viewLifecycleOwner) {
            adapter = PokemonAdapter(it, this)
            binding.pokeRecycler.adapter = adapter
        }
    }

    private fun listener() {
        binding.look.setOnClickListener {
            val namePoke = binding.choosePokemon.text.toString()
            val action = DexFragmentDirections.actionDexFragmentToPokeInfoFragment(namePoke, null)

            Navigation.findNavController(binding.root).navigate(action)
        }
        binding.catchPokemon.setOnClickListener {
            viewModel.getPokemon(binding.choosePokemon.text.toString())
        }
    }

    override fun onClick(position: Int) {
        val pokemon= adapter.getPokemon(position)
        val action = DexFragmentDirections.actionDexFragmentToPokeInfoFragment(pokemon.name, pokemon.date)

        Navigation.findNavController(binding.root).navigate(action)
    }
}