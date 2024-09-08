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

package space.lingu.lamp.setting.data

import org.springframework.data.jpa.repository.Query
import space.lingu.lamp.common.data.CommonDao
import space.lingu.lamp.common.data.Dao

/**
 * @author RollW
 */
@Dao
interface SystemSettingDao: CommonDao<SystemSettingDo, Long> {
    @Query("SELECT s FROM SystemSettingDo s WHERE s.key = :key")
    fun findByKey(key: String): SystemSettingDo?
}