package com.gaziev.testapp.ui.screen.transport

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gaziev.domain.model.DeviceTransportDetails
import com.gaziev.domain.usecase.GetListDeviceTransportUseCase
import com.gaziev.testapp.ui.common.StateUI
import com.gaziev.testapp.ui.model.Transport
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TransportViewModel @Inject constructor(
    private val getListDeviceTransportUseCase: GetListDeviceTransportUseCase,
) : ViewModel() {

    private val _stateUI: MutableLiveData<StateUI> = MutableLiveData(StateUI.Loading)
    val stateUI: LiveData<StateUI> = _stateUI

    private var disposable: Disposable? = null
    private var _listTransport: MutableLiveData<List<Transport>> = MutableLiveData()
    val listTransport: LiveData<List<Transport>> = _listTransport

    init {
        disposable = getListDeviceTransportUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                _stateUI.value = StateUI.Error
            }
            .subscribe { list ->
                _listTransport.value = mapToTransport(list)
                _stateUI.value = StateUI.Success
            }
    }

    override fun onCleared() {
        super.onCleared()
        disposable = null
    }

    private fun mapToTransport(list: List<DeviceTransportDetails>): List<Transport> {
        val group = getGroup(list)
        return getListTransport(list, group)
    }

    private fun getGroup(list: List<DeviceTransportDetails>): Set<String> {
        val set = mutableSetOf<String>()
        list.forEach { set.add(it.parent?.name!!) }
        return set
    }

    private fun getListTransport(
        list: List<DeviceTransportDetails>,
        group: Set<String>
    ): List<Transport> {
        val newList = mutableListOf<Transport>()
        for (item in group) {
            newList.add(Transport(true, item, null, null, null))
            for (tr in list) {
                if (item == tr.parent?.name!!) newList.add(
                    Transport(false, null, tr.title, tr.lat, tr.lon)
                )
            }
        }
        return newList
    }
}
