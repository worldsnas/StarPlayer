package com.worldsnas.starplayer.di.modules

import android.app.Application
import com.worldsnas.starplayer.api.WebServiceApi
import com.worldsnas.starplayer.model.LocalMusicProvider
import com.worldsnas.starplayer.model.LocalMusicProviderImpl
import com.worldsnas.starplayer.model.MusicRepository
import com.worldsnas.starplayer.model.MusicRepositoryImpl
import com.worldsnas.starplayer.model.persistent.AppDataBase
import com.worldsnas.starplayer.model.persistent.FavoriteMusicDao
import dagger.Module
import dagger.Provides

@Module
object MusicProviderModule {

    @JvmStatic
    @Provides
    fun provideLocalMusicImpl(application: Application): LocalMusicProvider {
        return LocalMusicProviderImpl(application.contentResolver)
    }

    @JvmStatic
    @Provides
    fun provideMusicRepository(
        webServiceApi: WebServiceApi,
        localMusicProvider: LocalMusicProvider, favoriteMusicDao: FavoriteMusicDao

    ): MusicRepository {
        return MusicRepositoryImpl(localMusicProvider, webServiceApi, favoriteMusicDao)
    }
}