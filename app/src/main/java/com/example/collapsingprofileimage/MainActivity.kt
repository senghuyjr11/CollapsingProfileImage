package com.example.collapsingprofileimage

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.collapsingprofileimage.databinding.ActivityMainBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.imageview.ShapeableImageView
import kotlin.math.abs


class MainActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.appBar.addOnOffsetChangedListener(this)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        /**
         * Collapsed
         */
        if (abs(verticalOffset) == appBarLayout!!.totalScrollRange) {
            binding.ivProfile.animate().scaleX(0.7.toFloat()).duration = 3000
            binding.ivProfile.animate().scaleY(0.7.toFloat()).duration = 3000
            binding.ivProfile.animate().alpha(1f).duration = 0

         /**
         * Expanded
         */
        } else if (verticalOffset == 0) {
            binding.ivProfile.animate().scaleX(1f).duration = 100
            binding.ivProfile.animate().scaleY(1f).duration = 100
            binding.ivProfile.animate().alpha(1f).duration = 0

        /**
         * Somewhere in between
         */
        } else {
            val scrollRange = appBarLayout.totalScrollRange
            val offsetFactor = (-verticalOffset).toFloat() / scrollRange.toFloat()
            val scaleFactor = 1f - offsetFactor * .3f
            binding.ivProfile.animate().scaleX(scaleFactor)
            binding.ivProfile.animate().scaleY(scaleFactor)
        }
    }
}