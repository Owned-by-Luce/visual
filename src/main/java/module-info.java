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
    exports com.bayarkhuu.visual.lab1;
    exports com.bayarkhuu.visual.lab2;
    exports com.bayarkhuu.visual.lab3;
    opens com.bayarkhuu.visual.lab1 to javafx.fxml;
}