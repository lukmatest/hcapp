package com.lukma.hcapplication.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenResumed
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukma.hcapplication.R
import com.lukma.hcapplication.presentation.common.MarginItemDecoration
import com.lukma.hcapplication.presentation.common.Resource
import com.lukma.hcapplication.shared.showSnackBar
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
                    progressBar.isVisible = it is Resource.Loading
                    when (it) {
                        is Resource.Success -> it.data.run(listAdapter::submit)
                        is Resource.Failure -> it.error.message?.run(::showSnackBar)
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
            addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen.default_component_space_medium).toInt()))
            adapter = listAdapter
        }
    }
}
