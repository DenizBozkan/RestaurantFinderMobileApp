package com.example.restaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.example.restaurant.databinding.ActivityDetailedBinding
import kotlinx.android.synthetic.main.activity_detailed.*

class DetailedActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailedBinding
    private var isLiked = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val zoomInAnim = AnimationUtils.loadAnimation(this,R.anim.zoom_in)
        val zoomOutAnim = AnimationUtils.loadAnimation(this,R.anim.zoom_out)
        binding.detailedActivityIv.setOnClickListener(object : DoubleClickListener(){
            override fun onDoubleClick(v: View?) {
                binding.heart.setImageResource(R.drawable.baseline_favorite_24)
                binding.heart.startAnimation(zoomInAnim)
                binding.insideHeart.startAnimation(zoomInAnim)
                binding.insideHeart.startAnimation(zoomOutAnim)
                isLiked = true
            }
        })
        binding.heart.setOnClickListener {
            if(isLiked){
                binding.heart.setImageResource(R.drawable.baseline_favorite_border_24)
            }
            else{
                binding.heart.setImageResource(R.drawable.baseline_favorite_24)
                binding.insideHeart.startAnimation(zoomInAnim)
                binding.insideHeart.startAnimation(zoomOutAnim)
            }
            binding.heart.startAnimation(zoomInAnim)
            isLiked = !isLiked
        }


        val name = intent.getParcelableExtra<SubNames>("name")
        if(name != null){
            val textView : TextView = findViewById(R.id.detailedActivityTv)
            val imageView : ImageView = findViewById(R.id.detailedActivityIv)

            textView.text = name.name
            imageView.setImageResource(name.image)
        }
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val images = listOf(
            R.drawable.baydonericfotobir,
            R.drawable.baydonericfotoiki,
            R.drawable.baydonermenu
        )
        val adapter= ViewPagerAdapter(images)
        viewPager.adapter = adapter
    }

    abstract class DoubleClickListener : View.OnClickListener{
        private var lastClickTime : Long = 0
        companion object{
            private const val DOUBLE_CLICK_TIME_DELTA= 300
        }
        override fun onClick(v : View?){
            val clickTime = System.currentTimeMillis()
            if(clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA){
                onDoubleClick(v)
            }
            lastClickTime = clickTime
        }
        abstract fun onDoubleClick(v : View?)
    }
}