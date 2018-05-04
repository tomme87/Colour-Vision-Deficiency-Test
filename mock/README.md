# Mock server

This folder contains files for the mock server data. with TestList.json and T1.zip which contians all the plates and JSON data.

The application is bound to the TestList.json file in this repository, if the file will be moved to an external server adjustment in the code needed: strings.xml element "url_test_list" should point to the new location.

To add or edit new tests refer to "resourceUrl" in TestJson.json file, it should point to the URL where the .zip file with the plates and extra data files (plates.json, thresholds.json) is located. (currently all demo test refer to the same .zip file in this repository)
