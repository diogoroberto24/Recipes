package com.project.recipes.screens

import android.content.res.Configuration
import android.text.Layout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.recipes.R
import com.project.recipes.ui.theme.RecipesTheme

@Composable
fun InitialScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            )
    ){
        TopEndCard(modifier = Modifier.align(Alignment.TopEnd))
        BottonStartCard(modifier = Modifier.align(Alignment.BottomStart))
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.chef),
                contentDescription = "Imagem do Chef.",
                modifier = Modifier
                    .size(190.dp)
            )
            Spacer(modifier = Modifier.height(100.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.unlimited_recipes),
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = stringResource(R.string.app_title),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.displayLarge
                )
                Row() {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults
                            .buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.tertiary
                        ),
                        modifier = Modifier
                            .size(width = 128.dp, height = 48.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.button_login),
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {},
                        colors = ButtonDefaults
                            .buttonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary
                            ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier
                            .size(width = 128.dp, height = 48.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.button_signup),
                            color = MaterialTheme.colorScheme.onTertiary,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }

    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
fun InitialScreenPreview(){
    RecipesTheme() {
        InitialScreen()
    }
}