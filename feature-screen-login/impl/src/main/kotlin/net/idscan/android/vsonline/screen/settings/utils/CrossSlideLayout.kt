/*
 * Copyright (c) 2018 IDScan.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Support: support@idscan.com
 *
 */

package net.idscan.android.vsonline.screen.settings.utils

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.*

private const val SCREEN_1_LAYOUT_ID = "screen1"
private const val SCREEN_2_LAYOUT_ID = "screen2"

@Composable
fun CrossSlideLayout(
    modifier: Modifier = Modifier,
    screen1: @Composable BoxScope.() -> Unit,
    screen2: @Composable BoxScope.() -> Unit,
    @FloatRange(from = 0.0, to = 1.0) animationProgress: Float
) {

    Layout(
        modifier = modifier,
        content = {
            Box(modifier = Modifier.layoutId(SCREEN_1_LAYOUT_ID), content = screen1)
            Box(modifier = Modifier.layoutId(SCREEN_2_LAYOUT_ID), content = screen2)
        }
    ) { measurables, constraints ->

        val screen1Placeables =
            measurables.first { it.layoutId == SCREEN_1_LAYOUT_ID }.measure(constraints)
        val screen2Placeables =
            measurables.first { it.layoutId == SCREEN_2_LAYOUT_ID }.measure(constraints)

        placeAnotherScreen(
            screen1Placeables,
            screen2Placeables,
            constraints.maxWidth,
            constraints.maxHeight,
            animationProgress
        )
    }
}

fun MeasureScope.placeAnotherScreen(
    screen1Placeable: Placeable,
    screen2Placeable: Placeable,
    width: Int,
    height: Int,
    @FloatRange(from = 0.0, to = 1.0) animationProgress: Float
): MeasureResult {

    val screen1Y = (height - screen1Placeable.height) / 2
    val screen2Y = (height - screen2Placeable.height) / 2

    val screen1X = -width * animationProgress
    val screen2X = width * (1.0f - animationProgress)

    return layout(width, height) {
        if (animationProgress < 0.999f) {
            screen1Placeable.placeRelative(screen1X.toInt(), screen1Y)
        }
        if (animationProgress > 0.001f) {
            screen2Placeable.placeRelative(screen2X.toInt(), screen2Y)
        }
    }
}