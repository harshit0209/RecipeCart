package app.hc.recipecart

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {



    @GET("he-public-data/reciped9d7b8c.json")
    fun fetchData(
    ): Call<ResponseBody?>?

}
