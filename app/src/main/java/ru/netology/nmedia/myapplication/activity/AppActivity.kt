package ru.netology.nmedia.myapplication.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.myapplication.R
import ru.netology.nmedia.myapplication.viewmodel.DataModel

class AppActivity : AppCompatActivity() {
    private val dataModel : DataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
//        dataModel.postIdMessage.observe(this, {
//            bind
//        })
    }
}