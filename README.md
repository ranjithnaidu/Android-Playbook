# Android-Playbook

This project demonstrates the app architecture of a master-detail user flow on android.
##### All the endpoints used by the app are given below:

 - http://jsonplaceholder.typicode.com/posts
 - http://jsonplaceholder.typicode.com/users
 - http://jsonplaceholder.typicode.com/comments
 
##### What's the app about?

The app displays a list of posts and allows the user to click on it by opening a bottom sheet with the following details:
 - Post Title (This is supplied from the master list)
 - Post Body (This is a description of the post and is also supplied from the master list)
 - Number of Comments for the post (This is loaded from the api upon opening the page)
 - Author name (This is also loaded from the api upon opening the page)

Please find the Screeshots below:

![Screenshot_1558520659](https://user-images.githubusercontent.com/2275562/58169007-39fea480-7cc2-11e9-85c7-2e0e9fffef8a.png)
![Screenshot_1558520670](https://user-images.githubusercontent.com/2275562/58169012-3c60fe80-7cc2-11e9-94d5-bae9ba47a45d.png)
![Screenshot_1558520666](https://user-images.githubusercontent.com/2275562/58169019-3ec35880-7cc2-11e9-8568-d27e08801f1b.png)

## App Architecture

This app is entirely written in [Kotlin](https://kotlinlang.org/).

[MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) has been used in the app to keep the business logic away from the activities and fragments. [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) and [Data Binding](https://developer.android.com/topic/libraries/data-binding) was used as required to bind the views and data elements. Repository layer has been partially implemented in order to handle data operations especially with comments and authors endpoints where a huge chunck of data is given by the API not fully required by the viewModels.

[Koin](https://insert-koin.io/) is used for Dependency injection ([Dagger2](https://google.github.io/dagger/) could also be used here)

The job of services is to interact with the apis and convert the responses into models which can be used by the rest of the app.[Retrofit2](https://square.github.io/retrofit/), [Okhttp](https://square.github.io/okhttp/) and [RxJava](https://github.com/ReactiveX/RxJava) Adapter has been used to automatically convert the api responses into Rx observables.

Junit and [nhaarman's mockito-kotlin](https://github.com/nhaarman/mockito-kotlin) library has been used for Unit-testing.

For any queries please contact <ranjithnaidu.v@gmail.com>
