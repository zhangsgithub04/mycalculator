package mycalculator;

/**
 * Created by zhangs on 1/27/2019.
 * @Copyright Dr. S. Zhang
 * Modification Permission given to SUNY Oneonta CSCI 311/324 students Only
 */

public class CalculatorModel {
        double result;
        double opl;
        double opr;
        String whichoperand ;
        String operator;
        String opls;
        String oprs;
        String input;

        public void reset()
        {
            input="";
            opl=opr=0;
            opls="";
            oprs="";
            whichoperand="left";
            operator="";
        }

        public CalculatorModel()
        {
            reset();
        }

        public void add()
        {
            result=opl+opr;
        }
        public void subtract()
        {
            result=opl-opr;
        }

        public void addtoinput(String in)
        {
            input=input+in;
        }

        public String getresult()
        {
            return String.valueOf(result);
        }
        public String getinput()
        {
            return input;
        }
        public void addtooperand(String in)
        {
            if (whichoperand.equals("left"))
            {
                opls=opls+in;
                opl=Integer.parseInt(opls);
            }
            else
            {
                oprs=oprs+in;
                opr=Integer.parseInt(oprs);
            }
        }

        public void calculate(String op)
        {
            if(operator.equals("")) {
                operator = op;
                whichoperand="right";
            }
            else
            {
                switch (operator)
                {
                    case "+":
                        add();
                        break;
                    case "-":
                        subtract();
                        break;
                }
                opl=result;
                operator="";
                oprs="";
                whichoperand="right";

            }
        }
}