package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.sql.Timestamp;
import java.util.Date;

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

    public TestInfo() {
    }

    public TestInfo(
            String id,
            String name,
            String description,
            String resources,
            String resultServer,
            Integer firstPlate,
            String type,
           // Timestamp created,
            Integer version
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.resourceUrl = resources;
        this.resultsUrl = resultServer;
        this.firstPlate = firstPlate;
        this.type = type;
       // this.created = created;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(@NonNull String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    @Nullable
    public String getResultsUrl() {
        return resultsUrl;
    }

    public void setResultsUrl(@Nullable String resultsUrl) {
        this.resultsUrl = resultsUrl;
    }

    public Integer getFirstPlate() {
        return firstPlate;
    }

    public void setFirstPlate(Integer firstPlate) {
        this.firstPlate = firstPlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /*
    @Nullable
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(@Nullable Timestamp created) {
        this.created = created;
    }
    */

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Nullable
    public Long getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(@Nullable Long downloadId) {
        this.downloadId = downloadId;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

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
    }

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
                    (Integer) parcel.readValue(Integer.class.getClassLoader())
            );
        }

        @Override
        public TestInfo[] newArray(int i) {
            return new TestInfo[i];
        }
    };
}
