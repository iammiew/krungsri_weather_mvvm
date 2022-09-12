package com.example.mytestkrungsri.di

import com.example.mytestkrungsri.R
import com.example.mytestkrungsri.application.app
import com.example.mytestkrungsri.data.api.*
import com.example.mytestkrungsri.data.providers.NetworkProvider
import com.example.mytestkrungsri.data.providers.NetworkProviderImpl
import com.example.mytestkrungsri.data.api.interceptor.loggingInterceptor
import com.example.mytestkrungsri.tools.Contextor
import com.franmontiel.persistentcookiejar.PersistentCookieJar

import com.ihsanbal.logging.LoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

const val NAME_BASE_URL = "Home base URL"

const val NAME_APP_GSON_CONVERTER_DEPENDENCY = "App's Gson Converter"

val appModule = module {

    factory(named(NAME_BASE_URL)) { Contextor.getInstance().context?.getString(R.string.base_url_open_weather) }

    factory(named(NAME_APP_GSON_CONVERTER_DEPENDENCY)) {
        GsonConverterFactory.create()
    }

    factory { app.getInstance().getCookieJar() }

    factory { loggingInterceptor }

    single<NetworkProvider> {
        NetworkProviderImpl(context = get())
    }

    factory<BaseOkHttpClientBuilder> {
        BaseOkHttpClientBuilderImpl(
            baseUrl = get(named(NAME_BASE_URL)),
            cookieJar = get<PersistentCookieJar>(),
            logInterceptor = get<LoggingInterceptor>()
        )
    }

    factory<WeatherApiInterface> {
        BaseRetrofitBuilder(
            baseUrl = get(named(NAME_BASE_URL)),
            baseOkHttpClientBuilder = get(),
            converterFactory = get<GsonConverterFactory>(named(NAME_APP_GSON_CONVERTER_DEPENDENCY))
        ).build()
    }
}