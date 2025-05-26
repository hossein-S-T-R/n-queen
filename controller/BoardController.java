
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Board;
import solver.BacktrackingSolver;
import solver.GeneticSolver;
import solver.Solver;

public class BoardController {

    @FXML
    private GridPane boardGrid;

    @FXML
    private TextField sizeField;

    @FXML
    private ChoiceBox<String> algorithmChoice;

    @FXML
    private Button solveButton;

    @FXML
    public void initialize() {
        algorithmChoice.getItems().addAll("Backtracking", "Genetic");
        algorithmChoice.setValue("Backtracking");

        solveButton.setOnAction(e -> solve());
    }

    private void solve() {
        int n;
        try {
            n = Integer.parseInt(sizeField.getText());
        } catch (NumberFormatException ex) {
            showAlert("Size must be a number.");
            return;
        }

        Solver solver;

        String selectedAlgorithm = algorithmChoice.getValue();
        if (selectedAlgorithm.equals("Backtracking")) {
            solver = new BacktrackingSolver();
        } else if (selectedAlgorithm.equals("Genetic")) {
            solver = new GeneticSolver();
        } else {
            showAlert("Invalid algorithm selected.");
            return;
        }

        Board solution = solver.solve(n);
        if (solution == null) {
            showAlert("No solution found!");
        } else {
            drawBoard(solution);
        }
    }

    /*private void solve() {
        int n;
        try {
            n = Integer.parseInt(sizeField.getText());
        } catch (NumberFormatException ex) {
            showAlert("Size must be a number.");
            return;
        }

            Solver solver = algorithmChoice.getValue().equals("Backtracking")
                ? new BacktrackingSolver()
                : new GeneticSolver();

        Board solution = solver.solve(n);
        if (solution == null) {
            showAlert("No solution found!");
        } else {
            drawBoard(solution);
        }
    }*/

    private void drawBoard(Board board) {
        boardGrid.getChildren().clear();
        int size = board.getSize();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Label cell = new Label();
                cell.setMinSize(30, 30);
                cell.setStyle("-fx-border-color: black; -fx-alignment: center;");
                if (board.getQueens()[row] == col) {
                    cell.setText("♛");
                }
                boardGrid.add(cell, col, row);
            }
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}
