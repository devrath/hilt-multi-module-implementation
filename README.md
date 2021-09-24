# hilt-multi-module-implementation
![Banner](https://github.com/devrath/android-in-app-review-engine/blob/main/assets/banner_new.png)


<p align="center">
<a><img src="https://img.shields.io/badge/Hilt-Dependency%20Injection-green"></a>
<a><img src="https://img.shields.io/badge/MVVM-Architecture-purple"></a>
</p>
<p align="center">
<a><img src="https://img.shields.io/badge/Built%20Using-Kotlin-silver?style=for-the-badge&logo=kotlin"></a>
<a><img src="https://img.shields.io/badge/Built%20By-Android%20Studio-red?style=for-the-badge&logo=android%20studio"></a>  
<a><img src="https://img.shields.io/badge/persistence-Datastore%20preferences%20library-deeppink?style=for-the-badge&logo=Bitrise"></a>  
</p>


| Contents |
| -------- |
| [About the project](https://github.com/devrath/android-in-app-review-engine/blob/main/README.md#about-the-project) |
| [Representation of flow](https://github.com/devrath/android-in-app-review-engine/blob/main/README.md#representation-of-flow) |
| [Output](https://github.com/devrath/android-in-app-review-engine/blob/main/README.md#output) |

## About the project
* In this project we demonstrate the use of hilt in a multi module project. 
* We have set up four modules including the app module.
  * `App-Module` contains the screen that takes input string to save it to data store. 
  *  `inappreview-module` contains the UI of the bottom sheet dialog and logic of in app review manager implementation in android.
  *  `preferences-module` contains the `data store` to store the `key/value` pairs in android.
  *  `styles-module` contains the common styles that is being re-used throught all the modules.
* From the `App-Module` we call the UI and logic of review from `inappreview-module`.
* In the `preferences-module` there is `general preferences` that is common to all the modules implemented using data store.
* In the `inappreview-module`there is `module specific preferences` specific to that module.
* Module objects are facilitated using the `hilt`.
* The implementations are facilitated by a abstraction so that if needed, its easier to implement the `unit testing` in the project.


## Representation of flow
![flow](https://github.com/devrath/android-in-app-review-engine/blob/main/assets/sym.png)

## Output
<p align="left">
  <img width=300 height=600 src="https://github.com/devrath/android-in-app-review-engine/blob/main/assets/output.gif">
</p>


<p align="center">
<a><img src="https://forthebadge.com/images/badges/built-for-android.svg"></a>
</p>

