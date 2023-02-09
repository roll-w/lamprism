/*
 * Copyright (C) 2023 RollW
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package space.lingu.lamp.web.controller.user;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import space.lingu.lamp.BusinessRuntimeException;
import space.lingu.lamp.HttpResponseEntity;
import space.lingu.lamp.web.common.ApiContextHolder;
import space.lingu.lamp.web.controller.Api;
import space.lingu.lamp.web.domain.authentication.common.AuthErrorCode;
import space.lingu.lamp.web.domain.user.UserIdentity;
import space.lingu.lamp.web.domain.user.dto.UserInfo;
import space.lingu.lamp.web.domain.user.service.UserSearchService;
import space.lingu.lamp.web.domain.user.vo.UserCommonDetailsVo;
import space.lingu.lamp.web.domain.userdetails.UserPersonalData;
import space.lingu.lamp.web.domain.userdetails.UserPersonalDataService;

/**
 * @author RollW
 */
@Api
public class UserController {
    private final UserSearchService userSearchService;
    private final UserPersonalDataService userPersonalDataService;

    public UserController(UserSearchService userSearchService,
                          UserPersonalDataService userPersonalDataService) {
        this.userSearchService = userSearchService;
        this.userPersonalDataService = userPersonalDataService;
    }

    @GetMapping("/user")
    public HttpResponseEntity<UserCommonDetailsVo> getAuthenticatedUser() {
        ApiContextHolder.ApiContext context = ApiContextHolder.getContext();
        UserInfo userInfo = context.userInfo();
        if (userInfo == null) {
            throw new BusinessRuntimeException(AuthErrorCode.ERROR_UNAUTHORIZED_USE);
        }
        UserPersonalData userPersonalData =
                userPersonalDataService.getPersonalData(userInfo);
        return HttpResponseEntity.success(
                UserCommonDetailsVo.of(userInfo, userPersonalData)
        );
    }

    @GetMapping("/user/{userId}")
    public HttpResponseEntity<UserCommonDetailsVo> getUserInfo(@PathVariable("userId") Long userId) {
        UserIdentity userIdentity = userSearchService.findUser(userId);
        UserPersonalData userPersonalData =
                userPersonalDataService.getPersonalData(userIdentity);
        return HttpResponseEntity.success(
                UserCommonDetailsVo.of(userIdentity, userPersonalData)
        );
    }

    @PutMapping("/user/{userId}/blocks")
    public void blockUser(@PathVariable("userId") Long userId) {

    }

    @DeleteMapping("/user/{userId}/blocks")
    public void unblockUser(@PathVariable("userId") Long userId) {

    }
}