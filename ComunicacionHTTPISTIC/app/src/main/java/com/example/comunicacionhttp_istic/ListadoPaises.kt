package com.example.comunicacionhttp_istic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.JsonReader
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.comunicacionhttp_istic.ConsultaDatos.Companion.consultarDatos
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONStringer
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class ListadoPaises : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_paises)

        var lsvPaises=findViewById<ListView>(R.id.LsvPaises)

        val datos=consultarDatos("https://restcountries.eu/rest/v2/all")
       // Log.d("ConsultaSimple" ,datos)
       // val arrayDenombreDePaises= emptyArray<String>()
        val arrayDenombreDePaises:  ArrayList<String> = ArrayList()
        val arrayDeobjetosDePaises:  ArrayList<Pais> = ArrayList()
        val datosObjectJson= JSONArray(datos)

        for ( i in 0..10)
        {
            var pais= datosObjectJson.getJSONObject(i)
            Log.d(pais.getString("name") ,pais.toString(0))
            Log.d("pais listado", pais.getString("name"))
            arrayDenombreDePaises.add( pais.getString("name"))
            arrayDeobjetosDePaises.add(Pais( pais.getString("name"), pais.getString("capital"),pais.getString("timezones"),pais.getString("flag")))
        }
        val adaptadorSimple=AdaptadorLsvSimple(this,arrayDeobjetosDePaises)
        lsvPaises.adapter=adaptadorSimple



      // val adaptador= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayDenombreDePaises)
       //lsvPaises.adapter=adaptador

    }

}