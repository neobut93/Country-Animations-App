package com.kodeco.android.countryinfo.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.R
import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.sample.sampleCountry

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryInfoRow(
    country: Country,
    onTap: () -> Unit,
    onStarLiked: (Country) -> Unit
) {
    val color by animateColorAsState(
        targetValue = if (country.isFavorite) Color.Yellow else Color.Black,
        label = "Star color"
    )
    val startFilling by animateIntAsState(
        targetValue = if (country.isFavorite) R.drawable.star_filled else R.drawable.star_outline,
        label = "Start filling"
    )
    val starRotation by animateFloatAsState(targetValue = if (country.isFavorite) 360f else 0f)

    Card(
        onClick = onTap,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.padding(all = 8.dp)) {
                Text(text = "Name: ${country.commonName}")
                Text(text = "Capital: ${country.capital?.firstOrNull() ?: "N/A"}")
            }
            Image(
                painter = painterResource(id = startFilling),
                contentDescription = "starIcon",
                colorFilter = ColorFilter.tint(color),
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 5.dp)
                    .graphicsLayer {
                        rotationZ = starRotation
                    }
                    .clickable {
                        onStarLiked(country)
                    }
            )
        }
    }
}

@Preview
@Composable
fun CountryInfoRowPreview() {
    CountryInfoRow(
        country = sampleCountry,
        onTap = {},
        onStarLiked = {}
    )
}
