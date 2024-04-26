package com.example.latihan_network.ui.home
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latihan_network.model.Tweet
import com.example.latihan_network.model.TweetResponse
import com.example.latihan_network.network.ApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.job
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

import javax.security.auth.callback.Callback


class HomeViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response

    private val  _teksTweet = MutableLiveData<List<Tweet>>()
    val  teksTweet : LiveData<List<Tweet>>
        get() =  _teksTweet

    private var vmjob = Job()
    private val crScope = CoroutineScope (vmjob + Dispatchers.IO)


    init {
        initData()
    }

    fun initData(){
        crScope.launch {
            try {
                val items = ApiCall.retrofitService.showList()
                val tweetData = items.data
                val tweetMessage = items.message
                val tweetBool = items.success
                if (tweetData.isNotEmpty()){
                    _teksTweet.postValue(tweetData.reversed())
                    _response.postValue(tweetMessage)
                }

                if (tweetBool){
                    _teksTweet.postValue(tweetData)
                }
            }catch (t : Throwable){
                Log.i("dataAPI", t.message.toString())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmjob.cancel()
    }

//    fun initData() {
//        ApiCall.retrofitService.showList().enqueue(object : Callback,
//            retrofit2.Callback<TweetResponse>{
//            override fun onResponse(call: Call<TweetResponse>, hasil: Response<TweetResponse>) {
//                _response.value = hasil.body()!!.message
//                _teksTweet.value = hasil.body()!!.data
//           }
//
//            override fun onFailure(call: Call<TweetResponse>, t: Throwable) {
//                _response.value ="failed"
//            }
//        })
//
//        _response.value = "hanya data kosong"
//    }

}




