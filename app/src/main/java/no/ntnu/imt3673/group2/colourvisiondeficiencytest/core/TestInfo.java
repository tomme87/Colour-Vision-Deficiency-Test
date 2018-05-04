package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Class that represents information for each test.
 */
@Entity (
        tableName = "local_tests"
)
public class TestInfo implements Parcelable {
    public static final String EXTRA = "core.TestInfo";

    @PrimaryKey
    @NonNull
    private String id;

    @Nullable
    private String name;

    @Nullable
    private String description;

    @NonNull
    private String resourceUrl;       // zip url

    @Nullable
    private String resultsUrl;    // POST results url

    @Nullable
    private Integer firstPlate;

    @NonNull
    private String type;

    //@Nullable
    //private Timestamp created;

    @Nullable
    private Integer version;

    @Nullable
    private Long downloadId;

    private boolean processed = false;

    /**
     * Empty constructor.
     */
    public TestInfo() {
    }

    /**
     * Constructor.
     *
     * @param id id of the test.
     * @param name name of the test.
     * @param description description of the test.
     * @param resources URL where to fetch the zip file of the test.
     * @param resultServer URL where the results will be posted to.
     * @param firstPlate Indicates which plate (if any) will be shown in first when running the test.
     * @param type Type of test.
     * @param version Version of the est.
     * @param downloadId Download Id given to thest.
     * @param processed True if the test is processed (Downloaded, unzipped i.e. ready to use).
     */
    public TestInfo(
            @NonNull String id,
            @Nullable String name,
            @Nullable String description,
            @NonNull String resources,
            @Nullable String resultServer,
            @Nullable Integer firstPlate,
            @NonNull String type,
            @Nullable Integer version,
            @Nullable Long downloadId,
            boolean processed
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.resourceUrl = resources;
        this.resultsUrl = resultServer;
        this.firstPlate = firstPlate;
        this.type = type;
        this.version = version;
        this.downloadId = downloadId;
        this.processed = processed;
    }

    /**
     * Gets the id of the test.
     * @return the id of the test.
     */
    @NonNull
    public String getId() {
        return id;
    }


    /**
     * Sets the id of the test.
     * @param id the id of the test.
     */
    public void setId(@NonNull String id) {
        this.id = id;
    }


    /**
     * Gets the name of the test.
     * @return  the name of the test.
     */
    @Nullable
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the test.
     * @param name  the name of the test.
     */
    public void setName(@Nullable String name) {
        this.name = name;
    }

    /**
     * Gets the description of the test.
     * @return he description of the test.
     */
    @Nullable
    public String getDescription() {
        return description;
    }

    /**
     * Gets the description of the test.
     * @param description the description of the test.
     */
    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    /**
     * Gets the URL where to fetch the zip file of the test.
     * @return theURL where to fetch the zip file of the test.
     */
    @NonNull
    public String getResourceUrl() {
        return resourceUrl;
    }

    /**
     * Sets the URL where to fetch the zip file of the test.
     * @param resourceUrl the URL where to fetch the zip file of the test
     */
    public void setResourceUrl(@NonNull String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    /**
     * Gets the URL where the results will be posted to.
     * @return the URL where the results will be posted to.
     */
    @Nullable
    public String getResultsUrl() {
        return resultsUrl;
    }

    /**
     * Sets the URL where the results will be posted to.
     * @param resultsUrl the URL where the results will be posted to.
     */
    public void setResultsUrl(@Nullable String resultsUrl) {
        this.resultsUrl = resultsUrl;
    }

    /**
     * Gets  the first plate that will be shown when running the test.
     * @return  The first plate that will be shown when running the test.
     */
    @Nullable
    public Integer getFirstPlate() {
        return firstPlate;
    }

    /**
     * Sets  the first plate that will be shown when running the test.
     * @param firstPlate the first plate that will be shown when running the test.
     */
    public void setFirstPlate(@Nullable Integer firstPlate) {
        this.firstPlate = firstPlate;
    }

    /**
     * Gets the type of test.
     * @return the type of test.
     */
    @NonNull
    public String getType() {
        return type;
    }

    /**
     * Sets the type of test.
     * @param type the type of test.
     */
    public void setType(@NonNull String type) {
        this.type = type;
    }

    /**
     * Gets the version of the test.
     * @return the version of the test.
     */
    @Nullable
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the version of the test.
     * @param version the version of the test.
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * Gets the Download Id of the test.
     * @return the Download Id of the test.
     */
    @Nullable
    public Long getDownloadId() {
        return downloadId;
    }

    /**
     * Sets the Download Id of the test.
     * @param downloadId the Download Id of the test.
     */
    public void setDownloadId(@Nullable Long downloadId) {
        this.downloadId = downloadId;
    }

    /**
     * Gets if the test is processed (Downloaded, unzipped i.e. ready to use).
     * @return bool.
     */
    public boolean isProcessed() {
        return processed;
    }

    /**
     * Sets if the test is processed (Downloaded, unzipped i.e. ready to use).
     * @param processed bool.
     */
    public void setProcessed(boolean processed) {
        this.processed = processed;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * How to write the parcel.
     * @param parcel Parcel object
     * @param i flag that is 0 or PARCELABLE_WRITE_RETURN_VALUE.
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.description);
        parcel.writeString(this.resourceUrl);
        parcel.writeString(this.resultsUrl);
        parcel.writeValue(this.firstPlate);
        parcel.writeString(this.type);
        parcel.writeValue(this.version);
        parcel.writeValue(this.downloadId);
        parcel.writeByte((byte) (this.processed ? 1 : 0));
    }

    /**
     * The creator needed for the Pracelable.
     */
    public static final Parcelable.Creator<TestInfo> CREATOR = new Parcelable.Creator<TestInfo>() {

        @Override
        public TestInfo createFromParcel(Parcel parcel) {
            return new TestInfo(
                    parcel.readString(),
                    parcel.readString(),
                    parcel.readString(),
                    parcel.readString(),
                    parcel.readString(),
                    (Integer) parcel.readValue(Integer.class.getClassLoader()),
                    parcel.readString(),
                    (Integer) parcel.readValue(Integer.class.getClassLoader()),
                    (Long) parcel.readValue(Long.class.getClassLoader()),
                    parcel.readByte() != 0
            );
        }

        @Override
        public TestInfo[] newArray(int i) {
            return new TestInfo[i];
        }
    };
}
