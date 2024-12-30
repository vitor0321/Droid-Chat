package com.example.droidchat.ui.components.bottomSheet

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import com.example.droidchat.DroidChatFileProvider
import com.example.droidchat.R
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpace
import com.example.droidchat.strings.strings

@OptIn(ExperimentalMaterial3Api::class)
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

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ) {
        ProfilePictureOptionRow(
            iconResId = R.drawable.ic_photo_camera,
            text = strings.commonStrings.commonTakePhoto,
            onClick = {
                photoUri = DroidChatFileProvider.getImageUri(context.applicationContext)
                cameraLauncher.launch(photoUri!!)
            }
        )

        ProfilePictureOptionRow(
            iconResId = R.drawable.ic_photo_library,
            text = strings.commonStrings.commonTakePhoto,
            onClick = { imagePicker.launch("image/*") }
        )
    }
}

@Composable
private fun ProfilePictureOptionRow(
    @DrawableRes
    iconResId: Int,
    text: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = DroidSpace.XLarge.value)
            .clickable { onClick() }
            .padding(horizontal = DroidSpace.MMedium.value),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.width(DroidSpace.XSmall.value))

        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge,
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