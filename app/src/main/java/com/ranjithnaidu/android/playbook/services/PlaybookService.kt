package com.ranjithnaidu.android.playbook.services

import com.ranjithnaidu.android.playbook.services.models.Comment
import com.ranjithnaidu.android.playbook.services.models.Post
import com.ranjithnaidu.android.playbook.services.models.User
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Retrofit
import retrofit2.http.GET

class PlaybookService : KoinComponent {

    private interface Client {
        @GET("posts")
        fun getPosts(): Single<List<Post>>

        @GET("users")
        fun getUsers(): Single<List<User>>

        @GET("comments")
        fun getComments(): Single<List<Comment>>
    }

    private val retrofit: Retrofit by inject()
    private val client = retrofit.create(Client::class.java)

    fun loadPosts(): Single<List<Post>> {
        return client.getPosts()
            .subscribeOn(Schedulers.io())
    }

    fun loadAuthors(): Single<List<User>> {
        return client.getUsers()
            .subscribeOn(Schedulers.io())
    }

    fun loadComments(): Single<List<Comment>> {
        return client.getComments()
            .subscribeOn(Schedulers.io())
    }
}