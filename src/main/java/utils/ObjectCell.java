package utils;

import javafx.scene.control.ListCell;

public class ObjectCell<T> extends ListCell<T> {

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null && !empty) {
            // Extract specific object properties
            
        	String info= item.toString();

            // Format and display property values
            setText(info); // Customize formatting as needed
        } else {
            setText("");
        }
    }
}