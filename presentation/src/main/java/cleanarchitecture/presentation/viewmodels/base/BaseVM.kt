package cleanarchitecture.presentation.viewmodels.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

open class BaseVM:ViewModel()
{

    @Inject
    lateinit var compositeDisposable: CompositeDisposable


    fun add(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}