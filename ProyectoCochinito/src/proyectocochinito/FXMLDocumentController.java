/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocochinito;

import AlcanciaClases.Alcancia;
import AlcanciaClases.Propietario;
import java.awt.Color;
import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

/**
 *
 * @author javv
 */
public class FXMLDocumentController implements Initializable {

    private Alcancia mAlcancia = new Alcancia();
    float sumaActual = 0;
    /*
    current dice que moneda fue presionada;
     */
    private int current = 0;
    @FXML
    private Label label;
    @FXML
    private Button addCoin;
    @FXML
    private Label infoCurrentUpdate;
    @FXML
    private Label infoTotalUpdate;
    @FXML
    private Label propietario;
    @FXML
    private Label lbMonedaDiez;
    @FXML
    private Label lbMonedaCinco;
    @FXML
    private Label lbMonedaDos;
    @FXML
    private Label lbMonedaUno;
    @FXML
    private Label lbMonedaCents;
    @FXML
    private Label fechaDeCreacion;
    @FXML
    private Button btNuevaAlcancia;
    @FXML
    private ImageView imgMoneda1;
    @FXML
    private ImageView imgMoneda2;
    @FXML
    private ImageView imgMoneda10;
    @FXML
    private ImageView imgMoneda5;
    @FXML
    private ImageView imgMonedaCent;
    @FXML
    private ImageView romperAlcancia;

    /*
    Contadores para cada moneda utiles para saber cuantos clics se le ha dado
    a cada una.
     */
    private int contadorMoneda10 = 0;
    private int contadorMoneda5 = 0;
    private int contadorMoneda2 = 0;
    private int contadorMoneda1 = 0;
    private int contadorMonedaCent = 0;
    @FXML
    private StackPane stkMoneda10;
    @FXML
    private StackPane stkMoneda5;
    @FXML
    private StackPane stkMoneda2;
    @FXML
    private StackPane stkMoneda1;
    @FXML
    private StackPane stkMonedaCent;

    final int BORDE = 5;
    Paint color = Paint.valueOf("#DF0101");
    private int globalCounter = 0;
    @FXML
    private Pane pnAlcancia;
    @FXML
    private ImageView imgViewCochinito;
    @FXML
    private StackPane stkMartillo;
    @FXML
    private AnchorPane panelPrincipal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        String fileAdress = "alcancia.txt";

        File mFile = new File(fileAdress);
        if (mFile.exists()) {
            Alcancia mAlcancia = new Alcancia();
            actualizarInterfaz();
            btNuevaAlcancia.setDisable(true);
            addCoin.setDisable(true);
            infoCurrentUpdate.setText("No se ha agregado nada a la alcancía");
            color = Paint.valueOf("#0404B4");//decode("#DF0101");
            BorderStroke borderStroke;
            borderStroke = new BorderStroke(color, BorderStrokeStyle.SOLID,
                    null, new BorderWidths(BORDE));
            stkMartillo.setBorder(new Border(borderStroke));
        } else {
            addCoin.setDisable(true);
            btNuevaAlcancia.setDisable(false);
            infoCurrentUpdate.setText("No se ha creado alguna alcancía");
        }
        sumaActual = 0;

    }

    /**
     * Controla las acciones del boton "Nueva Alcancia". -Advierte que
     * cualquieranterior se perdera. -Recopila informacion del propietario
     */
    @FXML
    private void handleNuevaAlcancia(ActionEvent event) {

        String nombre = null;
        String apellido = null;
        contadorMoneda10 = 0;
        contadorMoneda5 = 0;
        contadorMoneda2 = 0;
        contadorMoneda1 = 0;
        contadorMonedaCent = 0;
        // Pattern mPattern = new Pattern("[a-zA-Z]*");
        /*
            Obteniendo la fecha del sistema y dandole formato
         */
        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(date);

        /*
        Aviso de advertencia informandole al usuario que se borraran todos
        los datos guardados para la creacion de una alcancia nueva
         */
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Sistema");
        alert.setHeaderText("ADVERTENCIA! ");
        alert.setContentText("Se creará una Alcancía nueva");

        Optional<ButtonType> result1 = alert.showAndWait();
        if (result1.get() == ButtonType.OK) {

            if (mAlcancia == null) {
                mAlcancia = new Alcancia();
                Image image = new Image(getClass().getResource("img/pig.png").toExternalForm());
                // imgViewCochinito.setImage(null);
                imgViewCochinito.setImage(image);
            }

            /*
            Recopilando Datos del Propietario por medio de un cuadro de
            dialogo
             */
            //Colectando nombre
            TextInputDialog dialog = new TextInputDialog("Ejemplo: Juan");
            dialog.setTitle("Sistema");
            dialog.setHeaderText("RECOPILACION DE INFORMACION");
            dialog.setContentText("Ingrese su nombre: ");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();

            while (result.toString().matches("[a-zA-Z]*")) {

                // Traditional way to get the response value.
                result = dialog.showAndWait();
            }
            if (result.isPresent()) {
                // System.out.println("Your name: " + result.get());
                nombre = result.get();
            }

            //Colectando apellido
            TextInputDialog dialog2 = new TextInputDialog("Ejemplo: Gomez");
            dialog2.setTitle("Sistema");
            dialog2.setHeaderText("RECOPILACION DE INFORMACION");
            dialog2.setContentText("Ingrese su primer Apellido: ");

            // Traditional way to get the response value.
            result = dialog2.showAndWait();
            while (result.toString().matches("[a-zA-Z]*")) {

                // Traditional way to get the response value.
                result = dialog.showAndWait();
            }

            if (result.isPresent()) {
                // System.out.println("Your name: " + result.get());
                apellido = result.get();
            }

            /*
            Creando una nueva Alcancia con la fecha,nombre y apellido
            recopilado.
             */
            Propietario newPropietario = new Propietario(nombre, apellido);
            Alcancia newAlcancia = new Alcancia(newPropietario, today);
            System.out.println(today);
            /*
            Escribiendo la nueva alcancia en el archivo. Esto borrara la
            anterior.
             */
            newAlcancia.salvarAlcancia();
            actualizarInterfaz();
            sumaActual = 0;
            infoCurrentUpdate.setText("No se ha agregado nada a la alcancía");
            btNuevaAlcancia.setDisable(true);

            color = Paint.valueOf("#0404B4");//decode("#DF0101");
            BorderStroke borderStroke;
            borderStroke = new BorderStroke(color, BorderStrokeStyle.SOLID,
                    null, new BorderWidths(BORDE));
            stkMartillo.setBorder(new Border(borderStroke));

        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    /*
    Los handlers para las monedas todos se manejan igual. Primero se le asigna
    un borde a la moneda seleccionada luego se pilla un click para incrementar
    al contador de esa moneda y luego se desactiva el onMouseClicked
    para dejar de escuchar clicks
     */
    @FXML
    private void handle1Coin(MouseEvent event) {

        // imgMoneda1.set
        selectCoin(stkMoneda1);
        current = 1;
        if (event.getClickCount() == 1 && globalCounter == 0) {

            globalCounter++;

            try {
                mAlcancia.setTotalUno(mAlcancia.getTotalUno() + 1);
            } catch (NullPointerException e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Sistema");
                alert.setHeaderText("ADVERTENCIA ! ");
                alert.setContentText("NO existe ninguna alcancía, intente crear"
                        + "una nueva");

                alert.showAndWait();
            }

            addCoin.setDisable(false);
        }

    }

    @FXML
    private void handle2Coin(MouseEvent event) {

        selectCoin(stkMoneda2);
        current = 2;
        if (event.getClickCount() == 1 && globalCounter == 0) {

            globalCounter++;

            try {
                mAlcancia.setTotalDos(mAlcancia.getTotalDos() + 1);
            } catch (NullPointerException e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Sistema");
                alert.setHeaderText("ADVERTENCIA ! ");
                alert.setContentText("NO existe ninguna alcancía, intente crear"
                        + "una nueva");

                alert.showAndWait();
            }
            addCoin.setDisable(false);
        }

    }

    @FXML
    private void handle10Coin(MouseEvent event) {

        // imgMoneda1.set
        /*
        Sistema de cambios de color de borde para la moneda seleccionada
         */
        selectCoin(stkMoneda10);
        current = 10;
        if ((event.getClickCount() == 1 && globalCounter == 0) || stkMoneda10.getBorder() != null) {

            globalCounter++;
            try {
                mAlcancia.setTotalDiez(mAlcancia.getTotalDiez() + 1);
            } catch (NullPointerException e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Sistema");
                alert.setHeaderText("ADVERTENCIA ! ");
                alert.setContentText("NO existe ninguna alcancía, intente crear"
                        + "una nueva");

                alert.showAndWait();
            }

        }
        addCoin.setDisable(false);

        System.out.println(contadorMoneda10);
    }

    @FXML
    private void handle5Coin(MouseEvent event) {

        selectCoin(stkMoneda5);
        current = 5;
        if (event.getClickCount() == 1 && globalCounter == 0) {

            globalCounter++;
            try {
                mAlcancia.setTotalCinco(mAlcancia.getTotalCinco() + 1);
            } catch (NullPointerException e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Sistema");
                alert.setHeaderText("ADVERTENCIA ! ");
                alert.setContentText("NO existe ninguna alcancía, intente crear"
                        + "una nueva");

                alert.showAndWait();
            }

        }
        addCoin.setDisable(false);
    }

    @FXML
    private void handleCentCoin(MouseEvent event) {

        selectCoin(stkMonedaCent);
        current = 3; //si current es 3 entonces el usuario metio 50 cents
        if (event.getClickCount() == 1 && globalCounter == 0) {

            globalCounter++;
            try {
                mAlcancia.setTotalCincuentaCentavos(mAlcancia.getTotalCincuentaCentavos() + 1);
            } catch (NullPointerException e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Sistema");
                alert.setHeaderText("ADVERTENCIA ! ");
                alert.setContentText("NO existe ninguna alcancía, intente crear"
                        + "una nueva");

                alert.showAndWait();
            }

        }
        addCoin.setDisable(false);
    }

    @FXML
    private void handleRomperAlcancia(MouseEvent event) {
        /*
        Confirmamos que el Propietario quiere romper su alcancía
         */
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Sistema");
        alert.setHeaderText("ADVERTENCIA!");
        alert.setContentText("¿Esta seguro que quiere romper su alcancia?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            Image image = new Image(getClass().getResource("rota.png").toExternalForm());
            // imgViewCochinito.setImage(null);
            imgViewCochinito.setImage(image);
            mAlcancia.borrarAlcancia();
            mAlcancia = null;
            btNuevaAlcancia.setDisable(false);
            stkMartillo.setBorder(null);

        } else {
            // ... user chose CANCEL or closed the dialog
        }

        //imgViewCochinito.setFitWidth(100);
    }

    /**
     * Actualiza la interfaz de usuario con los valores del archivo alcancia.txt
     */
    private void actualizarInterfaz() {
        // Alcancia mAlcancia = new Alcancia();
        mAlcancia.leerAlcancia();
        propietario.setText("Propietario: "
                + mAlcancia.getPropietario().getNombre() + " "
                + mAlcancia.getPropietario().getApellido());
        fechaDeCreacion.setText("Fecha de creación "
                + mAlcancia.getFechaDeCreacion());
        lbMonedaDiez.setText(Integer.toString(mAlcancia.getTotalDiez()));
        lbMonedaCinco.setText(Integer.toString(mAlcancia.getTotalCinco()));
        lbMonedaDos.setText(Integer.toString(mAlcancia.getTotalDos()));
        lbMonedaUno.setText(Integer.toString(mAlcancia.getTotalUno()));
        lbMonedaCents.setText(Integer.toString(
                mAlcancia.getTotalCincuentaCentavos()));
        infoTotalUpdate.setText("En la alcancia hay $"
                + Float.toString((float) mAlcancia.calculaTotal()));
    }

    /**
     * Le da un borde a la moneda seleccionada.
     *
     * @param selected
     */
    private void selectCoin(StackPane selected) {

        stkMoneda10.setBorder(null);
        stkMoneda5.setBorder(null);
        stkMoneda2.setBorder(null);
        stkMoneda1.setBorder(null);
        stkMonedaCent.setBorder(null);

        color = Paint.valueOf("#DF0101");//decode("#DF0101");
        BorderStroke borderStroke;
        borderStroke = new BorderStroke(color, BorderStrokeStyle.SOLID,
                null, new BorderWidths(BORDE));
        selected.setBorder(new Border(borderStroke));
    }

    @FXML
    private void handleAddCoin(ActionEvent event) {

        if (mAlcancia != null) {
            switch (current) {
                case 1:
                    contadorMoneda1++;
                    break;
                case 2:
                    contadorMoneda2++;
                    break;
                case 3:
                    contadorMonedaCent++;
                    break;
                case 5:
                    contadorMoneda5++;
                    break;
                case 10:
                    contadorMoneda10++;
                    break;
                default:
                    break;
            }
            sumaActual = (float) (contadorMoneda10 * 10 + contadorMoneda5 * 5 + contadorMoneda2 * 2 + contadorMoneda1 + contadorMonedaCent * 0.5);
            infoCurrentUpdate.setText("Se le agregaron $" + Float.toString(sumaActual) + " a la alcancía");

            infoTotalUpdate.setText("En la alcancia hay $" + Float.toString(mAlcancia.calculaTotal()));
            mAlcancia.salvarAlcancia();
            actualizarInterfaz();
            System.out.println(mAlcancia.calculaTotal());
            globalCounter = 0;

            addCoin.setDisable(true);
            /*
        se le quita el borde a todos las monedas para que se vuelva a elegir
        una
             */

            stkMoneda10.setBorder(null);
            stkMoneda5.setBorder(null);
            stkMoneda2.setBorder(null);
            stkMoneda1.setBorder(null);
            stkMonedaCent.setBorder(null);
            /*
        Activando el onMouseClicked para cada moneda denuevo
             */
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sistema");
            alert.setHeaderText("ADVERTENCIA! ");
            alert.setContentText("NO existe ninguna alcancía, intente crear"
                    + "una nueva");

            alert.showAndWait();
        }
    }

}
