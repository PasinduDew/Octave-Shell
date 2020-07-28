import java.util.HashMap;
import java.util.Scanner;

public class OctaveShell{

    static HashMap<String,int[][]> hmVectorMatrix = new HashMap<String,int[][]>(); 
    static HashMap<String,Integer> hmScalar = new HashMap<String,Integer>(); 
    static boolean loop = true;

    // CLI Configurations
    static String prompt = ">>";

     static String strInput = "";

     static Scanner input = new Scanner(System.in);




    public static void main(String[] args) {

        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Welcome to Universal Complex Scripting Calculator by UCSC, Sri Lanka.");
        System.out.println("Version: 1.0");
        System.out.println("----------------------------------------------------------------------------");

        while(loop){
        // Prompting For User Input
        System.out.print(prompt + " ");
        strInput = input.nextLine();

        // System.out.println(strInput);

        // System.out.println(getOperationType(preprocessInpuString(strInput)));

        switch (getOperationType(preprocessInpuString(strInput))) {
            case "Vector_Matrix_Assignment": 
            createVectorMatrix(preprocessInpuString(strInput));
                
            break;
            case "Scalar_Assignment": 
            createScalar(preprocessInpuString(strInput));
   
            break;
            // case "Equation": 
            // break;
            case "Expression": 
            evaluateExpression(preprocessInpuString(strInput));
            break;

            case "Variable": 
            displayVariable(preprocessInpuString(strInput));
            break;

            case "exit": exitProgram();
            break;

            case "prompt":
            break;

            case "clc": clearScreen(); 
            break;

            default: System.out.println("Error: Invalid Input");
            break;
        }
        
    }
        // createVariable(preprocessInpuString(strInput))

        // Closing the Command Line Input
        // input.close();
    

    }

    // Exit Program
    public static void exitProgram() {
        loop = false;
        System.exit(0);
        return;
    }

    // Clear the Console Screen
    public static void clearScreen() {  
        for (int i = 0; i < 50; ++i) System.out.println();  
       }

    // Preprocess the Input String
    public static String preprocessInpuString(String str) {
        String tempStr = str;
        // Remove Multiple Spaces
        tempStr = tempStr.replaceAll("\\s{2,}", " ").trim();
        return tempStr;     
    }
       
    // This Method Evaluates the Input Expression
    public static void evaluateExpression(String inStr) {
        String expStr;
        String[] strArray;
        String op;
        // Validate Expression
        expStr = inStr.trim();

        if(expStr.contains(".*")){
            expStr = expStr.replaceAll("[.][*]", " .* ").trim();

        } else {
            expStr = expStr.replaceAll("[+]", " + ").trim();
            expStr = expStr.replaceAll("[-]", " - ").trim();
            expStr = expStr.replaceAll("[*]", " * ").trim();
            expStr = expStr.replaceAll("[/]", " / ").trim();
        }
        expStr = expStr.replaceAll("\\s{2,}", " ").trim();
        // System.out.println(expStr);

        // Split the String
        strArray = expStr.split(" ");

        // Operator Selection
        op = strArray[1];

        // Operands type analysis

        // System.out.println(isNumeric(strArray[0]) + " -- " + isNumeric(strArray[2]));
        if(isNumeric(strArray[0]) && isNumeric(strArray[2])){
            int op1 = Integer.parseInt(strArray[0]);
            int op2 = Integer.parseInt(strArray[2]);
            // System.out.println("");

            switch (op) {
                case "+": System.out.println("ans = " + (op1 + op2));
                break;

                case "-": System.out.println("ans = " + (op1 - op2));
                break;

                case "*": System.out.println("ans = " + (op1 * op2));
                break;

                case "/": 
                        if(op1 % op2 == 0){
                            System.out.println("ans = " + (op1 / op2));

                        } else {
                            System.out.println("ans = " + ((double)op1 / (double)op2));

                        }
                break;

                default:System.out.println("Invalid Operation!");
                break;
            }

            System.out.println("");
        } else if(isNumeric(strArray[0]) && !isNumeric(strArray[2])){
            // System.out.println("2 + A");
            int op1 = Integer.parseInt(strArray[0]);
            String varName = strArray[2];

            if(hmScalar.containsKey(varName)){

                int op2 = hmScalar.get(varName);
                // System.out.println("");
                switch (op) {
                    case "+": System.out.println("ans = " + (op1 + op2));
                        
                    break;
                    case "-": System.out.println("ans = " + (op1 - op2));
                    
                    break;
                    case "*": System.out.println("ans = " + (op1 * op2));
                    
                    break;
                    case "/": 
                            if(op1 % op2 == 0){
                                System.out.println("ans = " + (op1 / op2));
    
                            } else {
                                System.out.println("ans = " + ((double)op1 / (double)op2));
    
                            }
                    break;
                
                    default:System.out.println("Invalid Operation!");
                    break;
                }
                System.out.println("");

            } else if(hmVectorMatrix.containsKey(varName)){

                switch (op) {
                    case "+": displayVectorMatrix(scalarVecMatAddition(op1, hmVectorMatrix.get(varName)));
                        
                    break;
                    case "-": displayVectorMatrix(scalarVecMatSubstraction(op1, hmVectorMatrix.get(varName)));
                    
                    break;
                    case "*": displayVectorMatrix(scalarVecMatMultiplication(op1, hmVectorMatrix.get(varName)));
                    
                    break;
                    case "/": System.out.println("Invalid Operation! : Scalar Vector/Matrix Division");
                    break;
                
                    default:System.out.println("Invalid Operation!");
                    break;
                } 
                System.out.println("");

            } else {
                System.out.println("Variable '" + varName + "' is Undefined");
            }

        }else if(!isNumeric(strArray[0]) && isNumeric(strArray[2])){
            // System.out.println("A + 2");
            int op2 = Integer.parseInt(strArray[2]);
            String varName = strArray[0];

            if(hmScalar.containsKey(varName)){

                int op1 = hmScalar.get(varName);
                switch (op) {
                    case "+": System.out.println("ans = " + (op1 + op2));
                        
                    break;
                    case "-": System.out.println("ans = " + (op1 - op2));
                    
                    break;
                    case "*": System.out.println("ans = " + (op1 * op2));
                    
                    break;
                    case "/": 
                            if(op1 % op2 == 0){
                                System.out.println("ans = " + (op1 / op2));
    
                            } else {
                                System.out.println("ans = " + ((double)op1 / (double)op2));
    
                            }
                    break;
                
                    default:System.out.println("Invalid Operation!");
                    break;
                }
                System.out.println("");

            } else if(hmVectorMatrix.containsKey(varName)){

                switch (op) {
                    case "+": displayVectorMatrix(scalarVecMatAddition(op2, hmVectorMatrix.get(varName)));
                        
                    break;
                    case "-": displayVectorMatrix(scalarVecMatSubstraction(op2, hmVectorMatrix.get(varName)));
                    
                    break;
                    case "*": displayVectorMatrix(scalarVecMatMultiplication(op2, hmVectorMatrix.get(varName)));
                    
                    break;
                    case "/": displayVectorMatrix(scalarVecMatDivision(op2, hmVectorMatrix.get(varName)));
                    break;
                
                    default:System.out.println("Error: Invalid Operation!");
                    break;
                } 

            } else {
                System.out.println("Variable '" + varName + "' is Undefined");
            }

            
        }else if(!isNumeric(strArray[0]) && !isNumeric(strArray[2])){
            
            // System.out.println("A + B");
            String op1Str = strArray[0].trim();
            String op2Str = strArray[2].trim();

            if(hmScalar.containsKey(op1Str) && hmScalar.containsKey(op2Str)){

                int op1 = hmScalar.get(op1Str);
                int op2 = hmScalar.get(op2Str);

            switch (op) {
                case "+": System.out.println("ans = " + (op1 + op2));
                    
                break;
                case "-": System.out.println("ans = " + (op1 - op2));
                
                break;
                case "*": System.out.println("ans = " + (op1 * op2));
                
                break;
                case "/": 
                        if(op1 % op2 == 0){
                            System.out.println("ans = " + (op1 / op2));

                        } else {
                            System.out.println("ans = " + ((double)op1 / (double)op2));

                        }
                break;
            
                default:System.out.println("Invalid Operation!");
                break;
            }
            System.out.println("");

            } else if(hmScalar.containsKey(op1Str) && hmVectorMatrix.containsKey(op2Str)){

                switch (op) {
                    case "+": displayVectorMatrix(scalarVecMatAddition(hmScalar.get(strArray[0].trim()), hmVectorMatrix.get(strArray[2].trim())));
                        
                    break;
                    case "-": displayVectorMatrix(scalarVecMatSubstraction(hmScalar.get(strArray[0].trim()), hmVectorMatrix.get(strArray[2].trim())));
                    
                    break;
                    case "*": displayVectorMatrix(scalarVecMatMultiplication(hmScalar.get(strArray[0].trim()), hmVectorMatrix.get(strArray[2].trim())));
                    
                    break;
                    case "/": displayVectorMatrix(scalarVecMatDivision(hmScalar.get(strArray[0].trim()), hmVectorMatrix.get(strArray[2].trim())));
                    break;
                
                    default:System.out.println("Error: Invalid Operation!");
                    break;
                }
                System.out.println("");

            } else if(hmVectorMatrix.containsKey(op1Str) && hmScalar.containsKey(op2Str)){

                switch (op) {
                    case "+": displayVectorMatrix(scalarVecMatAddition(hmScalar.get(strArray[2].trim()), hmVectorMatrix.get(strArray[0].trim())));
                        
                    break;
                    case "-": displayVectorMatrix(scalarVecMatSubstraction(hmScalar.get(strArray[2].trim()), hmVectorMatrix.get(strArray[0].trim())));
                    
                    break;
                    case "*": displayVectorMatrix(scalarVecMatMultiplication(hmScalar.get(strArray[2].trim()), hmVectorMatrix.get(strArray[0].trim())));
                    
                    break;
                    case "/": displayVectorMatrix(scalarVecMatDivision(hmScalar.get(strArray[2].trim()), hmVectorMatrix.get(strArray[0].trim())));
                    break;
                
                    default:System.out.println("Error: Invalid Operation!");
                    break;
                } 
                System.out.println("");
                
            } else if(hmVectorMatrix.containsKey(op1Str) && hmVectorMatrix.containsKey(op2Str)){

                int[][] matA = hmVectorMatrix.get(op1Str);
                int[][] matB = hmVectorMatrix.get(op2Str);

                switch (op) {
                    case "+": displayVectorMatrix(vectorMatrixAddition(matA, matB));
                        
                    break;
                    case "-": displayVectorMatrix(vectorMatrixSubstraction(matA, matB));
                    
                    break;
                    case "*": displayVectorMatrix(vectorMatrixMultiplication(matA, matB));
                    
                    break;
                    case ".*": displayVectorMatrix(vectorMatrixElementwiseMultiplication(matA, matB));
                
                    break;
                
                    default:System.out.println("Invalid Operation!");
                    break;
                }
                System.out.println("");
                
            } else {
                if(!hmScalar.containsKey(op1Str) && !hmVectorMatrix.containsKey(op1Str) && (hmScalar.containsKey(op2Str) || hmVectorMatrix.containsKey(op2Str))){
                    System.out.println("Variable '" + strArray[0] + "' is Undefined");
                } else if(!hmScalar.containsKey(op2Str) && !hmVectorMatrix.containsKey(op2Str) && (hmScalar.containsKey(op1Str) || hmVectorMatrix.containsKey(op1Str))){
                    System.out.println("Variable '" + strArray[2] + "' is Undefined");
                }else if(!hmScalar.containsKey(op2Str) && !hmVectorMatrix.containsKey(op2Str) && !hmScalar.containsKey(op1Str) && !hmVectorMatrix.containsKey(op1Str)){
                    System.out.println("Variables '" + op1Str + "', '" + op2Str + "' are Undefined");
                }
            }

        }
    }

    // To Find Which Operation Has to be Performed
    public static String getOperationType(String inStr) {
        String tempStr = inStr;

        if(tempStr.contains("=") && tempStr.contains("[")  && tempStr.contains("]")){
            return "Vector_Matrix_Assignment";
        } else if(tempStr.contains("=") && isNumeric(tempStr.split("=")[1].trim())) {
            return "Scalar_Assignment";

        } else if(tempStr.contains("=") && (tempStr.contains("+") || tempStr.contains("-") || tempStr.contains("/") || tempStr.contains("*"))) {
            return "Equation";
            
        }else if(tempStr.contains("+") || tempStr.contains("-") || tempStr.contains("/") || tempStr.contains("*")) {
            return "Expression";
            
        }else if(!isNumeric(tempStr.trim()) && !(tempStr.contains("=") || tempStr.contains("+") || tempStr.contains("-") || tempStr.contains("/") || tempStr.contains("*"))) {
            if(tempStr.trim().equals("exit")  || tempStr.trim().equals("EXIT") || tempStr.trim().equals("Exit") ){

                return "exit";
    
            }
            else if(tempStr.trim().equals("clear") || tempStr.trim().equals("CLEAR") || tempStr.trim().equals("Clear")){
                return "clear";
            }
            else if(tempStr.trim().equals("clc") || tempStr.trim().equals("CLC") || tempStr.trim().equals("Clc")){
                return "clc";
    
            }else if(tempStr.trim().equals("")){
                return "prompt";
            } else {
                return "Variable";

            }
            
        } else {
            return "Error: Invalid Input";
        }
    }


    // This Method Create a New Vector Or Matrix
    public static void createVectorMatrix(String inStr) {

        String tempStr;
        int[] dims = new int[2];
        String[] strArray = inStr.split("=");
        String varName = strArray[0].trim();
        String varValue = strArray[1].trim();

        // System.out.println("varName--" + varName + "--varValue--" + varValue + "--");

        // Create Standard Vector/Matrix String
        tempStr = varValue.replaceAll("\\[|\\]", "").trim(); // Remove Square Brackets from the Value Portion
        tempStr = tempStr.replaceAll(";", " ;").trim();
        tempStr = tempStr.replaceAll("\\s{2,}", " ").trim();
        tempStr = tempStr.replaceAll("; ", ";").trim();
        if(tempStr.charAt(tempStr.length() - 1) == ';'){
            tempStr = tempStr.substring(0, tempStr.length()-1);
        } else {
            tempStr += " ";
        }

        // System.out.println("tempStr--" + tempStr + "--");
        // Get Dimensions
        dims = getDims(tempStr);

        // Create Corresponding Array
        int[][] vec_mat;

        if(dims[0] == 1){
            vec_mat = new int[1][dims[1]];
        } else if(dims[1] == 1) {
            vec_mat = new int[dims[0]][1];

        } else {
            vec_mat = new int[dims[0]][dims[1]];

        }

        String[] strRows = new String[dims[0]];
        String[] strCols = new String[dims[1]];

        strRows = tempStr.split(";");
        for(int i = 0; i < strRows.length; i++){
            strCols = strRows[i].trim().split(" ");
            for(int j = 0; j < strCols.length; j++){
                try {
                    vec_mat[i][j] = Integer.parseInt(strCols[j].trim());
                } catch (Exception e) {
                    System.out.println("Error Creating Matrix!");
                    return;
                }
            }
        }

        // for (int i = 0; i < strRows.length; i++) {
        //     for (int j = 0; j < strCols.length; j++) {
        //         System.out.println("vec_mat[" + i + "][" + j + "] : " + vec_mat[i][j]);
        //     }
        // }
        if(hmScalar.containsKey(varName)){
            hmScalar.remove(varName);
            hmVectorMatrix.put(varName, vec_mat);

        } else if(hmVectorMatrix.containsKey(varName)){
            hmVectorMatrix.remove(varName);
            hmVectorMatrix.put(varName, vec_mat);
        }
        else {
            hmVectorMatrix.put(varName, vec_mat);

        }

        displayVariable(varName);
        // hmVectorMatrix.put(varName, vec_mat);

        // int[][] temp = hmVectorMatrix.get(varName);
        // for (int i = 0; i < temp.length; i++) {
        //     for (int j = 0; j < temp[0].length; j++) {
        //         System.out.println("temp[" + i + "][" + j + "] : " + temp[i][j]);
        //     }
        // }
    }

    // Scalar + Vector/Matrix
    public static int[][] scalarVecMatAddition(int scalar, int[][] vecMat) {
        int[][] tempVecMat = new int[vecMat.length][vecMat[0].length];

        for (int i = 0; i < tempVecMat.length; i++) {
            for (int j = 0; j < tempVecMat[0].length; j++) {
                tempVecMat[i][j] = vecMat[i][j] + scalar; 
            }
        }

        return tempVecMat;
    }

    // Scalar - Vector/Matrix
    public static int[][] scalarVecMatSubstraction(int scalar, int[][] vecMat) {
        int[][] tempVecMat = new int[vecMat.length][vecMat[0].length];


        for (int i = 0; i < tempVecMat.length; i++) {
            for (int j = 0; j < tempVecMat[0].length; j++) {
                tempVecMat[i][j] = vecMat[i][j] - scalar; 
            }
        }

        return tempVecMat;
    }
    // Scalar * Vector/Matrix
    public static int[][] scalarVecMatMultiplication(int scalar, int[][] vecMat) {
        int[][] tempVecMat = new int[vecMat.length][vecMat[0].length];

        for (int i = 0; i < tempVecMat.length; i++) {
            for (int j = 0; j < tempVecMat[0].length; j++) {
                tempVecMat[i][j] = vecMat[i][j] * scalar; 
            }
        }

        return tempVecMat;
    }

    // Scalar / Vector/Matrix
    public static int[][] scalarVecMatDivision(int scalar, int[][] vecMat) {
        int[][] tempVecMat = new int[vecMat.length][vecMat[0].length];

        for (int i = 0; i < tempVecMat.length; i++) {
            for (int j = 0; j < tempVecMat[0].length; j++) {
                tempVecMat[i][j] = vecMat[i][j] / scalar; 
            }
        }

        return tempVecMat;
    }

    // This Method Creates a New Scalar Variable
    public static void createScalar(String inStr) {
        String varName = inStr.split("=")[0].trim();
        String varValue = inStr.split("=")[1].trim();

        if(isNumeric(varValue)){
            if(hmVectorMatrix.containsKey(varName)){
                hmVectorMatrix.remove(varName);
                hmScalar.put(varName, Integer.parseInt(varValue));
            } else if(hmScalar.containsKey(varName)){
                hmScalar.remove(varName);
                hmScalar.put(varName, Integer.parseInt(varValue));
            } else {
                hmScalar.put(varName, Integer.parseInt(varValue));

            }
            displayVariable(varName);
        }  
    }

    // Display Scalar Variable
    public static void displayScalar(String key) {

        if(hmScalar.containsKey(key)){
            System.out.println(key + " = " + hmScalar.get(key));
        }
        System.out.println("\n\n");

        
    }

    // Vector/Matrix * Vector/Matrix
    public static int[][] vectorMatrixMultiplication(int[][] matA, int[][] matB) {
        int[][] resMat;

        if(matA[0].length == matB.length){

            resMat = new int[matA.length][matB[0].length];

            for (int i = 0; i < matA.length; i++) {
                
                for (int j = 0; j < matB[0].length; j++) {
                    int tempVal = 0;
                    for (int k = 0; k < matA[0].length; k++) {
                        
                        tempVal += (matA[i][k] * matB[k][j]);

                    }
                    resMat[i][j] = tempVal;
                }
            }
            return resMat;
            
        } else {
            System.out.println("The Matrix Multplication Cannot be Performed!");
            resMat = new int[0][0];
            return resMat;
        }
    }

    // Vector/Matrix + Vector/Matrix
    public static int[][] vectorMatrixAddition(int[][] matA, int[][] matB) {
        int[][] resMat;
        int[][] tempMat1;
        int[][] tempMat2;

        if((matA.length * matA[0].length) >= (matB.length * matB[0].length)){

            resMat = new int[matA.length][matA[0].length];
            tempMat1 = matA;
            tempMat2 = matB;    
        } else {
            resMat = new int[matB.length][matB[0].length];
            tempMat1 = matB;
            tempMat2 = matA;
        }

        if(tempMat1.length == tempMat2.length && tempMat1[0].length == tempMat2[0].length){

            for (int i = 0; i < resMat.length; i++) {
                for (int j = 0; j < resMat[0].length; j++) {
                    resMat[i][j] = matA[i][j] + matB[i][j];
                }
            }
            return resMat;
        } else if(tempMat1.length == tempMat2.length && (tempMat2[0].length == 1)){
            for (int i = 0; i < resMat.length; i++) {
                for (int j = 0; j < resMat[0].length; j++) {
                    resMat[i][j] = tempMat1[i][j] + tempMat2[i][0];
                }
            }
            return resMat;

        } else if(tempMat1[0].length == tempMat2[0].length && tempMat2.length == 1){
            for (int i = 0; i < resMat.length; i++) {
                for (int j = 0; j < resMat[0].length; j++) {
                    resMat[i][j] = tempMat1[i][j] + tempMat2[0][i];
                }  
            }
            return resMat;
        } else {

            System.out.println("Mismatched Dimensions. Cannot Perform Matrix Addition");
            resMat = new int[0][0];
        }
        return resMat;    
    }

    // Vector/Matrix - Vector/Matrix
    public static int[][] vectorMatrixSubstraction(int[][] matA, int[][] matB) {
        int[][] resMat;

        if((matA.length * matA[0].length) >= (matB.length * matB[0].length)){

            resMat = new int[matA.length][matA[0].length];   
        } else {
            resMat = new int[matB.length][matB[0].length];
        }

        if(matA.length == matB.length && matA[0].length == matB[0].length){

            for (int i = 0; i < resMat.length; i++) {
                for (int j = 0; j < resMat[0].length; j++) {
                    resMat[i][j] = matA[i][j] - matB[i][j];
                }     
            }
            return resMat;
        } else if(matA.length == matB.length && matB[0].length == 1){
            for (int i = 0; i < resMat.length; i++) {
                for (int j = 0; j < resMat[0].length; j++) {
                    resMat[i][j] = matA[i][j] - matB[i][0];
                }   
            }
            return resMat;

        } else if(matA.length == matB.length && matA[0].length == 1){
            for (int i = 0; i < resMat.length; i++) {
                for (int j = 0; j < resMat[0].length; j++) {
                    resMat[i][j] = matA[i][0] - matB[i][j];
                }
                
            }
            return resMat;

        } else if(matA[0].length == matB[0].length && matB.length == 1){
            for (int i = 0; i < resMat.length; i++) {
                for (int j = 0; j < resMat[0].length; j++) {
                    resMat[i][j] = matA[i][j] - matB[0][i];
                }
                
            }
            return resMat;
        } else if(matA[0].length == matB[0].length && matA.length == 1){

            for (int i = 0; i < resMat.length; i++) {
                for (int j = 0; j < resMat[0].length; j++) {
                    resMat[i][j] = matA[0][i] - matB[i][j];
                }
                
            }
            return resMat;
        }
         else {

            System.out.println("Mismatched Dimensions. Cannot Perform Matrix Addition");
            resMat = new int[0][0];
        }

        return resMat;
    }

    // Vector/Matrix .* Vector/Matrix
    public static int[][] vectorMatrixElementwiseMultiplication(int[][] matA, int[][] matB) {
        int[][] resMat;
        int[][] tempMat1;
        int[][] tempMat2;

        if((matA.length * matA[0].length) >= (matB.length * matB[0].length)){

            resMat = new int[matA.length][matA[0].length];
            tempMat1 = matA;
            tempMat2 = matB;    
        } else {
            resMat = new int[matB.length][matB[0].length];
            tempMat1 = matB;
            tempMat2 = matA;
        }

        if(tempMat1.length == tempMat2.length && tempMat1[0].length == tempMat2[0].length){

            for (int i = 0; i < resMat.length; i++) {
                for (int j = 0; j < resMat[0].length; j++) {
                    resMat[i][j] = matA[i][j] * matB[i][j];
                }
                
            }
            return resMat;
        } else if(tempMat1.length == tempMat2.length && (tempMat2[0].length == 1)){
            for (int i = 0; i < resMat.length; i++) {
                for (int j = 0; j < resMat[0].length; j++) {
                    resMat[i][j] = tempMat1[i][j] * tempMat2[i][0];
                }
                
            }
            return resMat;

        } else if(tempMat1[0].length == tempMat2[0].length && tempMat2.length == 1){
            for (int i = 0; i < resMat.length; i++) {
                for (int j = 0; j < resMat[0].length; j++) {
                    resMat[i][j] = tempMat1[i][j] * tempMat2[0][i];
                }
                
            }
            return resMat;
        } else {

            System.out.println("Mismatched Dimensions. Cannot Perform Matrix Addition");
            resMat = new int[0][0];
        }
        return resMat; 
        
    }

    // This Method Displays a Vector Or Matrix When Given the Variable Name
    public static void displayVectorMatrix(String key) {
        System.out.println("");
        if(hmVectorMatrix.containsKey(key)){
            System.out.println(key + " =");
            int[][] vecMat = hmVectorMatrix.get(key);
            for (int i = 0; i < vecMat.length; i++) {
                System.out.print("    ");

                for (int j = 0; j < vecMat[0].length; j++) {
                    System.out.print(vecMat[i][j] + "  ");

                }
                System.out.println();
            }
        }
        System.out.println("");
        
    }

    // This Method Displays a Vector Or Matrix When Given the Vector Or Matrix in Array
    public static void displayVectorMatrix(int[][] vecMat) {
        System.out.println("");
        System.out.println("ans = ");

        for (int i = 0; i < vecMat.length; i++) {
            System.out.print("    ");

            for (int j = 0; j < vecMat[0].length; j++) {
                System.out.print(vecMat[i][j] + "  ");

            }
            System.out.println();
        }
        System.out.println("");
        
    }

    // This Method Displays the Value of a Variable
    public static void displayVariable(String varName) {

        if(hmScalar.containsKey(varName)){
                System.out.println(varName + " = " + hmScalar.get(varName));

        } else if(hmVectorMatrix.containsKey(varName)){
            displayVectorMatrix(varName);
        } else {
            System.out.println("Variable '" + varName + "' is Undefined");

        }
        System.out.println("");

        
    }

    
// This Method Returns Bolean Value Based on Given String is a Number or Not
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    // This Method Counts Number of Occurences of a Given Character in a Given String
    public static int countChar(String str, char character) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == character){
                count++;
            }
        }
        return count;
        
    }

    // This Method Returns the Dimentions of a Vector Or Matrix in String Form
    public static int[] getDims(String valStr) {
    
        String tempStr = valStr;
        int firstRowCols = 0;
        int cols = 0;
        int rows = 0;
        boolean isValid = true;
        boolean rowFlag = false;
        int[] retArray = new int[2];
        for (int i = 0; i < tempStr.length(); i++) {
            
            if(tempStr.charAt(i) == ' ' && rowFlag == false){
                firstRowCols++;
                

            } else if(tempStr.charAt(i) == ' ' && rowFlag == true){
                cols++;
            } else if(tempStr.charAt(i) == ';' && rowFlag == false){
                rowFlag = true;
                rows++;
            } else if((tempStr.charAt(i) == ';' || tempStr.charAt(i) == ' ') && rowFlag == true && cols == firstRowCols){
                cols = 0;
                rows++;

            } else if((tempStr.charAt(i) == ';' || tempStr.charAt(i) == ' ') && rowFlag == true && cols != firstRowCols) {
                
                isValid = false;
            }
        }
        rows++;

        if(!isValid){
            System.out.println("Invalid Matrix/Vector");
            retArray[0] = 0;
            retArray[1] = 0;
            return retArray;
        } else {
            // System.out.println("Rows: " + rows + " | " + "Cols: " + firstRowCols); 
            retArray[0] = rows;
            retArray[1] = firstRowCols;
            return retArray;

        }
        
    }
}