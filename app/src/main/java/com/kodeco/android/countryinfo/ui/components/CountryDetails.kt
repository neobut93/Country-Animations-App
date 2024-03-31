package com.kodeco.android.countryinfo.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kodeco.android.countryinfo.models.Country
import com.kodeco.android.countryinfo.sample.sampleCountry
import com.kodeco.android.countryinfo.ui.theme.MyApplicationTheme

@Composable
fun CountryDetails(
    country: Country,
    modifier: Modifier,
) {
    var isExpandedFlag by remember {
        mutableStateOf(false)
    }
    var isCapitalChanged by remember {
        mutableStateOf(false)
    }
    var isPopulationChanged by remember {
        mutableStateOf(false)
    }
    var isAreaChanged by remember {
        mutableStateOf(false)
    }
    val flagSize by animateDpAsState(
        targetValue = if (isExpandedFlag) 300.dp else 150.dp,
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearEasing
        ), label = "flag size"
    )
    val capitalTransition = updateTransition(targetState = isCapitalChanged)
    val populationTransition = updateTransition(targetState = isPopulationChanged)
    val areaTransition = updateTransition(targetState = isAreaChanged)

    LazyColumn(modifier = modifier) {
        item {
            Text(
                text = "Capital: ${country.mainCapital}",
                color = textColor(capitalTransition),
                modifier = Modifier
                    .graphicsLayer(
                        rotationY = textRotation(transition = capitalTransition)
                    )
                    .clickable {
                        isCapitalChanged = !isCapitalChanged
                    },
                fontWeight = FontWeight(textWeight(transition = capitalTransition))
            )
        }
        item {
            Text(
                text = "Population: ${country.population}",
                color = textColor(populationTransition),
                modifier = Modifier
                    .graphicsLayer(
                        rotationY = textRotation(transition = populationTransition)
                    )
                    .clickable {
                        isPopulationChanged = !isPopulationChanged
                    },
                fontWeight = FontWeight(textWeight(transition = populationTransition))
            )
        }
        item {
            Text(
                text = "Area: ${country.area}",
                color = textColor(areaTransition),
                modifier = Modifier
                    .graphicsLayer(
                        rotationY = textRotation(transition = areaTransition)
                    )
                    .clickable {
                        isAreaChanged = !isAreaChanged
                    },
                fontWeight = FontWeight(textWeight(transition = areaTransition))
            )
        }
        item {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(country.flagUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Flag",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .border(1.dp, color = MaterialTheme.colorScheme.primary)
                    .clickable { isExpandedFlag = !isExpandedFlag }
                    .size(flagSize),
            )
        }
    }
}

@Composable
private fun textColor(transition: Transition<Boolean>): Color {
    val textColor by transition.animateColor(
        transitionSpec = { tween(500) },
        label = "textColor",
        targetValueByState = { isTextChanged ->
            if (isTextChanged) Color.Red else Color.Black
        }
    )
    return textColor
}

@Composable
private fun textWeight(transition: Transition<Boolean>): Int {
    val textFont by transition.animateInt(
        transitionSpec = { tween(500) },
        label = "textColor",
        targetValueByState = { isTextChanged ->
            if (isTextChanged) 700 else 400
        }
    )
    return textFont
}

@Composable
private fun textRotation(transition: Transition<Boolean>): Float {
    val textRotation by transition.animateFloat(
        transitionSpec = { tween(500) },
        label = "rotation",
        targetValueByState = { isTextChanged ->
            if (isTextChanged) 360f else 0f
        }
    )
    return textRotation
}

@Preview
@Composable
fun CountryDetailsPreview() {
    MyApplicationTheme {
        CountryDetails(
            country = sampleCountry,
            modifier = Modifier,
        )
    }
}
