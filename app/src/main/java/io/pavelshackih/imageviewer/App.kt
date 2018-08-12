package io.pavelshackih.imageviewer

import android.app.Application
import io.pavelshackih.imageviewer.di.APP_SCOPE
import io.pavelshackih.imageviewer.di.AppModule
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.registries.FactoryRegistryLocator
import toothpick.registries.MemberInjectorRegistryLocator

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Toothpick.setConfiguration(getConfiguration())
        MemberInjectorRegistryLocator.setRootRegistry(io.pavelshackih.imageviewer.MemberInjectorRegistry())
        FactoryRegistryLocator.setRootRegistry(io.pavelshackih.imageviewer.FactoryRegistry())
        val appScope = Toothpick.openScope(APP_SCOPE)
        appScope.installModules(AppModule(this))
    }

    private fun getConfiguration(): Configuration {
        val conf = when {
            BuildConfig.DEBUG -> Configuration.forDevelopment()
            else -> Configuration.forProduction()
        }
        return conf.disableReflection()
    }
}