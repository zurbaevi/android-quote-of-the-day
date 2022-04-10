package dev.zurbaevi.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.zurbaevi.common.exentsion.showShortSnackBar
import dev.zurbaevi.common.util.Language
import dev.zurbaevi.settings.databinding.FragmentSettingsBottomDialogBinding

@AndroidEntryPoint
class SettingsBottomDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSettingsBottomDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBottomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        renderEffect()
    }

    private fun renderEffect() {
        lifecycleScope.launchWhenStarted {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is SettingsContract.Effect.ShowSnackBarChangeLanguage -> showShortSnackBar(
                        "${getString(R.string.successfully_changed_the_language)}: ${effect.language.uppercase()}"
                    )
                    is SettingsContract.Effect.ShowSnackBarError -> showShortSnackBar(effect.message)
                }
            }
        }
    }

    private fun initListeners() {
        binding.apply {
            textViewChooseLanguage.setOnClickListener {
                AlertDialog.Builder(requireContext())
                    .setItems(R.array.language) { _, language ->
                        viewModel.setEvent(
                            SettingsContract.Event.OnChooseLanguage(Language.create(language))
                        )
                    }.show()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}