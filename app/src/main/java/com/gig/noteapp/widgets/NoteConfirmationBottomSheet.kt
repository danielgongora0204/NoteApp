package com.gig.noteapp.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gig.noteapp.components.NoteButton
import com.gig.noteapp.models.database.Note
import com.gig.noteapp.utilities.extensions.default
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Preview
@Composable
fun NoteConfirmationBottomSheet(
    modifier: Modifier = Modifier,
    note: Note? = null,
    title: String = String(),
    message: String = String(),
    cancelText: String = String(),
    confirmText: String = String(),
    onCancel: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = { onCancel() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp, 0.dp, 16.dp, 32.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            Text(
                text = title.default(""),
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message.default(""),
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            NoteButton(
                modifier = Modifier.fillMaxWidth(),
                text = confirmText
            ) {
                scope.launch {
                    modalBottomSheetState.hide()
                    onConfirm()
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            NoteButton(
                modifier = Modifier.fillMaxWidth(),
                text = cancelText
            ) {
                scope.launch {
                    modalBottomSheetState.hide()
                    onCancel()
                }
            }
        }
    }
}