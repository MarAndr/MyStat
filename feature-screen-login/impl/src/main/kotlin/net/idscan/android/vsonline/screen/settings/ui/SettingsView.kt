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

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import net.idscan.android.vsonline.screen.settings.utils.CrossSlideLayout
import net.idscan.android.vsonline.ui.theme.LocalWindowSize
import net.idscan.android.vsonline.ui.theme.WindowSize


@Composable
internal fun SettingsView(
    modifier: Modifier,
    viewModelProvider: ViewModelProvider.Factory,
) {
    val viewModel = viewModel<SettingsViewModel>(factory = viewModelProvider)
    val state by viewModel.state.collectAsState()
    val interactor = viewModel.interactor

    SettingsView(
        modifier = modifier,
        state = state,
        interactor = interactor,
        viewModelProvider = viewModelProvider
    )
}

@Composable
private fun SettingsView(
    modifier: Modifier,
    state: SettingsViewState,
    interactor: SettingsViewInteractor,
    viewModelProvider: ViewModelProvider.Factory
) {
    when (LocalWindowSize.current) {
        WindowSize.Compact -> SinglePanelView(
            modifier = modifier,
            state = state,
            interactor = interactor,
            viewModelProvider = viewModelProvider
        )
        WindowSize.Medium -> SinglePanelView(
            modifier = modifier,
            state = state,
            interactor = interactor,
            viewModelProvider = viewModelProvider
        )
        WindowSize.Expanded -> TwoPanelView(
            modifier = modifier,
            state = state,
            interactor = interactor,
            viewModelProvider = viewModelProvider
        )
    }
}

@Composable
private fun TwoPanelView(
    modifier: Modifier,
    state: SettingsViewState,
    interactor: SettingsViewInteractor,
    viewModelProvider: ViewModelProvider.Factory
) {
    interactor.setIsChapter(true)
    Row(modifier = modifier) {
        ChapterListView(
            modifier = Modifier
                .weight(0.35f)
                .fillMaxHeight(),
            onChapterSelected = interactor::selectChapter,
            activeChapter = state.activeChapter
        )

        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp),
            color = MaterialTheme.colors.background
        )

        Crossfade(
            targetState = state.activeChapter,
            modifier = Modifier.weight(0.65f)
        ) { chapter ->
            when (chapter) {
                Chapter.Readers -> {
                    ReadersView(
                        isHeader = false
                    )
                }
                Chapter.Interface -> {
                    InterfaceCustomizationView(
                        modifier = Modifier.fillMaxSize(),
                        interfaceCustomizationViewModel = viewModel(factory = viewModelProvider),
                        isHeader = false
                    )

                }
                Chapter.Warnings -> {
                    WarningPopupView(
                        modifier = Modifier.fillMaxSize(),
                        warningPopupViewModel = viewModel(factory = viewModelProvider),
                        isHeader = false
                    )
                }
            }
        }
    }
}

@Composable
private fun SinglePanelView(
    modifier: Modifier,
    state: SettingsViewState,
    interactor: SettingsViewInteractor,
    viewModelProvider: ViewModelProvider.Factory,
) {

    val animationProgress by animateFloatAsState(
        targetValue = if (state.isChapter) 1f else 0f,
        animationSpec = tween(
            durationMillis = 200,
            easing = FastOutSlowInEasing
        )
    )

    BackHandler(
        enabled = state.isChapter,
        onBack = interactor::back
    )


    CrossSlideLayout(
        screen1 = {
            ChapterListView(modifier = modifier, onChapterSelected = interactor::selectChapter)
        },
        screen2 = {

            when (state.activeChapter) {
                Chapter.Readers -> {
                    ReadersView()
                }
                Chapter.Interface -> {
                    InterfaceCustomizationView(
                        modifier = modifier,
                        interfaceCustomizationViewModel = viewModel(factory = viewModelProvider)
                    )
                }
                Chapter.Warnings -> {
                    WarningPopupView(
                        modifier = modifier,
                        warningPopupViewModel = viewModel(factory = viewModelProvider)
                    )
                }
            }
        },
        animationProgress = animationProgress
    )

}
