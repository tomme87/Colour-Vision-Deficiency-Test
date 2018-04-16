package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;


import java.util.Date;

public class TestInfo {
    private String id;
    private String name;
    private String description;
    private String resources;       // zip url
    private String resultServer;    // POST results url
    private Integer firstPlate;
    private String type;
    private Date created;
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
            Date created,
            Integer version
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.resources = resources;
        this.resultServer = resultServer;
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

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getResultServer() {
        return resultServer;
    }

    public void setResultServer(String resultServer) {
        this.resultServer = resultServer;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
