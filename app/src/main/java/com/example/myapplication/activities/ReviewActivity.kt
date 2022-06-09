package com.example.myapplication.activities

import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.DTO.ReviewDTO
import com.example.myapplication.Entity.User
import com.example.myapplication.R
import com.example.myapplication.RetrofitServices
import com.example.myapplication.retrofit.RetrofitClient
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ReviewActivity : AppCompatActivity() {
    var myExecutor: ExecutorService = Executors.newFixedThreadPool(2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val reviews: ArrayList<ReviewDTO> =
            this.intent.extras!!.getSerializable("reviews") as ArrayList<ReviewDTO>
        val user: User = intent.extras!!.getSerializable("user") as User
        setContentView(R.layout.admin_anns)
        val backbutton: ImageButton = findViewById(R.id.act_ann_button_back_admin)
        backbutton.setOnClickListener {
            finish()
        }

        val descriptions = Array(reviews.size) { "" }
        for (i in descriptions.indices) {
            descriptions.set(i, reviews.get(i)!!.description)
        }

        val listView: ListView = findViewById(R.id.admin_anns_list)
        listView.adapter = ArrayAdapter(this, R.layout.admin_anns, descriptions)

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(view.context, AnnouncementActivity::class.java)
            intent.putExtra("review", reviews[position])
            intent.putExtra("user", user)
            view.context.startActivity(intent)
        }
    }
}

class AdminChangeableReview : AppCompatActivity() {
    var myExecutor: ExecutorService = Executors.newFixedThreadPool(2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.admin_review)
        val description: EditText = findViewById(R.id.review_admin_text)
        val intent = this.intent

        var review: ReviewDTO = intent.extras!!.getSerializable("review") as ReviewDTO

        val user: User = intent.extras!!.getSerializable("user") as User
        val mail: String = user.mail
        description.text = SpannableStringBuilder(review.description);

        val backbutton: ImageButton = findViewById(R.id.act_ann_button_back)
        backbutton.setOnClickListener {
            val apiService: RetrofitServices =
                RetrofitClient.getClient().create(RetrofitServices::class.java)
            val call = apiService.updateAnnouncement(
                mail,
                ReviewDTO(review.id, description.text.toString())
            )
            myExecutor.execute {
                call.execute()
            }
            finish()
        }
        val deletebutton: Button = findViewById(R.id.delete_button_review)
        deletebutton.setOnClickListener {
            val apiService: RetrofitServices =
                RetrofitClient.getClient().create(RetrofitServices::class.java)
            val call = apiService.deleteReview(
                mail,
                ReviewDTO(review.id, description.text.toString())
            )
            myExecutor.execute {
                call.execute()
            }
            finish()
        }
    }
}