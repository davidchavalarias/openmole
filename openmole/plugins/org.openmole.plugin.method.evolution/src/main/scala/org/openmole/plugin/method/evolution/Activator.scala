/*
 * Copyright (C) 2015 Romain Reuillon
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

package org.openmole.plugin.method.evolution

import org.openmole.core.pluginmanager._
import org.openmole.core.preference.ConfigurationInfo
import org.osgi.framework.BundleContext

class Activator extends PluginInfoActivator {

  override def stop(context: BundleContext): Unit = {
    PluginInfo.unregister(this)
    ConfigurationInfo.unregister(this)
  }

  override def start(context: BundleContext): Unit = {
    import org.openmole.core.pluginmanager.KeyWord._

    val keyWords: Vector[KeyWord] =
      Vector(
        Task(objectName(BreedTask)),
        Task(objectName(DeltaTask)),
        Task(objectName(ElitismTask)),
        Task(objectName(FromIslandTask)),
        Task(objectName(GenerateIslandTask)),
        Task(objectName(ReassignStateRNGTask)),
        Task(objectName(ScalingGenomeTask)),
        Task(objectName(TerminationTask)),
        Task(objectName(InitialStateTask)),
        Pattern(objectName(NichedNSGA2)),
        Pattern(objectName(NichedNSGA2Evolution)),
        Pattern(objectName(GenomeProfile)),
        Pattern(objectName(GenomeProfileEvolution)),
        Pattern(objectName(NSGA2)),
        Pattern(objectName(NSGA2Evolution)),
        Pattern(objectName(OSE)),
        Pattern(objectName(OSEEvolution)),
        Pattern(objectName(PSE)),
        Pattern(objectName(PSEEvolution)),
        Pattern("SteadyStateEvolution"),
        Pattern("IslandEvolution"),
        Word(Stochastic.getClass),
        Hook(objectName(SavePopulationHook))
      )

    PluginInfo.register(this, Vector(this.getClass.getPackage), keyWords = keyWords)
    ConfigurationInfo.register(
      this,
      ConfigurationInfo.list()
    )
  }
}