package app.hc.recipecart

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Splash : AppCompatActivity() {


    lateinit var context:Splash
    val TAG="SplashActi123"
    //this : null object reference here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        context=this



        getAllRecipe()

//        GlobalScope.launch {
//            delay(500)
//            sessionsFlowforUser(sharedStore)
//        }




    }

    fun getAllRecipe()
    {
        val retrofit = Retrofit.Builder()

            .baseUrl("https://s3-ap-southeast-1.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(Api::class.java)

        api.fetchData()?.enqueue(object : Callback<ResponseBody?> {
            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Log.d(TAG, "error : ")
                t.printStackTrace()
                Toast.makeText(context,"On Faliure", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<ResponseBody?>,
                response: Response<ResponseBody?>
            ) {
                val responseStr = response.body()?.string()
                if (responseStr != null) {

                    val i= Intent(context,MainActivity::class.java)
                    i.putExtra("data",responseStr)
                    startActivity(i)
                    finishAffinity()
                }
                Toast.makeText(context,"Data Received", Toast.LENGTH_LONG).show()
                Log.d(TAG,""+responseStr.toString())
            }

        })
    }

}
