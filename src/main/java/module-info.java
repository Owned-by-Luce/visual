module com.bayarkhuu.visual {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.bayarkhuu.visual to javafx.fxml;
    exports com.bayarkhuu.visual.labs.lab1;
    exports com.bayarkhuu.visual.labs.lab2;
    exports com.bayarkhuu.visual.labs.lab3;
    exports com.bayarkhuu.visual.labs.lab4;
    exports com.bayarkhuu.visual.labs.lab5;
    exports com.bayarkhuu.visual.home.home2;
    opens com.bayarkhuu.visual.labs.lab1 to javafx.fxml;
    opens com.bayarkhuu.visual.labs.lab4 to javafx.fxml;
    opens com.bayarkhuu.visual.labs.lab5 to javafx.fxml;
}