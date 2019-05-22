package com.ranjithnaidu.android.playbook.helpers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.ranjithnaidu.android.playbook.data.TestData
import com.ranjithnaidu.android.playbook.post.PostsRepository
import com.ranjithnaidu.android.playbook.services.PlaybookService
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.koin.standalone.StandAloneContext
import org.koin.test.KoinTest
import org.koin.test.declare

/**
 * This base class just mocks out some common dependencies and tears down Koin at the end of
 * each test and provides some common functions for unit tests.
 */
open class BaseUnitTest : KoinTest {

    @get:Rule
    val immediateSchedulersRule = ImmediateSchedulersRule()
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    protected val mockPlaybookService = mock<PlaybookService>()
    protected val mockPostsRepository = mock<PostsRepository>()

    @Before
    open fun setup() {
        declare {
            single { mockPlaybookService }
            single { mockPostsRepository }
        }

        whenever(mockPostsRepository.loadPosts()).thenReturn(Single.just(TestData.postsList))
    }

    @After
    fun tearDown() {
        StandAloneContext.stopKoin()
    }
}