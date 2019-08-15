package cleanarchitecture.presentation.viewmodels.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseVM:ViewModel()
{
    lateinit var compositeDisposable: CompositeDisposable

    fun add(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}