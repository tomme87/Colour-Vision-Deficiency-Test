# Colour Vision Deficiency Test

This application is created for researchers to add tests for colour vision deficiency,
which this application can download and present to a user which can test its colour vision.

This was created as student project in the cource imt3673 Mobile Development.

[Documentation](https://github.com/tomme87/Colour-Vision-Deficiency-Test/blob/master/docs/Home.md)

[Project Report](https://github.com/tomme87/Colour-Vision-Deficiency-Test/blob/master/docs/imt3673_project_report.pdf)

## Lint Warnings ##

### Correctness ###
* Obsolate Gradle Dependency (6 warnings) -  We use Picasso library that require older versions.
### Performance ###
* Overdraw (6 warnings) - Problems with fragments not being removed from stack. Used background for fragment view to avoid duplicate view when we use more complex navigation between fragments.
* Static Field Leaks (3 warnings) - Context objects in AsyncTask that fetches data from database, we tried to fix it in case of other classes (like e.g. AddLocalTest), but unsure if it acctually slove the problem with memory leak when killing an app while fetching data from database.
### Usability ###
* Missing support for Firebase App Indexing (1 warning) - not using Google App Indexing
