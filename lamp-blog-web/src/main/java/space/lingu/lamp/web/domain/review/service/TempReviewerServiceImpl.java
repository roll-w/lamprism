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

package space.lingu.lamp.web.domain.review.service;

import org.springframework.stereotype.Service;
import space.lingu.InfoPolicy;
import space.lingu.Todo;
import space.lingu.Warning;
import space.lingu.lamp.web.domain.content.ContentType;

/**
 * @author RollW
 */
@Service
@Todo(todo = "Replace this with a real implementation.")
@Warning(value = "This is a temporary implementation.", policy = InfoPolicy.ALL)
// TODO: Replace this with a real implementation.
@Deprecated
public class TempReviewerServiceImpl implements ReviewerAllocateService {
    @Override
    public long allocateReviewer(ContentType contentType, boolean allowAutoReviewer) {

        return 1;
    }

    @Override
    public void releaseReviewer(long reviewerId, ContentType contentType) {
    }
}
