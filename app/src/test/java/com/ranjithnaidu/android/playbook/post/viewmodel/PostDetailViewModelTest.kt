package com.ranjithnaidu.android.playbook.post.viewmodel

import com.nhaarman.mockitokotlin2.whenever
import com.ranjithnaidu.android.playbook.data.TestData.authorItemToBePassed
import com.ranjithnaidu.android.playbook.data.TestData.commentItemToBePassed
import com.ranjithnaidu.android.playbook.data.TestData.commentsExpected
import com.ranjithnaidu.android.playbook.data.TestData.expectedAuthorName
import com.ranjithnaidu.android.playbook.data.TestData.postBody
import com.ranjithnaidu.android.playbook.data.TestData.postId
import com.ranjithnaidu.android.playbook.data.TestData.postTitle
import com.ranjithnaidu.android.playbook.data.TestData.postUserId
import com.ranjithnaidu.android.playbook.helpers.BaseUnitTest
import com.ranjithnaidu.android.playbook.helpers.LiveDataTestUtil
import io.reactivex.Single
import org.junit.Assert
import org.junit.Test

class PostDetailViewModelTest : BaseUnitTest() {

    private lateinit var postDetailViewModelUnderTest: PostDetailViewModel

    override fun setup() {
        super.setup()

        whenever(mockPostsRepository.loadNoOfCommentsForPost(postId)).thenReturn(Single.just(commentItemToBePassed))
        whenever(mockPostsRepository.loadAuthorNameForPost(postUserId)).thenReturn(Single.just(authorItemToBePassed))

        postDetailViewModelUnderTest = PostDetailViewModel()
        postDetailViewModelUnderTest.setPostTitleAndBody(
            postTitle = postTitle,
            postBody = postBody
        )
    }

    @Test
    fun `test post data in the viewModel`(){
        Assert.assertNotNull(postDetailViewModelUnderTest.postTitle)
        Assert.assertNotNull(postDetailViewModelUnderTest.postBody)
    }

    @Test
    fun `test live data in the viewModel`(){
        Assert.assertNotNull(LiveDataTestUtil.getValue(postDetailViewModelUnderTest.postTitle))
        Assert.assertNotNull(LiveDataTestUtil.getValue(postDetailViewModelUnderTest.postBody))

        Assert.assertEquals(postTitle, LiveDataTestUtil.getValue(postDetailViewModelUnderTest.postTitle))
        Assert.assertEquals(postBody, LiveDataTestUtil.getValue(postDetailViewModelUnderTest.postBody))
    }

    @Test
    fun `test the number of comments`() {
        postDetailViewModelUnderTest.setPostId(postId)

        Assert.assertEquals(postId, LiveDataTestUtil.getValue(postDetailViewModelUnderTest.postId))
        Assert.assertEquals(commentsExpected, LiveDataTestUtil.getValue(postDetailViewModelUnderTest.noOfComments))
    }

    @Test
    fun `test post's author name`() {
        postDetailViewModelUnderTest.setPostUserId(postUserId)

        Assert.assertEquals(postUserId, LiveDataTestUtil.getValue(postDetailViewModelUnderTest.postUserId))
        Assert.assertEquals(expectedAuthorName, LiveDataTestUtil.getValue(postDetailViewModelUnderTest.authorName))
    }
}