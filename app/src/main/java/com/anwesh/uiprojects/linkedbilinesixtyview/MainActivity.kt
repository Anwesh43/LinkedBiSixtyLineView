package com.anwesh.uiprojects.linkedbilinesixtyview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.bilinesixtyview.BiLineSixtyView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BiLineSixtyView.create(this)
    }
}
