package com.twinrock.mymvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.twinrock.mymvvm.R
import com.twinrock.mymvvm.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}