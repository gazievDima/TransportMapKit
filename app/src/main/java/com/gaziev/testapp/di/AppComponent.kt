package com.gaziev.testapp.di

import android.app.Application
import com.gaziev.testapp.di.modules.*
import com.gaziev.testapp.ui.MainActivity
import com.gaziev.testapp.ui.screen.map.MapFragment
import com.gaziev.testapp.ui.screen.transport.TransportFragment
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(transportFragment: TransportFragment)
    fun inject(mapFragment: MapFragment)
}

@Module(includes = [
    DomainModule::class,
    MapperModule::class,
    RepositoryModule::class,
    SourceModule::class,
    ViewModelModule::class,
    DeserializedModule::class
])
class AppModule(app: Application) {
    private val application: Application = app

    @Provides
    fun provideApplication(): Application {
        return application
    }
}