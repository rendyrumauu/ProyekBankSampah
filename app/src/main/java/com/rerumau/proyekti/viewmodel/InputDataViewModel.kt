package com.rerumau.proyekti.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.rerumau.proyekti.database.DatabaseClient.Companion.getInstance
import com.rerumau.proyekti.database.DatabaseDao
import com.rerumau.proyekti.model.ModelDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class InputDataViewModel(application: Application) : AndroidViewModel(application) {

    var databaseDao: DatabaseDao?

    fun addDataSampah(
        nama_pengguna: String,
        jenis_sampah: String,
        berat: Int,
        harga: Int,
        tanggal: String,
        alamat: String,
    ) {
        Completable.fromAction {
            val modelDatabase = ModelDatabase(
                namaPengguna = nama_pengguna,
                jenisSampah = jenis_sampah,
                berat = berat,
                harga = harga,
                tanggal = tanggal,
                alamat = alamat
            )
            databaseDao?.insertData(modelDatabase)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    init {
        databaseDao = getInstance(application)?.appDatabase?.databaseDao()
    }

}