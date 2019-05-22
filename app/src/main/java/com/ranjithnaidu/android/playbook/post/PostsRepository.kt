package com.ranjithnaidu.android.playbook.post

import com.ranjithnaidu.android.playbook.services.PlaybookService
import com.ranjithnaidu.android.playbook.services.models.Post
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class PostsRepository : KoinComponent {

    private val postDetailsSubject = PublishSubject.create<Post>()
    val postDetailsUpdates: Observable<Post> = postDetailsSubject.hide()

    fun openPostDetail(post: Post) {
        postDetailsSubject.onNext(post)
    }

    private val playbookService: PlaybookService by inject()

    fun loadNoOfCommentsForPost(postId: String): Single<Int> {
        return playbookService.loadComments()
            .map { comments ->
                comments.filter { postId == it.postId }.size
            }
    }

    fun loadAuthorNameForPost(userId: String): Single<String> {
        return playbookService.loadAuthors()
            .map { users ->
                users.find { userId == it.id }?.name
            }
    }

    fun loadPosts(): Single<List<Post>> {
        return playbookService.loadPosts()
    }
}