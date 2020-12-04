package app.hc.recipecart

class DataModel : ArrayList<DataModel.DataModelItem>(){
    data class DataModelItem(
        val id: Int,
        val name: String,
        val image: String,
        val category: String,
        val label: String,
        val price: String,
        val description: String
    )
}