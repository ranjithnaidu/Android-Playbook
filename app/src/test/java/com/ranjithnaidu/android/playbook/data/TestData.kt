package com.ranjithnaidu.android.playbook.data

import com.ranjithnaidu.android.playbook.services.models.Comment
import com.ranjithnaidu.android.playbook.services.models.Post
import com.ranjithnaidu.android.playbook.services.models.User

object TestData {

    private const val postOneUserId = "1"
    private const val postOneId = "1"
    private const val postOneTitle = "Post One"
    private const val postOneBody = "First post's description"
    private val postOne = Post(
        userId = postOneUserId,
        id = postOneId,
        title = postOneTitle,
        body = postOneBody
    )

    private const val postTwoUserId = "2"
    private const val postTwoId = "2"
    private const val postTwoTitle = "Post Two"
    private const val postTwoBody = "Second post's description"
    private val postTwo = Post(
        userId = postTwoUserId,
        id = postTwoId,
        title = postTwoTitle,
        body = postTwoBody
    )

    private const val postThreeUserId = "3"
    private const val postThreeId = "3"
    const val postThreeTitle = "Post Three"
    private const val postThreeBody = "Third post's description"
    private val postThree = Post(
        userId = postThreeUserId,
        id = postThreeId,
        title = postThreeTitle,
        body = postThreeBody
    )

    val postsList = listOf(postOne, postTwo, postThree)

    const val postId = "1"
    const val postUserId = "1"
    const val postTitle = "Post Title"
    const val postBody = "Post description"

    private val commentOne = Comment(
        postId = "1",
        id = "1",
        body = "First Comment's description",
        email = "commentone@example.com",
        name = "Person One"
    )

    private val commentTwo = Comment(
        postId = "2",
        id = "2",
        body = "Second Comment's description",
        email = "commenttwo@example.com",
        name = "Person Two"
    )

    val commentsList = listOf(commentOne, commentTwo)

    const val commentsExpected = 1

    private val authorOne = User(
        id = "1",
        name = "Author One",
        email = "authorone@example.com",
        phone = "1234567890",
        username = "author_one",
        website = "www.author-one.com",
        address = null,
        company = null
    )

    private val authorTwo = User(
        id = "2",
        name = "Author Two",
        email = "authortwo@example.com",
        phone = "1234567899",
        username = "author_two",
        website = "www.author-two.com",
        address = null,
        company = null
    )

    val authorsList = listOf(authorOne, authorTwo)

    const val expectedAuthorName = "Author One"
}