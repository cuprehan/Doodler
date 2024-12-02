# How to run
To get the program to run normally there is not much setup to it after you have the files by cloning the repository. If the program does not build and run correctly, try these two things:
## 1.
Check the Module's build.gradle.kts file in Gradle scripts and make sure in the dependencies you have "implementation("com.github.dhaval2404:colorpicker:2.3")"
## 2.
Go to the settings.gradle.kts file in Gradle scripts and make sure in dependencyResolutionManagement you have "maven { url = uri("https://jitpack.io") }"

Once you have both of these the program should build and run normally.

# References
## 1. https://github.com/Dhaval2404/ColorPicker
Color picker library used to change pen color
## 2. https://jitpack.io
Dependency needed to implement color picker library
