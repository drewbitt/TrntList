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
import org.jetbrains.anko.alert
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import timber.log.Timber
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    @Inject lateinit var viewModel: ViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    // moving recyclerAdapter outside of onCreate to maintain instance and only update items
    private var recyclerAdapter = RecyclerAdapter()

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

        // This works but requires networking call to already have happened
        // viewModel.getListLiveDataDao().observe(this, Observer(this::bindResult))

        fab.setOnClickListener {
            longToast("Not currently implemented - only API is wrote & a retrofit object exists. API on posted files returns json")
        }
    }

    // livedata callback
    fun bindResult(trntJsonList: List<TrntJson>) {
        recyclerAdapter.items = trntJsonList
        recyclerView = findViewById<RecyclerView>(R.id.listRecycler).apply {
            adapter = recyclerAdapter
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
            R.id.action_about -> {
                alert(Appcompat,"\nProject Repo: https://gitgud.io/drewbitt/trntlist\n").show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onRefresh() {
        toast("Not currently implemented, data is static on server")
        Handler().postDelayed({ swipeRefreshLayout.isRefreshing = false }, 700)
    }
}
