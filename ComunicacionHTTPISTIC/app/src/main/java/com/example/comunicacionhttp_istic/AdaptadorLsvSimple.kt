package com.example.comunicacionhttp_istic

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide.with
import com.squareup.picasso.Picasso
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.InputStream
import java.net.URL

class AdaptadorLsvSimple(var contexto: Context, listado: ArrayList<Pais>) :BaseAdapter(){

    var listado:ArrayList<Pais>?=null
    init {
        this.listado=listado
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
      //  TODO("Not yet implemented")


        var holder :ViewHolder?=null
        var vista:View?=convertView
        if(vista==null)
        {
            vista=LayoutInflater.from(contexto).inflate(R.layout.filaconfotos, null)
            holder =ViewHolder(vista)
            vista.tag=holder
        }
        else
        {
            holder=vista.tag as? ViewHolder
        }
        val unPais=getItem(position)as Pais

        var imgUri = "https://robohash.org/utsitnecessitatibus.png"

        holder?.nombre?.text=unPais.nombre
        holder?.region?.text=unPais.region
        Picasso.get().load(imgUri).into(holder?.bandera)

        return vista!!
    }

    override fun getItem(position: Int): Any {
      //  TODO("Not yet implemented")
        return listado?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        //TODO("Not yet implemented")
        return  position.toLong()
    }

    override fun getCount(): Int {
       //TODO("Not yet implemented")
        return listado?.count()!!// para optener el valor !!
    }
    private class ViewHolder(vista: View){
        var nombre :TextView?=null
            var region:TextView?=null
            var bandera:ImageView?=null
            init {
                nombre=vista.findViewById(R.id.txtNombre)
                region=vista.findViewById(R.id.txtRegion)
                bandera=vista.findViewById(R.id.imgBandera)
        }
    }

}