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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.idscan.android.vsonline.ui.theme.VeriscanTheme
import net.idscan.android.vsonline.ui.widgets.VsSwitch

@Composable
internal fun CheckableRow(
    modifier: Modifier = Modifier,
    iconId: Int? = null,
    title: String,
    description: String? = null,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier
            .clickable(onClick = { onCheckedChange(!isChecked) })
            .background(color = MaterialTheme.colors.surface)
            .padding(16.dp),
        verticalAlignment = CenterVertically
    ) {

        if (iconId != null) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = iconId),
                contentDescription = "",
                tint = MaterialTheme.colors.secondary
            )
        } else {
            Spacer(
                modifier = Modifier
                    .size(24.dp)
                    .align(CenterVertically)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    bottom = if (description == null) 16.dp else 0.dp
                )
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface,
            )

            if (description != null) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.alpha(0.6f),
                )
            }

        }

        VsSwitch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            enabled = true
        )
    }
}

@Preview(widthDp = 550, heightDp = 100)
@Composable
private fun Preview() {
    VeriscanTheme {
        CheckableRow(
            title = "Preview",
            isChecked = false,
            onCheckedChange = {},
            description = "Description"
        )
    }
}