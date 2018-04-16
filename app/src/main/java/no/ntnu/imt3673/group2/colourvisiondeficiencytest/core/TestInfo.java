package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.sql.Timestamp;
import java.util.Date;

@Entity (
        tableName = "local_tests"
)
public class TestInfo {

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

    @Nullable
    private Timestamp created;

    @Nullable
    private Integer version;

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
            Timestamp created,
            Integer version
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.resourceUrl = resources;
        this.resultsUrl = resultServer;
        this.firstPlate = firstPlate;
        this.type = type;
        this.created = created;
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

    @Nullable
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(@Nullable Timestamp created) {
        this.created = created;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
