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

package net.idscan.android.vsonline.screen.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.idscan.android.vsonline.ui.theme.VeriscanTheme

@Composable
internal fun ChapterHeader(
    modifier: Modifier,
    text: String,
    isElevated: Boolean,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        elevation = if (isElevated) 4.dp else 0.dp,

        ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(
                start = 24.dp,
                bottom = 16.dp,
                top = 16.dp
            )
        )
    }
}

@Preview
@Composable
private fun Preview() {
    VeriscanTheme {
        ChapterHeader(
            modifier = Modifier.background(Color.White),
            text = "Preview",
            isElevated = false
        )
    }
}