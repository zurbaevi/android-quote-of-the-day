package dev.zurbaevi.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class GlobalEntryPointFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        findNavController().navigate(GlobalEntryPointFragmentDirections.actionGlobalEntryPointToFeatureHome())
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}