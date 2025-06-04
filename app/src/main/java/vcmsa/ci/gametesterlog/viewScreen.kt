package vcmsa.ci.gametesterlog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


    class DetailedViewScreenActivity : AppCompatActivity() {

        private lateinit var tvEntries: TextView
        private lateinit var tvStats: TextView
        private lateinit var btnBack: Button
        private lateinit var btnExit: Button

        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_detailed_view)

            tvEntries = findViewById(R.id.tvEntries)
            tvStats = findViewById(R.id.tvStats)
            btnBack = findViewById(R.id.btnBack)
            btnExit = findViewById(R.id.btnExit)

            // Get data from intent
            val dates = intent.getStringArrayListExtra("dates") ?: arrayListOf()
            val minutes = intent.getIntegerArrayListExtra("minutes") ?: arrayListOf()
            val genres = intent.getStringArrayListExtra("genres") ?: arrayListOf()
            val ratings = intent.getIntegerArrayListExtra("ratings") ?: arrayListOf()

            displayEntries(dates, minutes, genres, ratings)
            displayStats(minutes, genres, ratings)

            btnBack.setOnClickListener {
                finish() // Go back to MainInputActivity
            }

            btnExit.setOnClickListener {
                AlertDialog.Builder(this)
                    .setTitle("Exit App")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes") { _, _ -> finishAffinity() }
                    .setNegativeButton("Cancel", null)
                    .show()
            }
        }

        private fun displayEntries(
            dates: ArrayList<String>,
            minutes: ArrayList<Int>,
            genres: ArrayList<String>,
            ratings: ArrayList<Int>
        ) {
            if (dates.isEmpty()) {
                tvEntries.text = "No entries to display."
                return
            }

            val entryText = StringBuilder()
            for (i in dates.indices) {
                entryText.append("Entry ${i + 1}:\n")
                entryText.append("Date: ${dates[i]}\n")
                entryText.append("Minutes Played: ${minutes[i]}\n")
                entryText.append("Genre: ${genres[i]}\n")
                entryText.append("Rating: ${ratings[i]}\n\n")
            }
            tvEntries.text = entryText.toString()
        }

        private fun displayStats(
            minutes: ArrayList<Int>,
            genres: ArrayList<String>,
            ratings: ArrayList<Int>
        ) {
            if (minutes.isEmpty()) {
                tvStats.text = "No statistics to calculate."
                return
            }

            val totalSessions = minutes.size
            val totalMinutes = minutes.sum()
            val avgMinutes = totalMinutes / totalSessions

            // Find highest-rated game and its genre
            val maxRating = ratings.maxOrNull() ?: 0
            val maxIndex = ratings.indexOfFirst { it == maxRating }
            val topGenre = if (maxIndex != -1) genres[maxIndex] else "N/A"

            val statsText = """
            Total Play Sessions: $totalSessions
            Average Minutes Played: $avgMinutes
            Highest-Rated Game: $maxRating Stars
            Genre of Highest-Rated: $topGenre
        """.trimIndent()

            tvStats.text = statsText
        }
    }

