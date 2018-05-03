## Tasks ##
Following tasks are identified:
Main goal is an app that can run standard tests, like Basic Ishihara, that has some variance in plates and randomized sequence of plates. Use plates with numbers and input as free text not buttons that suggest an answer.
Could support wider range of colors than sRGB (then bound to minimum Android 8.0, API 26+), but this is not implemented in this version.

* (DONE) create picture set that represents one test (pack it along with json file into a zip file) and place it on public available URL, (different sets of pictures ?)
* (NOT DOING - insufficient time to prepare a set of vector graphics that could have a randomize colours when running a test) (optional) create one set of .svg files and randomize colours on local device
* (DONE - json file on public URL) API that responds with json (array of TestInfo objects) - mock, firebase, mLab+GO... (data need to match resources URL) (alternative: list of tests as a json file on given url that represents possible API response)
* (DONE - downloading .json file) Fetch Test list from that API or .json file.
* (DONE) Create Activity/Fragment with a list of available tests to download (when downloaded populate list, on element click start download service)
* (DONE) Local database (stores Test Info for downloaded and available - decide when to update list from a server - is it needed evry time the list is shown?)
* (DONE) Download Service (create folder named <TestID>, unzip pictures and plates.json there, run Local Database Service to add TestInfo to local tests)
* (NOT DOING - used internal storage to local tests, no initial test data on app installation) (optional) Create folder named <TestID> with pictures and plates.json in Assets. Use the for GUI/Run Test Activity as a demo/default. Replace with internal storage later (or keep both sources).
* (DONE) Create Activity/Fragment with a list of downloaded (local) tests (run Local Database Service to populate list, on element click start run test activity)
* (NOT DOING - insufficient time, high-end devices binding) (optional) add mobile screen colour calibration (e.g. Enhancing Graphics with Wide Color Content)
* (DONE - shown on test Welcome Screen) (optional) implement Activity to adjust screen brightness before test.
* (DONE) Create RunTest Activity (that should run chosen test, need to be implemented as a package for each test type to ensure that questions and input from users fit the test eg. integer input for Ishihara test, click on rectangle that has different colour for another test etc). All necessary data (pictures from local storage) will be get after "Run test" button clicked (don't fetch resources in advance).
* (DONE) There is a limit of three seconds to give an answer on the analog version of the Ishihara test. After discussion we decided that this should be changed. To allow adjustment it is possible to add an attribute timeLimit (Integer) to a thresholds.json file; if this attribute is null or negative time limit is set to a default value.
* (DONE) (optional) Show the same plate multiply times, use eg Plate.priority or randomize (implementation proposal Plate object in Test.plates more than once, this could be decided by test author, who may use multiply Plate objects in plates.json file)
* (DONE) Create JSON parser that could create Set<Plate> out of plates.json file in test folder
* (DONE) PlateFragments that are designed for a concrete test type (consider timer - eg max 3 sec to give an answer on Ishihara test)
* (DONE) Create logic to summerize test results
* (DONE - no receiver of POST Request, payload created) Create payload of ResultSet and upload/POST as json to results server (requested to not store in local storage/database)
* (NOT DOING - agreed not to implement this) (optional)  Use as a part of metadata reading from sensors etc.(light enviroment). Discussed with supervisor: tests metadata not that important.
