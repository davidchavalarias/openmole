/*
 * Copyright (C) 2014 Romain Reuillon
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

package org.openmole.plugin.method.evolution.ga

import fr.iscpif.mgo._
import org.openmole.plugin.method.evolution._

object CMAES {

  def apply(
    termination: GATermination { type G >: CMAES#G; type P >: CMAES#P; type F >: CMAES#F; type MF >: CMAES#MF },
    inputs: Inputs[String],
    objectives: Objectives) = {
    val (_inputs, _objectives) = (inputs, objectives)
    new CMAES {
      val inputs = _inputs
      val objectives = _objectives
      val stateManifest: Manifest[STATE] = termination.stateManifest
      val populationManifest: Manifest[Population[G, P, F, MF]] = implicitly
      val individualManifest: Manifest[Individual[G, P, F]] = implicitly
      val aManifest: Manifest[A] = implicitly
      val fManifest: Manifest[F] = implicitly
      val gManifest: Manifest[G] = implicitly

      val genomeSize = inputs.size

      //val mu = _mu
      type STATE = termination.STATE
      def initialState: STATE = termination.initialState
      def terminated(population: ⇒ Population[G, P, F, MF], terminationState: STATE): (Boolean, STATE) = termination.terminated(population, terminationState)
    }
  }
}

trait CMAES extends GAAlgorithm
    with KeepOffspringElitism
    with GAGenomeWithRandomValue
    with NoModifier
    with MaxAggregation
    with CMAESBreeding
    with CMAESArchive
    with ClampedGenome {
  type INPUT = String
  def inputConverter = implicitly
}