package mycalculator;

/**
 * Created by zhangs on 1/27/2019.
 * @Copyright Dr. S. Zhang
 * Modification Permission given to SUNY Oneonta CSCI 311/324 students Only
 */


public class mycalculator {
    static CalculatorModel model;
    static CalculatorView view;
    static CalculatorController controller;

    public static void main(String[] args) {
        model= new CalculatorModel();
        view = new CalculatorView();
        controller = new CalculatorController(model, view);

    }

}

/*
* This is the starter code made by Dr. S. Zhang for his CSCI 311 students.
* Using MVC design pattern and finite state machine
* What you need to do
* Test the current solution, find any bugs (comparing with standard calculator)
* Fix the bugs
* extend the calculator to include 4,5,6,7,8,9, *, / buttons and CE button
* Make sure if divisor is zero, the result display should show warning and not proceed.
* When CE button is clicked, the calculator is ready to start over.
* You may end up with 5 rows, with CE to be the only button on the fifth row.
* */