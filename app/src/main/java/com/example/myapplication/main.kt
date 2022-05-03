package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.fragment.AnnouncementsFragment
import com.example.myapplication.fragment.DetailsFragment
import com.example.myapplication.fragment.MenuFragment
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import javax.net.ssl.SSLContext


class main : AppCompatActivity() {
    private val tag: String = this.javaClass.name

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            // Google Play will install latest OpenSSL
            ProviderInstaller.installIfNeeded(applicationContext)
            val sslContext: SSLContext = SSLContext.getInstance("TLSv1.2")
            sslContext.init(null, null, null)
            sslContext.createSSLEngine()
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        }

        setContentView(R.layout.activity_main)
        val chip: ChipNavigationBar = findViewById(R.id.bottom_menu)
        chip.setItemSelected(R.id.ann)
        supportFragmentManager.beginTransaction()
            .add(R.id.announcements_frame, AnnouncementsFragment()).commit()
        chip.setOnItemSelectedListener {
            when (it) {
                R.id.ann -> {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.announcements_frame, AnnouncementsFragment()).commit()
                    android.util.Log.i(tag, "User come to car announcements")
                }

                R.id.details -> {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.announcements_frame, DetailsFragment()).commit()
                    android.util.Log.i(tag, "User come to details announcements ")
                }
                R.id.menu -> {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.announcements_frame, MenuFragment()).commit()
                    android.util.Log.i(tag, "User come to menu")
                }
            }
        }

    }
}