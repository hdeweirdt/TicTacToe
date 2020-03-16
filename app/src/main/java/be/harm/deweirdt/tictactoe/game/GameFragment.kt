package be.harm.deweirdt.tictactoe.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import be.harm.deweirdt.tictactoe.databinding.FragmentGameBinding
import org.koin.android.viewmodel.ext.android.viewModel

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding

    private val gameViewModel by viewModel<GameViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater)

        binding.gameViewModel = gameViewModel
        binding.lifecycleOwner = this

        startListeners()

        return binding.root
    }

    private fun startListeners() {
        gameViewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            errorMessage?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
