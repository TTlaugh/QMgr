package components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import model.Group;

public class Group_card {
        final String url = "/imgs/icons8-archive-24.png";

        private Group group;
        Label group_name = new Label();
        Label group_id = new Label();
        Label date_created_label = new Label("Date created");
        Label date_created = new Label();
        Button archive_btn = new Button();
        private Button details_btn = new Button("Details");

        AnchorPane group_content = new AnchorPane();

        public AnchorPane getGroup_Instance() {
                return this.group_content;
        }

        public Group_card(
                        Group group) {

                this.group = group;
                this.group_name.setText(group.getGroupName());
                this.group_id.setText(
                                "GROUP_ID : " + (group.getGroupId() >= 10 ? group.getGroupId()
                                                : "0" + group.getGroupId()));
                this.date_created.setText(group.getCreateDate()
                                .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                Image image = new Image(url);
                ImageView image_view = new ImageView(image);

                image_view.setFitWidth(20);
                image_view.setFitHeight(20);

                archive_btn.setGraphic(image_view);

                this.group_content.getChildren().addAll(
                                this.group_name,
                                this.group_id,
                                this.date_created_label,
                                this.date_created,
                                this.archive_btn,
                                this.details_btn);

                setUp();
        }

        public void setUp() {
                // Set Anchor
                this.group_content.setPrefWidth(232);
                this.group_content.setPrefHeight(123);
                this.group_content.setScaleX(1);
                this.group_content.setScaleY(1);
                this.group_content.setScaleZ(1);
                this.group_content.getStyleClass().add("small-pane");

                // Set Group Name
                this.group_name.setStyle(
                                " -fx-font-size: 14px; -fx-font-weight: bold; -fx-font-family:System; -fx-margin-top:12px; -fx-margin-right:12px;");
                this.group_name.setTextFill(javafx.scene.paint.Color.BLACK);
                this.group_name.setScaleX(1);
                this.group_name.setScaleY(1);
                this.group_name.setScaleZ(1);
                this.group_name.prefWidth(130);
                this.group_name.prefHeight(61);
                this.group_name.setLayoutX(17);
                this.group_name.setLayoutY(14);
                this.group_name.setWrapText(true);
                this.group_name.setPrefSize(80, 61);
                this.group_name.setTextOverrun(javafx.scene.control.OverrunStyle.ELLIPSIS);
                this.group_name.setGraphicTextGap(4);
                this.group_name.setContentDisplay(javafx.scene.control.ContentDisplay.LEFT);
                this.group_name.setAlignment(javafx.geometry.Pos.TOP_LEFT);
                this.group_name.setWrapText(true);
                this.group_name.setNodeOrientation(javafx.geometry.NodeOrientation.INHERIT);
                this.group_name.setRotationAxis(Rotate.Z_AXIS);
                this.group_name.setRotate(1);

                // set Group ID
                this.group_id.setStyle(
                                " -fx-font-size: 10px;  -fx-font-family:System; -fx-background-radius: 10; -fx-background-color: #eaecf0; -fx-padding:3 7 3 7;  -fx-margin-top:12px; -fx-margin-right:12px;");
                this.group_id.setScaleX(1);
                this.group_id.setScaleY(1);
                this.group_id.setScaleZ(1);
                this.group_id.setLayoutX(142);
                this.group_id.setLayoutY(12);
                this.group_id.prefWidth(81);
                this.group_id.prefHeight(27);
                this.group_id.setTextOverrun(javafx.scene.control.OverrunStyle.ELLIPSIS);
                this.group_id.setGraphicTextGap(4);
                this.group_id.setWrapText(true);
                this.group_id.setPrefSize(81, 27);
                this.group_id.setContentDisplay(javafx.scene.control.ContentDisplay.LEFT);
                this.group_id.setAlignment(javafx.geometry.Pos.TOP_LEFT);
                this.group_id.setNodeOrientation(javafx.geometry.NodeOrientation.INHERIT);
                this.group_id.setBlendMode(javafx.scene.effect.BlendMode.SRC_OVER);
                this.group_id.setDepthTest(javafx.scene.DepthTest.INHERIT);
                this.group_id.setPickOnBounds(true);

                // Set Date Created Label
                this.date_created_label.setStyle(
                                " -fx-font:System 15px; -fx-color:#eaecf0;");
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
                this.archive_btn.getStyleClass().add("button-donhat");
                this.archive_btn.setId("archive_btn");
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
                // this.archive_btn.setDisable(true);
                // Set Details Button
                this.details_btn.getStyleClass().add("button-xanhnhat");
                this.details_btn.setId("details_btn");
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
                // this.details_btn.setDisable(true);
        }

        public Group getGroup() {
                return group;
        }

        public Button getDetails_btn() {
                return details_btn;
        }

        public Button getArchive_btn() {
                return archive_btn;
        }

}