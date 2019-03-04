package mycalculator;


/**
 * Created by zhangs on 1/27/2019.
 * @Copyright Dr. S. Zhang
 * Modification Permission given to SUNY Oneonta CSCI 311/324 students Only
 */

public class CalculatorModel {
        private String resultstr;
        private String inputstr;
        private String opls;
        private String oprs;
        private double result;
        private int state;
        private char currentoperation;

        /*

         My sample is not exactly the same as the standard calculator, but close.
         You can try to come up a solution that works exactly the same as the Windows Standard Calculator.
          States
          1: ready for left operand
          2: integer left operand
          3: float left operand
          4: ready for right operand
          5: integer right operand
          6: float right operand
          7: Divide by Zero

          Input signals:
          0-9 +-/*= CE and C

          State variables and extra varaibles holding operand strings and operand numbers and operator
          Transition Table:
          state 1: ready for left operand, result initialize to 0
          stay at 1 and does nothing , if input is = or 0 or CE
          transit to state 2, set left operand string to the digit, update result and formula, if input is from 1 to 9.
          transit to state 3, the left float string is 0, if input is .
          transit to state 4, save the operator, if input is +-/*

          state 2: integer left operand
          stay at 2 and does nothing, if input is =
          tansit to state 1, if  input is CE
          stay at 2 and keep appending to left operand string, if input is 0 to 9
          transit to state 3, append . to left operand string, if input is .
          transit to state 4 and save the operator, if input is +-/*

          state 3: double left operand
          stay at state 3 and does nothing , if input is = or .
          tansit to state 1, if input is CE
          Stay at state 3 append , if input is from 0 to 9
          Transit to state 5 and save the operator, complete left, make left to be right and result if input is +-/*

          state 4: ready for right operand,initialize to be 0
          stay at state 4 and if the current one is zero then does nothing , if input is 0 or CE
          transit to state 5 after set left operand string to the digit, update result and formula, if input is from 1 to 9.
          transit to state 6 after the left float string is 0, if input is .
          transit to state 7 after save the operator, if input is +-/*
          transit to state 5 after do the previous operation , if input is =

          state 5:
          stay 5, set if first one, input is from 1 to 9
          stay 5, if not first one, append, if input is from 0-9
          transit state 4, after set right to the digit, if input is 0 or CE
          transit to state 6, if input is .
          transit to state 5 after doing the operation, update the operation, if input is +-/* if the  previous operator is not / or when / right is not zero
          transit to state 7 showing divide by zero , if input is +-/* if the  previous operator is / and divisor is zero
          transit to state 5 after doing the operation, update the operation, if input is = if the  previous operator is not / or when / right is not zero
          transit to state 7 showing divide by zero , if input is = if the  previous operator is / and divisor is zero

          State 6:
          stay at state 6 and does nothing , if input is = or .
          Stay at state 6 append , if input is from 0 to 9
          tansit to state 4, if input is CE
          transit to state 5 after doing the operation, update the operation, if input is +-/* if the  previous operator is not / or when / right is not zero
          transit to state 7 showing divide by zero , if input is +-/* if the  previous operator is / and divisor is zero
          transit to state 5 after doing the operation, update the operation, if input is = if the  previous operator is not / or when / right is not zero
          transit to state 7 showing divide by zero , if input is = if the  previous operator is / and divisor is zero

          State 7:
          stay 7, if input is anything else but CE
          transit to state 4 if input is CE

         */

        public void reset()
        {
            state=1;
            //extra variables besides state variable,
            // updated as part of the transition
            inputstr="";
            resultstr="";
            result=0;
            opls=""; //string version of left operand
            oprs="";  // string version of right operand
            currentoperation=' ';

        }

        public CalculatorModel()
        {
            reset();
        }

        public void add()
        {
            result=Double.parseDouble(opls)+Double.parseDouble(oprs);
            resultstr=String.valueOf(result);
        }
        public void subtract()
        {
            result=Double.parseDouble(opls)-Double.parseDouble(oprs);
            resultstr=String.valueOf(result);
        }

        private void addtoinput(String in)
        {
            inputstr=inputstr+in;
        }

        public String getresult()
        {
            return String.valueOf(resultstr);
        }
        public String getinput()
        {
            return inputstr;
        }

        private boolean isanumerial(String keytext) {
                if (keytext.charAt(0)>='0'&&keytext.charAt(0)<='9')
                    return true;
                else
                    return false;
        }
        private boolean isCE(String keytext)
        {
            if (keytext.equals("CE")) return true;
            else
                return false;
        }
        private boolean isdecimalpoint(String keytext)
        {
            if (keytext.charAt(0)=='.') return true;
            else
                return false;
        }
        private boolean isassignment(String keytext) {
            if (keytext.charAt(0)=='=')
                return true;
            else
                return false;
        }
        private boolean iszero(String keytext) {
        if (keytext.charAt(0)=='0')
            return true;
        else
            return false;
    }

        private boolean isanoperator(String keytext) {
            switch (keytext.charAt(0)) {
                case '+':
                case '-':
                case '*':
                case '/':
                //case '=':
                    return true;
                default:
                    return false;
            }
        }
/*
    state 1: ready for left operand, result initialize to 0
    stay at 1 and does nothing , if input is = or 0 or CE
    transit to state 2, set left operand string to the digit, update result and formula, if input is from 1 to 9.
    transit to state 3, the left float string is 0, if input is .
    transit to state 4, save the operator, if input is +-/*
*/
        private void state1transition(String currentkey)
        {
            if (iszero(currentkey)||isassignment(currentkey) ||isCE(currentkey))
                return;

            if (isanumerial(currentkey)&&!iszero(currentkey))
            {
                opls=currentkey;
                resultstr=inputstr=opls;
                state=2;
                return;
            }

            if (isdecimalpoint(currentkey))
            {
                opls="0.";
                resultstr=inputstr=opls;
                state=3;
                return;
            }
            if (isanoperator(currentkey))
            {
                oprs=opls;
                inputstr=inputstr+currentkey;
                resultstr=oprs;
                state=5;
                return;
            }

        }
/*
state 2: integer left operand
          stay at 2 and does nothing, if input is =
          transit to state 1, if  input is CE
          stay at 2 and keep appending to left operand string, if input is 0 to 9
          transit to state 3, append . to left operand string, if input is .
          transit to state 4 and save the operator, if input is +-/*
 */
    private void state2transition(String currentkey)
    {
        // extra if CE ?
        if(isassignment(currentkey))
            return;
        if(isanumerial(currentkey))
        {
            opls=opls+currentkey;
            inputstr=inputstr+currentkey;
            resultstr=opls;
            return;
        }
        if(isdecimalpoint(currentkey))
        {
            opls=opls+currentkey;
            inputstr=inputstr+currentkey;
            resultstr=opls;
            state=3;
            return;
        }
        if(isanoperator(currentkey))
        {
            currentoperation=currentkey.charAt(0);
            oprs="0";  // slightly different treatment than the standard calcuator.
            inputstr=inputstr+currentkey;
            resultstr=oprs;
            state=5;
            return;
        }



    }
/*
    state 3: double left operand
    stay at state 3 and does nothing , if input is = or .
    transit to state 1, if input is CE
    Stay at state 3 append , if input is from 0 to 9
    Transit to state 5 and save the operator, complete left, make left to be right and result if input is +-/*
*/
   private void state3transition(String currentkey)
    {
        if (isanumerial(currentkey))
        {
            opls=opls+currentkey;
            inputstr=inputstr+currentkey;
            resultstr=opls;
            return;
        }
        if (isanoperator(currentkey))
        {
            currentoperation=currentkey.charAt(0);
            oprs="0";
            inputstr=inputstr+currentkey;
            resultstr=oprs;
            state=5;
            return;

        }

    }

    /*
state 4: ready for right operand,initialize to be 0

    stay at state 4 and if the current one is zero then does nothing , if input is 0 or CE
    transit to state 5 after set left operand string to the digit, update result and formula, if input is from 1 to 9.
    transit to state 6 after the left float string is 0, if input is .
    transit to state 7 after save the operator, if input is +-/*
          transit to state 5 after do the previous operation , if input is =
*/

    private void state4transition(String currentkey)
    {


    }

/*
state 5:
    stay 5, set if first one, input is from 1 to 9
    stay 5, if not first one, append, if input is from 0-9
    transit state 4, after set right to the digit, if input is 0 or CE
    transit to state 6, if input is .
    transit to state 5 after doing the operation, update the operation, if input is +-/* if the  previous operator is not / or when / right is not zero
          transit to state 7 showing divide by zero , if input is +-/* if the  previous operator is / and divisor is zero
          transit to state 5 after doing the operation, update the operation, if input is = if the  previous operator is not / or when / right is not zero
          transit to state 7 showing divide by zero , if input is = if the  previous operator is / and divisor is zero
*/
    private void state5transition(String currentkey)
    {
        System.out.println("In state 5"); // for debugging purpose.

        if (isanumerial(currentkey)&&!iszero(currentkey))
        {
            addtoinput(currentkey);
            if (oprs.equals("0"))
            {
                oprs=currentkey;
            }
            else
                oprs+=currentkey;

            resultstr=oprs;
            return;

        }
        if (isanoperator(currentkey) ) {
            addtoinput(currentkey);
            switch (currentoperation)
            {
                case '+': add(); break;
                case '-': subtract(); break;
            }
            oprs="0";
        }
        if (isassignment(currentkey) ) {
            //addtoinput(currentkey);
            switch (currentoperation)
            {
                case '+': add(); break;
                case '-': subtract(); break;
            }

            oprs="0";
        }

    }
    private void state6transition(String currentkey)
    {


    }
    private void state7transition(String currentkey)
    {


    }

    private void transitfunction(String currentkey)
     {
            switch(state)
            {
                case 1: state1transition(currentkey); break;
                case 2: state2transition(currentkey); break;
                case 3: state3transition(currentkey); break;
                case 4: state4transition(currentkey); break;
                case 5: state5transition(currentkey); break;
                case 6: state6transition(currentkey); break;
                case 7: state7transition(currentkey); break;

            }
     }


       /* FSM state ransition due to input signal */
        public void processinputsignal(String keytext)
        {
            transitfunction(keytext);

        }


}