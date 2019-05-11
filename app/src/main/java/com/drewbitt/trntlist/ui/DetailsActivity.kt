package com.drewbitt.trntlist.ui

import android.os.Bundle
import com.drewbitt.trntlist.R
import com.drewbitt.trntlist.data.model.TrntJson
import dagger.android.support.DaggerAppCompatActivity
import uk.co.onemandan.materialtextview.MaterialTextView

class DetailsActivity: DaggerAppCompatActivity() {
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
    }
}
