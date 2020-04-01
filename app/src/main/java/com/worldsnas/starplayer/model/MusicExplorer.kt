package com.worldsnas.starplayer.model

import android.content.ContentResolver
import android.net.Uri
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class MusicExplorer @Inject
constructor(val contentResolver: ContentResolver) : GetAllMusic {


    private val projection = arrayOf(
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.ALBUM
    )
    private val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

    override suspend fun getAllMusic(): ArrayList<Music> {
        return GlobalScope.async(IO) {
            val cursor = contentResolver.query(uri, projection, null, null, null, null)
            val musics: ArrayList<Music> = ArrayList()

            if (cursor != null) {
                val titleColumn =
                    cursor.getColumnIndex(MediaStore.Audio.Genres.Members.TITLE)
                val idColumn = cursor.getColumnIndex(MediaStore.Audio.Genres.Members._ID)
                val artistColumn =
                    cursor.getColumnIndex(MediaStore.Audio.Genres.Members.ARTIST)
                val albumColumn =
                    cursor.getColumnIndex(MediaStore.Audio.Genres.Members.ALBUM)

                do {
                    var musicModel = Music(
                        cursor.getInt(idColumn),
                        cursor.getString(titleColumn),
                        cursor.getString(albumColumn),
                        cursor.getString(artistColumn),
                        ""
                    )

                    musics.add(musicModel)
                } while (cursor.moveToNext())
                cursor.close()
            }
            return@async musics
        }.await()
    }
}

