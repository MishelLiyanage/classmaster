/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classmaster.repository;

import classmaster.shared.DBConnection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ComponentRegistry {

    private static ComponentRegistry componentRegistry;

    public static ComponentRegistry getInstance() {
        if (componentRegistry == null) {
            componentRegistry = new ComponentRegistry();
        }
        return componentRegistry;
    }

    private Map<String, Component> registry = new HashMap<>();

    public void register() throws SQLException {
        DBConnection dbConnection = new DBConnection(
                "jdbc:mysql://localhost:3306/classdatabase",
                "masteruser",
                "ma5tErU5erPa55"
        );

        dbConnection.connect();
        AuthRepository authRepository = new AuthRepository(dbConnection);

        registry.put(authRepository.getName(), authRepository);

    }

    public Component getComponent(String name) {
        return registry.get(name);
    }

    public void set(String name, Component component) {
        registry.put(name, component);
    }

}
