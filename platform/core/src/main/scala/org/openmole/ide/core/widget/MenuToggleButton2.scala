/*
 * Copyright (C) 2011 <mathieu.leclaire at openmole.org>
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
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.openmole.ide.core.widget

import java.awt.Color
import java.awt.Graphics
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import javax.swing.BorderFactory
import javax.swing.Icon
import scala.swing._
import swing.Swing._

class MenuToggleButton2(text: String) extends ToggleButton(text){
  val popup = new PopupMenu
  icon = new MenuArrowIcon
  peer.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4 + icon.getIconWidth))
      
  popup.peer.addPropertyChangeListener( "visible", new PropertyChangeListener {
      def propertyChange(e: PropertyChangeEvent) = peer.setSelected( popup.peer.isVisible )})
  
  action = new Action( text ) { override def apply = popup.show(MenuToggleButton2.this, 0,MenuToggleButton2.this.peer.getHeight-1 ) }
  
  def addItem(item : MenuItem) = popup.contents += item
  
  class MenuArrowIcon extends Icon {
    override def paintIcon(c: java.awt.Component,g: Graphics, x: Int, y: Int) = {
      val g2 = g.asInstanceOf[Graphics2D]
      g2.setPaint(Color.BLACK)
      g2.translate(x, y)
      g2.drawLine(2, 3, 6, 3)
      g2.drawLine(3, 4, 5, 4)
      g2.drawLine(4, 5, 4, 5)
      g2.translate(-x, -y)
    }
    
    override def getIconWidth = 9
    override def getIconHeight = 9
  }
}