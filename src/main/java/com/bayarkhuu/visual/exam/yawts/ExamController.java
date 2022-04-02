package com.bayarkhuu.visual.exam.yawts;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.binding.When;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

import java.util.function.Function;

/**
 * ExamController
 *
 * @author Баярхүү.Лув 2022.04.02 18:40
 */
public class ExamController {
    /*Нийт сурагчдийг хадгалах list*/
    private final ObservableList<Student> students = FXCollections.observableArrayList();

    @FXML
    private TextField idTf;
    @FXML
    private TextField firstnameTf;
    @FXML
    private TextField lastnameTf;
    @FXML
    private TextField attendanceScoreTf;
    @FXML
    private TextField attendanceEgTf;
    @FXML
    private TextField attendancePercentTf;
    @FXML
    private TextField projectScoreTf;
    @FXML
    private TextField projectEgTf;
    @FXML
    private TextField projectPercentTf;
    @FXML
    private TextField writtenScoreTf;
    @FXML
    private TextField writtenEgTf;
    @FXML
    private TextField writtenPercentTf;
    @FXML
    private TextField prelimScoreTf;
    @FXML
    private TextField prelimEgTf;
    @FXML
    private TextField prelimPercentTf;
    @FXML
    private TextField practicalScoreTf;
    @FXML
    private TextField practicalEgTf;
    @FXML
    private TextField practicalPercentTf;
    @FXML
    private TextField prelimGradeTf;
    @FXML
    private TextField remarksTf;

    @FXML
    private TableView<Student> table;

    @FXML
    private void initialize() {
        bind(20, 0.1, attendanceScoreTf, attendancePercentTf, attendanceEgTf);
        bind(100, 0.3, projectScoreTf, projectPercentTf, projectEgTf);
        bind(50, 0.1, writtenScoreTf, writtenPercentTf, writtenEgTf);
        bind(100, 0.3, prelimScoreTf, prelimPercentTf, prelimEgTf);
        bind(50, 0.2, practicalScoreTf, practicalPercentTf, practicalEgTf);

        bindPrelim();

        /*Анхны утгууд*/
        students.add(new Student(1L, "Jav", "Joy", 0, 0, 0, 0, 0));
        students.add(new Student(2L, "Bay", "Bat", 0, 0, 0, 0, 0));
        students.add(new Student(3L, "Luv", "Khuu", 0, 0, 0, 0, 0));

        /*Бүх баганыг устгах*/
        table.getColumns().clear();

        /*Багана нэмэх*/
        table.getColumns().add(addColumn("STUDENTID", Student::getId));
        table.getColumns().add(addColumn("Firstname", Student::getFirstName));
        table.getColumns().add(addColumn("Lastname", Student::getLastName));
        table.getColumns().add(addColumn("Attendance Score", Student::getAttendance));
        table.getColumns().add(addColumn("Project Score", Student::getProject));
        table.getColumns().add(addColumn("Written Quiz Score", Student::getWrittenQuiz));
        table.getColumns().add(addColumn("Prelim Exam Score", Student::getPrelimExam));
        table.getColumns().add(addColumn("Practical Quiz Score", Student::getPracticalQuiz));
    }

    /**
     * Урьдчилсан үнэлгээг bind хийх.
     *
     * Сурагчийн авсан оноог 320-д хувьчилж бодсон. 320-г 100% гэж үзээд "100 / 320 * сурагчийн авсан онооны нийлбэр" гэх томёогоор бодсон.
     */
    private void bindPrelim() {
        DoubleProperty attendance = new SimpleDoubleProperty();
        DoubleProperty project = new SimpleDoubleProperty();
        DoubleProperty written = new SimpleDoubleProperty();
        DoubleProperty prelim = new SimpleDoubleProperty();
        DoubleProperty practical = new SimpleDoubleProperty();
        DoubleProperty prelimGrade = new SimpleDoubleProperty();


        prelimGrade.bind(new SimpleDoubleProperty(100).divide(320).multiply(attendance.add(project).add(written).add(prelim).add(practical)));
        StringBinding remarks = new When(prelimGrade.greaterThan(59)).then("Passed").otherwise("Failed");

        StringConverter<? extends Number> converter = new DoubleStringConverter();
        Bindings.bindBidirectional(attendanceScoreTf.textProperty(), attendance, (StringConverter<Number>) converter);
        Bindings.bindBidirectional(projectScoreTf.textProperty(), project, (StringConverter<Number>) converter);
        Bindings.bindBidirectional(writtenScoreTf.textProperty(), written, (StringConverter<Number>) converter);
        Bindings.bindBidirectional(prelimScoreTf.textProperty(), prelim, (StringConverter<Number>) converter);
        Bindings.bindBidirectional(practicalScoreTf.textProperty(), practical, (StringConverter<Number>) converter);

        prelimGradeTf.textProperty().bind(prelimGrade.asString("%.1f"));
        remarksTf.textProperty().bind(remarks);
    }

    /**
     * Attendance, Project, Written Quiz гэх зэрэг үнэлгээний оноог бодох binder.
     *
     * @param maxScore Авах боломжтой дээд оноо
     * @param percent Нийт онооны эзлэх хувь
     * @param scoreTf Оноог бичих талбар
     * @param percentTf Хувийг харуулах талбар
     * @param egTf EG-г харуулах талбар
     */
    private void bind(double maxScore, double percent, TextField scoreTf, TextField percentTf, TextField egTf) {
        DoubleProperty scoreBind = new SimpleDoubleProperty();
        DoubleProperty percentBind = new SimpleDoubleProperty();
        DoubleProperty egBind = new SimpleDoubleProperty();

        egBind.bind(new SimpleDoubleProperty(100).divide(maxScore).multiply(scoreBind));
        percentBind.bind(egBind.multiply(percent));

        StringConverter<? extends Number> converter = new DoubleStringConverter();

        Bindings.bindBidirectional(scoreTf.textProperty(), scoreBind, (StringConverter<Number>) converter);
        percentTf.textProperty().bind(percentBind.asString("%.1f"));
        egTf.textProperty().bind(egBind.asString("%.1f"));
    }

    /**
     * Binder ашигласан учир compute хийх шаардлаггүй гэж үзсэн
     */
    @FXML
    private void compute() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Binder ашигласан bind() гэх нэртэй методыг харна уу.");
        alert.setContentText("Binder ашигласан учир оноог бичих үед бүх compute үйлдэл хийгдэнэ.");
        alert.show();
    }

    /**
     * TableView дээрээс сонгосон оюунты мэдээллийг Text талбарт setText метод ашиглаж харуулсан
     */
    @FXML
    private void showDetails() {
        Student student = table.getSelectionModel().getSelectedItem();
        if (student != null) {
            idTf.setText(student.getId().toString());
            firstnameTf.setText(student.getFirstName());
            lastnameTf.setText(student.getLastName());
            attendanceScoreTf.setText(String.valueOf(student.getAttendance()));
            projectScoreTf.setText(String.valueOf(student.getProject()));
            writtenScoreTf.setText(String.valueOf(student.getWrittenQuiz()));
            prelimScoreTf.setText(String.valueOf(student.getPrelimExam()));
            practicalScoreTf.setText(String.valueOf(student.getPracticalQuiz()));
        }
    }

    /**
     * TableView дээрээс сонгосон сурагчийн мэдээллийг нийт хадгалаастай сурагчийн list-нээс remove метод ашиглаж устгасан.
     */
    @FXML
    private void delete() {
        Student student = table.getSelectionModel().getSelectedItem();
        System.out.println(student == null ?"null":student.getId());
        if (student != null) students.remove(student);
    }

    /**
     * Update хийхдээ TableView дээр сонгогдсон сурагчийн мэдээллийг clone хийсэн.
     * Нийт сурагчийн мэдээллээс update хийж буй сурагчийн мэдээллийг устгаад,
     * доторх мэдээллийг нь шинэчилсэний дараа буцаагаад нийт сурагчийн list-нд нэсмэн.
     */
    @FXML
    private void update() {
        Student student = table.getSelectionModel().getSelectedItem().clone();
        if (student != null) {
            student.setId(Long.parseLong(idTf.getText()));
            student.setFirstName(firstnameTf.getText());
            student.setLastName(lastnameTf.getText());
            student.setAttendance(Double.parseDouble(attendanceScoreTf.getText()));
            student.setProject(Double.parseDouble(projectScoreTf.getText()));
            student.setWrittenQuiz(Double.parseDouble(writtenScoreTf.getText()));
            student.setPrelimExam(Double.parseDouble(prelimScoreTf.getText()));
            student.setPracticalQuiz(Double.parseDouble(practicalScoreTf.getText()));

            table.getItems()
                    .stream().filter(f -> f.getId().equals(student.getId()))
                    .findFirst()
                    .ifPresent(f -> table.getItems().remove(f));
            students.add(student);
        }
    }

    /**
     * Хамгийн эхний сурагчийг select методын аргументед нэмсэн.
     */
    @FXML
    private void first() {
        table.getSelectionModel().select(students.get(0));
    }

    /**
     * Сонгогдсон байгаа сурагчийн индексээс хасах нь 1 дэх индекс гэвэл нэг сурагч буцна
     */
    @FXML
    private void back() {
        Student student = table.getSelectionModel().getSelectedItem();
        int index = students.indexOf(student);
        if (index > 0) table.getSelectionModel().select(students.get(index - 1));
    }

    /**
     * Сонгогдсон байгаа сурагчийн индек дээр нэмэх нь 1 дэх индекс гэвэл нэг сурагч урагшлана
     */
    @FXML
    private void next() {
        Student student = table.getSelectionModel().getSelectedItem();
        int index = students.indexOf(student);
        if (index < students.size() - 1) table.getSelectionModel().select(students.get(index + 1));
    }

    /**
     * Нийт сурагчийн тооноос хасах нь 1 гэвэл хамгийн сүүлийн сурагчийг сонгоно.
     * Яагаад хасах нь 1 вэ гэвэл list-н индекс 0-ээс эхэлдэг.
     */
    @FXML
    private void last() {
        table.getSelectionModel().select(students.get(students.size() - 1));
    }

    /**
     * setItems метод ашиглаж Load Students товчлуурийн үүргийг гүйцэтгэсэн
     */
    @FXML
    private void load() {
        table.setItems(students);
    }

    /**
     * Нийт сурагч дээр шинээр Student классын объектийг үүсгэж нэмсэн.
     */
    @FXML
    private void saveRecord() {
        students.add(new Student(Long.parseLong(idTf.getText()),
                firstnameTf.getText(), lastnameTf.getText(),
                Double.parseDouble(attendanceScoreTf.getText()),
                Double.parseDouble(projectScoreTf.getText()),
                Double.parseDouble(writtenScoreTf.getText()),
                Double.parseDouble(prelimScoreTf.getText()),
                Double.parseDouble(practicalScoreTf.getText())));
    }

    /**
     * Цэвэрлэх шаардлагатай талбаруудыг clear метод ашиглаж цэвэрлэсэн.
     * Бусад талбарууд bind хийгдсэн учир дагаад цэвэрлэгдэнэ.
     */
    @FXML
    private void clearEvent() {
        idTf.clear();
        firstnameTf.clear();
        lastnameTf.clear();
        attendanceScoreTf.clear();
        projectScoreTf.clear();
        writtenScoreTf.clear();
        prelimScoreTf.clear();
        practicalScoreTf.clear();
    }

    @FXML
    private void exit() {
        Platform.exit();
    }

    /**
     * TableView-д багана нэмэх арга
     *
     * @param caption баганы гарчиг
     * @param value   баганы утгад харуулах field
     */
    private TableColumn<Student, String> addColumn(String caption, Function<Student, ?> value) {
        TableColumn<Student, String> column = new TableColumn<>(caption);
        column.setCellValueFactory(data -> {
            Student model = data.getValue();
            return new ObservableValueBase<>() {
                @Override
                public String getValue() {
                    return String.valueOf(value.apply(model));
                }
            };
        });
        column.setStyle("-fx-alignment: BASELINE-CENTER;");
        column.setPrefWidth(100);
        return column;
    }
}
