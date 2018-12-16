package com.training.victor.development.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.google.gson.JsonObject
import com.training.victor.development.MainApplication
import com.training.victor.development.R
import com.training.victor.development.data.models.ProfileItem
import com.training.victor.development.presenter.ImagesPresenter
import com.training.victor.development.utils.getDpFromValue
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ImagesPresenter.ImagesView {

    @Inject lateinit var imagesPresenter: ImagesPresenter
    private val mProfilesList = ArrayList<ProfileItem>()
    private lateinit var profilesAdapter: ProfilesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MainApplication).createPresenterComponent().inject(this)

        val myGridLayoutManager = GridLayoutManager(this, 2)
        lstProfiles.layoutManager = myGridLayoutManager
        lstProfiles.addItemDecoration(SpaceDecorator(getDpFromValue(10)))
        profilesAdapter = ProfilesAdapter(mProfilesList)
        lstProfiles.adapter = profilesAdapter

        imagesPresenter.view = this
        imagesPresenter.getImageList("cars")
    }

    override fun onDestroy() {
        super.onDestroy()
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

    override fun onImageListReceived(profilesList: ArrayList<JsonObject>) {
        // todo:: refactor all models
//        mProfilesList.clear()
//        mProfilesList.addAll(profilesList)
//        profilesAdapter.notifyDataSetChanged()
    }

    override fun onImageListError() {
        mProfilesList.clear()
        profilesAdapter.notifyDataSetChanged()
        // todo:: show error message
    }
}
