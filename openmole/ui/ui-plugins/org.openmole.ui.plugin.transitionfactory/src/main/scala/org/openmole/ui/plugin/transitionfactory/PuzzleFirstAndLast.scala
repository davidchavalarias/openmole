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

package org.openmole.ui.plugin.transitionfactory

import org.openmole.core.model.capsule.IGenericCapsule

class PuzzleFirstAndLast[+T1 <: IGenericCapsule, +T2 <: IGenericCapsule](val firstCapsule: T1, val lastCapsule: T2) extends IPuzzleFirstAndLast[T1,T2]