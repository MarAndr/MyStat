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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import net.idscan.android.vsonline.screen.settings.R
import net.idscan.android.vsonline.ui.theme.VeriscanTheme


@Composable
internal fun ResetCounterDialog(
    modifier: Modifier,
    onDismissRequest: () -> Unit,
    onResetClick: () -> Unit,
    onCloseClick: () -> Unit,

    ) {
    Dialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        title = {
            DialogTitle(
                iconId = net.idscan.android.vsonline.resources.R.drawable.ic_refresh,
                iconTint = MaterialTheme.colors.error,
                titleText = stringResource(id = R.string.reset_counter_title),
            )
        },
        content = {
            Text(
                text = stringResource(id = R.string.reset_counter_question_text),
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 40.dp)
            )
        },
        onCloseClick = onCloseClick,
        onResetClick = onResetClick
    )
}

@Composable
private fun Dialog(
    modifier: Modifier,
    onDismissRequest: () -> Unit,
    title: @Composable () -> Unit,
    content: @Composable () -> Unit,
    onCloseClick: () -> Unit,
    onResetClick: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        DialogContent(
            modifier = modifier,
            onCloseClick = onCloseClick,
            onResetClick = onResetClick,
            content = content,
            title = title
        )
    }
}

@Composable
private fun DialogContent(
    modifier: Modifier,
    onCloseClick: () -> Unit,
    onResetClick: () -> Unit,
    title: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier
            .padding(vertical = 16.dp)
            .widthIn(max = 328.dp)
            .heightIn(max = 400.dp),
        shape = RoundedCornerShape(6.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(0.3f)
            ) {
                title()
            }
            Divider()
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(0.5f)
            ) {
                content()
            }
            Divider()
            Box(modifier = Modifier.heightIn(max = 64.dp)) {
                Buttons(onCloseClick = onCloseClick, onResetClick = onResetClick)
            }
        }
    }
}

@Composable
private fun DialogTitle(
    iconId: Int,
    iconTint: Color,
    titleText: String = "",
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(
                id = iconId
            ),
            contentDescription = "",
            tint = iconTint
        )
        Text(
            text = titleText,
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
private fun Buttons(
    onCloseClick: () -> Unit,
    onResetClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DialogButton(
            modifier = Modifier.weight(0.5f),
            text = stringResource(id = R.string.dialog_button_close_text),
            textColor = MaterialTheme.colors.onSurface,
            onClick = onCloseClick
        )
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        DialogButton(
            modifier = Modifier.weight(0.5f),
            text = stringResource(id = R.string.dialog_button_reset_text),
            textColor = MaterialTheme.colors.error,
            onClick = onResetClick
        )
    }
}

@Composable
private fun DialogButton(
    modifier: Modifier,
    text: String,
    textColor: Color,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text
                .uppercase(),
            color = textColor,
            style = MaterialTheme.typography.button
        )
    }
}

@Preview(widthDp = 328, heightDp = 400)
@Composable
private fun Preview() {
    VeriscanTheme {
        DialogContent(
            modifier = Modifier,
            onCloseClick = { },
            onResetClick = { },
            title = {
                DialogTitle(
                    iconId = net.idscan.android.vsonline.resources.R.drawable.ic_refresh,
                    iconTint = MaterialTheme.colors.error,
                    titleText = stringResource(id = R.string.reset_counter_title),
                )
            },
            content = {
                Text(
                    text = stringResource(id = R.string.reset_counter_question_text),
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 40.dp)
                )
            }
        )
    }
}
