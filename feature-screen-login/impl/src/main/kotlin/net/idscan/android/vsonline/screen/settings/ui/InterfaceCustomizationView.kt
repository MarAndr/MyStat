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

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import net.idscan.android.vsonline.screen.settings.R
import net.idscan.android.vsonline.ui.theme.VeriscanTheme
import net.idscan.android.vsonline.resources.R as OriginR

@Composable
internal fun InterfaceCustomizationView(
    modifier: Modifier,
    interfaceCustomizationViewModel: InterfaceCustomizationViewModel,
    isHeader: Boolean = true
) {
    val state by interfaceCustomizationViewModel.state.collectAsState()
    val interactor = interfaceCustomizationViewModel.interactor

    InterfaceCustomizationView(
        modifier = modifier,
        state = state,
        interactor = interactor,
        isHeader = isHeader
    )
}

@Composable
private fun InterfaceCustomizationView(
    modifier: Modifier,
    state: InterfaceCustomizationState,
    interactor: InterfaceCustomizationInteractor,
    isHeader: Boolean
) {
    var isDialogShown by rememberSaveable(key = "reset_counter_dialog_state") {
        mutableStateOf(false)
    }

    if (isDialogShown) {
        ResetCounterDialog(
            modifier = Modifier,
            onDismissRequest = { isDialogShown = false },
            onResetClick = {},
            onCloseClick = { isDialogShown = false }
        )
    }

    val scrollableState = remember{
        ScrollState(initial = 0)
    }
    val isScroll = scrollableState.value > 0

    Box(modifier = modifier) {

        if (isHeader) {
            ChapterHeader(
                modifier = Modifier.zIndex(1f),
                text = stringResource(id = R.string.interface_customization_title),
                isElevated = isScroll
            )
        }

        Column(
            Modifier
                .padding(top = if (isHeader) 56.dp else 0.dp)
                .background(color = MaterialTheme.colors.surface)
                .fillMaxSize()
                .verticalScroll(scrollableState)
        ) {
            CheckableRow(
                iconId = OriginR.drawable.ic_settings_list,
                title = stringResource(R.string.screening_service_button_title),
                description = stringResource(R.string.show_button_annotation_text),
                isChecked = state.isScreenServiceButtonSelected,
                onCheckedChange = { interactor.changeScreenServiceButtonSelection(it) }
            )

            Crossfade(targetState = state.isScreenServiceButtonSelected) { isScreenServiceButtonSelected ->
                if (isScreenServiceButtonSelected) {
                    CheckableRow(
                        title = stringResource(R.string.auto_run_title),
                        isChecked = state.isAutoRunAllAvailableScreeningServicesSelected,
                        onCheckedChange = { isChecked ->
                            interactor.changeAutoRunAllAvailableScreenServiceSelection(isChecked)
                        }
                    )
                }
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            CheckableRow(
                iconId = OriginR.drawable.ic_agreement,
                title = stringResource(R.string.agreement_button_title),
                description = stringResource(R.string.show_button_annotation_text),
                isChecked = state.isAgreementButtonSelected,
                onCheckedChange = { interactor.changeAgreementButtonSelection(it) }
            )

            CheckableRow(
                iconId = OriginR.drawable.ic_no_idcard,
                title = stringResource(R.string.cant_scan_id_button_title),
                description = stringResource(R.string.show_button_annotation_text),
                isChecked = state.isCantScanIdButtonSelected,
                onCheckedChange = { interactor.changeCantScanIdButtonSelection(it) }
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            CheckableRow(
                iconId = OriginR.drawable.ic_counter,
                title = stringResource(R.string.counter_title),
                description = stringResource(R.string.show_button_annotation_text),
                isChecked = state.isCounterSelected,
                onCheckedChange = { interactor.changeCounterSelection(it) }
            )

            ClickableTextRow(
                text = stringResource(id = R.string.reset_counter_title),
                onClick = { isDialogShown = true }
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            CheckableRow(
                iconId = OriginR.drawable.ic_play,
                title = stringResource(R.string.auto_start_title),
                description = stringResource(R.string.launch_app_annotation_text),
                isChecked = state.isAutoStartSelected,
                onCheckedChange = { interactor.changeAutoStartSelection(it) }
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            CheckableRow(
                iconId = OriginR.drawable.ic_location,
                title = stringResource(id = R.string.location_title),
                description = stringResource(R.string.set_location_cloud_annotation_text),
                isChecked = state.isLocationSelected,
                onCheckedChange = { interactor.changeLocationSelection(it) }
            )
        }
    }
}

@Composable
private fun ClickableTextRow(
    text: String,
    onClick: () -> Unit,
) {
    Surface(
        Modifier
            .fillMaxSize()
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .padding(start = 56.dp, top = 20.dp, bottom = 32.dp)
        )
    }
}

@Preview(widthDp = 320, heightDp = 500)
@Composable
private fun Preview() {
    VeriscanTheme {
        InterfaceCustomizationView(
            modifier = Modifier.fillMaxSize(),
            state = InterfaceCustomizationState(),
            interactor = object : InterfaceCustomizationInteractor {
                override fun changeScreenServiceButtonSelection(isSelected: Boolean) {}
                override fun changeAutoRunAllAvailableScreenServiceSelection(isSelected: Boolean) {}
                override fun changeAgreementButtonSelection(isSelected: Boolean) {}
                override fun changeCantScanIdButtonSelection(isSelected: Boolean) {}
                override fun changeCounterSelection(isSelected: Boolean) {}
                override fun changeAutoStartSelection(isSelected: Boolean) {}
                override fun changeLocationSelection(isSelected: Boolean) {}
            },
            isHeader = true
        )
    }
}



