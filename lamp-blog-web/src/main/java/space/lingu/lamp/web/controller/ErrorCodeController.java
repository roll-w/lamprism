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

package space.lingu.lamp.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import space.lingu.lamp.ErrorCodeFinderChain;

/**
 * @author RollW
 */
@RestController
@CommonApi
public class ErrorCodeController {
    private final ErrorCodeFinderChain codeFinderChain;

    public ErrorCodeController(ErrorCodeFinderChain codeFinderChain) {
        this.codeFinderChain = codeFinderChain;
    }

    @GetMapping("/code")
    public String getErrorCodeName(String code) {
        return codeFinderChain.findErrorCode(code).getName();
    }
}