package com.example.newsapp.View

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.net.toUri
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.ViewModel.NewsViewModel
import com.example.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        splashScreen.setOnExitAnimationListener { splashView ->

            val iconView = splashView.iconView

            iconView.animate()
                .rotation(360f)
                .setDuration(400)
                .withEndAction {
                    iconView.animate()
                        .scaleX(1.3f)
                        .scaleY(1.3f)
                        .alpha(0f)
                        .setDuration(400)
                        .withEndAction {
                            splashView.remove()
                        }
                        .start()
                }
                .start()
        }
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = NewsAdapter(
            emptyList(),

            onItemClick = { article ->
                val intent = Intent(Intent.ACTION_VIEW, article.url.toUri())
                startActivity(intent)
            },

            onShareClick = { article ->
                ShareCompat.IntentBuilder(this)
                    .setType("text/plain")
                    .setChooserTitle("Share Article With:")
                    .setText(article.url)
                    .startChooser()
            }
        )

        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(this)




        var currentCategory = "general"
        vm.fetchNews(currentCategory)

        val chips = listOf(
            binding.chipGeneral,
            binding.chipSports,
            binding.chipTechnology,
            binding.chipScience
        )

        chips.forEach { chip ->
            chip.setOnClickListener {

                val category = when (chip.id) {
                    R.id.chipGeneral -> "general"
                    R.id.chipSports -> "sports"
                    R.id.chipTechnology -> "technology"
                    R.id.chipScience -> "science"
                    else -> "general"
                }


                if (category == currentCategory) return@setOnClickListener

                currentCategory = category

                // reset selection
                chips.forEach { it.isChecked = false }
                chip.isChecked = true

                vm.fetchNews(currentCategory)
            }
        }

        //  Observe news
        vm.news.observe(this) { newsList ->
            adapter.updateData(newsList)
        }


        //  Observe error
        vm.error.observe(this) { errorMsg ->
            binding.progress.visibility = View.GONE


            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
        }
    }
}