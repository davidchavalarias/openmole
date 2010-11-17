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

package org.openmole.plugin.environment.glite.internal

import java.util.logging.Level
import java.util.logging.Logger
import org.ogf.saga.context.Context
import org.openmole.misc.updater.IUpdatable
import org.openmole.plugin.environment.glite.GliteAuthentication

class ProxyChecker(authentication: GliteAuthentication, context: Context) extends IUpdatable {

    override def update: Boolean = {
        try {
            authentication.initContext(context)
        } catch {
          case(ex: Throwable) => Logger.getLogger(classOf[ProxyChecker].getName).log(Level.SEVERE, "Error while renewing the proxy.", ex);
        } 
        
        true
    }
}
