package com.example.contactapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ContactAdapter (val context: Context, var userList: ArrayList<UserData>): RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    lateinit var listener: ContactAdapterInterface

    //Untuk Menentukan Jumlah Item List yang di tampilkan
    override fun getItemCount(): Int {
        return userList.size
    }

    //Untuk Mengelola Inisialisai View (Tampilan Item)
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtName = itemView.findViewById<TextView>(R.id.tv_name)
        val txtAge = itemView.findViewById<TextView>(R.id.tv_age)
        val imgProfile = itemView.findViewById<ImageView>(R.id.img_profile)
        val linearView = itemView.findViewById<LinearLayout>(R.id.ll_view)
        val imgDelete = itemView.findViewById<ImageView>(R.id.img_delete)
    }

    //Untuk Set Data di Item List
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text = "${userList[position].first} ${userList.get(position).last}"
        holder.txtAge.text = "${userList[position].age}"
        Glide.with(context)
            .load(userList[position].url)
            .into(holder.imgProfile)
        holder.imgDelete.setOnClickListener {
            listener.onDeleteClick(userList[position])
        }
        holder.linearView.setOnClickListener {
            context.startActivity(Intent(context, ContactFormActivity::class.java).apply {
                putExtra(
                    ContactFormActivity::class.java.simpleName,
                    userList[position]
                )
            })
        }
    }

    //Untuk Mengambil View Item Contact
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(v)
    }

    interface ContactAdapterInterface {
        fun onDeleteClick(userData: UserData)
    }
}
