package com.err.kotlin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.security.Permission

class MainActivity : AppCompatActivity() {

    lateinit var edt:EditText
    lateinit var text:TextView
    lateinit var text1:TextView
    lateinit var text2:TextView
    lateinit var string:String
    lateinit var spinner:Spinner
    lateinit var seekbar:SeekBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        edt=findViewById(R.id.edt) as EditText
        text=findViewById(R.id.text) as TextView
        text1=findViewById(R.id.text1) as TextView
        text2=findViewById(R.id.text2) as TextView
        spinner=findViewById(R.id.spinner) as Spinner
        seekbar=findViewById(R.id.seekbar) as SeekBar

        val options= arrayOf("one","two","three")
        spinner.adapter=ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,options)

        spinner.onItemSelectedListener=object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                text1.text="No option selected"
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
             text1.text=options.get(p2)
            }

        }


        seekbar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                text2.text=p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })




        btn.setOnClickListener {
           string=edt.text.toString()
           if(string.isEmpty()){
               Toast.makeText(applicationContext,"Please enter name",Toast.LENGTH_LONG).show()
           }
            else
               text.text=string

//            sendBroadcast(Intent("MyReceiver"))
        }


        permission.setOnClickListener {
requestPermissions()
        }


    }

    private fun  isFLocationGranted(): Boolean {
        return  ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions(){
        var perRequestList= mutableListOf<String>()

        Log.d("Permission granted",isFLocationGranted().toString())
        if(!isFLocationGranted()){
            perRequestList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)

        }
        if(perRequestList.isEmpty()){
            Toast.makeText(this,"permission granted",Toast.LENGTH_LONG).show()
        }

        if(perRequestList.isNotEmpty()){
            ActivityCompat.requestPermissions(this,perRequestList.toTypedArray(),0)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==0 && permissions.isNotEmpty()){
            for(i in grantResults.indices){

                Log.d("Permission granted","${grantResults[i]} permission")
            }
        }
    }
}