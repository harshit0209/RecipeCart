

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.hc.recipecart.*
import com.google.gson.Gson
import com.squareup.picasso.Picasso

/**
 * Created by Belal on 6/19/2017.
 */

class CartAdapter(val context:CartAct,val dataModel: DataModel) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    val TAG="fssfds"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        Log.d(TAG,"DataRec")
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        var dataModelItem=dataModel[position]
        holder.bindItems(dataModel[position])
        Picasso.with(context).load(dataModel.get(position).image).into(holder.img)


        holder.del.setOnClickListener{
         dataModel.remove(dataModelItem)
         var s=Gson().toJson(dataModel)
         SharedStore(context).storeString(s)
            notifyDataSetChanged()
            var amt=0.0
            for (i in dataModel)
                amt+=i.price.toDouble()
            context.updateAmount(amt)
        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return dataModel.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val item = itemView.findViewById(R.id.itemName) as TextView
        val rate = itemView.findViewById(R.id.itembaserate) as TextView
        val qty = itemView.findViewById(R.id.itemCount) as TextView

        val del = itemView.findViewById(R.id.removeCartItem) as TextView
        val img = itemView.findViewById(R.id.itemImage) as ImageView
        fun bindItems(dataModelItem: DataModel.DataModelItem) {

            item.text=dataModelItem.name
            rate.text="₹ "+dataModelItem.price
            qty.text="1"



        }
    }
}
