package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.FoodBean.DataBean
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mainRv: RecyclerView? = null
    private var foodAdapter: FoodAdapter? = null
    private var list: List<DataBean>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list = ArrayList()
        foodAdapter = FoodAdapter()
        mainRv = findViewById<View>(R.id.main_rv) as RecyclerView
        mainRv!!.adapter = foodAdapter
        mainRv!!.layoutManager = LinearLayoutManager(this)
        mainRv!!.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        initFood()
    }

    private fun initFood() {
        Log.i("xnhh", "initFood: ")


//        OkHttpClient client = new OkHttpClient.Builder()
////                .connectTimeout(2000, TimeUnit.MILLISECONDS)
////                .readTimeout(2000, TimeUnit.MILLISECONDS)
////                .writeTimeout(2000, TimeUnit.MILLISECONDS)
////                .build();
////
////        Request request = new Request.Builder()
////                .url("http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1")
////                .get()
////                .build();
////
////        client.newCall(request)
////                .enqueue(new Callback() {
////                    @Override
////                    public void onFailure(Call call, IOException e) {
////                        Log.i("xnhh", "onFailure: " + e.getMessage());
////                    }
////
////                    @Override
////                    public void onResponse(Call call, okhttp3.Response response) throws IOException {
////                        Log.i("xnhh", "onResponse: " + response.toString());
////                        FoodBean foodBean = new Gson().fromJson(response.body().string(), FoodBean.class);
////                        list.addAll(foodBean.getData());
////                        foodAdapter.setNewData(list);
////                    }
////                });
        OkGo.get<String>("http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1")
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val foodBean = Gson().fromJson(response.body(), FoodBean::class.java)
                        Log.i("xnhh", "onSuccess: " + response.message())
                        list.addAll(foodBean.data)
                        foodAdapter!!.setNewData(list)
                    }

                    override fun onError(response: Response<String>) {
                        super.onError(response)
                        Log.i("xnhh", "onError: " + response.message())
                    }
                })
    }
}