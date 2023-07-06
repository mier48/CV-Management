package com.albertomier.cv_management.profile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.albertomier.cv_management.main.base.FabButton
import com.albertomier.cv_management.main.base.Title
import com.albertomier.cv_management.main.components.EducationItem
import com.albertomier.cv_management.main.data.SheetContentState
import com.albertomier.cv_management.profile.domain.model.EducationData
import com.albertomier.cv_management.profile.ui.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EducationScreen(viewModel: ProfileViewModel) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val sheetStateContent by viewModel.sheetStateContent.collectAsState()

    val listOfEducation: List<EducationData> by viewModel.educationList.observeAsState(initial = emptyList())

    ModalBottomSheetLayout(
        scrimColor = Color.Black.copy(alpha = 0.6f),
        sheetState = modalBottomSheetState,
        sheetElevation = 8.dp,
        sheetShape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp),
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetContent = {
            when (sheetStateContent) {
                SheetContentState.ADD -> {
                    AddEducationSheetContent(
                        context = context,
                        mainViewModel = viewModel,
                        modalBottomSheetState = modalBottomSheetState,
                        scope = scope,
                        viewModel = viewModel,
                        onAddClicked = {

                        }
                    )
                }
                SheetContentState.UPDATE -> {
                    UpdateEducationSheetContent(
                        context = context,
                        mainViewModel = viewModel,
                        onSaveClicked = {

                        }
                    )
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                FabButton(text = "Añadir", isVisibleBecauseOfScrolling = true, onItemClick = {
                    viewModel.setSheetStateContent(SheetContentState.ADD)
                    scope.launch {
                        modalBottomSheetState.show()
                    }
                })
            }
        ) {
            Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                when {
                    listOfEducation.isEmpty() -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Title(title = "No has añadido ningún título todavía")
                        }
                    }
                    else -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = MaterialTheme.colors.background)
                        ) {
                            items(listOfEducation) { item ->
                                EducationItem(item = item)
                            }
                        }
                    }
                }
            }
        }
    }
}