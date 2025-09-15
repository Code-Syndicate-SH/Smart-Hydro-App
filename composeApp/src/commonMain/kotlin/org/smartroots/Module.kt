package org.smartroots

import dev.tmapps.konnection.Konnection
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.smartroots.data.database.AppDatabase
import org.smartroots.data.database.getRoomDatabase
import org.smartroots.data.repository.NetworkConfigRepository
import org.smartroots.data.repository.NetworkConfigRepositoryImpl
import org.smartroots.data.repository.SensorRepository
import org.smartroots.data.repository.SensorRepositoryImpl
import org.smartroots.data.repository.dbRepository.BoxRepository
import org.smartroots.data.repository.dbRepository.BoxRepositoryImpl
import org.smartroots.data.repository.dbRepository.NoteRepository
import org.smartroots.data.repository.dbRepository.NoteRepositoryImpl
import org.smartroots.data.repository.dbRepository.SensorStatusRepository
import org.smartroots.data.repository.dbRepository.SensorStatusRepositoryImpl
import org.smartroots.data.repository.dbRepository.TentRepository
import org.smartroots.data.repository.dbRepository.TentRepositoryImpl
import org.smartroots.data.service.NetworkConfig
import org.smartroots.data.service.NetworkService
import org.smartroots.data.service.SensorAPI
import org.smartroots.data.service.SensorAPIImpl
import org.smartroots.domain.GetNetworkConnectionUseCase
import org.smartroots.domain.GetSensorReadingsUseCase
import org.smartroots.presentation.viewmodel.HomeViewModel


expect fun platformModule(): Module
fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            platformModule(),
            dbDatabaseDao,
            provideDatabaseRepository,
            sensorRepositoryModule,
            networkConfigModule,
            networkRepositoryModule,
            NetworkConnectionUseCaseModule,
            GetSensorReadingsUseCaseModule,
            homeViewModelModule,
            ktorClientModule,
            SensorReadingModule
            // add my modules
        )
    }

// repos for database
val provideDatabaseRepository = module {
    single { getRoomDatabase(get()) }
    factory<BoxRepository> { BoxRepositoryImpl(get()) }
    factory<SensorStatusRepository> { SensorStatusRepositoryImpl(get()) }
    factory<TentRepository> { TentRepositoryImpl(get()) }
    factory<NoteRepository> { NoteRepositoryImpl(get()) }
}


val sensorRepositoryModule = module {
    factory<SensorRepository> { (baseURL: String) ->
        val api = get<SensorAPI> { parametersOf(baseURL) }
        SensorRepositoryImpl(api)
    }
}
val networkConfigModule = module {
    factory<NetworkService> { NetworkConfig(get()) }
    single { Konnection.createInstance() }
}

// network configREpo
val networkRepositoryModule = module {
    factory<NetworkConfigRepository> { NetworkConfigRepositoryImpl(get()) }
}
val ktorClientModule = module {
    single(named("BASE_URL_LOCAL")) { "http://192.168.8.14/" }
    single(named("BASE_URL_REMOTE")) { "http://192.168.1.102/" } // this will change to web service soon.

}
val SensorReadingModule = module {
    factory<SensorAPI> { (baseURL:String) ->
        SensorAPIImpl(
            baseURL = baseURL,
            tentClient = get(),

            )
    }
}
// daos
val dbDatabaseDao = module {
    factory { get<AppDatabase>().getBoxDao() }
    factory { get<AppDatabase>().getNoteDao() }
    factory { get<AppDatabase>().getTentDao() }
    factory { get<AppDatabase>().getSensorReadingActivity() }
}
// use case

val NetworkConnectionUseCaseModule = module {
    factoryOf(::GetNetworkConnectionUseCase)
}
val GetSensorReadingsUseCaseModule = module {
    factoryOf(::GetSensorReadingsUseCase)
}

// viewModels
val homeViewModelModule = module {
    viewModelOf(::HomeViewModel)
}