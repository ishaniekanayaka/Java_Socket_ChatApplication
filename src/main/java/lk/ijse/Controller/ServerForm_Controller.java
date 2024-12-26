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
import java.net.ServerSocket;
import java.net.Socket;

public class ServerForm_Controller {

    @FXML
    private JFXButton btnSend;

    @FXML
    private TextArea clientTextArea;

    @FXML
    private TextField clientTextField;

    String msg = "";

    @FXML
    private AnchorPane rootClient;

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public void initialize(){
        new Thread(()-> {
            try {
                ServerSocket serverSocket = new ServerSocket(3000);
                clientTextArea.appendText("Server started. \n");
                socket = serverSocket.accept();
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while (!socket.isClosed()){
                    msg = dataInputStream.readUTF();
                    clientTextArea.appendText("Client: " + msg + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }

    @FXML
    void btnSendOnAction(ActionEvent event) throws IOException {
        String message = clientTextField.getText();
        clientTextArea.appendText("you: " + message + "\n");

        dataOutputStream.writeUTF(message);
        dataOutputStream.flush();
        clientTextField.clear();
    }
}
