package com.deni.hilhamsyah.cookhub.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.deni.hilhamsyah.cookhub.R
import com.deni.hilhamsyah.cookhub.ui.theme.CookhubTheme

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    model: Any?,
    showFileChooser: Boolean = false,
    chooseFileOnclick: () -> Unit = {}
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomEnd
    ) {

        if (model != null) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                model = model,
                contentDescription = "Profile image",
            )

        } else {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                painter = painterResource(R.drawable.avatar),
                contentDescription = "Profile image"
            )
        }

        if (showFileChooser) {
            Column(
                modifier = Modifier
                    .padding(end = 10.dp, bottom = 5.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .size(25.dp),
                    onClick = chooseFileOnclick) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_camera),
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = "Camera icon"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileImagePreview() {
    CookhubTheme {
        ProfileImage(model = null)
    }
}