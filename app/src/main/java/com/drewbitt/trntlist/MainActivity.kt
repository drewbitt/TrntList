package com.drewbitt.trntlist

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.drewbitt.trntlist.data.ViewModel
import com.drewbitt.trntlist.data.model.TrntJson
import com.drewbitt.trntlist.ui.recyclerview.RecyclerAdapter
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import timber.log.Timber
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
        toast("Not currently implemented, data is static on server")
        Handler().postDelayed({swipeRefreshLayout.isRefreshing = false}, 700)
    }

    @Inject lateinit var viewModel: ViewModel
    private lateinit var recyclerView: RecyclerView
    lateinit var viewAdapter: RecyclerAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        swipeRefreshLayout = findViewById(R.id.refresh)
        swipeRefreshLayout.setOnRefreshListener(this)

        viewManager = LinearLayoutManager(this)
        viewModel.getListLiveData().observe(this, Observer(this::bindResult))

        fab.setOnClickListener {
            longToast("Not currently implemented - only API is wrote & a retrofit object exists. Would post files & return JSON")
        }
    }

    fun bindResult(trntJsonList: List<TrntJson>) {
        viewAdapter = RecyclerAdapter(items = trntJsonList)
        recyclerView = findViewById<RecyclerView>(R.id.listRecycler).apply {
            adapter = viewAdapter
            layoutManager = viewManager
        }
        Timber.d(trntJsonList.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
