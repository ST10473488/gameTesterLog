package vcmsa.ci.gametesterlog

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

    class SplashActivity : AppCompatActivity() {


        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_splash)

            // Ensure buttons exist in layout
            val btnStart = findViewById<Button>(R.id.btnStart)
            val btnExit = findViewById<Button>(R.id.btnExit)

            // Navigate to Main Input Screen (Replace with actual target activity)
            btnStart.setOnClickListener {
                val intent = Intent(
                    this,
                    MainInputActivity::class.java
                )  // <--- Replace with your actual activity name
                startActivity(intent)
                finish() // Optional: Finish splash screen
            }

            // Exit the app
            btnExit.setOnClickListener {
                finishAffinity() // Close all activities and exit app
            }
        }

        class MainInputActivity {

        }
    }




