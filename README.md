# AppIntro

[![](https://jitpack.io/v/AppIntro/AppIntro.svg)](https://jitpack.io/#AppIntro/appintro) [![Build Status](https://travis-ci.org/AppIntro/AppIntro.svg?branch=master)](https://travis-ci.org/AppIntro/AppIntro) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AppIntro-green.svg?style=flat)](https://android-arsenal.com/details/1/1939) [![Join the chat at https://gitter.im/AppIntro/Lobby](https://badges.gitter.im/AppIntro/Lobby.svg)](https://gitter.im/AppIntro/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) [![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)

<p align="center">
    <img src="assets/logo/logo.png" alt="appintro icon" width="80%"/>
</p>

AppIntro is an Android Library that helps you build a **cool carousel intro** for your App. AppIntro has support for **requesting permissions** and helps you create a great onboarding experience in just a couple of minutes.

!!! TODO !!! 
<img src="https://github.com/AppIntro/AppIntro/blob/master/art/intro.png" width="300"> <img src="https://github.com/AppIntro/AppIntro/blob/master/art/layout2.png" width="300">

* [Getting Started](#getting-started-)
* [Features](#features-)
* [Configure](#configure-)
* [Migrating](#migrating-)
* [Snapshots](#snapshots-)
* [Contributing](#contributing-)
* [Acknowledgments](#acknowledgments-)
* [License](#license-)

## Getting Started 👣

AppIntro is distributed through [JitPack](https://jitpack.io/#AppIntro/AppIntro).

### Adding a dependency

To use it you need to add the following gradle dependency to your `build.gradle` file of the module where you want to use AppIntro (NOT the root file).

```groovy
repositories {
    maven { url "https://jitpack.io" }
}
```

```groovy
dependencies {
    // AndroidX Capable version
    implementation 'com.github.AppIntro:AppIntro:6.0.0'
    
    // *** OR ***
    
    // Latest version compatible with the old Support Library
    implementation 'com.github.AppIntro:AppIntro:4.2.3'
}
```

Please note that since AppIntro 5.x, the library supports [Android X](https://developer.android.com/jetpack/androidx/). If you haven't migrated yet, you probably want to use a previous version of the library that uses the **old Support Library** packages (or try [Jetifier Reverse mode](https://ncorti.com/blog/jetifier-reverse)).

### Basic usage

To use AppIntro, you simply have to create a new **Activity that extends AppIntro** like the following:

```kotlin
class MyCustomAppIntro : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure you don't call setContentView!

        // Call addSlide passing your Fragments.
        // You can use AppIntroFragment to use a pre-built fragment
        addSlide(AppIntroFragment.newInstance(
                title = "Welcome!",
                description = "This is the first slide of the example"
        ))

        // Just add as many as you want. AppIntroFragment has
        // several contructors to help you customize your AppIntro
        addSlide(AppIntroFragment.newInstance(
                title = "A slide with custom background color!",
                description = "and with custom text color",
                bgColor = Color.BLUE,
                titleColor = Color.YELLOW,
                descColor = Color.RED
        ))
        
        // You can add graphics to your fragment.
        addSlide(AppIntroFragment.newInstance(
                title = "And a slide with some images!",
                imageDrawable = R.drawable.ic_slide1,
                bgDrawable = R.drawable.ic_sample_bg
        ))
        
        // Use AppIntroCustomLayoutFragment to supply a custom layout.
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.my_custom_fragment))
        
        // Or just pass your Fragment instance.
        addSlide(MyCustomFragment.newInstance())
    }

    public override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        finish()
    }

    public override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        finish()
    }
}
```

Please note that you **must NOT call** setContentView.

Finally, declare the activity in your Manifest like so:

``` xml
<activity android:name="com.example.MyCustomAppIntro"
    android:label="My Custom AppIntro" />
```

We suggest to don't declare `MyCustomAppIntro` as your first Activity unless you want the intro to launch every time your app starts. Ideally you should show the AppIntro activity only once to the user, and you should hide it once completed (you can use a flag in the `SharedPreferences`).

!!! TODO !!!
Refer to the [wiki](https://github.com/AppIntro/AppIntro/wiki/How-to-Use#show-the-intro-once) for an example of how to launch the intro once from your main activity.


## Features 🧰

Don't forget to check the [changelog](CHANGELOG.md) to have a look at all the changes in the latest version of AppIntro.

* **API >= 14** compatible.
* 100% Kotlin Library.
* **AndroidX** Compatible.
* Support for **runtime permissions**.
* Dependent only on AndroidX AppCompat/Annotations, ConstraintLayout and Kotlin JDK.
* Full RTL support.

## Creating Slides

## Configure 🎨

AppIntro offers several configuration option to help you customize your onboarding experience.

### Slide Transformer

TODO

### Color Transition

TODO

### Multiple Windows Layout

AppIntro is shipped with two window layouts that you can use. To change the Window layout, you can simply change your superclass to `AppIntro2`.

```kotlin
class MyCustomAppIntro : AppIntro2() {
    // ...
}
```

| `AppIntro` | `AppIntro2` |
| ---------- | ----------- |
| TODO | TODO |
| TODO | TODO |

### Indicators


### Vibration


### Wizard Mode


### Immersive Mode


### System Back button


### System UI (Status Bar and Navigation Bar)


## Permission


### Slide Policy







<img src="https://github.com/AppIntro/AppIntro/blob/master/art/layout2.png" width="300"> <img src="https://github.com/AppIntro/AppIntro/blob/master/art/layout2_2.png" width="300">
<br>

#### Slides

##### Basic slides

AppIntro provides two simple classes, `AppIntroFragment` and `AppIntro2Fragment` which one can use to build simple slides.

```java
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    // ...

    addSlide(AppIntroFragment.newInstance(title, description, image, backgroundColor));
}
```

##### Custom slides example

One may also define custom slides using the [AppIntroCustomLayoutSlide] class.
You can just add a custom slides in this way:

```java
addSlide(AppIntroCustomLayoutSlide.newInstance(R.layout.your_slide_here));
```

There's no need to create one class for fragment anymore. :)

### Extended usage

#### Animations
AppIntro comes with some pager animations.
Choose the one you like and then activate it with:

```java
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    // ...

    setFadeAnimation();
}
```

Available animations:
```java
setFadeAnimation()
setZoomAnimation()
setFlowAnimation()
setSlideOverAnimation()
setDepthAnimation()
```

If you want to create nice parallax effect or your own custom animation, create your own **PageTransformer** and call:

```java
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    // ...

    setCustomTransformer(transformer);
}
```

Click [here](https://github.com/AppIntro/AppIntro/blob/90a513fda9b70a5e5df35435a7f2984832727eeb/AppIntroExample/app/src/main/java/com/github/appintro/example/animations/CustomAnimation.java) to see how I did it in the example app.

#### Background color transitions

AppIntro supports background color transitions:

<img src="art/background_color_transition.gif" style="width: 250px">

In order to setup the transitions, simply implement `ISlideBackgroundColorHolder`:
```java
public final class MySlide extends Fragment implements ISlideBackgroundColorHolder {
    @Override
    public int getDefaultBackgroundColor() {
        // Return the default background color of the slide.
        return Color.parseColor("#000000");
    }

    @Override
    public void setBackgroundColor(@ColorInt int backgroundColor) {
        // Set the background color of the view within your slide to which the transition should be applied.
        if (layoutContainer != null) {
            layoutContainer.setBackgroundColor(backgroundColor);
        }
    }
}
```

The API is quite low-level, therefore highly customizable. The interface contains two methods:

- `getDefaultBackgroundColor`: Return the default background color (i.e. the background color the slide has in non-sliding state) of the slide here.
- `setBackgroundColor(int)`: This method will be called while swiping between two slides. Update the background color of the view to which the transition should be applied.
This is normally the root view of your Fragment's layout. But one may also apply the color transition to some other view only (i.e. a Button).

#### Runtime Permissions (Android 6.0+)

<img src="https://github.com/AppIntro/AppIntro/blob/master/art/permissions.png" width="300">

Android 6.0 introduced a new permissions model for developers. Now all your apps have to request permissions which can be a tedious thing to implement.

However, AppIntro simplifies this down to one single line of code!

```java
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    // ...

    // Ask for CAMERA permission on the second slide
    askForPermissions(new String[]{Manifest.permission.CAMERA}, 2); // OR

    // This will ask for the camera permission AND the contacts permission on the same slide.
    // Ensure your slide talks about both so as not to confuse the user.
    askForPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS}, 2);
}
```

**NOTE:** It is advised that you only put one permission in the String array unless you want the app to ask for multiple permissions on the same slide.

**NOTE 2:** Requesting permissions automatically disables sliding, and users will have to result to pressing the buttons. Please do not open any issues regarding this, as they will be immmediately closed. Thanks!

#### Slide Policies

If you want to restrict navigation between your slides (i.e. the user has to toggle a checkbox in order to continue), our **Slide Policy** feature might help you.

All you have to do is implement `ISlidePolicy` in your slides:
```java
public final class MySlide extends Fragment implements ISlidePolicy {
    @Override
    public boolean isPolicyRespected() {
        return // If user should be allowed to leave this slide
    }

    @Override
    public void onUserIllegallyRequestedNextPage() {
        // User illegally requested next slide
    }
}
```
The interface contains two methods:

- `isPolicyRespected`: The return value of this method defines if the user can leave this slide, i.e. navigate to another one
- `onUserIllegallyRequestedNextPage`: This method gets called if the user tries to leave the slide although `isPolicyRespected` returned false. One may show some error message here.








## Example App

AppIntro comes with a **sample app** full of examples and use case that you can use as inspiration for your project. You can find it inside the [/example folder](https://github.com/AppIntro/AppIntro/tree/master/example).

!!! TODO Sample App Screenshot !!!

## Translating 🌍

Do you want to help AppIntro becoming international 🌍? We are more than happy!
AppIntro currently supports [the following languages](appintro/src/main/res).

To add a new translation just add a pull request with a new `strings.xml` file inside a `values-xx` folder (where `xx` is a [two-letter ISO 639-1 language code](https://en.wikipedia.org/wiki/ISO_639-1)).

In order to provide the translation, your file needs to contain the following strings:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources xmlns:tools="http://schemas.android.com/tools">
    <string name="app_intro_skip_button">[Translation for SKIP]</string>
    <string name="app_intro_next_button">[Translation for NEXT]</string>
    <string name="app_intro_back_button">[Translation for BACK]</string>
    <string name="app_intro_done_button">[Translation for DONE]</string>
    <string name="app_intro_image_content_description">[Translation for "graphics"]</string>
</resources>
```

An updated version of the englsih version translation is [available here](appintro/src/main/res/values/strings.xml).

If a translation in your language is already available, please check it and eventually fix it (all the strings should be listed, not just a subset).

## Migrating 🚗

If you're migrating **from AppIntro v5.x to v6.x**, please expect multiple breaking changes. You can find documentation on how to update your code on this other [migration guide](/docs/migrating-from-5.0.md).

## Snapshots 📦

Development of AppIntro happens on the [master](https://github.com/AppIntro/AppIntro/tree/master) branch. You can get `SNAPSHOT` versions directly from JitPack if needed.

```gradle
repositories {
    maven { url "https://jitpack.io" }
}
```

```gradle
dependencies {
  implementation "com.github.AppIntro:AppIntro:master-SNAPSHOT"
}
```

⚠️ Please note that the latest snapshot might be **unstable**. Use it at your own risk ⚠️

## Contributing 🤝

We're offering support for [AppIntro on Gitter](https://gitter.im/AppIntro/Lobby). Come and joing the conversation over there.

**We're looking for contributors! Don't be shy.** 😁 Feel free to open issues/pull requests to help me improve this project.

* When reporting a new Issue, make sure to attach **Screenshots**, **Videos** or **GIFs** of the problem you are reporting.
* When submitting a new PR, make sure tests are all green. Write new tests if necessary.

## Acknowledgments 🌸

### Maintainers

Chucker is currently developed and maintained by the [AppIntro Github Org](https://github.com/AppIntro). When submitting a new PR, please ping one of:

- [@paolorotolo](https://github.com/paolorotolo)
- [@cortinico](https://github.com/cortinico)

### Hall of Fame

TODO

### Libraries

AppIntro uses the following open source libraries:

- [OkHttp](https://github.com/square/okhttp) - Copyright Square, Inc.
- [Gson](https://github.com/google/gson) - Copyright Google Inc.
- [Room](https://developer.android.com/topic/libraries/architecture/room) - Copyright Google Inc.

## License 📄

```
    Copyright (C) 2015-2020 AppIntro Developers

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```

## Real life examples

!!! TODO Remove? !!!

Do you need inspiration? A lot of apps are using AppIntro out there:

**Planets**

<img src="https://github.com/AppIntro/AppIntro/blob/master/art/planets.png">

**Hermes - Material IRC Client**

<img src="https://github.com/AppIntro/AppIntro/blob/master/art/Screenshot_2015-06-03-12-41-59.png" width="300"> <img src="https://github.com/AppIntro/AppIntro/blob/master/art/Screenshot_2015-06-03-12-42-02.png" width="300">
<img src="https://github.com/AppIntro/AppIntro/blob/master/art/Screenshot_2015-06-03-12-42-07.png" width="300"> <img src="https://github.com/AppIntro/AppIntro/blob/master/art/Screenshot_2015-06-03-12-42-10.png" width="300">


## Apps using AppIntro

!!! TODO Remove? or Spoiler? !!!

If you are using AppIntro in your app and would like to be listed here, please let us know by commenting in [this issue](https://github.com/AppIntro/AppIntro/issues/325)!

 * [Numix Hermes](https://play.google.com/store/apps/details?id=org.numixproject.hermes)
 * [Audio Reminder Pro](https://play.google.com/store/apps/details?id=com.brandon.audioreminderpro)
 * [Wizr Daily Quotes](https://play.google.com/store/apps/details?id=com.wizrapp)
 * [Planets](https://play.google.com/store/apps/details?id=com.andrewq.planets)
 * [Weather Delta](https://play.google.com/store/apps/details?id=com.felkertech.n.weatherdelta)
 * [PDF Me](https://play.google.com/store/apps/details?id=com.pdfme)
 * [Circles](https://play.google.com/store/apps/details?id=com.felipejoglar.circles)
 * [Task Master](https://play.google.com/store/apps/details?id=com.cr5315.taskmaster)
 * [Smoothie Recipes](https://play.google.com/store/apps/details?id=com.skykonig.smoothierecipes)
 * [SideBar Notes](https://play.google.com/store/apps/details?id=com.app.floating.notes)
 * [just food](https://play.google.com/store/apps/details?id=scientist.jobless.foodmana)
 * [AlarmSMS](https://play.google.com/store/apps/details?id=com.qhutch.alarmsms)
 * [Aware](https://play.google.com/store/apps/details?id=com.bunemekyakilika.aware)  <!-- App is region restricted - please confirm avail. region -->
 * [neutriNote](https://play.google.com/store/apps/details?id=com.appmindlab.nano)
 * [Handwriting Note](https://play.google.com/store/apps/details?id=com.lyk.immersivenote)
 * [Friends Roulette](https://play.google.com/store/apps/details?id=com.crioltech.roulette)
 * [Karting Tools](https://play.google.com/store/apps/details?id=com.fabreax.android.kartingtools.activity)
 * [ChineseDictionary (粵韻漢典離線粵語普通話發聲中文字典)](https://play.google.com/store/apps/details?id=com.jonasng.chinesedictionary)
 * [Sifter](https://play.google.com/store/apps/details?id=sifter.social.network.archaeologist)
 * [#-ludus 2.0](https://play.google.com/store/apps/details?id=com.fallenritemonk.ludus)
 * [Snipit Text Grabber](https://play.google.com/store/apps/details?id=com.om.snipit)
 * [Service Notes](https://play.google.com/store/apps/details?id=notes.service.com.servicenotes)
 * [Salary Barometer](https://play.google.com/store/apps/details?id=anaware.salarybarometer)
 * [Best Business Idea!](https://play.google.com/store/apps/details?id=anaware.bestidea)
 * [Wi-Fi password reminder](https://play.google.com/store/apps/details?id=com.rusdelphi.wifipassword)
 * [Safe Notes](https://play.google.com/store/apps/details?id=software.codeplus.safenotes)
 * [Xpaper - Moto X Wallpapers](https://play.google.com/store/apps/details?id=com.dunrite.xpaper)
 * [Find My Parked Car](https://play.google.com/store/apps/details?id=com.ofirmiron.findmycarandroidwear)
 * [BoxPlay Music Player](https://play.google.com/store/apps/details?id=de.luckyworks.boxplay)
 * [Vape Tool Pro](https://play.google.com/store/apps/details?id=com.stasbar.vapetoolpro)
 * [NebelNiek Soundboard](https://play.google.com/store/apps/details?id=de.logtainment.nebelnieksoundboard)
 * [sdiwi | Win your purchase!](https://play.google.com/store/apps/details?id=com.sdiwi.app)
 * [Helal ve Sağlıklı Yaşam](https://play.google.com/store/apps/details?id=org.yasam.hsy.helalvesaglikliyasam)
 * [HipCar - Car Rental](https://play.google.com/store/apps/details?id=com.hipcar.android)
 * [Schematiskt Skolschema](https://play.google.com/store/apps/details?id=se.zinokader.schematiskt)
 * [Third Eye](https://play.google.com/store/apps/details?id=com.miragestacks.thirdeye)
 * [Crypton - Password Manager](https://play.google.com/store/apps/details?id=mindstorm.crypton)
 * [Web Video Cast](https://play.google.com/store/apps/details?id=com.instantbits.cast.webvideo)
 * [Sask. Geo-Memorial](https://play.google.com/store/apps/details?id=com.github.dstaflund.geomemorial)
 * [SchoolBox](https://play.google.com/store/apps/details?id=com.deenysoft.schoolbox)
 * [Fitness Challenge](https://play.google.com/store/apps/details?id=com.isidroid.fitchallenge)
 * [Crunch (ICE)](https://play.google.com/store/apps/details?id=com.figsandolives.ice.free)
 * [Filmy - Your Movie Guide](https://play.google.com/store/apps/details?id=tech.salroid.filmy)
 * [HEBF Optimizer ▪ Root](https://play.google.com/store/apps/details?id=com.androidvip.hebf)
 * [Wifi Captive Login](https://play.google.com/store/apps/details?id=com.anantharam.wificaptivelogin)
 * [IIFYM](https://play.google.com/store/apps/details?id=com.javierd.iifym)
 * [Ampwifi Winamp Remote](https://play.google.com/store/apps/details?id=com.blitterhead.ampwifi)
 * [AaiKya: Leave Tracker](https://play.google.com/store/apps/details?id=com.ranveeraggarwal.letrack)
 * [Angopapo - People around you](https://play.google.com/store/apps/details?id=com.msingapro.angopapofb)
 * [Hugetwit](https://play.google.com/store/apps/details?id=com.halilibo.hugetwit)
 * [Wake Me Up (Mumbai Railway)](https://play.google.com/store/apps/details?id=com.catacomblabs.wakemeup)
 * [SelfMote - Wireless Remote app](https://play.google.com/store/apps/details?id=com.dmicse.selfmote.free)
 * [Boo Music Player](https://play.google.com/store/apps/details?id=cdn.BooPlayer)
 * [BeatPrompter](https://play.google.com/store/apps/details?id=com.stevenfrew.beatprompter)
 * [Orario Treni Trenitalia](https://play.google.com/store/apps/details?id=com.jaus.albertogiunta.justintrain_oraritreni)
 * [Tipsy for Gardaland](https://play.google.com/store/apps/details?id=com.MonkeyLab.MyGardaland&hl=it)
 * [BlueWords](https://play.google.com/store/apps/details?id=com.thesrb.bluewords&referrer=utm_source%3Dappintro%26utm_medium%3Dgithub%26utm_campaign%3Dreadme)
 * [Best Quotes & Status 2019 (99000+ Collection)](https://play.google.com/store/apps/details?id=com.swastik.quotesandstatus&hl=en_IN)
