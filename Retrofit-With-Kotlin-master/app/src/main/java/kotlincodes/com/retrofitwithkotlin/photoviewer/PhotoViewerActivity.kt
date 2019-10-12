package kotlincodes.com.retrofitwithkotlin.photoviewer

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.jsibbold.zoomage.ZoomageView
import kotlincodes.com.retrofitwithkotlin.R

class PhotoViewerActivity : AppCompatActivity() {
    lateinit var iv_image: ImageView
    lateinit var myZoomageView: ZoomageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_viewer)

        initUI()

        setAppBar()

        loadImage()
    }


    private fun initUI() {
        iv_image = findViewById(R.id.iv_image)
        myZoomageView = findViewById(R.id.myZoomageView)
    }

    private fun setAppBar() {
        actionBar?.setDisplayHomeAsUpEnabled(true);

        val title = intent.getStringExtra("title")
        supportActionBar?.title = title
    }

    private fun loadImage() {
        val avatar_url = intent.getStringExtra("avatar_url")

        if (avatar_url == null)
            finish()

        Glide.with(this)
                .load(avatar_url)
                .into(myZoomageView)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
        }
    }

    private fun hideSystemUI() {
        val decorView = window.decorView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    // Hide the nav bar and status bar
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }
}
