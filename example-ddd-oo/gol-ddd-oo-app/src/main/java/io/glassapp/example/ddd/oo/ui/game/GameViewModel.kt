package io.glassapp.example.ddd.oo.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.glassapp.ddd.oo.gameoflife.GolContext
import io.glassapp.ddd.oo.gameoflife.domain.WorldData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class GameViewModel : ViewModel() {
    private val gofContext = GolContext()
    private val compositeDisposable = CompositeDisposable()
    private val worldData: MutableLiveData<WorldData> = MutableLiveData()

    fun start(rows: Int, columns: Int) {
        val newWorldData = gofContext.gameService.createNewWorld(rows, columns)
        worldData.value = newWorldData

        Observable.interval(500, TimeUnit.MILLISECONDS)
            .map { gofContext.gameService.generateNextIteration(newWorldData.id) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { worldData.value = it }
            .let { compositeDisposable.add(it) }
    }

    fun worldData(): LiveData<WorldData> {
        return worldData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
