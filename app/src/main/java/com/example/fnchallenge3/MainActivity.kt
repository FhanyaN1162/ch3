package com.example.fnchallenge3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.view.View

class MainActivity : AppCompatActivity(), FragmentMenu.FragmentMenuListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Menampilkan FragmentMenu saat aplikasi pertama kali dibuka
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container1, FragmentMenu())
            .commit()
    }

    override fun onMenuItemClicked(args: Bundle) {
        // Buat instance FragmentDetail dan kirimkan data menggunakan Bundle
        val detailFragment = FragmentDetails()
        detailFragment.arguments = args

        // Tampilkan FragmentDetail di container2
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container2, detailFragment)
            .addToBackStack(null)
            .commit()

        findViewById<View>(R.id.fragment_container1).visibility = View.GONE
        findViewById<View>(R.id.fragment_container3).visibility = View.GONE
        findViewById<View>(R.id.banner_menu).visibility = View.GONE
        findViewById<View>(R.id.fragment_container2).visibility = View.VISIBLE
    }

    // Override onBackPressed untuk mengatasi tombol "Back"
    override fun onBackPressed() {
        val fragment2 = supportFragmentManager.findFragmentById(R.id.fragment_container2)

        if (fragment2 != null && fragment2.isVisible) {
            // Kembali ke tampilan sebelumnya
            supportFragmentManager.popBackStack()
            findViewById<View>(R.id.fragment_container1).visibility = View.VISIBLE
            findViewById<View>(R.id.fragment_container3).visibility = View.VISIBLE
            findViewById<View>(R.id.banner_menu).visibility = View.VISIBLE
            findViewById<View>(R.id.fragment_container2).visibility = View.GONE
        } else {
            super.onBackPressed()
        }
    }

    // Fungsi untuk membuka Google Maps
    fun openGoogleMaps(googleMapsUrl: String) {
        val gmmIntentUri = Uri.parse(googleMapsUrl)
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        }
    }
}

