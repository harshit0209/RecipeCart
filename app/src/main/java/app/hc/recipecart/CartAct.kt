package app.hc.recipecart

import CartAdapter
import HomeAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class CartAct : AppCompatActivity() {
    val TAG="CArt23"
    lateinit var amtTV:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)


        amtTV=findViewById(R.id.totalAmount)
        var data=SharedStore(this).getString("NaN")


        var gson = Gson()
        var testModel = gson.fromJson(data, DataModel::class.java)
        Log.d(TAG,testModel[0].description)




        var homeR=findViewById<RecyclerView>(R.id.home_recycler)
        homeR.layoutManager= GridLayoutManager(this,1)
        homeR.setHasFixedSize(true)
        homeR.adapter=CartAdapter(this,testModel)
        var amt=0.0
        for (i in testModel)
            amt+=i.price.toDouble()
        updateAmount(amt)
    }
    fun updateAmount(amt:Double)
    {
        amtTV.text=" ₹ "+amt
    }
}