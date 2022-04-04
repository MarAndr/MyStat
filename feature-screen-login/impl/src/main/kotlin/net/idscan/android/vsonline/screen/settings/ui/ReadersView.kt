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

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import net.idscan.android.vsonline.screen.settings.R
import net.idscan.android.vsonline.ui.theme.VeriscanTheme

@Composable
internal fun ReadersView(
    //it's a stub. The design is not ready yet.
    isHeader: Boolean = true,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
    ) {

        val scrollableState = remember{
            ScrollState(initial = 0)
        }
        val isScroll = scrollableState.value > 0

        if (isHeader) {
            ChapterHeader(
                modifier = Modifier.zIndex(1f),
                text = stringResource(id = R.string.readers_title),
                isElevated = isScroll
            )
        }

        Box(
            modifier = Modifier
                .padding(top = if (isHeader) 56.dp else 0.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = R.string.readers_title))
        }
    }
}

@Preview(widthDp = 320, heightDp = 500)
@Composable
private fun Preview() {
    VeriscanTheme {
        ReadersView()
    }
}