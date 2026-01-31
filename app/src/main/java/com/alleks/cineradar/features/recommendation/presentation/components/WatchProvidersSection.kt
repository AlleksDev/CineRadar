package com.alleks.cineradar.features.recommendation.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
            color = Color.White.copy(alpha = 0.7f)
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
            .background(Color.White.copy(alpha = 0.1f))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = getProviderAbbreviation(provider.name),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

private fun getProviderAbbreviation(name: String): String {
    return when {
        name.contains("Netflix", ignoreCase = true) -> "N"
        name.contains("HBO", ignoreCase = true) -> "HBO"
        name.contains("Amazon", ignoreCase = true) -> "Prime"
        name.contains("Disney", ignoreCase = true) -> "D+"
        name.contains("Apple", ignoreCase = true) -> "TV+"
        name.contains("Paramount", ignoreCase = true) -> "P+"
        name.contains("Star", ignoreCase = true) -> "Star+"
        else -> name.take(3)
    }
}
