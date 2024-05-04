package za.ac.iie.st10437399.mytamagotchiapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class page2_interaction : AppCompatActivity() {
    private lateinit var progressbarfeed: ProgressBar
    private var hungerlevel = 100f
    private var hungerdecreaserate  = 2f
    private lateinit var progressBarclean: ProgressBar
    private var cleanlevel = 100f
    private var cleanDecreaseRate = 2f
    private lateinit var progressBarplay: ProgressBar
    private var playlevel = 100f
    private var playDecreaseRate = 2f

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page2_interaction)

        val feed_Button = findViewById<Button>(R.id.feed_Button)
        val clean_Button = findViewById<Button>(R.id.clean_Button)
        val play_Button = findViewById<Button>(R.id.play_Button)

        val imageView = findViewById<ImageView>(R.id.dogimages)
        val TextView = findViewById<TextView>(R.id.iamnowplaying_Textview)
         progressbarfeed = findViewById(R.id.progressBarFeed)
        progressBarplay = findViewById(R.id.progressBarplay)
        progressBarclean = findViewById(R.id.progressBarclean)
        progressbarfeed.progress = hungerlevel.toInt()
        val hungercoroutine = launchFeedCoroutine()
        val cleancoroutine = launchCleanCoroutine()
        val playcoroutine = launchPlayCoroutine()

        feed_Button.setOnClickListener {
            imageView.setImageResource(R.drawable.eatingdog)
            TextView.text = "i am now eating"
            hungerlevel = 100f
            progressbarfeed.progress = hungerlevel.toInt()

        }
        clean_Button.setOnClickListener {
            imageView.setImageResource(R.drawable.bathingdog)
            TextView.text = "i am now bathing"
            cleanlevel = 100f
            progressBarclean.progress = cleanlevel.toInt()
        }
       play_Button.setOnClickListener {
           imageView.setImageResource(R.drawable.playingbagel)
           TextView.text = "i am now playing"
           playlevel = 100f
           progressBarplay.progress = playlevel.toInt()
       }
        val returnButton = findViewById<Button>(R.id.returnButton)
        returnButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun launchPlayCoroutine(): Job {
        return CoroutineScope(Dispatchers.Main).launch {
            while (isActive){
                if (playlevel > 0f){
                    playlevel -= playDecreaseRate
                    progressBarplay.progress = playlevel.toInt()
                }
                delay(1000)
            }
        }

    }

    private fun launchCleanCoroutine(): Job {
        return CoroutineScope(Dispatchers.Main).launch {
            while (isActive){
                if (cleanlevel > 0f){
                    cleanlevel -= cleanDecreaseRate
                    progressBarclean.progress = cleanlevel.toInt()
                }
                delay(1000)
            }
        }
    }

    private fun launchFeedCoroutine(): Job {
        return CoroutineScope(Dispatchers.Main).launch {
            while (isActive) {
                if (hungerlevel > 0f) {
                    hungerlevel -= hungerdecreaserate
                    progressbarfeed.progress = hungerlevel.toInt()
                }
                delay(1000)
            }
        }

    }


}