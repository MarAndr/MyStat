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

import androidx.compose.runtime.Immutable
import net.idscan.android.vsonline.core.architecture.ComposeViewModel


@Immutable
internal data class WarningPopUpState(
    val isAddressSelected: Boolean = true,
    val isAgeSelected: Boolean = true,
    val isAlertListSelected: Boolean = true,
    val isExpirationSelected: Boolean = true,
    val isGoldZipCodeSelected: Boolean = true,
    val isGroupSelected: Boolean = true,
    val isOfflineAlertListSelected: Boolean = true,
    val isRescanSelected: Boolean = true,
    val isSuspiciousIdSelected: Boolean = true,
    val isAgreementScreenSelected: Boolean = true,
)

internal interface WarningPopupViewInteractor {
    fun changeAddressSelection(isSelected: Boolean)
    fun changeAgeSelection(isSelected: Boolean)
    fun changeAlertListSelection(isSelected: Boolean)
    fun changeExpirationSelection(isSelected: Boolean)
    fun changeGoldZipCodeSelection(isSelected: Boolean)
    fun changeGroupSelection(isSelected: Boolean)
    fun changeOfflineAlertLIstSelection(isSelected: Boolean)
    fun changeRescanSelection(isSelected: Boolean)
    fun changeSuspiciousIdSelection(isSelected: Boolean)
    fun changeAgreementScreenSelection(isSelected: Boolean)
}

internal class WarningPopupViewModel :
    ComposeViewModel<WarningPopUpState, Nothing, WarningPopupViewInteractor>(WarningPopUpState()) {

    override val interactor = object : WarningPopupViewInteractor {
        override fun changeAddressSelection(isSelected: Boolean) {
            activeState = activeState.copy(isAddressSelected = isSelected)
        }

        override fun changeAgeSelection(isSelected: Boolean) {
            activeState = activeState.copy(isAgeSelected = isSelected)
        }

        override fun changeAlertListSelection(isSelected: Boolean) {
            activeState = activeState.copy(isAlertListSelected = isSelected)
        }

        override fun changeExpirationSelection(isSelected: Boolean) {
            activeState = activeState.copy(isExpirationSelected = isSelected)
        }

        override fun changeGoldZipCodeSelection(isSelected: Boolean) {
            activeState = activeState.copy(isGoldZipCodeSelected = isSelected)
        }

        override fun changeGroupSelection(isSelected: Boolean) {
            activeState = activeState.copy(isGroupSelected = isSelected)
        }

        override fun changeOfflineAlertLIstSelection(isSelected: Boolean) {
            activeState = activeState.copy(isOfflineAlertListSelected = isSelected)
        }

        override fun changeRescanSelection(isSelected: Boolean) {
            activeState = activeState.copy(isRescanSelected = isSelected)
        }

        override fun changeSuspiciousIdSelection(isSelected: Boolean) {
            activeState = activeState.copy(isSuspiciousIdSelected = isSelected)
        }

        override fun changeAgreementScreenSelection(isSelected: Boolean) {
            activeState = activeState.copy(isAgreementScreenSelected = isSelected)
        }
    }

}