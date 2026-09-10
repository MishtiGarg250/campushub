package com.campus.hub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.campus.hub.ui.navigation.CampusNavigation
import com.campus.hub.ui.theme.CampushubTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        setContent {

            CampushubTheme() {

                CampusNavigation()
            }
        }
    }
}