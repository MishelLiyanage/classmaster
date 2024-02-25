/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.shared;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author bhagy
 */
public class CenterCellRenderer extends DefaultTableCellRenderer {

    public CenterCellRenderer() {
        this.setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public java.awt.Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
        java.awt.Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);

        return com;
    }

}
