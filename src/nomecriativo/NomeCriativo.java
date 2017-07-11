/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nomecriativo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Hamã Cândido
 * @author Mauricio Hiraaki Ishida
 */
public class NomeCriativo extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader load = new FXMLLoader((getClass().getResource("FXMLDocument.fxml")));
                
        Scene scene = new Scene(load.load());
        
        ((Controler.FXMLDocumentController)load.getController()).carregou(scene);
        
        stage.setScene(scene);
        stage.setTitle("Mega Mod de CG");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
