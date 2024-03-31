package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.utils.ShimmerAnimation.shimmerLoadingAnimation

@Composable
fun Loading() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(24.dp))
                .background(color = MaterialTheme.colorScheme.primary)
                .size(height = 40.dp, width = 70.dp)
                .shimmerLoadingAnimation()
        )
        LazyColumn {
            items(20) {
                Column(modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .size(height = 65.dp, width = 500.dp)
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(12.dp))
                            .background(Color(0xFFD9DADF))
                            .shimmerLoadingAnimation()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LoadingPreview() {
    Loading()
}
