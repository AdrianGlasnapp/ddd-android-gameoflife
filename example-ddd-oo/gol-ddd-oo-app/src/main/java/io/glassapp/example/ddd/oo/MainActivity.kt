package io.glassapp.example.ddd.oo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.glassapp.example.ddd.oo.ui.game.GameFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, GameFragment.newInstance())
                .commitNow()
        }
    }
}
