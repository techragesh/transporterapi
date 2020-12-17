package com.kodebyt.api.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import com.kodebyt.api.domain.enumeration.Mobile;

/**
 * A DTO for the {@link com.kodebyt.api.domain.UserAccount} entity.
 */
public class UserAccountDTO implements Serializable {
    
    private Long id;

    private String deviceId;

    private Mobile mobile;

    private Boolean socialLogin;

    private String facebookId;

    private String googleId;

    private String otp;

    private LocalDate lastLogin;

    private LocalDate createdDate;

    private LocalDate modifiedDate;


    private Long customerId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Mobile getMobile() {
        return mobile;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    public Boolean isSocialLogin() {
        return socialLogin;
    }

    public void setSocialLogin(Boolean socialLogin) {
        this.socialLogin = socialLogin;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserAccountDTO)) {
            return false;
        }

        return id != null && id.equals(((UserAccountDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserAccountDTO{" +
            "id=" + getId() +
            ", deviceId='" + getDeviceId() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", socialLogin='" + isSocialLogin() + "'" +
            ", facebookId='" + getFacebookId() + "'" +
            ", googleId='" + getGoogleId() + "'" +
            ", otp='" + getOtp() + "'" +
            ", lastLogin='" + getLastLogin() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", customerId=" + getCustomerId() +
            "}";
    }
}
