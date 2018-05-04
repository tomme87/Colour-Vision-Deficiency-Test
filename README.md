# Colour Vision Deficiency Test

This application is created for researchers to add tests for colour vision deficiency,
which this application can download and present to a user which can test its colour vision.

[Latest release can be found here](https://github.com/tomme87/Colour-Vision-Deficiency-Test/releases)

This was created as student project in the cource imt3673 Mobile Development.
Group members:
* Manuel Bravo Garcia
* Tom Roar Furunes
* Tomasz Rudowski
* Sjur Sutterud Sagen

[Documentation](https://github.com/tomme87/Colour-Vision-Deficiency-Test/blob/master/docs/Home.md)

[Project Report](https://github.com/tomme87/Colour-Vision-Deficiency-Test/blob/master/docs/imt3673_project_report.pdf)

## Lint Error ##
* Incompatible Gradle Versions

We developed and tested application with API 26, but it seems that some of the dependencies (possibly from libraries) refers to API 27. We tried to upgrade the target and compile SDK version to 27, then no errors were indicated, but we experienced some disturbance (reloading/blinking of the plate picture) in the moment of loading it into the fragment. There were no ohter visual effects, but we decided that switching it back to API 26 is better for smooth running of the test. 

## Lint Warnings ##
### Correctness ###
* Obsolate Gradle Dependency (3 warnings) -  We use Picasso library that require older versions.
* Target SDK is not latest version (1 warning) - Using API 26 as explained above.
### Performance ###
* Overdraw (6 warnings) - Problems with fragments not being removed from stack. Used background for fragment view to avoid duplicate view when we use more complex navigation between fragments.
* Static Field Leaks (3 warnings) - Context objects in AsyncTask that fetches data from database, we tried to fix it in case of other classes (like e.g. AddLocalTest), but unsure if it acctually slove the problem with memory leak when killing an app while fetching data from database.
### Usability ###
* Missing support for Firebase App Indexing (1 warning) - not using Google App Indexing
