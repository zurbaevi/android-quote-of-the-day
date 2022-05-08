package dev.zurbaevi.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<Event : UiEvent, State : UiState, Effect : UiEffect, VB : ViewBinding, VM : BaseViewModel<Event, State, Effect>> :
    Fragment() {

    private var _binding: VB? = null
    abstract val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> VB

    protected abstract val viewModel: VM

    protected val binding: VB
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindLayout.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                renderState(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.effect.collect {
                renderEffect(it)
            }
        }

        prepareView(savedInstanceState)
    }

    abstract fun prepareView(savedInstanceState: Bundle?)

    abstract fun renderState(state: State)

    abstract fun renderEffect(effect: Effect)

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}