package com.rerumau.proyekti.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rerumau.proyekti.database.DatabaseClient.Companion.getInstance
import com.rerumau.proyekti.database.DatabaseDao
import com.rerumau.proyekti.model.ModelDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class RiwayatViewModel(application: Application) : AndroidViewModel(application) {

    var totalSaldo: LiveData<Int>
    var dataBank: LiveData<List<ModelDatabase>>
    var databaseDao: DatabaseDao

    fun deleteDataById(uid: Int) {
        Completable.fromAction {
            databaseDao.deleteSingleData(uid)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    init {
        databaseDao = getInstance(application)?.appDatabase?.databaseDao()!!
        dataBank = databaseDao.getAll()
        totalSaldo = databaseDao.getSaldo()
    }

}