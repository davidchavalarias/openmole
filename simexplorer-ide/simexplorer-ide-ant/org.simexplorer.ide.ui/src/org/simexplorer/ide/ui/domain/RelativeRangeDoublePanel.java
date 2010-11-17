/*
 *  Copyright © 2008, 2009, Cemagref
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation; either version 3 of
 *  the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public
 *  License along with this program; if not, write to the Free
 *  Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston,
 *  MA  02110-1301  USA
 */
package org.simexplorer.ide.ui.domain;

import org.simexplorer.core.workflow.methods.EditorPanel;
import javax.swing.SpinnerNumberModel;
import org.openide.util.lookup.ServiceProvider;
import org.openmole.plugin.domain.relative.RelativeRangeDouble;

@ServiceProvider(service=EditorPanel.class)
public class RelativeRangeDoublePanel extends EditorPanel<RelativeRangeDouble> {

    private SpinnerNumberModel nominalModel,  percentModel, sizeModel;

    /** Creates new form RelativeRangeDoublePanel */
    public RelativeRangeDoublePanel() {
        super(RelativeRangeDouble.class);
        initComponents();
        nominalModel = (SpinnerNumberModel) nominalSpinner.getModel();
        percentModel = (SpinnerNumberModel) percentSpinner.getModel();
        sizeModel = (SpinnerNumberModel) sizeSpinner.getModel();
        sizeModel.setMinimum(1);
        percentModel.setMinimum(0.);
    }

    @Override
    public void setObjectEdited(RelativeRangeDouble range) {
        super.setObjectEdited(range);
        // we ensure with this flag, that bounds setting will not trigger events
        nominalModel.setValue(Double.parseDouble(range.getNominal()));
        percentModel.setValue(Double.parseDouble(range.getPercent()));
        sizeModel.setValue(Integer.parseInt(range.getSize()));
    }

    @Override
    public void applyChanges() {
        getObjectEdited().setNominal(nominalModel.getValue().toString());
        getObjectEdited().setPercent(percentModel.getValue().toString());
        getObjectEdited().setSize(sizeModel.getValue().toString());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nominalSpinner = new javax.swing.JSpinner();
        percentSpinner = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        sizeSpinner = new javax.swing.JSpinner();

        jLabel1.setText("Nominal");

        jLabel2.setText("Percent");

        nominalSpinner.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(1.0d), null, null, Double.valueOf(1.0d)));

        percentSpinner.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(10.0d), null, null, Double.valueOf(1.0d)));

        jLabel3.setText("Size");

        sizeSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(2), null, null, Integer.valueOf(1)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(percentSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(nominalSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(sizeSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nominalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(percentSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSpinner nominalSpinner;
    private javax.swing.JSpinner percentSpinner;
    private javax.swing.JSpinner sizeSpinner;
    // End of variables declaration//GEN-END:variables

}