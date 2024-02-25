/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.ui.component.customtable;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author bhagy
 */
public class TableActionCellRender extends DefaultTableCellRenderer {

    private String icon;
    private TableActionEvent event;

    public TableActionCellRender(String icon, TableActionEvent event) {
        this.icon = icon;
        this.event = event;
        this.setHorizontalAlignment( JLabel.CENTER );
    }

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
        
        PanelAction action = new PanelAction(icon, event);
        if (isSeleted == false && row % 2 == 0) {
            action.setBackground(Color.WHITE);
        } else {
            action.setBackground(com.getBackground());
        }
        return action;
    }
}
