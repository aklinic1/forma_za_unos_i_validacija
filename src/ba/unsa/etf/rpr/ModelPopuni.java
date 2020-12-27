package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelPopuni {
    private  ObservableList<String> listaSvihStudenata = FXCollections.observableArrayList();

    public ModelPopuni() {}

    public void popuni(int brojMjesta, String textFieldValue){

        listaSvihStudenata.clear();
        int i;
        for(i = 0; i < brojMjesta - 1; i++)
            listaSvihStudenata.add("Student" + (i+1));
        if(brojMjesta == 15 && textFieldValue.equals("")) listaSvihStudenata.add("Student");
        else if(textFieldValue.equals("")) listaSvihStudenata.add("Student" + (i + 1));
        else{
            listaSvihStudenata.add("Student" + textFieldValue);
        }
    }

    public ObservableList<String> getListaSvihStudenata() {
        return listaSvihStudenata;
    }

    public void setListaSvihStudenata(ObservableList<String> listaSvihStudenata) {
        this.listaSvihStudenata = listaSvihStudenata;
    }
    public void dodaj(String novi){
        listaSvihStudenata.add(novi);
    }
}
