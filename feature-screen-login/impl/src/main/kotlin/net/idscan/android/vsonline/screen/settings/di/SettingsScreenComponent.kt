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

package net.idscan.android.vsonline.screen.settings.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.*
import dagger.multibindings.IntoMap
import net.idscan.android.vsonline.core.architecture.ViewModelFactory
import net.idscan.android.vsonline.core.architecture.ViewModelKey
import net.idscan.android.vsonline.screen.settings.SettingsScreenApi
import net.idscan.android.vsonline.screen.settings.SettingsScreenImpl
import net.idscan.android.vsonline.screen.settings.ui.InterfaceCustomizationViewModel
import net.idscan.android.vsonline.screen.settings.ui.SettingsViewModel
import net.idscan.android.vsonline.screen.settings.ui.WarningPopupViewModel
import javax.inject.Provider
import javax.inject.Singleton


@Component(modules = [ComponentModule::class, ViewModelModule::class, ViewModelMappingModule::class])
@Singleton
interface SettingsScreenComponent {
    fun screensProvider(): SettingsScreenApi

    @Component.Factory
    interface Factory {
        fun create(): SettingsScreenComponent
    }
}

@Module
private object ComponentModule {
    @Provides
    fun provideApi(viewModel: ViewModelProvider.Factory): SettingsScreenApi {
        return SettingsScreenImpl(viewModel)
    }
}

@Module
private object ViewModelModule {
    @Provides
    @JvmStatic
    fun providesWarningPopupViewModel(): WarningPopupViewModel {
        return WarningPopupViewModel()
    }

    @Provides
    @JvmStatic
    fun providesInterfaceCustomizationViewModel(): InterfaceCustomizationViewModel {
        return InterfaceCustomizationViewModel()
    }

    @Provides
    @JvmStatic
    fun providesSettingsViewModel(): SettingsViewModel {
        return SettingsViewModel()
    }

    @Provides
    @JvmStatic
    fun provideViewModelFactory(creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory {
        return ViewModelFactory(creators)
    }
}

@Module
private abstract class ViewModelMappingModule {
    @Binds
    @IntoMap
    @ViewModelKey(WarningPopupViewModel::class)
    abstract fun scanningWarningPopupViewModel(vm: WarningPopupViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InterfaceCustomizationViewModel::class)
    abstract fun scanningInterfaceCustomizationViewModel(vm: InterfaceCustomizationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun settingsViewModel(vm: SettingsViewModel): ViewModel
}