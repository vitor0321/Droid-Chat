package com.walcker.droidchat.ui.components

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.walcker.droidchat.R
import com.walcker.droidchat.strings.strings
import com.walcker.droidchat.ui.theme.DroidChatTheme
import com.walcker.droidchat.ui.theme.DroidSpace

@Composable
internal fun ProfilePictureSelector(
    modifier: Modifier = Modifier,
    imageUri: Uri? = null,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .size(84.dp)
                .clip(shape = CircleShape),
            model = imageUri ?: R.drawable.ic_upload_photo,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_upload_photo),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(DroidSpace.XSmall.value))

        Text(
            text = strings.commonStrings.commonAddProfilePhoto,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfilePictureSelectorPreview() {
    DroidChatTheme {
        ProfilePictureSelector()
    }
}