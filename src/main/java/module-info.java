module com.jgomwal111.proyectomultihilo_psp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.logging;


    opens com.jgomwal111.proyectomultihilo_psp to javafx.fxml;
    exports com.jgomwal111.proyectomultihilo_psp;
    exports com.jgomwal111.proyectomultihilo_psp.controller;
    opens com.jgomwal111.proyectomultihilo_psp.controller to javafx.fxml;
}