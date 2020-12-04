package app.hc.recipecart

import HomeAdapter

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    val TAG="Main_ACt"
    lateinit var cart_badge :TextView
    lateinit var context :MainActivity
    var dataStr=""

    var arl : ArrayList<Int>
            = ArrayList<Int> ()
    var hashmap:HashMap<Int, DataModel.DataModelItem> =HashMap<Int, DataModel.DataModelItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context=this
        getSupportActionBar()?.setTitle("Home");

        var data=intent.getStringExtra("data")
        dataStr=""+data
        Log.d(TAG, data.toString())





        var gson = Gson()
        var testModel = gson.fromJson(data, DataModel::class.java)
        Log.d(TAG, testModel[0].description)



        for (i in testModel)
            hashmap.put(i.id, i)



        var homeR=findViewById<RecyclerView>(R.id.home_recycler)
        homeR.layoutManager=GridLayoutManager(context, 2)
        homeR.setHasFixedSize(true)
        homeR.adapter=HomeAdapter(this, testModel)





    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater

        menuInflater.inflate(R.menu.appbar, menu)
        val relativeLayout_c = menu?.findItem(R.id.menu_appbar_cart)?.actionView

        relativeLayout_c?.setOnClickListener {
            onOptionsItemSelected(menu.findItem(R.id.menu_appbar_cart))
        }

        if(relativeLayout_c!=null)
        cart_badge = relativeLayout_c?.findViewById<TextView>(R.id.counter)
        else {
            Toast.makeText(context, "Exception onCreatMenu", Toast.LENGTH_SHORT).show()

        }


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Toast.makeText(this, "clicked : " + item.title, Toast.LENGTH_SHORT).show()
        if (item.itemId == R.id.menu_appbar_cart) {

            if(SharedStore(this).getString("NaN")?.length!! <5)
            {
                Toast.makeText(context, "Cart Empty\nTry Adding Something...", Toast.LENGTH_LONG).show()
            }
            else{
            val i= Intent(context, CartAct::class.java)
            startActivity(i)
            }
            //Start Acctivity
        }
        return super.onOptionsItemSelected(item)
    }
    fun updateCart(id: Int){


        if(!arl.contains(id)){
            arl.add(id)

        cart_badge.text= arl.size.toString()
        val dataModel:DataModel= DataModel()

        for(i in arl)
            dataModel.add(hashmap.get(i)!!)
        var str=Gson().toJson(dataModel)
        SharedStore(this).storeString(str)}
    }

}