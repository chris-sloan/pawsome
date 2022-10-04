# pawsome
The purrfect cat finder app. :cat2

Welcome to Paw-some. A simple Cat image loader, and given that cat images power the internet, you'll be doing your bit too.

## Layout

### All Breeds Screen 

This screen lists all available breeds. Scroll up and down and select your favourite. 
There's a drawer on the left which (eventually) will allow you to filter and order the breeds.

### Breed Detail Screen.

This screen is where the party is at. The images shown are tagged as per your chosen breed. (User content sometimes though, so I find it's not always accurate, great nonetheless).
Pull down on the top bar (eventually) to see the detail of the chosen breed. 
Click on an image (eventually) to enlarge it and view it in it's full glory.

## Technologies Used

- Kotlin 
- Gradle for build tooling, including buildSrc and configuration cache.
- Coroutines for concurrency
- Jetpack Compose for UI.
- Koin for DI.
- Material3 Components and theming including Material You and dynamic themes.
- Retrofit, OkHttp and Moshi for network calls and json mapping.
- Coil for image loading. 
- junit and mockK for unit tests

## TODO's

- Implement drawer filtering options on All Breeds - explain no functionality yet
- Implement drawer options on Breed Detail - show seek bar with ratings (re-use styling in filters)
- Refine UI
- Previews
- Tests
- Work out how to show emojis in a README
