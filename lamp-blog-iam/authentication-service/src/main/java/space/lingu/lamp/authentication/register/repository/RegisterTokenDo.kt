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

package space.lingu.lamp.authentication.register.repository

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import space.lingu.lamp.DataEntity
import space.lingu.lamp.authentication.VerifiableToken
import space.lingu.lamp.authentication.register.RegisterTokenResourceKind
import space.lingu.lamp.authentication.register.RegisterVerificationToken
import tech.rollw.common.web.system.SystemResourceKind

/**
 * @author RollW
 */
@Entity
@Table(
    name = "register_verification_token",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["token"])
    ]
)
class RegisterTokenDo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private var id: Long? = null,

    @Column(name = "token")
    var token: String = "",

    @Column(name = "user_id")
    var userId: Long = 0,

    @Column(name = "expiry_time")
    var expiryTime: Long = 0,

    @Column(name = "used")
    var used: Boolean = false
) : VerifiableToken, DataEntity<Long> {
    override fun token(): String {
        return token
    }

    fun setId(id: Long?) {
        this.id = id
    }

    override fun getId(): Long? {
        return id
    }

    override fun getCreateTime(): Long = 0

    override fun getUpdateTime(): Long = 0

    override fun getSystemResourceKind(): SystemResourceKind =
        RegisterTokenResourceKind

    fun lock(): RegisterVerificationToken = RegisterVerificationToken(
        id, token, userId,
        expiryTime, used
    )

    companion object {
        fun RegisterVerificationToken.toDo(): RegisterTokenDo = RegisterTokenDo(
            id, token, userId, expiryTime, used
        )
    }
}