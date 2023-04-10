# compose-splash-screens

<div id="header" align="center">
  <a href="https://jitpack.io/#ygorluizfrazao/compose-audio-controls"><img src="https://jitpack.io/v/ygorluizfrazao/compose-audio-controls.svg" alt="Version Name"/></a>
  <img src="https://komarev.com/ghpvc/?username=ygorluizfrazao&style=flat-square&color=blue" alt=""/>
</div>
<div id="badges" align="center">
  <a href="https://www.linkedin.com/in/ygorluizfrazao/">
    <img src="https://img.shields.io/badge/LinkedIn-blue?style=flat&logo=linkedin&logoColor=white" alt="LinkedIn Badge"/>
  </a>
  <a href="https://ko-fi.com/ygorfrazao">
    <img src="https://img.shields.io/badge/Kofi-blue?style=flat&logo=kofi&logoColor=white" alt="Youtube Badge"/>
  </a>
</div>

## What is it?
An easy and intuitive way to provide a splash screen in your [Jetpack Compose App]([https://developer.android.com/jetpack/compose?gclid=CjwKCAjw586hBhBrEiwAQYEnHZOEMECdqsc2PVuxrptarSrEil2VmFCsftyhI_bE0_jgMYzocISQ5hoCTZYQAvD_BwE&gclsrc=aw.ds]).

## Why?
Because internet seems to love overcomplicating things. Almost all sources i've found uses [Navigation Component]([https://developer.android.com/jetpack/compose/navigation]) to achieve such a trivial thing, unnecessarily inflating code and creating a barrier for beginners.

Think with me:

When do you want a Splash Screen?
- When the *app launches*.

After that, you will need to display it again?
- *No*.

When your app goes into background and returns, do you want to show a splash screen again?
- *No*.

Then tell me, why would you want a backstack with it? Why would you want to create more destinations to your navigation component, and, most certainly, create nested navigation having to pass as param the navhost back and forth?

## How to use it:

At this moment you have tow options:

The base composable where you control when the Splash Screen finishes.

```kotlin
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    finished: Boolean,
    beforeFinished: @Composable BoxScope.() -> Unit,
    whenFinished: @Composable () -> Unit
)
```

- `finished: Boolean` -> Pass as a state and control when the splash screen finishes.
- `beforeFinished: @Composable BoxScope.() -> Unit` -> You splash screen, will be called when finished is `false`.
- `whenFinished: @Composable () -> Unit` -> You application, will be called when finished is `true`.

Say you want to load some resources from internet while your splash screen is displayed, use it and flick `finished` when you want to.

There is also:

```kotlin
@Composable
fun CountDownSplashScreen(
    modifier: Modifier = Modifier,
    totalTimeInMillis: Long = 2000,
    notifyMeEveryMillis: Long = 200,
    onNotify: () -> Unit = {},
    beforeFinished: @Composable BoxScope.() -> Unit,
    whenFinished: @Composable () -> Unit
)
```

Which will start a `Timer` and finishes after the `totalTimeInMillis` is reached.

- `totalTimeInMillis: Long` -> Time the splash screen will be visible.
- `notifyMeEveryMillis: Long` -> Frequency `onNotify` will be called.

### Helpers
I've also included two composables that you can use as ` beforeFinished: @Composable BoxScope.() -> Unit`.

#### When you want a static image and a text:

```kotlin
@Composable
fun CenteredImageAndText(
    modifier: Modifier = Modifier,
    @DrawableRes imageDrawableRes: Int,
    contentDescription: String,
    text: String,
    textStyle: TextStyle = LocalTextStyle.current
)
```

Example:

<img src="https://user-images.githubusercontent.com/17025709/230975842-73d5749b-35bf-48ec-a1e7-2ec704b2ab5d.gif" alt="A notes app demo" style="width:20%; height:20%">

In your Main Activity:

```kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                val navController = rememberNavController()
                val viewModel = hiltViewModel<MainViewModel>()
                CountDownSplashScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    beforeFinished = {
                        CenteredImageAndText(
                            modifier = Modifier
                                .fillMaxSize(0.4f)
                                .align(Alignment.Center),
                            imageDrawableRes = R.drawable.sticky_note,
                            contentDescription = stringResource(id = R.string.app_name),
                            text = stringResource(id = R.string.app_name),
                            textStyle = MaterialTheme.typography.titleMedium.copy(
                                color = contentColorFor(
                                    backgroundColor = MaterialTheme.colorScheme.background
                                ),
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                    }
                ) {
                    MainScreen(
                        navController = navController,
                        navStarDestination = Screen.NotesList,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
```

#### When you want a gif and a text:

Example:

<img src="https://user-images.githubusercontent.com/17025709/230977297-0ee61ed6-8857-4d72-abff-1273778670be.gif" alt="A notes app demo" style="width:20%; height:20%">

In your Main Activity:

```kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                val navController = rememberNavController()
                val viewModel = hiltViewModel<MainViewModel>()
                CountDownSplashScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    beforeFinished = {
                        CenteredGifAndText(
                            modifier = Modifier
                                .fillMaxSize(0.4f)
                                .align(Alignment.Center),
                            gifImage = R.drawable.love,
                            contentDescription = stringResource(id = R.string.app_name),
                            text = stringResource(id = R.string.app_name),
                            textStyle = MaterialTheme.typography.titleMedium.copy(
                                color = contentColorFor(
                                    backgroundColor = MaterialTheme.colorScheme.background
                                ),
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                    }
                ) {
                    MainScreen(
                        navController = navController,
                        navStarDestination = Screen.NotesList,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
```

Hope it helps you.
