package components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;

public class Exam_card {
    final String url_archive = "/ui/imgs/icons8-archive-24.png";
    Label Exam_name = new Label();
    Label Subject_name = new Label();
    Label date_created_label = new Label("Date created");
    Label date_created = new Label();
    Button archive_btn = new Button();

    private Button details_btn = new Button("Details");
    Image image_archive = new Image(url_archive);
    ImageView image_view_archive = new ImageView(image_archive);

    Image image_exam = new Image(url_archive);
    ImageView image_view_exam = new ImageView(image_exam);

    AnchorPane exam_content = new AnchorPane();

    public AnchorPane getGroup_Instance() {
        return this.exam_content;
    }

    public Exam_card(
            String Exam_name, String Subject_name,
            String date_created) {
        this.Exam_name.setText(Exam_name);
        this.Subject_name.setText(Subject_name);
        this.date_created.setText(date_created);
        image_view_archive.setFitWidth(20);
        image_view_archive.setFitHeight(20);

        image_view_exam.setFitWidth(36);
        image_view_exam.setFitHeight(36);

        archive_btn.setGraphic(image_view_archive);

        this.exam_content.getChildren().addAll(
                this.Exam_name,
                this.Subject_name,
                this.date_created_label,
                this.date_created,
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

        // Set Group Name
        this.Exam_name.setStyle(
                " -fx-font-size: 14px; -fx-font-weight: bold; -fx-font-family:System; -fx-margin-top:12px; -fx-margin-right:12px;");
        this.Exam_name.setTextFill(javafx.scene.paint.Color.BLACK);
        this.Exam_name.setScaleX(1);
        this.Exam_name.setScaleY(1);
        this.Exam_name.setScaleZ(1);
        this.Exam_name.prefWidth(130);
        this.Exam_name.prefHeight(61);
        this.Exam_name.setLayoutX(17);
        this.Exam_name.setLayoutY(14);
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

        // set Group ID
        this.Subject_name.setStyle(
                "-fx-margin: 102px 12px 0px 0px; -fx-font-size: 12px;  -fx-font-family:System; -fx-background-radius: 10; -fx-background-color: #eaecf0; -fx-padding:3 7 3 7;  -fx-margin-top:12px; -fx-margin-right:12px;");
        this.Subject_name.setScaleX(1);
        this.Subject_name.setScaleY(1);
        this.Subject_name.setScaleZ(1);
        this.Subject_name.setLayoutX(142);
        this.Subject_name.setLayoutY(12);
        this.Subject_name.prefWidth(81);
        this.Subject_name.prefHeight(27);
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

        // Set Date Created Label
        this.date_created_label.setStyle(
                "-fx-margin:0 0 31px 12px; -fx-font:System 15px; -fx-color:#eaecf0;");
        this.date_created_label.setScaleX(1);
        this.date_created_label.setScaleY(1);
        this.date_created_label.setScaleZ(1);
        this.date_created_label.prefWidth(130);
        this.date_created_label.prefHeight(61);
        this.date_created_label.setLayoutX(16);
        this.date_created_label.setLayoutY(91);
        this.date_created_label.prefWidth(85);
        this.date_created_label.prefHeight(21);
        this.date_created_label.setTextOverrun(javafx.scene.control.OverrunStyle.ELLIPSIS);
        this.date_created_label.setGraphicTextGap(4);
        this.date_created_label.setContentDisplay(javafx.scene.control.ContentDisplay.LEFT);
        this.date_created_label.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        this.date_created_label.setNodeOrientation(javafx.geometry.NodeOrientation.INHERIT);

        // Set Date Created
        this.date_created.setStyle(
                "-fx-margin-bottom:12px; -fx-margin-left:12px; -fx-font-size: 12px; -fx-font-weight: bold; -fx-font-family:System;  ");
        this.date_created.setTextFill(javafx.scene.paint.Color.BLACK);
        this.date_created.setScaleX(1);
        this.date_created.setScaleY(1);
        this.date_created.setScaleZ(1);
        this.date_created.setLayoutX(17);
        this.date_created.setLayoutY(109);
        this.date_created.prefWidth(17);
        this.date_created.prefHeight(109);
        this.date_created.setTextOverrun(javafx.scene.control.OverrunStyle.ELLIPSIS);
        this.date_created.setGraphicTextGap(4);

        this.date_created.setContentDisplay(javafx.scene.control.ContentDisplay.LEFT);
        this.date_created.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        this.date_created.setNodeOrientation(javafx.geometry.NodeOrientation.INHERIT);

        // Set Archive Button
        this.archive_btn.setStyle(
                "-fx-font:System 15px; -fx-text-fill: #f04438;");
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

    public void click_Button_Archive(StackPane stackPane) {
        archive_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stackPane.setVisible(true);
            }
        });
    }

}
