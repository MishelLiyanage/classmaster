package classmaster;

import classmaster.repository.ComponentRegistry;
import classmaster.ui.Login;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassMaster {

    public static void main(String[] args) {

        try {
            ComponentRegistry.getInstance().register();

            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Login().setVisible(true);
                }
            });

        } catch (SQLException ex) {
            Logger.getLogger(ClassMaster.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
