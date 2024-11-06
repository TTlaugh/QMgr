package components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Group_card {
    Label group_name = new Label();
    Label group_id = new Label();
    Label date_created_label = new Label("Date created: ");
    Label date_created = new Label();
    Button archive_btn = new Button();
    Button details_btn = new Button("Details");
    Image image = new Image("resources/icon/ic-archive-20px.png");
    ImageView image_view = new ImageView(image);

    AnchorPane group_content = new AnchorPane();

    public Group_card(
            String group_name, String group_id,
            String date_created) {
        this.group_name.setText(group_name);
        this.group_id.setText(group_id);
        this.date_created.setText(date_created);
        image_view.setFitWidth(20);
        image_view.setFitHeight(20);

        archive_btn.setGraphic(image_view);

        group_content.getChildren().addAll(
                group_name);
    }
}