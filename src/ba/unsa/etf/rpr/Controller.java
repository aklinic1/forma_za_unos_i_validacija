package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Controller {
    
    public TextField fldText;
    public ChoiceBox choiceColor;
    public Button unosBttn;
    public Slider sliderStudents;
    public ListView lvStudents;
    private ObservableList<String> listaIzbora = FXCollections.observableArrayList();
    private ModelPopuni model = new ModelPopuni();

    public ListView getLvStudents() {
        return lvStudents;
    }

    public Controller(){
        listaIzbora.add("Default");
        listaIzbora.add("Crvena");
        listaIzbora.add("Zelena");
        listaIzbora.add("Plava");
    }
    @FXML
    public void initialize(){
        choiceColor.setItems(listaIzbora);
        model.popuni((int)sliderStudents.getMin(), "");
        lvStudents.setItems(model.getListaSvihStudenata());
        sliderStudents.valueProperty().addListener((observable, oldvalue, newvalue) ->{
            model.popuni(newvalue.intValue(), fldText.getText());
            lvStudents.setItems(model.getListaSvihStudenata());
        });
    }

    public void bttnNumberClick(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        fldText.setText(fldText.getText() + button.getText());
    }

    public void bttnUnesiStudenta(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/novi.fxml"));
        Parent root = loader.load();
        stage.setTitle("Unos studenta");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
        stage.setResizable(false);
        stage.setOnCloseRequest(s -> {
            TextField txt = (TextField) ((Stage) s.getSource()).getScene().getRoot().lookup("#fldIme");
            model.dodaj(txt.getText());
            lvStudents.setItems(model.getListaSvihStudenata());
        });
        stage.show();
    }

    public void choiceAction(ActionEvent actionEvent) {
        Scene scene = ((Node) actionEvent.getSource()).getScene();
        scene.getStylesheets().removeAll();
        if(choiceColor.getValue().equals("Crvena"))
            scene.getStylesheets().add("/css/redstyle.css");
        else if(choiceColor.getValue().equals("Plava"))
            scene.getStylesheets().add("/css/bluestyle.css");
        else if(choiceColor.getValue().equals("Zelena"))
            scene.getStylesheets().add("/css/greenstyle.css");
        else {
            scene.getStylesheets().removeIf(style -> {
                if(style.equals("/css/redstyle.css") || style.equals("/css/bluestyle.css") ||
                        style.equals("/css/greenstyle.css"))
                    return true;
                return false;
            });
        }
    }
}
