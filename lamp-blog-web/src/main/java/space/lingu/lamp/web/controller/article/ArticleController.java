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

package space.lingu.lamp.web.controller.article;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import space.lingu.Todo;
import space.lingu.lamp.HttpResponseEntity;
import space.lingu.lamp.Result;
import space.lingu.lamp.web.common.ApiContextHolder;
import space.lingu.lamp.web.controller.WithAdminApi;
import space.lingu.lamp.web.domain.article.Article;
import space.lingu.lamp.web.domain.article.dto.ArticleInfo;
import space.lingu.lamp.web.domain.article.service.ArticleService;
import space.lingu.lamp.web.domain.article.vo.ArticleVo;
import space.lingu.lamp.web.domain.authentication.common.AuthErrorCode;
import space.lingu.lamp.web.domain.content.ContentAccessAuthType;
import space.lingu.lamp.web.domain.content.ContentAccessCredentials;
import space.lingu.lamp.web.domain.content.ContentAccessService;
import space.lingu.lamp.web.domain.content.ContentDetails;
import space.lingu.lamp.web.domain.content.ContentType;
import space.lingu.lamp.web.domain.user.dto.UserInfo;

/**
 * @author RollW
 */
@WithAdminApi
public class ArticleController {
    // TODO: article controller
    private final ArticleService articleService;
    private final ContentAccessService contentAccessService;

    public ArticleController(ArticleService articleService,
                             ContentAccessService contentAccessService) {
        this.articleService = articleService;
        this.contentAccessService = contentAccessService;
    }

    @PostMapping("/article")
    public HttpResponseEntity<ArticleInfo> createArticle(
            @RequestBody ArticleRequest articleRequest) {
        ApiContextHolder.ApiContext apiContext = ApiContextHolder.getContext();
        long userId = apiContext.id();
        Result<ArticleInfo> articleInfoResult = articleService.publishArticle(
                userId, articleRequest.title(), articleRequest.content()
        );
        return HttpResponseEntity.of(articleInfoResult.toResponseBody());
    }

    @PutMapping("/article/{articleId}")
    public HttpResponseEntity<ArticleInfo> updateArticle(
            @RequestBody ArticleRequest articleRequest,
            @PathVariable(name = "articleId") Long articleId) {
        return HttpResponseEntity.success();
    }


    @DeleteMapping("/article/{articleId}")
    public HttpResponseEntity<Void> deleteArticle(
            @PathVariable(name = "articleId") Long articleId) {
        return HttpResponseEntity.success();
    }

    @GetMapping("/article/{articleId}")
    public HttpResponseEntity<ArticleVo> getArticle(
            @PathVariable(name = "articleId") Long articleId) {
        ApiContextHolder.ApiContext apiContext = ApiContextHolder.getContext();
        UserInfo userInfo = apiContext.userInfo();
        if (apiContext.isAdminPass()) {
            ContentDetails details = contentAccessService.getContentDetails(
                    Long.toString(articleId),
                    ContentType.ARTICLE
            );
            return HttpResponseEntity.success(ArticleVo.from(details));
        }
        ContentDetails details = contentAccessService.openContent(
                Long.toString(articleId),
                ContentType.ARTICLE,
                ContentAccessCredentials.of(ContentAccessAuthType.USER, userInfo)
        );

        return HttpResponseEntity.success(ArticleVo.from(details));
    }

    @GetMapping("/article/{articleId}/info")
    // TODO: replace with ArticleInfoVo.
    @Todo(todo = "replace with ArticleInfoVo.")
    public HttpResponseEntity<ArticleInfo> getArticleInfo(
            @PathVariable(name = "articleId") Long articleId) {
        Article article = articleService.getArticle(articleId);
        ApiContextHolder.ApiContext apiContext = ApiContextHolder.getContext();
        if (apiContext.isAdminPass()) {
            return HttpResponseEntity.success(ArticleInfo.from(article));
        }
        return HttpResponseEntity.of(AuthErrorCode.ERROR_NOT_HAS_ROLE);
    }
    // TODO: get article
}
