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

package org.openmole.plugin.domain.range


import org.openmole.core.model.data.IContext
import org.openmole.core.implementation.tools.VariableExpansion._
import scala.math.Numeric.BigDecimalAsIfIntegral

class ScalaBigDecimalRange(min: String, max: String, step: String) extends IntegralRange[BigDecimal](min, max, step)(BigDecimalAsIfIntegral) {
  override def step(global: IContext, context: IContext): BigDecimal = BigDecimal(expandData(global, context, step))
  override def max(global: IContext, context: IContext): BigDecimal = BigDecimal(expandData(global, context, max))
  override def min(global: IContext, context: IContext): BigDecimal = BigDecimal(expandData(global, context, min))
}