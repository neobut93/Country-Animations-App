package com.kodeco.android.countryinfo.ui.components

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
) {
    var iconState by remember {
        mutableStateOf(false)
    }
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
            //todo add animation for spinning + cache
            val color by animateColorAsState(targetValue = if (iconState) Color.Yellow else Color.Black)
            val icon by animateIntAsState(targetValue = if (iconState) R.drawable.star_filled else R.drawable.star_outline)
            Image(
                painter = painterResource(icon),
                contentDescription = "starIcon",
                colorFilter = ColorFilter.tint(color),
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 5.dp)
                    .clickable {
                        iconState = !iconState
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
    )
}
