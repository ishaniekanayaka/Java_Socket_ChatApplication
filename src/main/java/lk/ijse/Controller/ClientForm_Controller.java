package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientForm_Controller {

    @FXML
    private JFXButton btnSend;

    @FXML
    private TextArea clientTextArea;

    @FXML
    private TextField clientTextField;

    @FXML
    private AnchorPane rootClient;

    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String msg= "";

    public void initialize(){
        new Thread(()->{
            try {
                socket = new Socket("localhost", 3000);

            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream= new DataOutputStream(socket.getOutputStream());
            clientTextArea.appendText("Client started. \n");

            while (!socket.isClosed()){
                msg = dataInputStream.readUTF();
                clientTextArea.appendText("Server: "+ msg + "\n");
            }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }


    @FXML
    void btnSendOnAction(ActionEvent event) throws IOException {
        String text = clientTextField.getText();
            clientTextArea.appendText("you: "+ text + "\n");
            dataOutputStream.writeUTF(text);
            dataOutputStream.flush();
            clientTextField.clear();
    }


}
