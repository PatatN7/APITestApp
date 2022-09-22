package com.awe.apitest1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.awe.apitest1.databinding.ActivityCardDetailsBinding
import com.bumptech.glide.Glide

class CardDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityCardDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.textCardNameDetails.setText(intent.getStringExtra("name"))
        binding.textCardSetDetails.setText(intent.getStringExtra("set"))
        binding.textCardRarityDetails.setText(intent.getStringExtra("rarity"))
        binding.textCardUpdatedDetails.setText(intent.getStringExtra("updatedAt"))
        binding.textCardNormalDetails.setText("Non-Holo: $" + intent.getDoubleExtra("marketNormal", 0.00).toString())
        binding.textCardHoloDetails.setText("Holo: $" + intent.getDoubleExtra("marketHolo", 0.00).toString())
        binding.textCardReverseHoloDetails.setText("Reverse Holo: $" + intent.getDoubleExtra("marketReverseHolo", 0.00).toString())
        Glide.with(this@CardDetailsActivity).load(intent.getStringExtra("image")).into(findViewById(R.id.imageCardSmall))

    }
}