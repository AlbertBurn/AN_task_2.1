package ru.netology.nmedia.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.myapplication.repository.PostRepository
import ru.netology.nmedia.myapplication.repository.PostRepositoryInMemoryImpl

class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)

//    //This function change number for format of app
//    fun changeNumber(count: Int): String {
//        val numberFirstToStr: Int
//        val numberSecondToStr: Int
//        if ((count >= 1_000) && (count < 10_000)) {
//            numberFirstToStr = count / 1_000
//            numberSecondToStr = ((count % 1_000) / 100)
//            if (numberSecondToStr == 0) {
//                return "$numberFirstToStr" + "K"
//            } else return "$numberFirstToStr.$numberSecondToStr" + "K"
//        } else if ((count >= 10_000) && (count < 1_000_000)) {
//            numberFirstToStr = count / 1_000
//            return "$numberFirstToStr" + "K"
//        } else if (count >= 1_000_000) {
//            numberFirstToStr = count / 1_000_000
//            numberSecondToStr = ((count % 1_000_000) / 100_000)
//            if (numberSecondToStr == 0) {
//                return "$numberFirstToStr" + "M"
//            } else return "$numberFirstToStr.$numberSecondToStr" + "M"
//        } else {
//            return "$count"
//        }
//    }
}