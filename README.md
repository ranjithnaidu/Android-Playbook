# Android-Playbook

This project aims to show the App architecture of a master-detail user flow on android.
##### All the endpoint used by the app are mentioned below

 - http://jsonplaceholder.typicode.com/posts
 - http://jsonplaceholder.typicode.com/users
 - http://jsonplaceholder.typicode.com/comments
 
##### What's the app about?

The app displays a list of posts and allows the users to click on it and opens a bottom sheet with the details of post with following info
 - Post Title (This is supplied from the master list)
 - Post Body (This describes the post and this data is also supplied from the master list)
 - No. of Comments for the post (This is loaded from the api upon opening the page)
 - Author name (This is loaded from the api upon opening the page)

Please find the Screeshots below:-

![Screenshot_1558520659](https://user-images.githubusercontent.com/2275562/58169007-39fea480-7cc2-11e9-85c7-2e0e9fffef8a.png)
![Screenshot_1558520670](https://user-images.githubusercontent.com/2275562/58169012-3c60fe80-7cc2-11e9-94d5-bae9ba47a45d.png)
![Screenshot_1558520666](https://user-images.githubusercontent.com/2275562/58169019-3ec35880-7cc2-11e9-8568-d27e08801f1b.png)

## App Architecture

This app is entirely written in [Kotlin](https://kotlinlang.org/).

I used [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) to keep out the business logic away from the activities and fragments. Also used [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) and [Data Binding](https://developer.android.com/topic/libraries/data-binding) (where ever required) to bind the views and data elements. I've partially implemented the Repository layer in order to handle data operations especially with comments and authors endpoints where a huge chunck of data is given by the API which is not fully required by the viewModels.

[Koin](https://insert-koin.io/) is used for Dependency injection (this can be changed to [Dagger2](https://google.github.io/dagger/))

The services job is to interact with our apis and convert the responses into models that the rest of the app can use. I'm currently using [Retrofit2](https://square.github.io/retrofit/), [Okhttp](https://square.github.io/okhttp/) and RxJava](https://github.com/ReactiveX/RxJava) Adapter to automatically convert the api responses into Rx observables.

I'm using Junit and [nhaarman's mockito-kotlin](https://github.com/nhaarman/mockito-kotlin) library for Unit-testing.

For any further queries please contact at <ranjithnaidu.v@gmail.com>
