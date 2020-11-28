package com.example.shopping

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.databinding.ItemBinding
import com.google.firebase.firestore.QueryDocumentSnapshot

data class Item(val id: String, val name: String, val price: Int, val cart: Boolean) {
    constructor(doc: QueryDocumentSnapshot) :
            this(doc.id, doc["name"].toString(), doc["price"].toString().toIntOrNull() ?: 0, doc["cart"].toString().toBoolean())
    constructor(key: String, map: Map<*, *>) :
            this(key, map["name"].toString(), map["price"].toString().toIntOrNull() ?: 0, map["cart"].toString().toBoolean())
}

class MyViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

class MyAdapter(private val context: Context, private var items: List<Item>)
    : RecyclerView.Adapter<MyViewHolder>() {

    fun interface OnItemClickListener {
        fun onItemClick(product_id: String)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    fun updateList(newList: List<Item>) {
        items = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemBinding = ItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.binding.textID.text = item.id
        holder.binding.textName.text = item.name
        if (item.cart)
            holder.binding.cartIn.text = "in Cart"
        holder.binding.textID.setOnClickListener {
            val intent = Intent(holder.itemView.context, FirestoreActivity::class.java)
            intent.putExtra("id", item.id)
            intent.putExtra("name", item.name)
            intent.putExtra("price", item.price.toString())
            intent.putExtra("cart", item.cart)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
        holder.binding.textName.setOnClickListener {
            val intent = Intent(holder.itemView.context, FirestoreActivity::class.java)
            intent.putExtra("id", item.id)
            intent.putExtra("name", item.name)
            intent.putExtra("price", item.price.toString())
            intent.putExtra("cart", item.cart)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }

    }

    override fun getItemCount() = items.size
}