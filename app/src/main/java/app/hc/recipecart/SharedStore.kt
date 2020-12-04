package app.hc.recipecart

import android.content.Context
import android.content.SharedPreferences
import android.util.Log


class SharedStore(activityContext: Context) {
    var activityContext: Context
    val STORE_NAME: String = "Cart_Reci"
    val TAG = "SharedStore"
    val sharedPreference: SharedPreferences = activityContext.getSharedPreferences(STORE_NAME, Context.MODE_PRIVATE)

    init {
        this.activityContext = activityContext
    }
    fun storeString(value: String?) {
        try {
            var editor = sharedPreference.edit()
            editor.putString("key", value)
            editor.commit()
        } catch (e: Exception) {
            Log.d(TAG, "Exception storeString $e")
        }
    }




    fun getString( default: String?): String?{
        try {


                val ret=sharedPreference.getString("key", default)

                return ret!!


            throw Exception("Null Parameter found")
        } catch (e: Exception) {
            Log.d(TAG, "Exception getString $e")
            return default
        }

    }
}
