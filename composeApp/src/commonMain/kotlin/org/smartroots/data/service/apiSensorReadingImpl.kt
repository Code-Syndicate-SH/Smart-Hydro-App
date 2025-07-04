package org.smartroots.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.smartroots.data.model.Sensor
import org.smartroots.data.module.createHttpClient

