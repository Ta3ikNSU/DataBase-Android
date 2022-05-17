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
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*

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

        disableSSLCertificateChecking()

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
//                R.id.addann -> {
//                    supportFragmentManager.beginTransaction()
//                        .add(R.id.announcements_frame, AddAnnouncementFragment()).commit()
//                    android.util.Log.i(tag, "User come to car announcements")
//                }
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
    private fun disableSSLCertificateChecking() {
        val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(object : X509TrustManager {
            val acceptedIssuers: Array<Any?>?
                get() = null

            @Throws(CertificateException::class)
            override fun checkClientTrusted(arg0: Array<X509Certificate?>?, arg1: String?) {
                // Not implemented
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(arg0: Array<X509Certificate?>?, arg1: String?) {
                // Not implemented
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                TODO("Not yet implemented")
            }
        })
        try {
            val sc = SSLContext.getInstance("TLS")
            sc.init(null, trustAllCerts, SecureRandom())
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
            HttpsURLConnection.setDefaultHostnameVerifier { hostname, session -> true }
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }
}