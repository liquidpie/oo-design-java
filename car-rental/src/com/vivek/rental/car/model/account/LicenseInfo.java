package com.vivek.rental.car.model.account;

import java.time.LocalDateTime;

public class LicenseInfo {
    private String licenceNumber;
    private LocalDateTime issueDate;
    private LocalDateTime expiryDate;
    private String issuedAtPlace;
    private String issuedInState;
    private String issuedInCountry;
    private LicenseType licenseType;

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIssuedAtPlace() {
        return issuedAtPlace;
    }

    public void setIssuedAtPlace(String issuedAtPlace) {
        this.issuedAtPlace = issuedAtPlace;
    }

    public String getIssuedInState() {
        return issuedInState;
    }

    public void setIssuedInState(String issuedInState) {
        this.issuedInState = issuedInState;
    }

    public String getIssuedInCountry() {
        return issuedInCountry;
    }

    public void setIssuedInCountry(String issuedInCountry) {
        this.issuedInCountry = issuedInCountry;
    }

    public LicenseType getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(LicenseType licenseType) {
        this.licenseType = licenseType;
    }
}
