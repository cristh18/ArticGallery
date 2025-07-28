package com.tolodev.artic_gallery.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.tolodev.artic_gallery.R
import com.tolodev.imperative.MainImperativeActivity
import timber.log.Timber

class WelcomeActivity : AppCompatActivity(R.layout.activity_welcome), OnClickListener {

    private var buttonHybrid: Button? = null

    private var buttonCompose: Button? = null

    private var buttonXml: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
        buttonHybrid = findViewById(R.id.button_hybrid)
        buttonCompose = findViewById(R.id.button_compose)
        buttonXml = findViewById(R.id.button_xml)
    }

    private fun initListeners() {
        buttonHybrid?.setOnClickListener(this)
        buttonCompose?.setOnClickListener(this)
        buttonXml?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            buttonHybrid?.id -> {
                Timber.d("Hybrid button clicked")
                startActivity(Intent(this, MainActivity::class.java))
            }

            buttonCompose?.id -> {
                Timber.d("Compose button clicked")
                Toast.makeText(this, "Compose is not implemented yet", Toast.LENGTH_SHORT).show()
            }

            buttonXml?.id -> {
                Timber.d("Xml button clicked")
                Toast.makeText(this, "Xml is not implemented yet", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainImperativeActivity::class.java))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        buttonXml = null
        buttonHybrid = null
        buttonCompose = null
    }
}