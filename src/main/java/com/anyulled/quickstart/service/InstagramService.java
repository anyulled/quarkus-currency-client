package com.anyulled.quickstart.service;

import com.anyulled.quickstart.model.MediaResponse;
import com.anyulled.quickstart.model.UserResponse;
import com.anyulled.quickstart.utils.BusinessException;

public interface InstagramService {

    void saveCode(String code);

    String obtainAccessToken() throws BusinessException;

    UserResponse getUserInfo() throws BusinessException;

    MediaResponse getMediaInfo() throws BusinessException;

    Boolean isTokenSet();
}
