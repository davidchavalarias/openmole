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

package org.openmole.plugin.tools.code

import org.openmole.core.implementation.data.Prototype
import org.openmole.core.model.data.IContext
import org.openmole.core.model.data.IData
import org.openmole.core.model.data.IVariable
import org.openmole.core.model.execution.IProgress

object IContextToCode {
   val progressVar = new Prototype[IProgress]("progress", classOf[IProgress])
}

trait IContextToCode {
  def execute(global: IContext, context: IContext, tmpVariables: Iterable[IVariable[_]], progress: IProgress, output: Iterable[IData[_]]): Object
}
