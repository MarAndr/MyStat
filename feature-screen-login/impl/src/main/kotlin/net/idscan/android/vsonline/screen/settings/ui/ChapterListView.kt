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

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.idscan.android.vsonline.ui.theme.VeriscanTheme
import net.idscan.android.vsonline.resources.R as OriginR

internal enum class Chapter {
    Readers,
    Interface,
    Warnings
}


@Composable
internal fun ChapterListView(
    modifier: Modifier,
    activeChapter: Chapter? = null,
    onChapterSelected: (chapter: Chapter) -> Unit,
) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colors.surface)
            .padding(top = 16.dp)
    ) {
        SettingsRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            iconId = OriginR.drawable.ic_reader_eseek,
            stringRes = stringResource(net.idscan.android.vsonline.screen.settings.R.string.readers_title),
            isItemSelected = activeChapter == Chapter.Readers,
            onClick = { onChapterSelected(Chapter.Readers) }
        )

        SettingsRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            iconId = OriginR.drawable.ic_settings_interface,
            stringRes = stringResource(net.idscan.android.vsonline.screen.settings.R.string.interface_customization_title),
            isItemSelected = activeChapter == Chapter.Interface,
            onClick = { onChapterSelected(Chapter.Interface) }
        )

        SettingsRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            iconId = OriginR.drawable.ic_alert_triangle,
            stringRes = stringResource(id = net.idscan.android.vsonline.screen.settings.R.string.warning_popup_title),
            isItemSelected = activeChapter == Chapter.Warnings,
            onClick = { onChapterSelected(Chapter.Warnings) }
        )
    }
}

@Composable
private fun SettingsRow(
    modifier: Modifier,
    @DrawableRes iconId: Int,
    stringRes: String,
    isItemSelected: Boolean = false,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .background(
                shape = RoundedCornerShape(6.dp),
                color = if (isItemSelected) {
                    MaterialTheme.colors.primaryVariant.copy(alpha = 0.1f)
                } else {
                    MaterialTheme.colors.surface
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(16.dp),
            painter = painterResource(id = iconId),
            contentDescription = "",
            tint = if (isItemSelected) {
                MaterialTheme.colors.primaryVariant
            } else {
                MaterialTheme.colors.secondary
            }
        )

        Text(
            text = stringRes,
            style = MaterialTheme.typography.h5,
            color =
            if (isItemSelected) {
                MaterialTheme.colors.primaryVariant
            } else {
                MaterialTheme.colors.onSurface
            }
        )
    }
}

@Preview(widthDp = 320, heightDp = 500)
@Composable
private fun Preview() {
    var state by remember { mutableStateOf(Chapter.Readers) }

    VeriscanTheme {
        ChapterListView(
            modifier = Modifier.fillMaxSize(),
            activeChapter = state,
            onChapterSelected = { state = it }
        )
    }
}


