@file:OptIn(ExperimentalMaterial3Api::class)

package com.albertomier.cv_management.main.base

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.albertomier.cv_management.R
import com.albertomier.cv_management.ui.theme.Typography

@Composable
fun TopAppBarMain(color: Color = Color.Transparent, tint: Color = Color.Red) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color,
            titleContentColor = tint
        ),
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = tint,
                style = Typography.titleLarge
            )
        },
    )
}