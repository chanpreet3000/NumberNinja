package com.chanpreet.numberninja;

import android.content.Context;
import android.widget.Toast;

import com.chanpreet.numberninja.databinding.ItemMatrixCellBinding;

import java.util.List;

public class GameInstance {
    private int currentRow;

    private final MatrixBuilder.MatrixInfo matrixInfo;
    private String currentEquation = "";
    private final List<List<ItemMatrixCellBinding>> listOfBindings;
    private final Context context;

    public GameInstance(Context context, MatrixBuilder.MatrixInfo matrixInfo) {
        this.context = context;
        this.matrixInfo = matrixInfo;
        currentRow = -1;
        listOfBindings = matrixInfo.getListOfBindings();
        moveToNextRow();
    }

    public void pushCharacter(char c) {
        if (currentEquation.length() < matrixInfo.getColumns()) {
            currentEquation += c;
        }
        changeEquationVisuals();
    }


    public void removeCharacter() {
        if (currentEquation.length() > 0) {
            currentEquation = currentEquation.substring(0, currentEquation.length() - 1);
        }
        changeEquationVisuals();
    }

    private void changeEquationVisuals() {
        for (int i = 0; i < currentEquation.length(); i++) {
            listOfBindings.get(currentRow).get(i).textView.setText(String.valueOf(currentEquation.charAt(i)));
        }
        for (int i = currentEquation.length(); i < matrixInfo.getColumns(); i++) {
            listOfBindings.get(currentRow).get(i).textView.setText(null);
        }
    }

    public void CheckButtonClicked() {
        if (preCheckCondition()) {
            moveToNextRow();
        }
    }

    private boolean preCheckCondition() {
        return (currentEquation.length() == matrixInfo.getColumns());
    }


    private void moveToNextRow() {
        if (currentRow == matrixInfo.getRows() - 1) {
            Toast.makeText(context, "Game has Ended. No tries left", Toast.LENGTH_SHORT).show();
            return;
        }

        currentRow++;
        currentEquation = "";

        for (int i = 0; i < matrixInfo.getRows(); i++) {
            for (int j = 0; j < matrixInfo.getColumns(); j++) {
                listOfBindings.get(i).get(j).textView.setEnabled(i == currentRow);
            }
        }
    }
}
