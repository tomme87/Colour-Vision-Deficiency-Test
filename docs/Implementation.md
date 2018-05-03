## Data structure ##

Test includes a list of Plates that are available to download from URL indicated by resources attribute. Each type of test needs both Plate and Result classes(interfaces) extended(implemented) in order to properly interpret values marked as awaited and given by a test subject; in Ishihara test example on a picture they are numbers that represents normal vision and deficiencies (red-green deficincies with care for protan/deutan results and total colour-blindness). To ensure that all (also future types of tests) will be shown on the mobile screen properly, we need to implement a class TestActivity to match test type. If an extra answer is needed for a specific plate, like e.g. plate number 16 in Ishihara test, it is placed in Result class extention (IshiharaResult); this will also allow to create a test type where answers could be more complex (more steps) or of different type (String, Array etc). 
Summary of results may consider thresholds.json file, on a picture below an example of a class (IshiharaThreshold) that the JSON file is marshalled to; two attributes are used to give a general answer (normal, uncertain, deficiency); counters for Deutan and Protan answers can additionally indicate strong Deutan/Protan deficiency if possible based on given answers - if not assume a general red-green deficiency.
ResultSet in addition to a set of Results may contain metadata (not defined yet) that could be eg. local time, light intensity while test taken or general personal data (gender, age etc) if accepted by user and needed for research. If chosen to publish, the ResultSet will be send to URL defined in TestInfo.resultServer (this need to be implemented separately if results are to be gathered).

![md_project_v7.png](https://github.com/tomme87/imt3673-project-wiki/blob/master/md_project_v7.png)

Core package includes all common and base classes (including main activity fragments, download services and database handling).

Ishihara package consists of all classes that are specific for this test type; among them visualisation (test specific input/output), results interpretation. All other test types should be implemented in this way.
