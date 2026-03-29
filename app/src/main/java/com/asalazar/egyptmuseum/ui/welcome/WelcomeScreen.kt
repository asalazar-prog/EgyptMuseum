package com.asalazar.egyptmuseum.ui.welcome

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asalazar.egyptmuseum.R
import com.asalazar.egyptmuseum.ui.theme.EgyptMuseumTheme
import com.asalazar.egyptmuseum.ui.theme.component.MuseumHorizontalDivider
import com.asalazar.egyptmuseum.ui.theme.component.PrimaryButton
import com.asalazar.egyptmuseum.ui.theme.secondaryContainerDark

private val BodySansFont = FontFamily.SansSerif
private val ItalicSerifFont = FontFamily.Serif

private val welcomeStyle = TextStyle(
    fontFamily = BodySansFont,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    letterSpacing = 2.sp,
    color = secondaryContainerDark,
    textAlign = TextAlign.Center
)

private val connectorStyle = TextStyle(
    fontFamily = ItalicSerifFont,
    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
    fontSize = 18.sp,
    color = secondaryContainerDark,
    textAlign = TextAlign.Center
)

@Composable
fun WelcomeScreen(
    onEnterClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            WelcomeText()
            ButtonSection(
                onEnterClick = onEnterClick,
                onSettingsClick = onSettingsClick
            )
        }
    }
}

@Composable
private fun ButtonSection(
    onEnterClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EnterButton(onClick = onEnterClick)
        TextButton(onClick = onSettingsClick) {
            Icon(
                Icons.Sharp.Settings,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text("CONFIGURACIÓN")
        }
    }

}

@Composable
private fun WelcomeText(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.title_welcome_to),
            style = welcomeStyle,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        Text(
            stringResource(R.string.title_interactive_museum),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(bottom = 12.dp),
        )
        Text(
            stringResource(R.string.title_connection),
            style = connectorStyle,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            stringResource(R.string.title_egypt),
            style = MaterialTheme.typography.displaySmall,
        )
        MuseumHorizontalDivider(
            Modifier
                .fillMaxWidth(0.6f)
                .padding(top = 24.dp)
        )
    }
}

@Composable
private fun EnterButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    PrimaryButton(onClick, modifier = modifier) {
        Text("ENTRAR AL MUSEO")
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun WelcomeScreenPreview() {
    EgyptMuseumTheme {
        WelcomeScreen({},{})
    }
}
