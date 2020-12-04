

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import app.hc.recipecart.DataModel
import app.hc.recipecart.MainActivity
import app.hc.recipecart.R
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso

/**
 * Created by Belal on 6/19/2017.
 */

class HomeAdapter(val context:MainActivity,val dataModel: DataModel) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

val TAG="fssfds"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        Log.d(TAG,"DataRec")
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
       holder.bindItems(dataModel[position])
        Picasso.with(context).load(dataModel.get(position).image).into(holder.img)
        holder.addToCart.setOnClickListener()
        {
            context.updateCart(dataModel.get(position).id)
            Toast.makeText(context,"Added To Cart",Toast.LENGTH_SHORT).show()
        }
        holder.cardVeiw.setOnClickListener(){
            Toast.makeText(context,dataModel[position].description,Toast.LENGTH_SHORT).show()
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
        val img = itemView.findViewById(R.id.itemImage) as ImageView
        val addToCart = itemView.findViewById(R.id.addToCartBtn) as MaterialButton
        val cardVeiw = itemView.findViewById(R.id.cardview1) as CardView

        fun bindItems(dataModelItem: DataModel.DataModelItem) {

            item.text=dataModelItem.name
            rate.text=dataModelItem.price

        }
    }
}
