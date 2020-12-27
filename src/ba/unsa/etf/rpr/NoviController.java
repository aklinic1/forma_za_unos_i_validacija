package ba.unsa.etf.rpr;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.robot.Robot;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;


public class NoviController {

    public TextField fldIme;
    public ProgressBar progressBar;
    public BorderPane lvStudents;

    @FXML
    public void initialize(){
        fldIme.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() < 10) {
                AtomicBoolean izbacen = new AtomicBoolean(false);
                progressBar.setProgress((double) newValue.length() / 10);
                progressBar.getStyleClass().removeIf(style ->{
                    if(style.equals("zeleniProgress")) {
                        izbacen.getAndSet(true);
                        return true;
                    }
                    return false;
                });
                if(izbacen.get())
                    progressBar.getStyleClass().add("crveniProgress");

            }
            else {
                AtomicBoolean izbacen = new AtomicBoolean(false);
                progressBar.setProgress(1);
                progressBar.getStyleClass().removeIf(style ->{
                    if(style.equals("crveniProgress")) {
                        izbacen.getAndSet(true);
                        return true;
                    }
                    return false;
                });
                if(izbacen.get())
                    progressBar.getStyleClass().add("zeleniProgress");
            }
        });
    }

    public void bttnOk(ActionEvent actionEvent) throws IOException {
        if(progressBar.getProgress() < 1){
            Alert upozorenje = new Alert(Alert.AlertType.ERROR);
            upozorenje.setTitle("Neispravno ime");
            upozorenje.setHeaderText("Neispravno ime studenta");
            upozorenje.setContentText("Ime studenta mora biti najmanje 10 karaktera dugačko");
            upozorenje.setResizable(false);
            upozorenje.showAndWait();
            fldIme.requestFocus();
        }
        else {
            zatvori();
        }
    }

    public void bttnCancel(ActionEvent actionEvent) {
        zatvori();
    }
    public void zatvori(){
        Robot close = new Robot();
        close.keyPress(KeyCode.ALT);
        close.keyType(KeyCode.F4);
        close.keyRelease(KeyCode.ALT);
    }
}
