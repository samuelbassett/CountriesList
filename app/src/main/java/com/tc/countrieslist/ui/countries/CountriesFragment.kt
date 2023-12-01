package com.tc.countrieslist.ui.countries

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tc.countrieslist.databinding.FragmentCountryListBinding

class CountriesFragment : Fragment() {

    private var _binding: FragmentCountryListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var countryAdapter: CountryAdapter
    private var recyclerViewState: Parcelable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCountryListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val countriesViewModel = ViewModelProvider(this).get(CountriesViewModel::class.java)
        countriesViewModel.countryData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is UIState.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.recyclerCountries.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = CountryAdapter(state.data)
                    }

                    binding.recyclerCountries.layoutManager?.onRestoreInstanceState(recyclerViewState)
                }

                is UIState.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    val errorMessage = state.message
                    Toast.makeText(requireContext(), "Error: $errorMessage", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        countryAdapter = CountryAdapter(arrayListOf())


        savedInstanceState?.let {
            recyclerViewState = it.getParcelable("recycler_state")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the current state of the RecyclerView
        recyclerViewState = binding.recyclerCountries.layoutManager?.onSaveInstanceState()
        outState.putParcelable("recycler_state", recyclerViewState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}