package com.example.comunicacionhttp_istic

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGExternalFileResolver
import com.caverock.androidsvg.SVGImageView
import com.caverock.androidsvg.SVGParseException
import com.example.comunicacionhttp_istic.ConsultaDatos.Companion.consultarDatos

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

        val svgFlagString = consultarDatos(unPais.flag)

        holder?.name?.text = unPais.name
        holder?.capital?.text = unPais.capital
        holder?.timezones?.text = unPais.timezones
        holder?.flag?.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        try {
            val svg: SVG = SVG.getFromString(svgFlagString)
            val drawable: Drawable = PictureDrawable(svg.renderToPicture())
            holder?.flag?.setImageDrawable(drawable)
        } catch (e: SVGParseException) {
            Log.d("ERROR SVG", e.message)
        }

        //holder?.cod?.tex
        //Picasso.get().load(imgUri).into(holder?.bandera)

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
        var name :TextView? = null
        var capital:TextView? = null
        var timezones:TextView? = null
        var flag:ImageView? = null
        init {
            name = vista.findViewById(R.id.txtNombrePais)
            capital = vista.findViewById(R.id.txtCapital)
            timezones = vista.findViewById(R.id.txtTimezone)
            flag = vista.findViewById(R.id.imgBandera)
        }
    }

}