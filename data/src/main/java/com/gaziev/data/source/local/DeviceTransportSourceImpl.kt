package com.gaziev.data.source.local

import android.content.res.AssetManager
import com.gaziev.data.model.DeviceTransportEntity
import com.gaziev.data.repository.DeviceTransportSource
import com.gaziev.data.source.Deserialized
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import javax.inject.Inject

class DeviceTransportSourceImpl @Inject constructor(
    private val assetsManager: AssetManager,
    private val deserialized: Deserialized<String, List<DeviceTransportEntity>>
) : DeviceTransportSource {

    companion object {
        const val FILE_NAME = "transport.json"
    }

    override fun getDeviceTransport(): Single<List<DeviceTransportEntity>> {
        return Single.create {

            try {
                val reader = InputStreamReader(assetsManager.open(FILE_NAME))
                val result = reader.readText()
                val list = deserialized.get(result)
                it.onSuccess(list)

            } catch(e: FileNotFoundException) {
                it.onError(e)
            } catch(e: IOException) {
                it.onError(e)
            } catch(e: Exception) {
                it.onError(e)
            }
        }
    }

}