package com.keshav.giphy.fragment.two

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.keshav.giphy.adapter.FavoriteAdapter
import com.keshav.giphy.database.DatabaseBuilder
import com.keshav.giphy.database.DatabaseHelper
import com.keshav.giphy.database.DatabaseHelperImpl
import com.keshav.giphy.databinding.FragmentTwoBinding
import com.keshav.giphy.model.response.TrendingResponse
import com.keshav.giphy.myinterface.ClickListener

class TwoFragment : Fragment() {

    companion object {
        fun newInstance() = TwoFragment()
    }

    private val viewModel: TwoViewModel by lazy {
        ViewModelProvider(this)[TwoViewModel::class.java]
    }
    private lateinit var binding: FragmentTwoBinding
    private lateinit var adapter: FavoriteAdapter

    private val dbHelper: DatabaseHelper by lazy {
        DatabaseHelperImpl(DatabaseBuilder.getInstance(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
          binding = FragmentTwoBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.include.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = FavoriteAdapter(arrayListOf(),clickListener)
        binding.include.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.include.recyclerView.context,
                (binding.include.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.include.recyclerView.adapter = adapter

        setupObservers()

    }

    override fun onResume() {
        setupObservers()
        super.onResume()
    }

    private fun setupObservers() {
        viewModel.getAllData(dbHelper).observe(viewLifecycleOwner, Observer { response->
            binding.include.progressBar.visibility =View.GONE
            retrieveList(response)
            if (response.isEmpty()){
               Toast.makeText(context,"Data Not Available",Toast.LENGTH_SHORT).show()
            }
        })
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun retrieveList(response: List<TrendingResponse.Data>) {
        adapter.apply {
            addList(response)
            notifyDataSetChanged()
        }
    }

    private val clickListener = object : ClickListener {
        override fun click(model: TrendingResponse.Data) {

            viewModel.deleteData(model,dbHelper).observe(viewLifecycleOwner, Observer { response ->
                if (response){
                    Toast.makeText(context,"delete from favorite",Toast.LENGTH_LONG).show()
                    setupObservers()
                }else{
                    Toast.makeText(context,"Try again",Toast.LENGTH_LONG).show()

                }
            })
        }
    }



}