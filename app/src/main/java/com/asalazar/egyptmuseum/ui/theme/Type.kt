package com.asalazar.egyptmuseum.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.asalazar.egyptmuseum.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val bodyFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Lora"),
        fontProvider = provider,
    )
)

val displayFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Cinzel"),
        fontProvider = provider,
    )
)

// Default Material 3 typography values
val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
    displaySmall = baseline.displaySmall.copy(
        fontFamily = displayFontFamily,
        letterSpacing = 2.sp,
        color = secondaryContainerDark,
        shadow = Shadow(
            color = secondaryContainerDark.copy(alpha = 0.5f),
            offset = Offset(0f, 0f),
            blurRadius = 15f
        ),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center

    ),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
    headlineSmall = baseline.headlineSmall.copy(
        fontFamily = displayFontFamily,
        letterSpacing = 2.sp,
        fontWeight = FontWeight.Bold
    ),
    titleLarge = baseline.titleLarge.copy(
        fontFamily = bodyFontFamily,
        letterSpacing = 2.sp,
        fontWeight = FontWeight.Bold
    ),
    titleMedium = baseline.titleMedium.copy(
        fontFamily = bodyFontFamily,
        letterSpacing = 2.sp,
        fontWeight = FontWeight.Bold
    ),
    titleSmall = baseline.titleSmall.copy(
        fontFamily = bodyFontFamily,
        letterSpacing = 2.sp,
        fontWeight = FontWeight.Bold
    ),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
    labelLarge = baseline.labelLarge.copy(
        fontFamily = bodyFontFamily,
        fontWeight = FontWeight.Bold
    ),
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
)

