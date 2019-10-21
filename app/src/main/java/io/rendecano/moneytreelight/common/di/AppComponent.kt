package io.rendecano.moneytreelight.common.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import io.rendecano.moneytreelight.MoneytreeLightApplication
import io.rendecano.moneytreelight.accounts.di.AccountModule
import io.rendecano.stox.list.di.AccountBindingModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            (AndroidSupportInjectionModule::class),
            (AppModule::class),
            (AppBindingModule::class),
            (AccountModule::class),
            (AccountBindingModule::class)
        ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MoneytreeLightApplication)
}