# Artic Gallery

Artic Gallery is an application designed to showcase artwork pictures sourced from the [Art Institute of Chicago API](https://api.artic.edu/docs/#website). It presents these images in a staggered grid layout. On the main screen, users can view thumbnails and brief descriptions of each artwork. Upon selecting a thumbnail, users are directed to a detailed screen featuring a higher quality image and comprehensive information about the piece, its creator, and any other pertinent data deemed relevant to the end user.

Furthermore, users have the ability to save favorite artworks, which remain accessible even after the application is closed and reopened through the 'Favorites' section. Additionally, the app sends push notifications every 15 minutes to prompt users to explore new artworks and engage with the application.

![ArticGallery](https://github.com/cristh18/ArticGallery/assets/1972427/0c21b098-1618-4b76-b91b-a6cea0c0c0f1)

[![Demo](https://img.youtube.com/vi/tAwNaFv7AIc/maxresdefault.jpg)](https://youtu.be/tAwNaFv7AIc)

## Architecture

The project is developed using Kotlin and adheres to the MVVM (Model-View-ViewModel) architectural pattern. It incorporates Hilt for dependency injection, Retrofit for handling network requests, Compose for constructing views, WorkflowManager for scheduling push notifications, and Timber for logging purposes.

![App architecture](https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview.png)

![Clean Architecture 2](https://github.com/android10/Sample-Data/raw/master/Android-CleanArchitecture/clean_architecture.png)

## Built With

- [`androidx-core-ktx`](https://developer.android.com/kotlin/ktx): This is a set of Kotlin extensions that are included with Android Jetpack and Android platform. These extensions add Kotlin language features, such as coroutines, extension functions, lambdas, and named parameters, to existing Android APIs.
- [`junit`](https://junit.org/junit4/): JUnit is a simple framework to write repeatable tests. It is an instance of the xUnit architecture for unit testing frameworks.
- [`androidx-espresso-core`](https://developer.android.com/training/testing/espresso): Espresso is a testing framework for Android to make it easy to write reliable user interface tests.
- [`androidx-lifecycle-runtime-ktx`](https://developer.android.com/kotlin/ktx#lifecycle): This is a set of Kotlin extensions for lifecycle-runtime.
- [`androidx-activity-compose`](https://developer.android.com/develop/ui/compose/migrate/strategy): This library contains the `setContent` function to set the content of an activity using Jetpack Compose.
- [`androidx-compose-bom`](https://developer.android.com/jetpack/androidx/releases/compose): The Bill of Materials (BoM) for Jetpack Compose. When you import the BoM, you don't need to specify versions for individual Compose libraries.
- [`hilt-android`](https://dagger.dev/hilt/) and [`hilt-android-compiler`](https://dagger.dev/hilt/): Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
- [`retrofit2`](https://square.github.io/retrofit/) and [`retrofit-moshi-converter`](https://square.github.io/retrofit/): Retrofit is a type-safe HTTP client for Android and Java. The Moshi converter is used for serialization and deserialization of objects.
- [`okhttp3`](https://square.github.io/okhttp/) and [`okhttp3-logging-interceptor`](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor): OkHttp is an HTTP client for Android and Java. The logging interceptor logs HTTP request and response data.
- [`moshi`](https://github.com/square/moshi) and [`moshi-kotlin-codegen`](https://github.com/square/moshi): Moshi is a modern JSON library for Android and Java. It makes it easy to parse JSON into Java objects (POJOs) and vice versa.
- [`androidx-constraintlayout`](https://developer.android.com/training/constraint-layout): ConstraintLayout allows you to create large and complex layouts with a flat view hierarchy (no nested view groups).
- [`androidx-appcompat`](https://developer.android.com/jetpack/androidx/releases/appcompat): This library adds support for the ActionBar user interface pattern with backward compatibility to older versions of Android.
- [`timber_logger`](https://github.com/JakeWharton/timber): Timber is a logger with a small, extensible API which provides utility on top of Android's normal Log class.
- [`fragment-ktx`](https://developer.android.com/kotlin/ktx#fragment): This is a set of Kotlin extensions for Fragment.
- [`coil-compose`](https://coil-kt.github.io/coil/compose/): Coil is an image loading library for Android backed by Kotlin Coroutines. This library is a Jetpack Compose extension of Coil.
- [`navigation-fragment-ktx`](https://developer.android.com/guide/navigation/navigation-getting-started) and [`navigation-ui-ktx`](https://developer.android.com/guide/navigation/navigation-ui): These are part of the Navigation component, which is a library that helps in implementing navigation in Android applications.
- [`legacy-support-v4`](https://developer.android.com/topic/libraries/support-library/packages#v4): This library provides backward-compatible versions of Android framework APIs.
- [`room-runtime`](https://developer.android.com/training/data-storage/room), [`room-ktx`](https://developer.android.com/training/data-storage/room), and [`room-compiler`](https://developer.android.com/training/data-storage/room): Room is a persistence library that provides an abstraction layer over SQLite and allows for more robust database access while harnessing the full power of SQLite.
- [`workManager`](https://developer.android.com/topic/libraries/architecture/workmanager): The WorkManager API makes it easy to specify deferrable, asynchronous tasks and when they should run.

### Prerequisites

- Java 17
- Kotlin SDK
- Android SDK

### Getting Started

To get started with the project, follow these steps:

1. Clone the repository: git clone https://github.com/cristh18/ArticGallery
2. Open the project in Android Studio.
3. Build and run the project on an Android device or emulator.

## Contributing

Please read `CONTRIBUTING.md` for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

- Cristhian Tolosa - Initial work - [cristh18](https://github.com/cristh18)

See also the list of [contributors](https://github.com/yourusername/AstronomyGallery/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the `LICENSE.md` file for details

