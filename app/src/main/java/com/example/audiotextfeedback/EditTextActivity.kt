package com.example.audiotextfeedback

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


class EditTextActivity : AppCompatActivity() {
    lateinit var counter: TextView
    lateinit var picker: Button
    lateinit var pdf: Button
    lateinit var text: TextView
    lateinit var imagepicker: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)



        text = findViewById(R.id.text_input)
        picker = findViewById(R.id.filepicker)
        counter = findViewById(R.id.counterId)
//         pdf = findViewById(R.id.pdf)
        imagepicker = findViewById(R.id.imageview)
        var count = 0;

//        pdf.setOnClickListener {
//            val intent = Intent(this, PdfViewer::class.java)
//        }
        picker.setOnClickListener {
            var colors_1: Array<String> = arrayOf("image/*", "application/pdf")
            val intent = Intent()
                .setType("*/*")
                .addCategory(Intent.CATEGORY_OPENABLE)
                .putExtra(Intent.EXTRA_MIME_TYPES, colors_1)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .setAction(Intent.ACTION_OPEN_DOCUMENT)

            startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
        }

        text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                count = 500 - text.length()
                counter.text = count.toString() + " Characters left"
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(

                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                111
            )

        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            val selectedFile = data?.data
   if(selectedFile.toString().contains("image"))
            imagepicker.setImageURI(selectedFile)
            imagepicker.getLayoutParams().height = 500;




            //The uri with the location of the file
        }
    }
}