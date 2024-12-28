package com.walcker.droidchat.ui.components

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
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
import com.walcker.droidchat.DroidChatFileProvider
import com.walcker.droidchat.R
import com.walcker.droidchat.strings.strings
import com.walcker.droidchat.ui.theme.DroidChatTheme
import com.walcker.droidchat.ui.theme.DroidSpace

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfilePictureOptionsModalBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    context: Context = LocalContext.current,
    onPictureSelected: (Uri) -> Unit,
    onDismissRequest: () -> Unit,
) {
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> uri?.let { onPictureSelected(uri) } }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) photoUri?.let { onPictureSelected(it) }
        }
    )

    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        content = {
            ProfilePictureOptionsItems(
                id = R.drawable.ic_photo_camera,
                text = strings.commonStrings.commonTakePhoto,
                onClick = {
                    photoUri = DroidChatFileProvider.getImageUri(context = context.applicationContext)
                    photoUri?.let { cameraLauncher.launch(it) }
                }
            )
            ProfilePictureOptionsItems(
                id = R.drawable.ic_photo_library,
                text = strings.commonStrings.commonUploadPhoto,
                onClick = {
                    imageLauncher.launch("image/*")
                }
            )
            Spacer(modifier = Modifier.height(DroidSpace.XMedium.value))
        }
    )
}

@Composable
private fun ProfilePictureOptionsItems(
    modifier: Modifier = Modifier,
    @DrawableRes id: Int,
    text: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = DroidSpace.XLarge.value)
            .clickable {
                onClick()
            }
            .padding(DroidSpace.MMedium.value),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = id),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.width(DroidSpace.MSmall.value))

        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun ProfilePictureOptionsModalBottomSheetPreview() {
    DroidChatTheme {
        ProfilePictureOptionsModalBottomSheet(
            sheetState = SheetState(
                skipPartiallyExpanded = false,
                density = Density(LocalContext.current),
                initialValue = SheetValue.Expanded
            ),
            onPictureSelected = {},
            onDismissRequest = {}
        )
    }
}