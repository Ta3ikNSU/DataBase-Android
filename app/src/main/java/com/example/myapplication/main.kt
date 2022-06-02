package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Entity.User
import com.example.myapplication.fragment.AddAnnouncementFragment
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
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

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
        val user: User = User()
        supportFragmentManager.beginTransaction()
            .add(R.id.announcements_frame, AnnouncementsFragment(user)).commit()
        var prevItem: Int = R.id.ann
        chip.setOnItemSelectedListener {
            when (it) {
                R.id.ann -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.announcements_frame, AnnouncementsFragment(user)).commit()
                    supportFragmentManager.beginTransaction()
                    android.util.Log.i(tag, "User come to car announcements")
                    prevItem = R.id.ann
                }
                R.id.addann -> {
                    if (user.isAuth) {
                        supportFragmentManager.beginTransaction()
                            .add(R.id.announcements_frame, AddAnnouncementFragment()).commit()
                        android.util.Log.i(tag, "User come to car announcements")
                        prevItem = R.id.addann
                    } else {
                        val text = "Зарегистрируйтесь или войдите в свой аккаунт, чтобы создать объявление"
                        Toast.makeText(this.applicationContext, text, text.length).show()
                        chip.setItemSelected(prevItem)
                    }
                }
                R.id.details -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.announcements_frame, DetailsFragment()).commit()
                    android.util.Log.i(tag, "User come to details announcements ")
                    prevItem = R.id.details
                }
                R.id.menu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.announcements_frame, MenuFragment(user)).commit()
                    supportFragmentManager.beginTransaction()
                    android.util.Log.i(tag, "User come to menu")
                    prevItem = R.id.menu
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