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

        var item = intent.getParcelableExtra<TrntJson>("item")

        var nameText: MaterialTextView = findViewById(R.id.name_text)
        var createdText: MaterialTextView = findViewById(R.id.created_text)
        var privateText: MaterialTextView = findViewById(R.id.private_text)
        var infoHashText: MaterialTextView = findViewById(R.id.infoHash_text)

        nameText.setContentText(item.name, MaterialTextView.ANIMATE_TYPE.NONE)
        createdText.setContentText(item.created, MaterialTextView.ANIMATE_TYPE.NONE)
        privateText.setContentText(item.private.toString(), MaterialTextView.ANIMATE_TYPE.NONE)
        infoHashText.setContentText(item.infoHash, MaterialTextView.ANIMATE_TYPE.NONE)
    }
}