package com.training.victor.development

import com.nhaarman.mockito_kotlin.*
import com.training.victor.development.data.DataManager
import com.training.victor.development.data.models.ImageViewModel
import com.training.victor.development.network.ImagesRepository
import com.training.victor.development.presenter.ImagesPresenter
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(MockitoJUnitRunner::class)
class ImagesPresenterTest: ParentUnitTest() {

    @Inject lateinit var dataManager: DataManager
    @Inject lateinit var imagesRepository: ImagesRepository
    @Mock lateinit var imagesView: ImagesPresenter.ImagesView

    private lateinit var testScheduler: TestScheduler
    private lateinit var imagesPresenter: ImagesPresenter

    @Before
    override fun setUp() {
        super.setUp()

        testNetworkComponent.inject(this)
        MockitoAnnotations.initMocks(this)
        testScheduler = TestScheduler()
        imagesPresenter = createMockedPresenter()
    }

    override fun <T> createMockedPresenter(): T {
        val imagesPresenter = ImagesPresenter(testScheduler, testScheduler, dataManager)
        imagesPresenter.view = imagesView
        return imagesPresenter as T
    }


    // --------------------------------------------- TESTING CASES ---------------------------------------------
    @Test
    fun `should call to image list API and retrieve a image list`() {
        val keyWord = "cars"
        whenever(imagesRepository.getImagesList(keyWord)).thenReturn(Observable.just(getFakeImageListResponse()))
        imagesPresenter.getImageList(keyWord)
        testScheduler.triggerActions()

        verify(imagesView, times(1)).onImageListReceived(anyList<ImageViewModel>())
    }

    @Test
    fun `should call to images list and retrieve an error`() {
        val keyWord = "cars"
        whenever(imagesRepository.getImagesList(keyWord)).thenReturn(Observable.error(Throwable("Token expired")))
        imagesPresenter.getImageList(keyWord)
        testScheduler.triggerActions()

        val errorMessage = "Token expired"
        verify(imagesView, times(1)).onImageListError(errorMessage)
    }
}
