package kotlincodes.com.retrofitwithkotlin.main

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlincodes.com.retrofitwithkotlin.R
import kotlincodes.com.retrofitwithkotlin.adapters.DataAdpter
import kotlincodes.com.retrofitwithkotlin.model.DataModel
import kotlincodes.com.retrofitwithkotlin.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var progerssProgressDialog: ProgressDialog
    var dataList = ArrayList<DataModel>()
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI();

        setAppBar();

        setUpRecyclerView();
    }


    private fun initUI() {
        recyclerView = findViewById(R.id.recycler_view)

        progerssProgressDialog = ProgressDialog(this)
    }

    private fun setAppBar() {
        getSupportActionBar()?.title = "HOME"
    }


    private fun setUpRecyclerView() {
        //setting up the adapter
        recyclerView.adapter = DataAdpter(dataList, this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        getDat1a()
    }

    private fun getDat1a() {
        progerssProgressDialog.setTitle("Getting users. Please wait...")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()


        val call: Call<List<DataModel>> = ApiClient.getClient.getUsers()
        call.enqueue(object : Callback<List<DataModel>> {

            override fun onResponse(call: Call<List<DataModel>>?, response: Response<List<DataModel>>?) {
                progerssProgressDialog.dismiss()
                dataList.addAll(response!!.body()!!)

                recyclerView.adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<DataModel>>?, t: Throwable?) {
                progerssProgressDialog.dismiss()
            }

        })
    }


}

