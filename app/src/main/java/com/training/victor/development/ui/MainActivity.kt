package com.training.victor.development.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.training.victor.development.MainApplication
import com.training.victor.development.R
import com.training.victor.development.data.models.ImageViewModel
import com.training.victor.development.presenter.ImagesPresenter
import com.training.victor.development.utils.getDpFromValue
import com.training.victor.development.utils.hideKeyboard
import com.training.victor.development.utils.showRequestErrorMessage
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ImagesPresenter.ImagesView {

    @Inject lateinit var imagesPresenter: ImagesPresenter
    private val mImageList = ArrayList<ImageViewModel>()
    private lateinit var imagesAdapter: ImagesAdapter
    private val disposable: CompositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MainApplication).createPresenterComponent().inject(this)

        val myLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        lstProfiles.layoutManager = myLayoutManager
        lstProfiles.addItemDecoration(SpaceDecorator(getDpFromValue(10)))
        imagesAdapter = ImagesAdapter(mImageList)
        lstProfiles.adapter = imagesAdapter

        imagesPresenter.view = this
        imagesPresenter.getImageList("popular")

        disposable.add(createTextChangeObservable(edtKeyWord)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                imagesPresenter.getImageList(it)
                edtKeyWord.hideKeyboard()
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
        (application as MainApplication).releasePresenterComponent()
    }



    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- PRESENTER VIEW ---------------------------------------------
    override fun showProgressBar(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun onImageListReceived(imageList: List<ImageViewModel>) {
        mImageList.clear()
        mImageList.addAll(imageList)
        imagesAdapter.notifyDataSetChanged()
    }

    override fun onImageListError(errorMessage: String) {
        mImageList.clear()
        imagesAdapter.notifyDataSetChanged()
        showRequestErrorMessage(errorMessage)
    }



    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- METHODS AND ROUTINES ---------------------------------------
    private fun createTextChangeObservable(editText: EditText): Observable<String> {
        return Observable.create<String>{ emitter ->
            val watcher = object : TextWatcher {
                override fun afterTextChanged(s: Editable?) = Unit

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.toString()?.let { emitter.onNext(it) }
                }
            }

            editText.addTextChangedListener(watcher)

            emitter.setCancellable { editText.removeTextChangedListener(watcher) }
        }.filter { it.length > 2 }.debounce(1, TimeUnit.SECONDS)
    }
}
