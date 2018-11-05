package com.munchies.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("file")
public class StorageProperties {

    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
