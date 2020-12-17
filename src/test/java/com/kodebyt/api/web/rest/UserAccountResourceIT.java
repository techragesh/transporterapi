package com.kodebyt.api.web.rest;

import com.kodebyt.api.TransporterapiApp;
import com.kodebyt.api.domain.UserAccount;
import com.kodebyt.api.repository.UserAccountRepository;
import com.kodebyt.api.service.UserAccountService;
import com.kodebyt.api.service.dto.UserAccountDTO;
import com.kodebyt.api.service.mapper.UserAccountMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.kodebyt.api.domain.enumeration.Mobile;
/**
 * Integration tests for the {@link UserAccountResource} REST controller.
 */
@SpringBootTest(classes = TransporterapiApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UserAccountResourceIT {

    private static final String DEFAULT_DEVICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_ID = "BBBBBBBBBB";

    private static final Mobile DEFAULT_MOBILE = Mobile.IOS;
    private static final Mobile UPDATED_MOBILE = Mobile.ANDROID;

    private static final Boolean DEFAULT_SOCIAL_LOGIN = false;
    private static final Boolean UPDATED_SOCIAL_LOGIN = true;

    private static final String DEFAULT_FACEBOOK_ID = "AAAAAAAAAA";
    private static final String UPDATED_FACEBOOK_ID = "BBBBBBBBBB";

    private static final String DEFAULT_GOOGLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_GOOGLE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OTP = "AAAAAAAAAA";
    private static final String UPDATED_OTP = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LAST_LOGIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_LOGIN = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_MODIFIED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MODIFIED_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserAccountMockMvc;

    private UserAccount userAccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserAccount createEntity(EntityManager em) {
        UserAccount userAccount = new UserAccount()
            .deviceId(DEFAULT_DEVICE_ID)
            .mobile(DEFAULT_MOBILE)
            .socialLogin(DEFAULT_SOCIAL_LOGIN)
            .facebookId(DEFAULT_FACEBOOK_ID)
            .googleId(DEFAULT_GOOGLE_ID)
            .otp(DEFAULT_OTP)
            .lastLogin(DEFAULT_LAST_LOGIN)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return userAccount;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserAccount createUpdatedEntity(EntityManager em) {
        UserAccount userAccount = new UserAccount()
            .deviceId(UPDATED_DEVICE_ID)
            .mobile(UPDATED_MOBILE)
            .socialLogin(UPDATED_SOCIAL_LOGIN)
            .facebookId(UPDATED_FACEBOOK_ID)
            .googleId(UPDATED_GOOGLE_ID)
            .otp(UPDATED_OTP)
            .lastLogin(UPDATED_LAST_LOGIN)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return userAccount;
    }

    @BeforeEach
    public void initTest() {
        userAccount = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserAccount() throws Exception {
        int databaseSizeBeforeCreate = userAccountRepository.findAll().size();
        // Create the UserAccount
        UserAccountDTO userAccountDTO = userAccountMapper.toDto(userAccount);
        restUserAccountMockMvc.perform(post("/api/user-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userAccountDTO)))
            .andExpect(status().isCreated());

        // Validate the UserAccount in the database
        List<UserAccount> userAccountList = userAccountRepository.findAll();
        assertThat(userAccountList).hasSize(databaseSizeBeforeCreate + 1);
        UserAccount testUserAccount = userAccountList.get(userAccountList.size() - 1);
        assertThat(testUserAccount.getDeviceId()).isEqualTo(DEFAULT_DEVICE_ID);
        assertThat(testUserAccount.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testUserAccount.isSocialLogin()).isEqualTo(DEFAULT_SOCIAL_LOGIN);
        assertThat(testUserAccount.getFacebookId()).isEqualTo(DEFAULT_FACEBOOK_ID);
        assertThat(testUserAccount.getGoogleId()).isEqualTo(DEFAULT_GOOGLE_ID);
        assertThat(testUserAccount.getOtp()).isEqualTo(DEFAULT_OTP);
        assertThat(testUserAccount.getLastLogin()).isEqualTo(DEFAULT_LAST_LOGIN);
        assertThat(testUserAccount.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testUserAccount.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createUserAccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userAccountRepository.findAll().size();

        // Create the UserAccount with an existing ID
        userAccount.setId(1L);
        UserAccountDTO userAccountDTO = userAccountMapper.toDto(userAccount);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserAccountMockMvc.perform(post("/api/user-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserAccount in the database
        List<UserAccount> userAccountList = userAccountRepository.findAll();
        assertThat(userAccountList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserAccounts() throws Exception {
        // Initialize the database
        userAccountRepository.saveAndFlush(userAccount);

        // Get all the userAccountList
        restUserAccountMockMvc.perform(get("/api/user-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].deviceId").value(hasItem(DEFAULT_DEVICE_ID)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].socialLogin").value(hasItem(DEFAULT_SOCIAL_LOGIN.booleanValue())))
            .andExpect(jsonPath("$.[*].facebookId").value(hasItem(DEFAULT_FACEBOOK_ID)))
            .andExpect(jsonPath("$.[*].googleId").value(hasItem(DEFAULT_GOOGLE_ID)))
            .andExpect(jsonPath("$.[*].otp").value(hasItem(DEFAULT_OTP)))
            .andExpect(jsonPath("$.[*].lastLogin").value(hasItem(DEFAULT_LAST_LOGIN.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getUserAccount() throws Exception {
        // Initialize the database
        userAccountRepository.saveAndFlush(userAccount);

        // Get the userAccount
        restUserAccountMockMvc.perform(get("/api/user-accounts/{id}", userAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userAccount.getId().intValue()))
            .andExpect(jsonPath("$.deviceId").value(DEFAULT_DEVICE_ID))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.toString()))
            .andExpect(jsonPath("$.socialLogin").value(DEFAULT_SOCIAL_LOGIN.booleanValue()))
            .andExpect(jsonPath("$.facebookId").value(DEFAULT_FACEBOOK_ID))
            .andExpect(jsonPath("$.googleId").value(DEFAULT_GOOGLE_ID))
            .andExpect(jsonPath("$.otp").value(DEFAULT_OTP))
            .andExpect(jsonPath("$.lastLogin").value(DEFAULT_LAST_LOGIN.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingUserAccount() throws Exception {
        // Get the userAccount
        restUserAccountMockMvc.perform(get("/api/user-accounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserAccount() throws Exception {
        // Initialize the database
        userAccountRepository.saveAndFlush(userAccount);

        int databaseSizeBeforeUpdate = userAccountRepository.findAll().size();

        // Update the userAccount
        UserAccount updatedUserAccount = userAccountRepository.findById(userAccount.getId()).get();
        // Disconnect from session so that the updates on updatedUserAccount are not directly saved in db
        em.detach(updatedUserAccount);
        updatedUserAccount
            .deviceId(UPDATED_DEVICE_ID)
            .mobile(UPDATED_MOBILE)
            .socialLogin(UPDATED_SOCIAL_LOGIN)
            .facebookId(UPDATED_FACEBOOK_ID)
            .googleId(UPDATED_GOOGLE_ID)
            .otp(UPDATED_OTP)
            .lastLogin(UPDATED_LAST_LOGIN)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        UserAccountDTO userAccountDTO = userAccountMapper.toDto(updatedUserAccount);

        restUserAccountMockMvc.perform(put("/api/user-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userAccountDTO)))
            .andExpect(status().isOk());

        // Validate the UserAccount in the database
        List<UserAccount> userAccountList = userAccountRepository.findAll();
        assertThat(userAccountList).hasSize(databaseSizeBeforeUpdate);
        UserAccount testUserAccount = userAccountList.get(userAccountList.size() - 1);
        assertThat(testUserAccount.getDeviceId()).isEqualTo(UPDATED_DEVICE_ID);
        assertThat(testUserAccount.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testUserAccount.isSocialLogin()).isEqualTo(UPDATED_SOCIAL_LOGIN);
        assertThat(testUserAccount.getFacebookId()).isEqualTo(UPDATED_FACEBOOK_ID);
        assertThat(testUserAccount.getGoogleId()).isEqualTo(UPDATED_GOOGLE_ID);
        assertThat(testUserAccount.getOtp()).isEqualTo(UPDATED_OTP);
        assertThat(testUserAccount.getLastLogin()).isEqualTo(UPDATED_LAST_LOGIN);
        assertThat(testUserAccount.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUserAccount.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingUserAccount() throws Exception {
        int databaseSizeBeforeUpdate = userAccountRepository.findAll().size();

        // Create the UserAccount
        UserAccountDTO userAccountDTO = userAccountMapper.toDto(userAccount);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserAccountMockMvc.perform(put("/api/user-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserAccount in the database
        List<UserAccount> userAccountList = userAccountRepository.findAll();
        assertThat(userAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserAccount() throws Exception {
        // Initialize the database
        userAccountRepository.saveAndFlush(userAccount);

        int databaseSizeBeforeDelete = userAccountRepository.findAll().size();

        // Delete the userAccount
        restUserAccountMockMvc.perform(delete("/api/user-accounts/{id}", userAccount.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserAccount> userAccountList = userAccountRepository.findAll();
        assertThat(userAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
