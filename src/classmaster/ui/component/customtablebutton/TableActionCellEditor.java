/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.ui.component.customtablebutton;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author bhagy
 */
public class TableActionCellEditor extends DefaultCellEditor {
    
    private String icon;
    private TableActionEvent event;

    public TableActionCellEditor(String icon, TableActionEvent event) {
        super(new JCheckBox());
        this.icon = icon;
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {

        PanelAction action = new PanelAction(icon, event);
        action.initEvent(row);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
