package com.lukma.hcapplication.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenResumed
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukma.hcapplication.R
import com.lukma.hcapplication.presentation.common.Resource
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainViewModel>()
    private val listAdapter = ContentListAdapter(
        onProductItemClicked = {},
        onArticleItemClicked = {}
    )

    init {
        lifecycleScope.launch {
            whenCreated {
                viewModel.content.observe(this@MainActivity, Observer {
                    when (it) {
                        is Resource.Success -> it.data.run(listAdapter::submit)
                    }
                })
            }
            whenResumed {
                viewModel.getHomeContent()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listAdapter
        }
    }
}
