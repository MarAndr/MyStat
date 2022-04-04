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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import net.idscan.android.vsonline.screen.settings.R
import net.idscan.android.vsonline.ui.theme.VeriscanTheme
import net.idscan.android.vsonline.resources.R as OriginR


@Composable
internal fun WarningPopupView(
    modifier: Modifier,
    warningPopupViewModel: WarningPopupViewModel,
    isHeader: Boolean = true,
) {
    val state by warningPopupViewModel.state.collectAsState()
    val interactor = warningPopupViewModel.interactor

    WarningPopupView(
        modifier = modifier,
        state = state,
        interactor = interactor,
        isHeader = isHeader
    )
}


@Composable
private fun WarningPopupView(
    modifier: Modifier,
    state: WarningPopUpState,
    interactor: WarningPopupViewInteractor,
    isHeader: Boolean,
) {
    val scrollableState = remember{
        ScrollState(initial = 0)
    }
    val isScroll = scrollableState.value > 0

    Box(modifier = modifier) {

        if (isHeader) {
            ChapterHeader(
                modifier = Modifier.zIndex(1f),
                text = stringResource(id = R.string.warning_popup_title),
                isElevated = isScroll
            )
        }

        Column(
            modifier = Modifier
                .padding(top = if (isHeader) 56.dp else 0.dp)
                .background(color = MaterialTheme.colors.surface)
                .fillMaxSize()
                .verticalScroll(scrollableState)
        ) {
            CheckableRow(
                iconId = OriginR.drawable.ic_home,
                title = stringResource(R.string.address_title),
                description = stringResource(R.string.warning_address_annotation_text),
                isChecked = state.isAddressSelected,
                onCheckedChange = { interactor.changeAddressSelection(it) }
            )

            CheckableRow(
                iconId = OriginR.drawable.ic_pacifier,
                title = stringResource(R.string.age_title),
                description = stringResource(R.string.age_warning_annotation_text),
                isChecked = state.isAgeSelected,
                onCheckedChange = { interactor.changeAgeSelection(it) }
            )

            CheckableRow(
                iconId = OriginR.drawable.ic_group_alert,
                title = stringResource(R.string.alert_list_title),
                description = stringResource(R.string.alert_list_annotation_text),
                isChecked = state.isAlertListSelected,
                onCheckedChange = { interactor.changeAlertListSelection(it) }
            )

            CheckableRow(
                iconId = OriginR.drawable.ic_calendar,
                title = stringResource(R.string.expiration_title),
                description = stringResource(R.string.expiration_after_scan_annotation_text),
                isChecked = state.isExpirationSelected,
                onCheckedChange = { interactor.changeExpirationSelection(it) }
            )

            CheckableRow(
                iconId = OriginR.drawable.ic_crown,
                title = stringResource(R.string.gold_zip_code_title),
                description = stringResource(R.string.vip_status_warning_scan_annotation_text),
                isChecked = state.isGoldZipCodeSelected,
                onCheckedChange = { interactor.changeGoldZipCodeSelection(it) }
            )

            CheckableRow(
                iconId = OriginR.drawable.ic_users,
                title = stringResource(R.string.group_title),
                description = stringResource(R.string.group_warning_scan_annotation_text),
                isChecked = state.isGroupSelected,
                onCheckedChange = { interactor.changeGroupSelection(it) }
            )

            CheckableRow(
                iconId = OriginR.drawable.ic_plug_disconnected,
                title = stringResource(R.string.offline_alert_title),
                description = stringResource(R.string.alert_offline_annotation_text),
                isChecked = state.isOfflineAlertListSelected,
                onCheckedChange = { interactor.changeOfflineAlertLIstSelection(it) }
            )

            CheckableRow(
                iconId = OriginR.drawable.ic_alert_triangle,
                title = stringResource(R.string.rescan_title),
                description = stringResource(R.string.rescan_warning_annotation_text),
                isChecked = state.isRescanSelected,
                onCheckedChange = { interactor.changeRescanSelection(it) }
            )

            CheckableRow(
                iconId = OriginR.drawable.ic_suspicious_id,
                title = stringResource(R.string.suspicious_id_title),
                description = stringResource(R.string.suspicious_id_warning_annotation_text),
                isChecked = state.isSuspiciousIdSelected,
                onCheckedChange = { interactor.changeSuspiciousIdSelection(it) }
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            CheckableRow(
                iconId = OriginR.drawable.ic_agreement,
                title = stringResource(R.string.agreement_screen_title),
                description = stringResource(R.string.agreement_screen_annotation_text),
                isChecked = state.isAgreementScreenSelected,
                onCheckedChange = { interactor.changeAgreementScreenSelection(it) }
            )
        }
    }
}

@Preview(widthDp = 320, heightDp = 500)
@Composable
private fun Preview() {
    VeriscanTheme {
        WarningPopupView(
            modifier = Modifier.fillMaxSize(),
            state = WarningPopUpState(),
            interactor = object : WarningPopupViewInteractor {
                override fun changeAddressSelection(isSelected: Boolean) {}
                override fun changeAgeSelection(isSelected: Boolean) {}
                override fun changeAlertListSelection(isSelected: Boolean) {}
                override fun changeExpirationSelection(isSelected: Boolean) {}
                override fun changeGoldZipCodeSelection(isSelected: Boolean) {}
                override fun changeGroupSelection(isSelected: Boolean) {}
                override fun changeOfflineAlertLIstSelection(isSelected: Boolean) {}
                override fun changeRescanSelection(isSelected: Boolean) {}
                override fun changeSuspiciousIdSelection(isSelected: Boolean) {}
                override fun changeAgreementScreenSelection(isSelected: Boolean) {}
            },
            isHeader = true
        )
    }
}