package co.icesi.edu.pokemonapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import co.icesi.edu.pokemonapi.R
import co.icesi.edu.pokemonapi.databinding.FragmentDexBinding

class DexFragment : Fragment() {

    private lateinit var binding: FragmentDexBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDexBinding.inflate(inflater,container,false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        listener()
    }

    private fun listener(){
        binding.look.setOnClickListener {
            val namePoke = binding.choosePokemon.text.toString()
            val action = DexFragmentDirections.actionDexFragmentToPokeInfoFragment(namePoke)

            Navigation.findNavController(binding.root).navigate(action)
        }
    }
}