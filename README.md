# Paw-some  - [![Android CI](https://github.com/chris-sloan/pawsome/actions/workflows/android.yml/badge.svg)](https://github.com/chris-sloan/pawsome/actions/workflows/android.yml)
The purrfect cat finder app. :cat2

Welcome to Paw-some. A simple Cat image loader, and given that cat images power the internet, you'll be doing your bit too.

## Layout

### All Breeds Screen 

This screen lists all available breeds. Scroll up and down and select your favourite. 
There's a drawer on the left which will allow you to filter and order the breeds. Currently you can view and interact with the available filters.

#### Next Steps 

- Implement filtering

### Breed Detail Screen.

This screen is where the party is at. The images shown are tagged as per your chosen breed. (User content sometimes though, so I find it's not always accurate, great nonetheless).
Expand the top bar to see further detail of the chosen breed. 

### Next Steps 

- Navigate to the wiki url, if it is available.
- Expand images on user clicks.

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

## Development Approach.

- Evaluate API, and create account
- Create new project, repo, set up build scripts etc.
- Migrate to clean multi module architecture
- Add some CI set up in github actions
- Starting with the first api call begin to implement the code and first iterations of UI
- Step back and evaluate. Decide on next steps.
- Refine / Improve / Optimise / etc.
- Finalise README documentation.

## TODO's

- Hide the api key in some sort of local.properties file. Use CI to inject it at build time. (then close existing account)
- Previews
- Further UI Tests
- Compose Tests
- Implement Room for improved caching. 
- Improve error handling / network status observing. 
- Work out how to show emojis in a README
