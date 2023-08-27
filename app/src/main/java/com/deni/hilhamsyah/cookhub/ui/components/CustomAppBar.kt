package com.deni.hilhamsyah.cookhub.ui.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.deni.hilhamsyah.cookhub.R
import com.deni.hilhamsyah.cookhub.ui.theme.CookhubTheme

@Composable
fun CustomAppBar(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    title: String? = null,
    content: @Composable () -> Unit = {}
) {
    val hideBackButton = navController == null

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        ConstraintLayout {
            val (backButtonRef, titleRef, contentRef) = createRefs()
            if (!hideBackButton) {
                IconButton(
                    modifier = Modifier
                        .border(
                            BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                            RoundedCornerShape(12.dp)
                        )
                        .constrainAs(backButtonRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        },
                    onClick = { navController?.popBackStack() },
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_back_arrow),
                        contentDescription = "ic_back_arrow"
                    )
                }
            }

            if (title != null) {
                Text(
                    modifier = Modifier.constrainAs(titleRef) {
                        if(!hideBackButton) top.linkTo(backButtonRef.bottom, 8.dp)
                        else {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                        start.linkTo(parent.start)
                    },
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Row(modifier = Modifier.constrainAs(contentRef) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }) {
                content()
            }
        }
    }
}

@Preview
@Composable
fun CustomAppBarPreview() {
    CookhubTheme {
        CustomAppBar(
            navController = rememberNavController(),
            title = "How to make french \ntoast",
            content = {
                IconButton(
                    onClick = {},
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_more),
                        contentDescription = "ic_back_arrow"
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun CustomAppBarNoTitlePreview() {
    CookhubTheme {
        val context = LocalContext.current
        CustomAppBar(
            navController = rememberNavController(),
            content = {
                IconButton(
                    onClick = {
                        Toast.makeText(context, "Hello world", Toast.LENGTH_LONG).show()
                    },
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_more),
                        contentDescription = "ic_back_arrow"
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun CustomAppBarNoContentPreview() {
    CookhubTheme {
        CustomAppBar(
            title = "How to make french \ntoast",
            navController = rememberNavController(),
        )
    }
}

@Preview
@Composable
fun CustomAppBarNoBackIconPreview() {
    CookhubTheme {
        val context = LocalContext.current
        CustomAppBar(
            title = "How to make french \ntoast",
            content = {
                IconButton(
                    onClick = {
                        Toast.makeText(context, "Hello world", Toast.LENGTH_LONG).show()
                    },
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_more),
                        contentDescription = "ic_back_arrow"
                    )
                }
            }
        )
    }
}