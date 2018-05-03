# GUI proposal #
Comments to GUI:
* avoid colours that could distract user taking the test. (DONE)
* don't implement additional question to distinct strong and mild (for both Protan and Deutan)
* Add-on: added a fragment for downloading the test chosen from the available tests list (test info, active download button, inactive start test button - download button is deactivated when clicked; start test button activated when downloading complete and db updated, i.e. added to local tests)
## Activities ##
### Activity 1 ###
Main activity consist of:

* list of local (downloaded test); choosing element from a list will start the test (Activity 2)
* floating button to add a test; it will start a new activity that look similar but with a list of tests available for downloading from researcher server (not implemented). choosing element from the list will start a downloading service

![md_project_04.png](https://bitbucket.org/repo/x8G5dpK/images/2140793419-md_project_04.png)

### Activity 2 ###
Test's Welcome Screen. Details about the test, description, recommended light intensity condition etc. Start test button will start new activity that presents the initial plate (if marked as one in Test object, firstPlate not null) and after that random chosen from not shown yet.

![md_project_05.png](https://bitbucket.org/repo/x8G5dpK/images/351695500-md_project_05.png)

### Activity 3 ###
Implementation of the test itself as a separate activity results in that pressing back button will cancel test and lead back to Test's Welcome Screen. No progres will be saved - we want to force by it uniform test condition day/night light etc. 

Picture below shows the first plate from Ishihara test. Plates that don't distinguished protan/deutan deficiency could be presented like this.

![md_project_06.png](https://bitbucket.org/repo/x8G5dpK/images/974756632-md_project_06.png)

Next picture shows Plate 16 that need an extra question asked if the answer is 26.

![md_project_07.png](https://bitbucket.org/repo/x8G5dpK/images/2449298398-md_project_07.png)

Additional qustion is proposed as on a picture below (this will not be implemented)

![md_project_08.png](https://bitbucket.org/repo/x8G5dpK/images/1969922708-md_project_08.png)

The last page of the test presents summary results and data that will be included if user decides to publish/send results back to researcher (option available if upload server implemented)

![md_project_09.png](https://bitbucket.org/repo/x8G5dpK/images/155277072-md_project_09.png)
