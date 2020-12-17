package com.kodebyt.api.service.mapper;


import com.kodebyt.api.domain.*;
import com.kodebyt.api.service.dto.UserAccountDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserAccount} and its DTO {@link UserAccountDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface UserAccountMapper extends EntityMapper<UserAccountDTO, UserAccount> {

    @Mapping(source = "customer.id", target = "customerId")
    UserAccountDTO toDto(UserAccount userAccount);

    @Mapping(source = "customerId", target = "customer")
    UserAccount toEntity(UserAccountDTO userAccountDTO);

    default UserAccount fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setId(id);
        return userAccount;
    }
}
