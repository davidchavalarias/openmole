/**
 * Created by Mathieu Leclaire on 23/04/18.
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
 *
 */
package org.openmole.gui.plugin.wizard.netlogo

import scala.concurrent.ExecutionContext.Implicits.global
import boopickle.Default._
import org.openmole.gui.ext.data._
import org.openmole.gui.ext.tool.client.OMPost
import scaladget.bootstrapnative.bsn._
import scaladget.tools._
import autowire._
import org.openmole.gui.ext.tool.client
import org.scalajs.dom.raw.HTMLElement
import scaladget.bootstrapnative.SelectableButtons

import scala.concurrent.Future
import scala.scalajs.js.annotation._
import scalatags.JsDom.TypedTag
import scalatags.JsDom.all._

@JSExportTopLevel("org.openmole.gui.plugin.wizard.netlogo.NetlogoWizardFactory")
class NetlogoWizardFactory extends WizardPluginFactory {
  type WizardType = NetlogoWizardData

  val fileType = CodeFile(NetLogoLanguage())

  def build(safePath: SafePath, onPanelFilled: (LaunchingCommand) ⇒ Unit = (LaunchingCommand) ⇒ {}): WizardGUIPlugin = new NetlogoWizardGUI()

  def parse(safePath: SafePath): Future[Option[LaunchingCommand]] = OMPost()[NetlogoWizardAPI].parse(safePath).call()

  def help: String = "If your Netlogo sript depends on plugins, you should upload an archive (tar.gz, tgz) containing the root workspace. Then set the empeddWorkspace option to true in the oms script."

  def name: String = "Netlogo"
}

@JSExportTopLevel("org.openmole.gui.plugin.wizard.netlogo.NetlogoWizardGUI")
class NetlogoWizardGUI extends WizardGUIPlugin {
  type WizardType = NetlogoWizardData

  def factory = new NetlogoWizardFactory

  lazy val embedWorkspaceCheckBox: SelectableButtons = radios()(
    selectableButton("Yes", onclick = () ⇒ println("YES")),
    selectableButton("No", onclick = () ⇒ println("NO"))
  )

  lazy val panel: TypedTag[HTMLElement] = div(
    hForm(
      div(embedWorkspaceCheckBox.render)
        .render.withLabel("EmbedWorkspace")
    ),
    div(client.modelHelp +++ client.columnCSS, "If your Jar sript depends on plugins, you should upload an archive (tar.gz, tgz) containing the root workspace. Then set the empeddWorkspace option to true in the oms script.")
  )

  def save(
    target:         SafePath,
    executableName: String,
    command:        String,
    inputs:         Seq[ProtoTypePair],
    outputs:        Seq[ProtoTypePair],
    libraries:      Option[String],
    resources:      Resources) =
    OMPost()[NetlogoWizardAPI].toTask(
      target,
      executableName,
      command,
      inputs,
      outputs,
      libraries,
      resources,
      NetlogoWizardData(if (embedWorkspaceCheckBox.activeIndex == 0) true else false)).call()

}