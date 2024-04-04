package com.example.l0403_final

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.l0403_final.databinding.FragmentMainBinding
import com.example.l0403_final.Onefragment

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        var onClick = false

        binding.fragBtn.setOnClickListener {
            if (onClick) {
                onClick = false
                val transaction = fragmentManager.beginTransaction()
                val fragment = fragmentManager.findFragmentByTag("OneFragment")
                if (fragment != null) {
                    transaction.remove(fragment)
                    transaction.commit()
                }
            } else {
                onClick = true
                val transaction = fragmentManager.beginTransaction()
                val fragment = Onefragment()
                transaction.replace(R.id.fragment_content, fragment, "OneFragment")
                transaction.commit()
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
