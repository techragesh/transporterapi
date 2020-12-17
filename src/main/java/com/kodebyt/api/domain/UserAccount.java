package com.kodebyt.api.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import com.kodebyt.api.domain.enumeration.Mobile;

/**
 * A UserAccount.
 */
@Entity
@Table(name = "user_account")
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "device_id")
    private String deviceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "mobile")
    private Mobile mobile;

    @Column(name = "social_login")
    private Boolean socialLogin;

    @Column(name = "facebook_id")
    private String facebookId;

    @Column(name = "google_id")
    private String googleId;

    @Column(name = "otp")
    private String otp;

    @Column(name = "last_login")
    private LocalDate lastLogin;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @OneToOne
    @JoinColumn(unique = true)
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public UserAccount deviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Mobile getMobile() {
        return mobile;
    }

    public UserAccount mobile(Mobile mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    public Boolean isSocialLogin() {
        return socialLogin;
    }

    public UserAccount socialLogin(Boolean socialLogin) {
        this.socialLogin = socialLogin;
        return this;
    }

    public void setSocialLogin(Boolean socialLogin) {
        this.socialLogin = socialLogin;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public UserAccount facebookId(String facebookId) {
        this.facebookId = facebookId;
        return this;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getGoogleId() {
        return googleId;
    }

    public UserAccount googleId(String googleId) {
        this.googleId = googleId;
        return this;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getOtp() {
        return otp;
    }

    public UserAccount otp(String otp) {
        this.otp = otp;
        return this;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public UserAccount lastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public UserAccount createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public UserAccount modifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public UserAccount customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserAccount)) {
            return false;
        }
        return id != null && id.equals(((UserAccount) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserAccount{" +
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
            "}";
    }
}
