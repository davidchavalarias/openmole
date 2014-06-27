/*
 * Copyright (C) 2013 Mathieu Leclaire
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.openmole.ide.plugin.task.netlogo

import org.openmole.ide.core.implementation.serializer.GUISerializer
import org.scalatest.FlatSpec
import org.scalatest._

class NetlogoTaskUISpec extends FlatSpec with Matchers {

  "NetlogoTask4DataUI" should "be unserializable" in {
    GUISerializer.unserialise(getClass.getClassLoader.getResource("nl4_09.xml"))
    GUISerializer.unserialise(getClass.getClassLoader.getResource("nl4_010.xml"))
    GUISerializer.unserialise(getClass.getClassLoader.getResource("nl4_1.xml"))
  }

  "NetlogoTask5DataUI" should "be unserializable" in {
    GUISerializer.unserialise(getClass.getClassLoader.getResource("nl5_09.xml"))
    GUISerializer.unserialise(getClass.getClassLoader.getResource("nl5_010.xml"))
    GUISerializer.unserialise(getClass.getClassLoader.getResource("nl5_1.xml"))
  }
}
