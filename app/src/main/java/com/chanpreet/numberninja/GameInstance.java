package com.chanpreet.numberninja;

import android.content.Context;
import android.graphics.Color;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import com.chanpreet.numberninja.databinding.ItemMatrixCellBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GameInstance {
    private int currentRow;

    private final MatrixBuilder.MatrixInfo matrixInfo;
    private String currentEquation = "";
    private final List<List<ItemMatrixCellBinding>> listOfBindings;
    private final Context context;
    private static final int ANIMATION_DURATION = 500;
    private final String equation;

    public GameInstance(Context context, MatrixBuilder.MatrixInfo matrixInfo, String equation) {
        this.context = context;
        this.matrixInfo = matrixInfo;
        this.equation = equation;
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
            validateCurrentEquation();
            if (Objects.equals(currentEquation, equation)) {
                Toast.makeText(context, "Correct Equation Guessed", Toast.LENGTH_SHORT).show();
            } else {
                moveToNextRow();
            }
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
        for (int j = 0; j < matrixInfo.getColumns(); j++) {
            listOfBindings.get(currentRow).get(j).parent.setRotationX(-180);
            listOfBindings.get(currentRow).get(j).parent.animate().rotationXBy(180).setDuration(ANIMATION_DURATION).setInterpolator(new AccelerateDecelerateInterpolator()).start();
        }
    }

    private void validateCurrentEquation() {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < equation.length(); i++) {
            List<Integer> list;
            if (map.containsKey(equation.charAt(i))) {
                list = map.get(equation.charAt(i));
                assert list != null;
            } else {
                list = new ArrayList<>();
            }
            list.add(i);
            map.put(equation.charAt(i), list);
        }

        for (int i = 0; i < currentEquation.length(); i++) {
            char c = currentEquation.charAt(i);
            if (map.containsKey(c)) {
                List<Integer> list = map.get(c);
                assert list != null;
                if (list.contains(i)) {
                    //EXISTS AT CORRECT LOCATION
                    listOfBindings.get(currentRow).get(i).textView.setTextColor(Color.GREEN);
                } else {
                    //EXISTS BUT AT INCORRECT LOCATION
                    listOfBindings.get(currentRow).get(i).textView.setTextColor(Color.YELLOW);
                }
            } else {
                //DOES NOT EXISTS
                listOfBindings.get(currentRow).get(i).textView.setTextColor(Color.RED);
            }
        }
    }
}
