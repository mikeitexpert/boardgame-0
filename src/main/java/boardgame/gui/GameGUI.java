package boardgame.gui;

import boardgame.engine.FullhouseCell;
import boardgame.engine.FullhouseModel;
import boardgame.engine.FullhousePuzzels;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class GameGUI extends Application {
    private Parent sceneRoot;
    private Stage primaryStage;
    private FullhouseModel m_fullhouseModel;
    private Label m_labelGrid [][];
    private Button m_resetBtn;
    private ComboBox<PuzzleItem> m_puzzleCombo;
    @Override
    public void start(Stage primaryStage) throws Exception{

        // load the fxml file
        //sceneRoot = FXMLLoader.load(getClass().getResource("../../../../../../../../fullhouse-javafx-master (1)/fullhouse-javafx-master/fullhouse/gui/sample.fxml"));
        primaryStage.setTitle("Fullhouse");
        this.primaryStage = primaryStage;

        // init GUI
        initGUI( 0 );

        // show scene
        primaryStage.setScene(new Scene(sceneRoot, 300, 275));
        primaryStage.show();
    }

    private void initGUI(int i) {
        // add items puzzle levels combo
        sceneRoot = new VBox();
        VBox mainVBox = (VBox) sceneRoot;

        HBox buttonHBox = new HBox(); // (HBox) mainVBox.getChildren().get(1);
        mainVBox.getChildren().add(buttonHBox);

        m_puzzleCombo = new ComboBox<PuzzleItem>(); // (ComboBox) buttonHBox.getChildren().get(0);
        PuzzleItem firstItem = null;
        for(int j = 0; j < FullhousePuzzels.puzzels.length; j++) {
            if (j == 0){
                firstItem = new PuzzleItem(j);
                m_puzzleCombo.getItems().add( firstItem );
            } else {
                m_puzzleCombo.getItems().add(new PuzzleItem(j));
            }
        }
        buttonHBox.getChildren().add(m_puzzleCombo);


        // select first itme
        m_puzzleCombo.getSelectionModel().select( firstItem );


        m_puzzleCombo.valueProperty().addListener((obs, oldItem, newItem) -> {
            PuzzleItem newPuzzleItem = (PuzzleItem) newItem;
            resetGrid(newPuzzleItem);
        });

        // set puzzle combo action listener
        primaryStage.setMaxHeight(primaryStage.getMinHeight());
        primaryStage.setMaxWidth(primaryStage.getMinWidth());

        m_resetBtn = new Button("Restart"); //(Button) buttonHBox.getChildren().get(1);
        buttonHBox.getChildren().add(m_resetBtn);
        //m_resetBtn.setText("Reset");
        m_resetBtn.setOnAction(ae ->{
            resetGrid((PuzzleItem) m_puzzleCombo.getSelectionModel().getSelectedItem());
        });

        Button helpBtn = new Button("Help");// (Button) buttonHBox.getChildren().get(2);
        buttonHBox.getChildren().add(helpBtn);
        helpBtn.setOnAction(ae -> {
            String helpMsg = "This puzzle was invented in 2004 by Erich Friedman (www.stetson.edu/~efriedma)."+
                    "\nFirst click on an empty square to mark your starting position. After that click on"+
                    "\na square to move the ball in horizontal or vertical direction. A game is solved,"+
                    "\nwhen no square on the grid remains empty."+
                    "\nGood luck!";
            showMessage("Fullhouse help...", helpMsg, AlertType.INFORMATION);
        });

        // reset board grid
        resetGrid( new PuzzleItem( 0) );

    }

    private static void showMessage(String title, String msg, AlertType information) {
        Alert alert = new Alert(information);
        alert.setTitle(title);
        alert.setHeaderText(msg);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }


    private void resetGrid(PuzzleItem puzzleItem) {
        VBox mainVBox = (VBox) sceneRoot;

        // create board model
        m_fullhouseModel = new FullhouseModel(puzzleItem.getLevel());
        int gridSize = m_fullhouseModel.getSize();

        // set main window size
        int cellSiz = 60;
        primaryStage.setMinWidth(gridSize*cellSiz + 20);
        primaryStage.setMinHeight(gridSize*cellSiz + 120);

        // draw Fullhouse Board
        GridPane boardGrid = new GridPane();
        boardGrid.setMaxWidth(Double.MAX_VALUE);

        double gridCellSize = primaryStage.getMinWidth() / gridSize;
        m_labelGrid = new Label[gridSize][gridSize];

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                Label label = new Label(row +" " + col);
                m_labelGrid[row][col] = label;
                label.setMinWidth( cellSiz );
                label.setMinHeight( cellSiz );

                label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Node source = (Node)event.getSource() ;
                        Integer colIndex = GridPane.getColumnIndex(source);
                        Integer rowIndex = GridPane.getRowIndex(source);
                        System.out.printf("Mouse entered cell [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());
                        if (m_fullhouseModel.isInitPointSet()) {
                            m_fullhouseModel.move(rowIndex.intValue(), colIndex.intValue());
                        } else {
                            m_fullhouseModel.setInitPoint(rowIndex.intValue(), colIndex.intValue());
                        }
                        updateFullhouseGrid();
                    }
                });

                String filename = getFilename( m_fullhouseModel.getCellType(row, col) );

                label.setStyle("-fx-background-image: url('boardgame/resources/"+ filename +"'); " +
                        "-fx-background-position: center center; " +
                        "-fx-background-repeat: stretch;");
                boardGrid.add(label, col, row);
            }
        }


        // add the board grid to the main scene
        if (mainVBox.getChildren().size() > 1){
            mainVBox.getChildren().remove(0);
        }
        mainVBox.getChildren().add(0, boardGrid);


    }

    private static String getFilename(FullhouseCell cellType) {
        String filename = "";
        switch (cellType){
            case Block:
                filename = "block.png";
                break;
            case Cursor:
                filename = "cursor.png";
                break;
            case Up:
                filename = "up.png";
                break;
            case Down:
                filename = "down.png";
                break;
            case Left:
                filename = "left.png";
                break;
            case Right:
                filename = "right.png";
                break;
            case Free:
                filename = "free.png";
                break;
            default:
        }
        return filename;
    }

    private void updateFullhouseGrid() {
        for(int row = 0; row < m_fullhouseModel.getSize(); row++){
            for(int col = 0; col < m_fullhouseModel.getSize(); col++){
                m_labelGrid[row][col].getStylesheets().clear();

                m_labelGrid[row][col].setStyle("-fx-background-image: url('boardgame/resources/"+
                        getFilename(m_fullhouseModel.getCellType(row, col) )+"'); " +
                        "-fx-background-position: center center; " +
                        "-fx-background-repeat: stretch;");
            }
        }

        if (!m_fullhouseModel.canMoveAnyMore()){
            // if winner show congrat and move to next level
            if(m_fullhouseModel.isSuccessfullyComplete()){
                showMessage("Fullhouse ...", "Congratulations you won!", AlertType.INFORMATION);
                PuzzleItem pitem = m_puzzleCombo.getSelectionModel().getSelectedItem();
                int nextLevelIndex = pitem.getLevel()+1;
                if (nextLevelIndex < m_puzzleCombo.getItems().size()){
                    PuzzleItem nextLevel = m_puzzleCombo.getItems().get(nextLevelIndex);
                    m_puzzleCombo.getSelectionModel().select(nextLevel);
                    resetGrid(nextLevel);
                }
            }
            // else show game over
//            else {
//                showMessage("Fullhouse ...", "Game over!", AlertType.ERROR);
//                PuzzleItem pitem = m_puzzleCombo.getSelectionModel().getSelectedItem();
//                resetGrid(pitem);
//            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    class PuzzleItem{
        int m_level;
        PuzzleItem(int level){
            this.m_level = level;
        }

        @Override
        public String toString() {
            return "Puzzle " + (m_level + 1);
        }

        public int getLevel(){
            return m_level;
        }
    }
}
