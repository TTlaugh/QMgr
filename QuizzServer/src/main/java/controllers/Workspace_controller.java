package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import model.Workspace;
import services.WorkspaceManager;
import utils.CheckTextField;
import utils.Notification;

public class Workspace_controller implements Initializable {
    // StackPane
    @FXML
    private TextField tf_PIN_StackPane;

    @FXML
    private ComboBox<Workspace> ComboBox_WorkSpace_StackPane;

    // Anchor Archive
    @FXML
    private AnchorPane archive_WorkSpace;

    @FXML
    private Label lb_WorkSpace_Name_Archive;

    @FXML
    private Label lb_WorkSpaceID_Archive;

    @FXML
    private TextField tf_WorkSpace_Name_Archive;

    @FXML
    private Label lb_Workspace_ConfirmName_Archive;

    // Anchor Rename
    @FXML
    private AnchorPane rename_WorkSpace;

    @FXML
    private Label lb_WorkSpace_Name_Rename;

    @FXML
    private Label lb_WorkSpaceID_Rename;

    @FXML
    private TextField tf_WorkSpace_Name_Rename;

    // Anchor Set Up
    @FXML
    private AnchorPane create_new_WorkSpace;

    @FXML
    private TextField tf_WorkSpaceName_SetUp;

    @FXML
    private TextField tf_PIN_SetUp;

    @FXML
    private TextField tf_Comfirm_PIN_SetUp;

    // Manager Workspace
    public static WorkspaceManager workspaceManager = WorkspaceManager.getInstance();

    private static List<Workspace> allListWorkspaces = workspaceManager.getAllWorkspace();

    public static int current_WorkSpaceID = 0;

   public static int indexComboBox = 0;

    // Function Archive

    @FXML
    void btn_archive_WorkSpace_Hidden(ActionEvent event) {

        Workspace workspace_comboBox = ComboBox_WorkSpace_StackPane.getValue();

        if (workspace_comboBox == null) {
            Notification.Error("Error", "Please select workspace!");
            return;
        }
        archive_WorkSpace.setVisible(true);

        tf_WorkSpace_Name_Archive.clear();

        Workspace workspace_Archive = workspaceManager.getWorkspace(workspace_comboBox.getWorkspaceId());

        String workSpaceID = String.valueOf(workspace_comboBox.getWorkspaceId());
        String workSpace_Name = workspace_Archive.getWorkspaceName();

        lb_WorkSpace_Name_Archive.setText(workSpace_Name);
        lb_WorkSpaceID_Archive.setText("ID : " + workSpaceID);
        lb_Workspace_ConfirmName_Archive.setText("To confirm, type \" " + workSpace_Name + " \" in the box below");

    }

    @FXML
    void btn_archive_WorkSpace(ActionEvent event) {
        Workspace workspace_comboBox = ComboBox_WorkSpace_StackPane.getValue();

        if (workspace_comboBox == null) {
            Notification.Error("Error", "Please select workspace!");
            return;
        }

        Workspace workSpace_Name_Archive = workspaceManager.getWorkspace(workspace_comboBox.getWorkspaceId());

        String workSpace_Name_Archive_String = workSpace_Name_Archive.getWorkspaceName();
        String workSpace_Name_Archive_Comfirm = tf_WorkSpace_Name_Archive.getText();

        if (!workSpace_Name_Archive_Comfirm.equalsIgnoreCase(workSpace_Name_Archive_String)) {
            Notification.Error("Error", "Workspace name does not match!");
            return;
        }

        // Func Archive workspace here
        Boolean isSuccess = workspaceManager.archiveWorkspace(workSpace_Name_Archive);

        if (!isSuccess) {
            Notification.Error("Error", "Archive workspace failed!");
            return;
        }

        Notification.Infomation("Success", "Archive workspace successfully!");

        archive_WorkSpace.setVisible(false);
    }

    // Function Set Up
    @FXML
    void btn_create_WorkSpace(ActionEvent event) {

        //
        String workSpace_Name = tf_WorkSpaceName_SetUp.getText();
        String workSpace_PIN = tf_PIN_SetUp.getText();
        String comfrimWorkSpace_PIN = tf_Comfirm_PIN_SetUp.getText();

        if (workSpace_Name.length() == 0 || workSpace_Name == null || workSpace_Name == "") {
            Notification.Error("Error", "Please enter workspace name!");
            return;
        }
        if (workSpace_Name.length() > 191) {
            Notification.Error("Error", "Workspace name is too long!");
            return;
        }

        if (workSpace_PIN.length() == 0 || workSpace_PIN == null || workSpace_PIN == "") {
            Notification.Error("Error", "Please enter workspace PIN!");
            return;
        }
        if (workSpace_PIN.length() < 6) {
            Notification.Error("Error", "Workspace PIN is 6 characters!");
            return;
        }

        if (!workSpace_PIN.equals(comfrimWorkSpace_PIN)) {
            Notification.Error("Error", "Workspace PIN does not match!");
            return;
        }

        Workspace workspace = new Workspace(0, workSpace_PIN, workSpace_Name, false);
        // Func Create new workspace here
        Boolean isSuccess = workspaceManager
                .setUpWorkspace(workspace);
        if (!isSuccess) {
            Notification.Error("Error", "Workspace name already exists!");
            return;
        }
        allListWorkspaces.add(workspace);

        Workspace workspace_add_ComboBox = workspaceManager.getWorkspace(allListWorkspaces.size());

        ComboBox_WorkSpace_StackPane.getItems().add(workspace_add_ComboBox);

        Notification.Infomation("Success", "Create new workspace successfully!");

        btn_cancel_NewWorkSpace(event);
    }

    @FXML
    void btn_setUpNewWorkSpace_Hidden(ActionEvent event) {
        create_new_WorkSpace.setVisible(true);
    }

    // Function Rename
    @FXML
    void btn_rename_WorkSpace_Hidden(ActionEvent event) {

        Workspace workspace_comboBox = ComboBox_WorkSpace_StackPane.getValue();

        if (workspace_comboBox == null) {
            Notification.Error("Error", "Please select workspace!");
            return;
        }
        rename_WorkSpace.setVisible(true);

        tf_WorkSpace_Name_Rename.clear();

        Workspace workspace_Rename = workspaceManager.getWorkspace(workspace_comboBox.getWorkspaceId());

        String workSpaceID = String.valueOf(workspace_comboBox.getWorkspaceId());
        String workSpace_Name = workspace_Rename.getWorkspaceName();

        lb_WorkSpace_Name_Rename.setText(workSpace_Name);
        lb_WorkSpaceID_Rename.setText("ID : " + workSpaceID);

    }

    @FXML
    void btn_rename_WorkSpace(ActionEvent event) {

        Workspace workspace_comboBox = ComboBox_WorkSpace_StackPane.getValue();

        if (workspace_comboBox == null) {
            Notification.Error("Error", "Please select workspace!");
            return;
        }

        Workspace workspace_Rename = workspaceManager.getWorkspace(workspace_comboBox.getWorkspaceId());

        String workSpace_Name_Replace = tf_WorkSpace_Name_Rename.getText();

        if (workSpace_Name_Replace.length() == 0 || workSpace_Name_Replace == null || workSpace_Name_Replace == "") {
            Notification.Error("Error", "Please enter workspace name!");
            return;
        }
        if (workSpace_Name_Replace.length() > 191) {
            Notification.Error("Error", "Workspace name is too long!");
            return;
        }
        workspace_Rename.setWorkspaceName(workSpace_Name_Replace);

        // Func Rename workspace here
        Boolean isSuccess = workspaceManager.renameWorkspace(workspace_Rename);

        if (!isSuccess) {
            Notification.Error("Error", "Workspace name already exists!");
            return;
        }

        ComboBox_WorkSpace_StackPane.getItems().set(indexComboBox, workspace_Rename);

        Notification.Infomation("Success", "Rename workspace successfully!");

        btn_cancel_NewWorkSpace(event);

    }

    // Function Continue
    @FXML
    void btn_continue_WorkSpace(ActionEvent event) {
        Workspace workspace_comboBox = ComboBox_WorkSpace_StackPane.getValue();

        if (workspace_comboBox == null) {
            Notification.Error("Error", "Please select workspace!");
            return;
        }
        Workspace workspace_Continue = workspaceManager.getWorkspace(workspace_comboBox.getWorkspaceId());

        if (tf_PIN_StackPane.getText().length() == 0 || tf_PIN_StackPane.getText() == null
                || tf_PIN_StackPane.getText() == "") {
            Notification.Error("Error", "Please enter PIN!");
            return;
        }

        if (!tf_PIN_StackPane.getText().equals(workspace_Continue.getPin())) {
            Notification.Error("Error", "Workspace PIN does not match!");
            return;
        }

        Parent root;
        try {
            root = (Parent) FXMLLoader.load(getClass().getResource("/ui/Screencontainer.fxml"));
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in loading workspace_controller");
        }
        current_WorkSpaceID = workspace_Continue.getWorkspaceId();

    }

    // Function shared
    @FXML
    void btn_cancel_NewWorkSpace(ActionEvent event) {
        create_new_WorkSpace.setVisible(false);
        archive_WorkSpace.setVisible(false);
        rename_WorkSpace.setVisible(false);
    }
    /*------------------------------------------------------------ */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTextField();
        loadComboBox_WorkSpace();
    }

    // set Up TextField
    void setTextField() {
        // Limit length
        int maxLength_PIN = 6;
        CheckTextField.limitCharTextField(tf_PIN_SetUp, maxLength_PIN);
        CheckTextField.limitCharTextField(tf_Comfirm_PIN_SetUp, maxLength_PIN);
        CheckTextField.limitCharTextField(tf_PIN_StackPane, maxLength_PIN);
        // set PIN not char
        CheckTextField.notCharTextField(tf_PIN_SetUp);
        CheckTextField.notCharTextField(tf_Comfirm_PIN_SetUp);
        CheckTextField.notCharTextField(tf_PIN_StackPane);
    }

    void loadComboBox_WorkSpace() {

        if (allListWorkspaces == null) {
            return;
        }
        for (Workspace workspace : allListWorkspaces) {
            ComboBox_WorkSpace_StackPane.getItems().add(workspace);
        }

        // Convert Display
        ComboBox_WorkSpace_StackPane.setConverter(new StringConverter<Workspace>() {
            @Override
            public String toString(Workspace workspace) {
                return workspace == null ? "" : workspace.getWorkspaceName();
            }

            @Override
            public Workspace fromString(String workspace) {
                return null;
            }
        });

        // set OnAction
        ComboBox_WorkSpace_StackPane.setOnAction(event -> {
            indexComboBox = ComboBox_WorkSpace_StackPane.getSelectionModel().getSelectedIndex();
        });
    }

}
