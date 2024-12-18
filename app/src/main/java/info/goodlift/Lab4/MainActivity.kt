package info.goodlift.Lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import info.goodlift.Lab4.adapter.MyAdapter

import info.goodlift.Lab4.databinding.ActivityMainBinding


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        val recyclerView = binding.rvMyList
        recyclerView.layoutManager = LinearLayoutManager(this)

        val myAdapter = MyAdapter(viewModel.itemList, viewModel)

        recyclerView.adapter = myAdapter

        binding.clearDataBtn.setOnClickListener {
            viewModel.clearData()
        }
        binding.addDataBtn.setOnClickListener {
            viewModel.createData()
        }

        viewModel.itemList.observe(this) {
            myAdapter.notifyDataSetChanged()
        }
    }
}