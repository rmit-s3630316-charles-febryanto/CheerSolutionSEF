package com.conference.user;

import com.conference.DialogBox;
import com.conference.MySQL;
import com.conference.company.Product;
import com.conference.lecture.Lecture;
import com.conference.company.Company;
import com.conference.lecture.Room;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import sun.plugin2.jvm.RemoteJVMLauncher;

import java.sql.*;
import java.time.LocalDate;

import static com.conference.Conference.loginScene;

public class Receptionist extends Member {
    private Connection cn = null;
    private PreparedStatement pst = null;
//    private Statement st = null;
    private ResultSet rs = null;

    private ToggleGroup genderGroup;
    private RadioButton maleRadio, femaleRadio;
    private DatePicker dobPicker;
    private Label firstName, lastName, gender, contactNo, address, memberId, dob;
    private TextField firstNameField, lastNameField, contactField, memberIdField, idScanner;
    private TextArea addressField;
    private ToggleGroup engagementGroup;
    private RadioButton lectureEngagement, boothEngagement;
    private ComboBox<String> selectionBox;

    private ObservableList<Lecture> lectures;
    private ObservableList<Company> companies;

//    private TableView<Lecture> lectureTable;
//    private TableView<Company> companyTable;

    public Receptionist(String memberId, String firstName, String lastName, String gender, String contactNumber, String address, Date dob, int position) {
        super(memberId, firstName, lastName, gender, contactNumber, address, dob, position);
    }

    @Override
    public void view(Stage stage) {
        BorderPane layout = new BorderPane();

        MenuBar menuBar = new MenuBar();

        Menu profile = new Menu("Profile");
//        MenuItem edit = new MenuItem("Edit");
        Menu activity = new Menu("Activity");
        MenuItem productPurchased = new MenuItem("Product Purchased");
        productPurchased.setOnAction(e -> layout.setCenter(productPurchasedView()));
        MenuItem engagementHistory = new MenuItem("Engagement History");
        engagementHistory.setOnAction(e -> layout.setCenter(engagementHistoryView()));
        activity.getItems().addAll(productPurchased, engagementHistory);

        MenuItem logout = new MenuItem("Log out");
        logout.setOnAction(e -> logout(stage, loginScene));
        profile.getItems().addAll(activity, logout);

        Menu manage = new Menu("Manage");
        MenuItem registerVisitor = new MenuItem("Register Visitor");
        registerVisitor.setOnAction(e -> layout.setCenter(registerVisitorView()));
        MenuItem addVisitorEngagement = new MenuItem("Add Visitor Engagement");
        addVisitorEngagement.setOnAction(e -> layout.setCenter(addVisitorView()));
        manage.getItems().addAll(registerVisitor, addVisitorEngagement);

        menuBar.getMenus().addAll(profile, manage);

        layout.setCenter(registerVisitorView());
        layout.setTop(menuBar);

        Scene scene = new Scene(layout, 1024, 600);
        stage.setTitle("Login As : " + getFirstName() + " " + getLastName() + " | Receptionist");
        stage.setScene(scene);
    }

    public GridPane registerVisitorView() {
        GridPane body = new GridPane();
        body.setVgap(10);
        body.setHgap(10);
        body.setPadding(new Insets(10));

        GridPane registerFormContainer = new GridPane();
        registerFormContainer.setHgap(5);
        registerFormContainer.setVgap(10);

        firstName = new Label("First Name : ");
//        GridPane.setConstraints(firstName, 0, 0);
        firstNameField = new TextField();
        firstNameField.setPromptText("Insert First Name");
//        GridPane.setConstraints(firstNameField, 1, 0);
        firstNameField.textProperty().addListener(e ->
                DialogBox.stringOnly(firstNameField, 20,
                        "Warning", "First Name is Too Long"));

        registerFormContainer.add(firstName, 0, 0);
        registerFormContainer.add(firstNameField, 1, 0);

        lastName = new Label("Last Name : ");
//        GridPane.setConstraints(lastName, 0, 1);
        lastNameField = new TextField();
        lastNameField.setPromptText("Insert Last Name");
//        GridPane.setConstraints(lastNameField, 1, 1);
        lastNameField.textProperty().addListener(e ->
                DialogBox.stringOnly(lastNameField, 20,
                        "Warning", "Last Name is Too Long"));


        registerFormContainer.add(lastName, 0, 1);
        registerFormContainer.add(lastNameField, 1, 1);

        gender = new Label("Gender : ");
//        GridPane.setConstraints(gender, 0, 2);

        genderGroup = new ToggleGroup();
        GridPane genderButtonContainer = new GridPane();
        genderButtonContainer.setHgap(10);

        maleRadio = new RadioButton("Male");
        genderButtonContainer.add(maleRadio, 0, 0);

//        GridPane.setConstraints(maleRadio, 1, 2);
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio = new RadioButton("Female");
//        GridPane.setConstraints(femaleRadio, 2, 2);
        femaleRadio.setToggleGroup(genderGroup);
        genderButtonContainer.add(femaleRadio, 1, 0);
        maleRadio.setSelected(true);
//        GridPane.setConstraints(genderButtonContainer, 1, 3);

        registerFormContainer.add(gender, 0, 2);
        registerFormContainer.add(genderButtonContainer, 1, 2);

        contactNo = new Label("Contact Number : ");
//        GridPane.setConstraints(contactNo, 0, 3);
        contactField = new TextField();
        contactField.setPromptText("Insert Contact Number");
//        GridPane.setConstraints(contactField, 1, 3);
        contactField.textProperty().addListener(e ->
                DialogBox.numberOnly(contactField, 20,
                        "Warning", "Contact is Too Long"));

        registerFormContainer.add(contactNo, 0, 3);
        registerFormContainer.add(contactField, 1, 3);

        address = new Label("Address : ");
//        GridPane.setConstraints(address, 0, 4);
        addressField = new TextArea();
        addressField.setPromptText("Insert Address");
        addressField.setMaxWidth(300);
        addressField.setMaxHeight(100);
//        GridPane.setConstraints(addressField, 1, 4);
        addressField.setWrapText(true);
        addressField.textProperty().addListener(e ->
                DialogBox.lengthCheck(addressField, 80,
                        "Warning", "Address is Too Long"));

        registerFormContainer.add(address, 0,4);
        registerFormContainer.add(addressField, 1, 4);

        dob = new Label("Date of Birth : ");
//        GridPane.setConstraints(dob, 0, 5);
        dobPicker = new DatePicker();
//        GridPane.setConstraints(dobPicker, 1, 5);

        registerFormContainer.add(dob, 0, 5);
        registerFormContainer.add(dobPicker, 1, 5);

        memberId = new Label("ID : ");
//        GridPane.setConstraints(memberId, 0, 6);
        memberIdField = new TextField();
        memberIdField.setPromptText("Scan Member ID");
//        GridPane.setConstraints(memberIdField, 1, 6);
        memberIdField.textProperty().addListener(e -> {
            if(DialogBox.numberOnly(memberIdField) && memberIdField.getText().length() >= 10) {
                    insertVisitor();
            }
        });

        registerFormContainer.add(memberId, 0, 6);
        registerFormContainer.add(memberIdField, 1, 6);

        Button addMember = new Button("Register Member");
//        GridPane.setConstraints(addMember, 1, 7);
        addMember.setOnAction(e -> insertVisitor());

        registerFormContainer.add(addMember, 1, 7);
//        body.getChildren().addAll(firstName, lastName, gender, contactNo, address, dob);
//        body.getChildren().addAll(firstNameField, lastNameField, maleRadio, femaleRadio, contactField, addressField,
//                dobPicker, memberId, memberIdField, addMember);
        body.add(registerFormContainer, 0,0);
        return body;
    }

    public GridPane addVisitorView() {
        getLectures();
        getCompanies();

        GridPane body = new GridPane();
        body.setVgap(10);
        body.setHgap(10);
        body.setPadding(new Insets(10));

        GridPane addVisitorContainer = new GridPane();
        addVisitorContainer.setHgap(5);
        addVisitorContainer.setVgap(10);
        Label engagementBy = new Label("Select type : ");
//        GridPane.setConstraints(engagementBy, 0, 0);
        addVisitorContainer.add(engagementBy, 0, 0);

        GridPane engagementButtonContainer = new GridPane();
        engagementButtonContainer.setHgap(10);

        engagementGroup = new ToggleGroup();
        lectureEngagement = new RadioButton("Lecture");
//        GridPane.setConstraints(lectureEngagement, 1, 0);
//        addVisitorContainer.add(lectureEngagement, 1, 0);
        engagementButtonContainer.add(lectureEngagement, 0, 0);

        lectureEngagement.setToggleGroup(engagementGroup);
        boothEngagement = new RadioButton("Booth");
//        GridPane.setConstraints(boothEngagement, 2, 0);

        boothEngagement.setToggleGroup(engagementGroup);
        lectureEngagement.setSelected(true);
        engagementButtonContainer.add(boothEngagement, 1, 0);
        engagementGroup.selectedToggleProperty().addListener(e -> engagementSelectionSwitch());
        addVisitorContainer.add(engagementButtonContainer, 1, 0);

        // make a combobox that connected to database and get list of lecture / following the type of radio
        Label selectionLabel = new Label("Select");
//        GridPane.setConstraints(selectionLabel, 0, 1);
        addVisitorContainer.add(selectionLabel, 0, 1);

        selectionBox = new ComboBox<>();
//        GridPane.setConstraints(selectionBox, 1, 1);
        addVisitorContainer.add(selectionBox, 1, 1);
        selectionBox.setMinWidth(250);
        engagementSelectionSwitch();

        Label idScannerLabel = new Label("Scan ID : ");
        addVisitorContainer.add(idScannerLabel, 0, 2);
        idScanner = new TextField();
        idScanner.setPromptText("Scan ID Tag");
//        GridPane.setConstraints(idScanner, 1, 2);
        addVisitorContainer.add(idScanner, 1, 2);
        idScanner.textProperty().addListener(e -> {
            if (DialogBox.numberOnly(idScanner) && idScanner.getText().length() >= 10) {
                if(selectionBox.getSelectionModel().getSelectedIndex() == -1) {
                    DialogBox.alertBox("Warning", "Select Lecture/Booth First");
                    Platform.runLater(() -> idScanner.clear());
                } else {
                    insertEngagement();
                }
            }
        });

        Button saveEngagement = new Button("Insert Engagement");
//        saveEngagement.setDisable(true);
//        GridPane.setConstraints(saveEngagement, 1, 3);
        addVisitorContainer.add(saveEngagement, 1, 3);
        saveEngagement.setOnAction(e -> insertEngagement());

//        body.getChildren().addAll(engagementBy, lectureEngagement, boothEngagement, selectionLabel,
//                selectionBox, idScanner, saveEngagement);
        body.add(addVisitorContainer, 0, 0);
        return body;
    }

    public void insertVisitor() {
        if (firstNameField.getText().isEmpty()) {
            DialogBox.alertBox("Warning", "First Name is Empty");
        } else if (lastNameField.getText().isEmpty()) {
            DialogBox.alertBox("Warning", "Last Name is Empty");
        } else if (contactField.getText().isEmpty()) {
            DialogBox.alertBox("Warning", "Contact is Empty");
        } else if (addressField.getText().isEmpty()) {
            DialogBox.alertBox("Warning", "Address is Empty");
        } else if (dobPicker.getEditor().getText().isEmpty()) {
            DialogBox.alertBox("Warning", "Date is Empty");
        } else if (memberIdField.getText().isEmpty()) {
            DialogBox.alertBox("Warning", "ID is Empty");
        } else if (memberIdField.getText().length() < 10) {
            DialogBox.alertBox("Warning", "ID is Too Short");
        } else {
            String selectedGender;
            if (genderGroup.getSelectedToggle() == maleRadio) {
                selectedGender = maleRadio.getText().charAt(0) + "";
            } else {
                selectedGender = femaleRadio.getText().charAt(0) + "";
            }
            try {
                cn = MySQL.connect();
                String sql = "INSERT INTO member VALUES(?,?,?,?,?,?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, memberIdField.getText());
                pst.setString(2, firstNameField.getText());
                pst.setString(3, lastNameField.getText());
                pst.setString(4, selectedGender);
                pst.setString(5, contactField.getText());
                pst.setString(6, addressField.getText());
                pst.setDate(7, Date.valueOf(dobPicker.getValue()));
                pst.setInt(8, 0);
                pst.executeUpdate();
                DialogBox.alertBox("Success", "Visitor " + firstNameField.getText() + " " +
                        lastNameField.getText() + " Successfuly added.");
                Platform.runLater(() -> {
                    firstNameField.clear();
                    lastNameField.clear();
                    maleRadio.setSelected(true);
                    contactField.clear();
                    addressField.clear();
                    dobPicker.getEditor().clear();
                });
            } catch (MySQLIntegrityConstraintViolationException e) {
                if (e.getErrorCode() == 1062) {
                    DialogBox.alertBox("Error", memberIdField.getText() + " Already Registered.");
                    Platform.runLater(() -> memberIdField.clear());
                }
            } catch (Exception e) {
                DialogBox.alertBox("Error", e + "");
            } finally {
                super.closeConnection();
            }
        }
        Platform.runLater(() -> memberIdField.clear());
    }

    public void insertEngagement() {
        String tableName;
        String boxId = "";
        if(engagementGroup.getSelectedToggle() == lectureEngagement) {
            tableName = "attend";
            if(selectionBox.getSelectionModel().getSelectedIndex() != -1) {
                boxId = lectures.get(selectionBox.getSelectionModel().getSelectedIndex()).getLectureId();
            }
        } else  {
            tableName = "engage";
            if(selectionBox.getSelectionModel().getSelectedIndex() != -1) {
                boxId = companies.get(selectionBox.getSelectionModel().getSelectedIndex()).getCompanyId();
            }
        }
        if(idScanner.getText().isEmpty()) {
            DialogBox.alertBox("Warning", "ID is Empty");
        } else if(selectionBox.getSelectionModel().getSelectedIndex() == -1) {
            if(engagementGroup.getSelectedToggle() == lectureEngagement) {
                DialogBox.alertBox("Warning", "Select Lecture First");
            } else {
                DialogBox.alertBox("Warning", "Select Booth First");
            }
        } else {
            try {
                cn = MySQL.connect();
                String sql = "INSERT INTO " + tableName + " VALUES(?,?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, idScanner.getText());
                pst.setString(2, boxId);
                pst.executeUpdate();
                DialogBox.alertBox("Success", idScanner.getText() + " Successfuly Recorded with " +
                        selectionBox.getSelectionModel().getSelectedItem());
            } catch (MySQLIntegrityConstraintViolationException e) {
                if(e.getErrorCode() == 1062) {
                    DialogBox.alertBox("Error", idScanner.getText() +
                            " already attend/engage the current booth/lecture");
                } else if(e.getErrorCode() == 1452) {
                    DialogBox.alertBox("Error", idScanner.getText() + " is not registered");
                } else {
                    DialogBox.alertBox("Error", e + " Error code : " + e.getErrorCode());
                }
            } catch(Exception e) {
                DialogBox.alertBox("Error", e + "");
            } finally {
                super.closeConnection();
            }
        }
        Platform.runLater(() -> idScanner.clear());
    }

//    @Override
    public ObservableList<Company> getCompanies() {
        companies = FXCollections.observableArrayList();
        try {
            cn = MySQL.connect();
            String sql = "SELECT * " +
                    "FROM company " +
                    "ORDER BY name";
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                Company company = new Company(rs.getString(1), rs.getString(2));
                companies.add(company);
            }
        } catch (Exception e) {
            DialogBox.alertBox("Warning", e + "");
        } finally {
            super.closeConnection();
        }
        return companies;
    }

    // this getLectures() based on the date, check the query
//    @Override
    public ObservableList<Lecture> getLectures() {
        lectures = FXCollections.observableArrayList();
        try {
            cn = MySQL.connect();
            String sql = "SELECT lecture.*, occupy.date, occupy.time, room.* " +
                    "FROM lecture, occupy, room " +
                    "WHERE (lecture.lectureId = occupy.lectureId AND occupy.roomId = room.roomId) " +
                    "AND occupy.date = ? " +
                    "ORDER BY occupy.date DESC, occupy.time ASC";
            pst = cn.prepareStatement(sql);
            pst.setDate(1, Date.valueOf(LocalDate.now()));
            rs = pst.executeQuery();

            while (rs.next()) {
                Room room = new Room(rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9));

                Lecture lecture = new Lecture(rs.getString(1),
                        rs.getString(2),
                        room,
                        rs.getDate(4),
                        rs.getTime(5),
                        rs.getInt(3));
                lectures.add(lecture);
            }
        } catch (Exception e) {
            DialogBox.alertBox("Warning", e + "");
        } finally {
            super.closeConnection();
        }
        return lectures;
    }

    // calling method to populate lectures/companies list then loop to fill the combobox
    public void engagementSelectionSwitch() {
        selectionBox.getItems().remove(0, selectionBox.getItems().size());
        if (engagementGroup.getSelectedToggle() == lectureEngagement) {
            selectionBox.setPromptText("Select Lecture");

            for(int i=0; i<lectures.size(); i++) {
                selectionBox.getItems().add(lectures.get(i).getTitle() + " - " + lectures.get(i).getRoom().getName() +
                        " - " + lectures.get(i).getTime());
            }
        } else {
            selectionBox.setPromptText("Select Booth");

            for(int i=0; i<companies.size(); i++) {
                selectionBox.getItems().add(companies.get(i).getName());
            }
        }
    }
}