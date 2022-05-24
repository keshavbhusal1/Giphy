package com.keshav.giphy.fragment.one

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.keshav.giphy.R
import com.keshav.giphy.adapter.TrendingAdapter
import com.keshav.giphy.base.ViewModelFactory
import com.keshav.giphy.database.DatabaseBuilder
import com.keshav.giphy.database.DatabaseHelper
import com.keshav.giphy.database.DatabaseHelperImpl
import com.keshav.giphy.databinding.FragmentOneBinding
import com.keshav.giphy.model.response.TrendingResponse
import com.keshav.giphy.myinterface.ClickListener
import com.keshav.giphy.network.ApiHelper
import com.keshav.giphy.network.RetrofitBuilder
import com.keshav.giphy.utils.Resource
import com.keshav.giphy.utils.Status


class OneFragment : Fragment() {

    companion object {
        fun newInstance() = OneFragment()
    }


    private val viewModel: OneViewModel by lazy{
        ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService)))[OneViewModel::class.java]
    }
    private val dbHelper: DatabaseHelper by lazy {
        DatabaseHelperImpl(DatabaseBuilder.getInstance(requireContext()))
    }

    private lateinit var binding: FragmentOneBinding
    private lateinit var adapter: TrendingAdapter

    private lateinit var searchView: SearchView
    private lateinit var queryTextListener: SearchView.OnQueryTextListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneBinding.inflate(inflater,container,false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TrendingAdapter(arrayListOf(),clickListener)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter

        setupObservers()


    }

    private fun setupObservers() {
        viewModel.getTrending(limit = 25).observe(viewLifecycleOwner) {
            it?.let { resource ->
                setUpUI(resource)
            }
        }
    }

   private val clickListener = object : ClickListener {
        override fun click(model: TrendingResponse.Data) {
            Toast.makeText(context,"Added favorite",Toast.LENGTH_LONG).show()
            viewModel.insertData(model,dbHelper)
        }
    }



    @SuppressLint("NotifyDataSetChanged")
    private fun retrieveList(it: TrendingResponse) {
        adapter.apply {
            addList(it.data)
            notifyDataSetChanged()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.action_search)
        val searchManager =
            activity?.let {
                it.getSystemService(Context.SEARCH_SERVICE) as SearchManager
            }

        searchView = searchItem.actionView as SearchView
        searchView.setSearchableInfo(searchManager?.getSearchableInfo(activity?.componentName))
        queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                Log.i("onQueryTextChange", newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                setupSearchItemObservers(query)
                Log.i("onQueryTextSubmit", query)
                return true
            }
        }
        searchView.setOnQueryTextListener(queryTextListener)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search ->                 // Not implemented here
                return false
            else -> {}
        }
        searchView.setOnQueryTextListener(queryTextListener)
        return super.onOptionsItemSelected(item)
    }

    private fun setupSearchItemObservers(searchText: String) {
        viewModel.getSearch(limit = 25, searchText = searchText).observe(viewLifecycleOwner) {
            it?.let { resource ->
                setUpUI(resource)

            }
        }
    }

    private fun setUpUI(resource: Resource<TrendingResponse>) {
        when (resource.status) {
            Status.SUCCESS -> {
                Log.d("TAG", "setupObservers: SUCCESS")
                binding.recyclerView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                resource.data?.let {response->
                    retrieveList(response)
                }
            }
            Status.ERROR -> {
                Log.d("TAG", "setupObservers: ERROR")
                binding.recyclerView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                Toast.makeText(context, resource.message, Toast.LENGTH_LONG).show()
            }
            Status.LOADING -> {
                Log.d("TAG", "setupObservers: LOADING")
                binding.progressBar.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            }
        }
    }
}