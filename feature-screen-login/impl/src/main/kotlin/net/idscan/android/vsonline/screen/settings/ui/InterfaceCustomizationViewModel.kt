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
internal data class InterfaceCustomizationState(
    val isScreenServiceButtonSelected: Boolean = true,
    val isAutoRunAllAvailableScreeningServicesSelected: Boolean = true,
    val isAgreementButtonSelected: Boolean = true,
    val isCantScanIdButtonSelected: Boolean = true,
    val isCounterSelected: Boolean = true,
    val isAutoStartSelected: Boolean = true,
    val isLocationSelected: Boolean = true,
)

internal interface InterfaceCustomizationInteractor {
    fun changeScreenServiceButtonSelection(isSelected: Boolean)
    fun changeAutoRunAllAvailableScreenServiceSelection(isSelected: Boolean)
    fun changeAgreementButtonSelection(isSelected: Boolean)
    fun changeCantScanIdButtonSelection(isSelected: Boolean)
    fun changeCounterSelection(isSelected: Boolean)
    fun changeAutoStartSelection(isSelected: Boolean)
    fun changeLocationSelection(isSelected: Boolean)
}

internal class InterfaceCustomizationViewModel :
    ComposeViewModel<InterfaceCustomizationState, Nothing, InterfaceCustomizationInteractor>(
        InterfaceCustomizationState()
    ) {

    override val interactor = object : InterfaceCustomizationInteractor {
        override fun changeScreenServiceButtonSelection(isSelected: Boolean) {
            activeState = activeState.copy(isScreenServiceButtonSelected = isSelected)
        }

        override fun changeAutoRunAllAvailableScreenServiceSelection(isSelected: Boolean) {
            activeState = activeState.copy(isAutoRunAllAvailableScreeningServicesSelected = isSelected)
        }

        override fun changeAgreementButtonSelection(isSelected: Boolean) {
            activeState = activeState.copy(isAgreementButtonSelected = isSelected)
        }

        override fun changeCantScanIdButtonSelection(isSelected: Boolean) {
            activeState = activeState.copy(isCantScanIdButtonSelected = isSelected)
        }

        override fun changeCounterSelection(isSelected: Boolean) {
            activeState = activeState.copy(isCounterSelected = isSelected)
        }

        override fun changeAutoStartSelection(isSelected: Boolean) {
            activeState = activeState.copy(isAutoStartSelected = isSelected)
        }

        override fun changeLocationSelection(isSelected: Boolean) {
            activeState = activeState.copy(isLocationSelected = isSelected)
        }
    }

}