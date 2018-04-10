package GUI;

import controlador.EEDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.StageStyle;
import modelo.pojos.Bloque;
import modelo.pojos.EE;
import modelo.pojos.Relacion;

/**
 * FXML Controller class
 * Controlador de la interfaz grafica
 * @author Daniel Escamilla Cortés
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private GridPane panel_principal;
    @FXML
    private Pane panel_agregar; //panel para agregar una nueva EE
    @FXML
    private Button boton_agregar;
    @FXML
    private Button boton_eliminar;
    @FXML
    private ChoiceBox<String> elegir_salones; //choicer para elegir el salon al agregar una clase al horario
    @FXML
    private ListView<String> lista_ee;
    @FXML
    private TextField txt_nombreEE;
    @FXML
    private TextField txt_maestroEE;
    @FXML
    private Button boton_aceptarEE;
    @FXML
    private Button boton_cancelarEE;
    @FXML
    private Text txt_salonesEE;
    @FXML
    private ComboBox<String> elegir_salonesEE; //choicer para elegir salones al agregar una nueva experiencia educativa
    
    private List<EE> ee; //experiencias educativas disponibles
    private List<Bloque> semana; //horario principal
    private List<String> salones_existentes; //lista de los salones disponibles para agregar al agregar una nueva experiencia educativa
    private List<String> salones_ee_actual = new ArrayList<>(); //lista de salones disponibles para la experiencia educativa seleccionada
    private Bloque bloque; //bloque auxiliar para relacion dia, hora, ee y salon
    
    /**
     * Carga las Experiencias educativas de la Base de datos y las muestra en la lista para seleccionar
     */
    private void cargarEE()
    {
        this.lista_ee.getItems().clear();
        this.ee = EEDAO.getAllEE();
        for (EE e:ee)
        {
            this.lista_ee.getItems().add(e.getNombre());
        }
    }
     
    /**
     * Limpia los campos del panel para agregar EE
     */
    private void limpiarPanelAgregar()
    {
        this.txt_maestroEE.clear();
        this.txt_nombreEE.clear();
        this.txt_salonesEE.setText("");
        this.salones_ee_actual.clear();
    }
    
    /**
     * Carga los paneles con listeners para el horario dentro del panel principal
     */
    private void cargarPanelesSemana()
    {
        for (int i = 0; i < 5; i++)
        {
            ColumnConstraints col = new ColumnConstraints();
            this.panel_principal.getColumnConstraints().add(col);
            for (int j = 0; j < 8; j++)
            {
                RowConstraints row = new RowConstraints();
                this.panel_principal.getRowConstraints().add(row);
                FlowPane panel = new FlowPane();
                panel.setOnMouseClicked(e -> {
                    actualizarPanel(e);
                });
                panel.setId(
                    Integer.toString(i)+","+Integer.toString(j));
                panel.setAlignment(Pos.CENTER);
                panel.setColumnHalignment(HPos.CENTER);
                panel.setRowValignment(VPos.CENTER);
                panel.setStyle("-fx-background-color: black,white; -fx-background-insets: 0,1");
                this.panel_principal.add(panel, i, j);
            }
        }
    }
     
    /**
     * Evento asignado a todos los paneles para agregar una clase al horario
     * @param e - Evento de mouse
     */
    private void actualizarPanel(MouseEvent e)
    {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.initStyle(StageStyle.TRANSPARENT);
        FlowPane fuente = (FlowPane)e.getSource();
        System.out.println(fuente.toString());
        int commaLoc = fuente.getId().indexOf(",");
        
        //Transforma el id del panel a numero para la busqueda en la base de datos
        Integer dia = Integer.parseInt(
                fuente.getId().substring(0,commaLoc));
        Integer hora = Integer.parseInt(
                fuente.getId().substring(commaLoc+1));
        System.out.println(dia);
        System.out.println(hora);
        
        //Si se da click izquierdo, se agrega una clase. Si se da click derecho, se elimina la clase
        if (e.getButton() == MouseButton.PRIMARY)
        {
            fuente.getChildren().clear();
            Integer idEE = EEDAO.obtenerIdEE(this.lista_ee.getSelectionModel().getSelectedItem());
            Integer idSalon = EEDAO.obtenerIdSalon(this.elegir_salones.getSelectionModel().getSelectedItem());
            if (idEE == null || idSalon == null)
            {                
                alerta.setTitle("Error");
                alerta.setContentText("Elija una experiencia educativa y un salon antes de agregar al horario");
                alerta.showAndWait();
            } else {
                bloque = new Bloque(dia,hora,idEE,idSalon);
                Text texto = new Text(
                    this.lista_ee.getSelectionModel().getSelectedItem());
                texto.setWrappingWidth(120);
                texto.setTextAlignment(TextAlignment.CENTER);
                fuente.getChildren().add(texto);
                Text texto2 = new Text(
                    this.elegir_salones.getSelectionModel().getSelectedItem());
                fuente.getChildren().add(texto2);
                Bloque bloque = new Bloque(dia,hora,idEE,idSalon);
                if (!EEDAO.registrarBloque(bloque))
                {
                    alerta.setTitle("Error");
                    alerta.setContentText("No se pudo registrar la materia en la hora especificada");
                    alerta.showAndWait();
                }
            }
        } else {
            fuente.getChildren().clear();
            Relacion bloque = new Relacion(dia,hora);
            if (!EEDAO.eliminar(bloque))
            {               
                alerta.setTitle("Error");
                alerta.setContentText("No se pudo eliminar clase");
                alerta.showAndWait();
            }
        }
    }
    
    
    /**
     * Funcion para el llenado de los paneles con base en los parametros de un Bloque
     * @param columna (dia)
     * @param fila (hora)
     * @param idEE - id de la experiencia educativa
     * @param idSalon - id del salon
     */
    private void actualizarPanel(Integer columna, Integer fila, Integer idEE, Integer idSalon)
    {
        String idPanel = Integer.toString(columna) + "," + Integer.toString(fila);
        Node destino = null;
        for (int i = 1; i <= this.panel_principal.getChildren().size() ; i++)
        {
            System.out.println(i);
            destino = this.panel_principal.getChildren().get(i);
            if (destino.getId().equals(idPanel))
            {
                System.out.println("Break");
                break;
            }
        }
        FlowPane panel = (FlowPane)destino;
        Text texto = new Text(EEDAO.getEE(idEE));
        texto.setWrappingWidth(120);
        texto.setTextAlignment(TextAlignment.CENTER);
        panel.getChildren().add(texto);
        Text texto2 = new Text(EEDAO.getSalon(idSalon));
        panel.getChildren().add(texto2);
    }
    /**
     * Initializes el controlador de la clase
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.panel_agregar.setVisible(false);
        this.boton_eliminar.setDisable(true);
        cargarEE();
        this.semana = EEDAO.getAllBloques();
        cargarPanelesSemana();
        for (Bloque b: this.semana)
        {
            actualizarPanel(b.getDia(),b.getHora(),b.getIdEE(),b.getIdSalon());
        }
    }    

    
    /**
     * Acciones ocurridas al presionar el boton para agregar una nueva EE
     * Se muestra el panel para agregar una nueva experiencia y se bloquean las acciones para agregar y eliminar una ee
     * @param event
     */
    @FXML
    private void presionarAgregarEE(ActionEvent event) {
        this.lista_ee.setVisible(false);
        this.panel_agregar.setVisible(true);
        this.salones_existentes = EEDAO.getAllSalones();
        this.elegir_salonesEE.setItems(FXCollections.observableArrayList(this.salones_existentes));
        this.boton_agregar.setDisable(true);
        this.boton_eliminar.setDisable(true);
        this.elegir_salones.setDisable(true);
    }

    /**
     * Acciones ocurridas al presionar el boton para eliminar una experiencia educativa
     * Se muestra un dialogo de confirmacion y se eliminan todas las clases de la experiencia educativa. Se mantienen lo salones
     * @param event 
     */
    @FXML
    private void presionarEliminarEE(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Eliminar EE");
        alert.setContentText("¿Esta seguro de eliminar esta experiencia educativa?");
        Optional<ButtonType> resultado = alert.showAndWait();
        if (resultado.get() == ButtonType.OK)
           {
               Integer idEE = EEDAO.obtenerIdEE(
                    this.lista_ee.getSelectionModel().getSelectedItem());
               if (!EEDAO.eliminarEE(idEE))
                {
                   Alert alerta = new Alert(AlertType.ERROR);
                   alerta.setTitle("Error");
                   alerta.setContentText("No se pudo eliminar la EE");
                   alerta.showAndWait(); 
                } else {
                    this.panel_principal.getChildren().clear();
                    cargarPanelesSemana();
                    cargarEE();
                    this.semana = EEDAO.getAllBloques();
                    for (Bloque b: this.semana)
                    {
                       actualizarPanel(b.getDia(),b.getHora(),b.getIdEE(),b.getIdSalon());
                    }
                    System.out.println("Eliminacion EE correcta(?)");
                }
           } else {
                alert.close();
           }
    }

    /**
     * Acciones ocurridas al presionar el boton para aceptar el agregar una nueva ee
     * Se agrega a la lista de EE disponibles y se muestra denuevo el panel de seleccion de EE. Se vuelven a activar los botones de control de EE
     * @param event 
     */
    @FXML
    private void presionarAceptarEE(ActionEvent event) {
       EE ee = new EE();
       ee.setNombre(this.txt_nombreEE.getText());
       ee.setMaestro(this.txt_maestroEE.getText());
       if (EEDAO.registrarEE(ee))
       {
           Relacion ee_salon = new Relacion();
           ee_salon.setIdEE(EEDAO.obtenerIdEE(ee.getNombre()));
           for (String s: this.salones_ee_actual)
           {
               Integer idSalon = EEDAO.obtenerIdSalon(s);
               ee_salon.setIdSalon(idSalon);
               EEDAO.registrarRelacionEE_Salon(ee_salon);
           }
           
       }
       this.panel_agregar.setVisible(false);
       this.lista_ee.setVisible(true);
       this.boton_agregar.setDisable(false);
       this.boton_eliminar.setDisable(false);
       this.elegir_salones.setDisable(false);
       cargarEE();
       limpiarPanelAgregar();
    }

    /**
     * Acciones ocurridas al presionar el boton para cancelar el agregar una nueva EE
     * Se borran los datos guardados hasta el momento y se muestra la lista de seleccion de EE nuevamente
     * @param event 
     */
    @FXML
    private void presionarCancelarEE(ActionEvent event) {
        this.panel_agregar.setVisible(false);
        this.lista_ee.setVisible(true);
        this.boton_agregar.setDisable(false);
        this.boton_eliminar.setDisable(false);
        this.elegir_salones.setDisable(false);
        cargarEE();
        limpiarPanelAgregar();
    }

    /**
     * Acciones ocurridas al presionar el boton para agregar un nuevo salon al agregar una nueva EE
     * Se agrega a la lista de los salones para la EE actual y, si no existe el salon, se guarda en la base de datos en lo salones disponibles
     * @param event 
     */
    @FXML
    private void agregarSalonEE(ActionEvent event) {
        String salon = (String)this.elegir_salonesEE.getValue();
        System.out.println(salon);
        if (!EEDAO.existeSalon(salon))
        {
            EEDAO.registrarSalon(salon);
        }
        this.salones_ee_actual.add(salon);
        this.txt_salonesEE.setText(this.txt_salonesEE.getText() + " | " + salon);
    }

    /**
     * Acciones ocurridas al presionar algun elemento de la lista de seleccion de EE valido
     * Se recuperan los salones disponibles para esa EE y se muestran como opciones en el choicer de salones
     * @param event 
     */
    @FXML
    private void presionarElementoLista(MouseEvent event) {
        String ee = this.lista_ee.getSelectionModel().getSelectedItem();
        System.out.println(ee);
        Integer id = EEDAO.obtenerIdEE(ee);
        System.out.println(id);
        if (id!=null)
        {
            this.salones_existentes = EEDAO.getAllSalones(id);
            for (String s: this.salones_existentes)
            {
                System.out.println(s);
            }
            this.elegir_salones.setItems(FXCollections.observableArrayList(this.salones_existentes));
            this.elegir_salones.setDisable(false);
            this.boton_eliminar.setDisable(false);
        }
    }
    
}
