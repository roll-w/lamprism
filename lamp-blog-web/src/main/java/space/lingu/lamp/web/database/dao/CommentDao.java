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

package space.lingu.lamp.web.database.dao;

import space.lingu.lamp.web.domain.comment.Comment;
import space.lingu.light.Dao;
import space.lingu.light.Delete;
import space.lingu.light.Insert;
import space.lingu.light.OnConflictStrategy;
import space.lingu.light.Query;
import space.lingu.light.Update;

import java.util.List;

/**
 * @author RollW
 */
@Dao
public abstract class CommentDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public abstract void insert(Comment... comments);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    public abstract void insert(List<Comment> comments);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public abstract void update(Comment... comments);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public abstract void update(List<Comment> comments);

    @Delete
    protected abstract void delete(Comment Comment);

    @Delete
    protected abstract void delete(List<Comment> comments);

    @Delete("DELETE FROM comment")
    protected abstract void clearTable();

    @Query("SELECT * FROM comment")
    public abstract List<Comment> get();
}


