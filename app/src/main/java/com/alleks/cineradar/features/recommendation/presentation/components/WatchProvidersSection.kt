package com.alleks.cineradar.features.recommendation.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alleks.cineradar.features.recommendation.domain.entities.WatchProvider

@Composable
fun WatchProvidersSection(
    providers: List<WatchProvider>,
    modifier: Modifier = Modifier
) {
    if (providers.isEmpty()) return

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "La puedes encontrar en",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
        
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            providers.take(4).forEach { provider ->
                ProviderBadge(provider = provider)
                Spacer(modifier = Modifier.width(12.dp))
            }
        }
    }
}

@Composable
private fun ProviderBadge(
    provider: WatchProvider,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = getProviderAbbreviation(provider.name),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

private fun getProviderAbbreviation(name: String): String {
    return when {
        name.contains("Netflix", ignoreCase = true) -> "Netflix"
        name.contains("HBO", ignoreCase = true) -> "HBO Max"
        name.contains("Amazon", ignoreCase = true) || name.contains("Prime", ignoreCase = true) -> "Prime"
        name.contains("Disney", ignoreCase = true) -> "Disney+"
        name.contains("Apple", ignoreCase = true) -> "Apple TV+"
        name.contains("Paramount", ignoreCase = true) -> "Paramount+"
        name.contains("Star", ignoreCase = true) -> "Star+"
        name.contains("Claro", ignoreCase = true) -> "Claro"
        name.contains("Movistar", ignoreCase = true) -> "Movistar"
        name.contains("Crunchyroll", ignoreCase = true) -> "Crunchy"
        name.contains("Mubi", ignoreCase = true) -> "MUBI"
        name.contains("Cinépolis", ignoreCase = true) || name.contains("Cinepolis", ignoreCase = true) -> "Cinépolis"
        name.contains("VIX", ignoreCase = true) || name.contains("ViX", ignoreCase = true) -> "ViX"
        name.contains("Google", ignoreCase = true) -> "Google Play"
        name.contains("YouTube", ignoreCase = true) -> "YouTube"
        name.contains("Microsoft", ignoreCase = true) -> "Microsoft"
        name.contains("Vudu", ignoreCase = true) -> "Vudu"
        name.contains("Rakuten", ignoreCase = true) -> "Rakuten"
        else -> name.take(10) // Mostrar más caracteres para nombres desconocidos
    }
}
