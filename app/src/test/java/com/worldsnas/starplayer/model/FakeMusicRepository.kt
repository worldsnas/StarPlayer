package com.worldsnas.starplayer.model

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * this fake repository is gonna used to simulate repository's treats
 */
class FakeMusicRepository(private val dispatcher: CoroutineDispatcher) : MusicRepository {

    private var emptyFlag: Boolean = false

    /**
     * by this method you can set empty list as a return for other methods
     * @param emptyFlag is used to determine if methods returns empty or not
     */
    fun setFlagReturnEmptyList(emptyFlag: Boolean) {
        this.emptyFlag = emptyFlag
    }


    /**
     * get musics from api
     */
    override suspend fun getApiData(page: Int, count: Int): List<MusicRepoModel> {
        return withContext(dispatcher) {
            if (emptyFlag) emptyList()
            else createFakeMusics(count)
        }

    }

    /**
     * get local musics from your device
     */
    override suspend fun getLocalData(): List<MusicRepoModel> {
        return withContext(dispatcher) {
            if (emptyFlag) emptyList()
            else createFakeMusics()
        }
    }


    /**
     * this method can be used for creating fake music list
     */
    private fun createFakeMusics(count: Int = 10): List<MusicRepoModel> {
        val fakeMusicList = arrayListOf<MusicRepoModel>()
        for (i in 0 until count) {
            fakeMusicList.add(
                MusicRepoModel(
                    i, "music_$i", "artist_$i", "album_$i", "genre_$i", "address_$i"
                )
            )
        }
        return fakeMusicList
    }
}