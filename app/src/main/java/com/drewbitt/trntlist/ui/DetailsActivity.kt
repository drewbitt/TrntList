package com.drewbitt.trntlist.ui

import android.os.Bundle
import android.widget.EditText
import com.drewbitt.trntlist.MainActivity
import com.drewbitt.trntlist.R
import com.drewbitt.trntlist.data.ViewModel
import com.drewbitt.trntlist.data.model.TrntJson
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.support.DaggerAppCompatActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar
import uk.co.onemandan.materialtextview.MaterialTextView
import javax.inject.Inject

class DetailsActivity: DaggerAppCompatActivity() {

    @Inject lateinit var viewModel: ViewModel
    @Inject lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val item = intent.getParcelableExtra<TrntJson>("item")

        val nameText: MaterialTextView = findViewById(R.id.name_text)
        val createdText: MaterialTextView = findViewById(R.id.created_text)
        val privateText: MaterialTextView = findViewById(R.id.private_text)
        val infoHashText: MaterialTextView = findViewById(R.id.infoHash_text)
        val announceText: MaterialTextView = findViewById(R.id.infoHash_announces)

        nameText.setContentText(item.name, MaterialTextView.ANIMATE_TYPE.NONE)
        createdText.setContentText(item.created, MaterialTextView.ANIMATE_TYPE.NONE)
        privateText.setContentText(item.private.toString(), MaterialTextView.ANIMATE_TYPE.NONE)
        infoHashText.setContentText(item.infoHash, MaterialTextView.ANIMATE_TYPE.NONE)
        item.announce.forEachIndexed { index, it ->
            if (index == 0) {
                announceText.setContentText(announceText.contentText.toString().plus(it),
                    MaterialTextView.ANIMATE_TYPE.NONE)
            } else {
                announceText.setContentText(
                    announceText.contentText.toString().plus("\n").plus(it),
                    MaterialTextView.ANIMATE_TYPE.NONE
                )
            }
        }

        val fabDetails: FloatingActionButton = findViewById(R.id.fab_details)
        fabDetails.setOnClickListener {
            alert("Add an announce url to the torrent", "Add announce") {
                var input: EditText? = null
                customView {
                    linearLayout {
                        input = editText() {
                            hint = "Add announce"
                            width = maxWidth
                        }
                        // these buttons are too close on my emulator but are fine on phone
                        negativeButton("Cancel") {}
                        positiveButton("Add") {
                            val newList = item.announce.toMutableList()
                            if (input?.text.toString().isEmpty()) {
                                toast("Can't add empty announce")
                            } else {
                                newList.add(input?.text.toString())
                                item.announce = newList.toList()

                                if (announceText.contentText.isNotEmpty()) {
                                    announceText.setContentText(
                                        announceText.contentText.toString().plus("\n").plus(input?.text.toString()),
                                        MaterialTextView.ANIMATE_TYPE.NONE
                                    )
                                } else {
                                    announceText.setContentText(input?.text.toString(), MaterialTextView.ANIMATE_TYPE.NONE)
                                }

                                fabDetails.snackbar("Added announce to list")
                                doAsync {
                                    viewModel.updateTrntJson(item)
                                }

                                /*doAsync {
                                    /*viewModel.getListLiveData().removeObservers(mainActivity)
                                    var test = viewModel.updateTrntJson(item).observeForever {
                                        finish()
                                        startActivity<DetailsActivity>("item" to item)
                                        mainActivity.viewAdapter.notifyDataSetChanged()
                                        mainActivity.recreate()
                                        viewModel.getListLiveData().observe(mainActivity, Observer(mainActivity::bindResult))
                                    }
                                    //viewModel.getListLiveData().removeObservers(mainActivity)
                                    //viewModel.getListLiveData().observe(mainActivity, Observer(mainActivity::bindResult))
                                    //mainActivity.viewAdapter.notifyDataSetChanged()
                                    //mainActivity.recreate()
                                    */

                                    // finish and start as this activity gets everything from the intent
                                    finish()
                                    startActivity<DetailsActivity>("item" to item)

                                    // now need to do a dao refresh for mainactivity as well. It is observing
                                    // liveData but never getting any updates, calls to the viewadapter have also
                                    // been unsuccessful (since no different data, duh)
                                }
                                */
                            }
                        }
                    }
                }
            }.show()
        }
    }


}
