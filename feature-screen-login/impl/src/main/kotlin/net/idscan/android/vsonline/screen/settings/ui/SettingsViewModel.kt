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

import net.idscan.android.vsonline.core.architecture.ComposeViewModel

internal data class SettingsViewState(
    val activeChapter: Chapter = Chapter.Readers,
    val isChapter: Boolean = false
)

internal interface SettingsViewInteractor {
    fun selectChapter(chapter: Chapter)
    fun setIsChapter(isChapter: Boolean)
    fun back()
}

internal class SettingsViewModel :
    ComposeViewModel<SettingsViewState, Nothing, SettingsViewInteractor>(SettingsViewState()) {
    override val interactor = object : SettingsViewInteractor {
        override fun selectChapter(chapter: Chapter) {
            activeState = when (chapter) {
                Chapter.Readers -> activeState.copy(
                    activeChapter = Chapter.Readers,
                    isChapter = true,
                )
                Chapter.Interface -> activeState.copy(
                    activeChapter = Chapter.Interface,
                    isChapter = true,
                )
                Chapter.Warnings -> activeState.copy(
                    activeChapter = Chapter.Warnings,
                    isChapter = true,
                )
            }
        }

        override fun setIsChapter(isChapter: Boolean) {
            activeState = activeState.copy(isChapter = isChapter)
        }

        override fun back() {
            activeState = activeState.copy(isChapter = false)
        }
    }
}