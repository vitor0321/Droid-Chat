package com.example.droidchat.ui.feature.signup.components.field

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import com.example.droidchat.DroidChatFileProvider
import com.example.droidchat.R
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.theme.DroidChatTheme
import com.walcker.topaz.ExperimentalTopazComposeLibraryApi
import com.walcker.topaz.components.TopazProfilePictureOptionRow
import com.walcker.topaz.components.bottomSheet.TopazModalBottomSheet

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTopazComposeLibraryApi::class)
@Composable
fun ProfilePictureOptionsModalBottomSheet(
    onPictureSelected: (uri: Uri) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    context: Context = LocalContext.current,
) {
    var photoUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { it?.let { onPictureSelected(it) } }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success && photoUri != null) {
                photoUri?.let { onPictureSelected(it) }
            }
        }
    )

    TopazModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
    ) {
        TopazProfilePictureOptionRow(
            topazProfilePictureOptionRow = TopazProfilePictureOptionRow(
                iconResId = R.drawable.ic_photo_camera,
                text = strings.commonStrings.commonTakePhoto,
                onClick = {
                    photoUri = DroidChatFileProvider.getImageUri(context.applicationContext)
                    cameraLauncher.launch(photoUri!!)
                }
            )
        )

        TopazProfilePictureOptionRow(
            topazProfilePictureOptionRow = TopazProfilePictureOptionRow(
                iconResId = R.drawable.ic_photo_library,
                text = strings.commonStrings.commonTakePhoto,
                onClick = { imagePicker.launch("image/*") }
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun ProfilePictureOptionsModalBottomSheetPreview() {
    val sheetState = SheetState(
        skipPartiallyExpanded = false,
        density = Density(LocalContext.current),
        initialValue = SheetValue.Expanded,
    )
    DroidChatTheme {
        ProfilePictureOptionsModalBottomSheet(
            onPictureSelected = {},
            onDismissRequest = {},
            sheetState = sheetState
        )
    }
}