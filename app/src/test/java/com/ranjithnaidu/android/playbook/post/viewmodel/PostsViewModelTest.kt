package com.ranjithnaidu.android.playbook.post.viewmodel

import com.jraska.livedata.test
import com.nhaarman.mockitokotlin2.whenever
import com.ranjithnaidu.android.playbook.data.TestData.postThreeTitle
import com.ranjithnaidu.android.playbook.data.TestData.postsList
import com.ranjithnaidu.android.playbook.helpers.BaseUnitTest
import com.ranjithnaidu.android.playbook.post.view.PostAdapter
import io.reactivex.Single
import org.junit.Assert
import org.junit.Test

class PostsViewModelTest : BaseUnitTest() {

    private lateinit var postsViewModelUnderTest: PostsViewModel

    override fun setup() {
        super.setup()
        whenever(mockPlaybookService.loadPosts()).thenReturn(Single.just(postsList))
        postsViewModelUnderTest = PostsViewModel()
    }

    @Test
    fun `test to check posts are loaded`() {
        Assert.assertFalse(postsViewModelUnderTest.postsLoading)
    }

    @Test
    fun `test to check posts are loaded and the post is present`() {
        val postsAdapterItemsTestObserver = postsViewModelUnderTest.postsAdapterItems.test()

        val postItems = postsAdapterItemsTestObserver.valueHistory().last()
        val posts = postItems
            .filter { it is PostAdapter.PostAdapterItem.PostItem }
            .map { it as PostAdapter.PostAdapterItem.PostItem }
        Assert.assertEquals(postsList.size, posts.size)
        assert(posts.any { it.post.title == postThreeTitle })
    }
}