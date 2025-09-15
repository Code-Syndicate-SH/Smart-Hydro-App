package org.smartroots

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
import org.smartroots.domain.GetNetworkConnectionUseCase
import org.smartroots.domain.GetSensorReadingsUseCase
import org.smartroots.presentation.viewmodel.HomeViewModel

// 👇 Fred (chat) imports
import org.smartroots.chat.ChatService
import org.smartroots.chat.ChatConfig
import org.smartroots.chat.KoogChatService
import org.smartroots.chat.Provider
import org.smartroots.chat.ui.ChatViewModel

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

            // 👇 Add Fred’s modules
            fredChatModule,
            fredChatUiModule
        )
    }

// ───────────────────────────────────────────────────────────────────────────────
// Existing modules
// ───────────────────────────────────────────────────────────────────────────────

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
    factory<NetworkService> { NetworkConfig() }
}

// network config repo
val networkRepositoryModule = module {
    factory<NetworkConfigRepository> { NetworkConfigRepositoryImpl(get()) }
}

val ktorClientModule = module {
    single(named("BASE_URL_LOCAL")) { "http://192.168.8.14/" }
    single(named("BASE_URL_REMOTE")) { "http://192.168.1.102/" } // this will change to web service soon.
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

// ───────────────────────────────────────────────────────────────────────────────
// NEW: Fred (chat) modules
// ───────────────────────────────────────────────────────────────────────────────

/**
 * Core chat wiring.
 * Default provider = OLLAMA (free/local). Override per-platform if needed.
 */
val fredChatModule = module {
    // Default config (override this in platformModule() if you want OpenRouter/OpenAI on that platform)
    single {
        ChatConfig(
            provider = Provider.OLLAMA,          // free/local by default
            apiKey = null,                       // not needed for Ollama
            modelId = "llama3.1:8b",             // change to any local model you’ve pulled
            temperature = 0.2,
            maxIterations = 8
        )
    }
    single<ChatService> { KoogChatService(get()) }
}

/**
 * UI-facing bits for Fred.
 * Plain factory is fine (our ChatViewModel is a lightweight class, not Android ViewModel).
 */
val fredChatUiModule = module {
    factory { ChatViewModel(get<ChatService>()) }
}
