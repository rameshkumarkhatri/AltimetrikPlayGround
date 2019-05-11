package com.mobifyall.music

import androidx.lifecycle.ViewModel;
import com.app.androidapp.service.RetrofitFactory
import com.mobifyall.altimetrikplayground.view.MainFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {
    lateinit var view: MainFragmentView
    fun selectDayClicked() {
    }

    fun selectSongsClicked() {
//        if (view.checkForPermission())
//            view.changeFragmentWithBackStack(SongListFragment.newInstance())
//        else view.askForPermission("")

    }

    fun selectTimeClicked() {


    }

    fun search(id: String) {
        if (id.isEmpty())
            view.showEmptyError();
        else serviceCall(id);
    }

    private fun serviceCall(id: String) {

        RetrofitFactory.create().getVehicalDetail(id, RetrofitFactory.header).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> view.showResult(result) },
                { error -> view.showApiError(error) })
    }

}
