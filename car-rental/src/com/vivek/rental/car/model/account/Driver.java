package com.vivek.rental.car.model.account;

public class Driver extends Account {
    private LicenseInfo licenseInfo;

    public LicenseInfo getLicenseInfo() {
        return licenseInfo;
    }

    public void setLicenseInfo(LicenseInfo licenseInfo) {
        this.licenseInfo = licenseInfo;
    }
}
