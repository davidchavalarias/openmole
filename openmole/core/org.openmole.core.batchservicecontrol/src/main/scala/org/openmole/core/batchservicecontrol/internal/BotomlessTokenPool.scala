/*
 * Copyright (C) 2010 reuillon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.openmole.core.batchservicecontrol.internal

import org.openmole.commons.exception.InternalProcessingError
import org.openmole.core.batchservicecontrol.AccessToken
import org.openmole.core.batchservicecontrol.IAccessTokenPool
import org.openmole.core.model.execution.batch.IAccessToken
import java.util.concurrent.TimeUnit

object BotomlessTokenPool extends IAccessTokenPool {

    val token = new AccessToken

    override def load: Int = -1

    override def releaseToken(token: IAccessToken) = {
        if (!this.token.equals(token)) {
            throw new InternalProcessingError("The token doesn't belong to this pool");
        }
    }

    override def waitAToken: IAccessToken = token

    override def waitAToken(time: Long, unit: TimeUnit): IAccessToken = token

    override def getAccessTokenInterruptly: IAccessToken = token
}
