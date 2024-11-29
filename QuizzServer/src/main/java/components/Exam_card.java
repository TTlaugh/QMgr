package components;

import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import model.Exam;
import model.Subject;

public class Exam_card {
    final String url_archive = "/imgs/icons8-archive-24.png";
    final String url_test = "/imgs/Screenshot_2024-11-04_091448-removebg-preview.png";

    Exam exam;
    Subject subject;

    Label Exam_name = new Label();
    Label Subject_name = new Label();
    Button archive_btn = new Button();

    Button details_btn = new Button("Details");
    Image image_archive = new Image(url_archive);
    ImageView image_view_archive = new ImageView(image_archive);

    Image image_exam = new Image(url_test);
    ImageView image_view_exam = new ImageView(image_exam);

    AnchorPane exam_content = new AnchorPane();

    public AnchorPane getExam_Instance() {
        return this.exam_content;
    }

    public Exam_card(
            Exam exam, Subject subject) {

        this.exam = exam;
        this.subject = subject;
        this.Exam_name.setText(this.exam.getName());
        this.Subject_name.setText(this.subject.getSubjectName());

        image_view_archive.setFitWidth(20);
        image_view_archive.setFitHeight(20);

        image_view_exam.setFitWidth(36);
        image_view_exam.setFitHeight(36);

        archive_btn.setGraphic(image_view_archive);

        this.exam_content.getChildren().addAll(
                this.Exam_name,
                this.Subject_name,
                this.image_view_exam,
                this.archive_btn,
                this.details_btn);

        setUp();
    }

    public void setUp() {
        // Set Anchor

        this.exam_content.setPrefWidth(232);
        this.exam_content.setPrefHeight(123);
        this.exam_content.setScaleX(1);
        this.exam_content.setScaleY(1);
        this.exam_content.setScaleZ(1);
        this.exam_content.getStyleClass().add("small-pane");

        // img test
        this.image_view_exam.setLayoutX(13);
        this.image_view_exam.setLayoutY(12);
        this.image_view_exam.setNodeOrientation(javafx.geometry.NodeOrientation.LEFT_TO_RIGHT);

        // Set Exam Name
        this.Exam_name.setStyle(
                " -fx-font-size: 14px; -fx-font-weight: bold; -fx-font-family:System; ");
        this.Exam_name.setTextFill(javafx.scene.paint.Color.BLACK);
        this.Exam_name.setScaleX(1);
        this.Exam_name.setScaleY(1);
        this.Exam_name.setScaleZ(1);
        this.Exam_name.prefWidth(170);
        this.Exam_name.prefHeight(20);
        this.Exam_name.setLayoutX(53);
        this.Exam_name.setLayoutY(15);
        this.Exam_name.setWrapText(true);
        this.Exam_name.setPrefSize(150, 61);
        this.Exam_name.setTextOverrun(javafx.scene.control.OverrunStyle.ELLIPSIS);
        this.Exam_name.setGraphicTextGap(4);
        this.Exam_name.setContentDisplay(javafx.scene.control.ContentDisplay.LEFT);
        this.Exam_name.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        this.Exam_name.setWrapText(true);
        this.Exam_name.setNodeOrientation(javafx.geometry.NodeOrientation.INHERIT);
        this.Exam_name.setRotationAxis(Rotate.Z_AXIS);
        this.Exam_name.setRotate(1);

        // set Subject Name
        this.Subject_name.setStyle(
                " -fx-font-size: 12px;  -fx-font-family:System;");
        this.Subject_name.setTextFill(javafx.scene.paint.Color.BLACK);
        this.Subject_name.setScaleX(1);
        this.Subject_name.setScaleY(1);
        this.Subject_name.setScaleZ(1);
        this.Subject_name.setLayoutX(53);
        this.Subject_name.setLayoutY(33);
        this.Subject_name.prefWidth(170);
        this.Subject_name.prefHeight(21);
        this.Subject_name.setTextOverrun(javafx.scene.control.OverrunStyle.ELLIPSIS);
        this.Subject_name.setGraphicTextGap(4);
        this.Subject_name.setWrapText(true);
        this.Subject_name.setPrefSize(81, 27);
        this.Subject_name.setContentDisplay(javafx.scene.control.ContentDisplay.LEFT);
        this.Subject_name.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        this.Subject_name.setNodeOrientation(javafx.geometry.NodeOrientation.INHERIT);
        this.Subject_name.setBlendMode(javafx.scene.effect.BlendMode.SRC_OVER);
        this.Subject_name.setDepthTest(javafx.scene.DepthTest.INHERIT);
        this.Subject_name.setPickOnBounds(true);

        // Set Archive Button
        this.archive_btn.setStyle(
                "-fx-font:System 15px; -fx-text-fill: #f04438;");
        this.archive_btn.getStyleClass().add("button-donhat");
        this.archive_btn.setScaleX(1);
        this.archive_btn.setScaleY(1);
        this.archive_btn.setScaleZ(1);
        this.archive_btn.setLayoutX(126);
        this.archive_btn.setLayoutY(91);
        this.archive_btn.prefWidth(32);
        this.archive_btn.prefHeight(32);
        this.archive_btn.setBlendMode(javafx.scene.effect.BlendMode.SRC_OVER);

        // Set Details Button
        this.details_btn.getStyleClass().add("button-xanhnhat");
        this.details_btn.setStyle(
                " -fx-font:System 12px; -fx-font-weight: bold; -fx-background-radius: 10;  ");
        this.details_btn.setScaleX(1);
        this.details_btn.setScaleY(1);
        this.details_btn.setScaleZ(1);
        this.details_btn.setLayoutX(171);
        this.details_btn.setLayoutY(91);
        this.details_btn.prefWidth(65);
        this.details_btn.prefHeight(32);
        this.details_btn.setTextOverrun(javafx.scene.control.OverrunStyle.ELLIPSIS);
        this.details_btn.setContentDisplay(javafx.scene.control.ContentDisplay.LEFT);
        this.details_btn.setAlignment(javafx.geometry.Pos.CENTER);
        this.details_btn.setNodeOrientation(javafx.geometry.NodeOrientation.INHERIT);
    }

    public Button getDetails_btn() {
        return details_btn;
    }

    public Button getArchive_btn() {
        return archive_btn;
    }

    public Exam getExam() {
        return this.exam;
    }

    public Subject getSubject() {
        return this.subject;
    }

}
