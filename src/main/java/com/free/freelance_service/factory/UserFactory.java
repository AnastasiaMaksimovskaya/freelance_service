package com.free.freelance_service.factory;

import com.free.freelance_service.entity.TempUser;
import com.free.freelance_service.entity.users.BaseUser;

public interface UserFactory {
    BaseUser createUser();
    BaseUser createUser(TempUser dto);
}
