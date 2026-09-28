import java.io.*;
import java.util.Scanner;


/*                      0000000000000000000000000000001
    1 = (1,2)       3   0000000000000000000000000000011

                        0000000000000000000000000000010
    2 = (0,1,3,4,6) 91  0000000000000000000000001011011

    3 = (0,1,2,3,6) 79  0000000000000000000000001001111
    4 = (1,2,5,6)   102 0000000000000000000000001100110
    5 = (0,2,3,5,6) 109 0000000000000000000000001101101

    
    lw  $1, counter($0)

    devi    $10, $1, 10
    remi    $1, $1, 10

    numbers:
            .word 0x3F # 0
            .word 0x05 # 1
            .word 0x5B # 2
            .word 0x4F # 3
            .word 0x66 # 4
            .word 0x6D # 5
            .word 0x7D # 6
            .word 0x07 # 7
            .word 0x7F # 8
            .word 0x6F # 9

            000000000000000000000000 0000 1111
            000000000000000000000000 1010 0111

            4/1
    

*/

public class D2B2H {


    public static String decimalToBinary(int decimal) {
        // Convert decimal to binary string
        String binary = Integer.toBinaryString(decimal);
        
        // Pad the binary string with zeroes to ensure it's 32 bits long
        while (binary.length() < 32) {
            binary = "0" + binary;
        }
        
        return binary;
    }

    public static int binaryToDecimal(String binary) {
        // Convert binary string to decimal integer
        int decimal = Integer.parseInt(binary, 2);
        return decimal;
    }
    
    public static String binaryToHex(String binary) {
        // Convert binary string to hexadecimal string
        String hex = Integer.toHexString(Integer.parseInt(binary, 2));
        
        // Pad the hexadecimal string with zeroes to ensure it's 8 characters long
        while (hex.length() < 8) {
            hex = "0" + hex;
        }
        return hex;
    }

    public static String hexToBinary(String hex) {
        hex = hex.substring(2);
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < hex.length(); i++) {
            String binarySegment = Integer.toBinaryString(Character.digit(hex.charAt(i), 16));
            binarySegment = "0000".substring(binarySegment.length()) + binarySegment;
            binary.append(binarySegment);
        }
        while (binary.length() < 32) {
            binary.insert(0, "0");
        }
        return binary.toString();
    }
    public static String getHexMaskForBit(int bitNumber) {
        if (bitNumber < 0 || bitNumber > 31) {
            return "Bit number must be between 0 and 31";
        }
        // Create a hexadecimal number with only the bit at bitNumber set to 0 and all other bits set to 1
        int mask = ~(1 << bitNumber) & 0xfff0;
        return "0x" + String.format("%01x0", mask);
    }

    static Scanner scan;

    public static void main(String[] args) {

        D2B2H d = new D2B2H();
        d.scan = new Scanner(System.in);
        Scanner in = d.scan;
        
        while(true){

            int task = 0;
            System.out.println("(1) BIT-MASK");
            System.out.println("(2) Bin --> HEXADECIMAL");
            System.out.println("(3) Bin --> DECIMAL");          
            System.out.println("(4) Hex --> BINARY");
            System.out.println("(5) Dec --> BINARY");
          


            // GETTING INPUT
            while(task == 0){          
                String x = in.nextLine();

                if(x.equals("q")){
                    System.exit(0);
                }
                else if(x.equals("1")){
                    task = 1;
                    break;
                }
                else if(x.equals("2")){
                    task = 2;
                    break;
                }
                else if(x.equals("3")){
                    task = 3;
                    break;
                }
                else if(x.equals("4")){
                    task = 4;
                    break;
                }
                else if(x.equals("5")){
                    task = 5;
                    break;
                }
            }
            if(task == 1){      
                taskBitToMask(in);
            }
            if(task == 2){     
                taskBinToHex(in);              
            }
            if(task == 3){     
                taskBinToDec(in);              
            }
            if(task == 4){
                taskHexToBin(in);
            }
            if(task == 5){
                taskDecToBin(in);
            }
        }
    }

    public static void taskBitToMask(Scanner in){

        int bitNumber = 0;
        String hexMask = null;

        while(bitNumber == 0 || hexMask == null){
            System.out.println("Enter bit number:");

            bitNumber = (in.nextInt());

            if (bitNumber < 0 || bitNumber > 31) {
                System.out.println("Bit number must be between 0 and 31");
            }

            // MASK OUTPUT
            if(bitNumber > 0){
                hexMask = getHexMaskForBit(bitNumber);
                if(hexMask != "null"){
                    System.out.println("Hexadecimal mask for bit " + bitNumber + ": " + hexMask);
                    break;
                }         
            }
        }
        return;
    }

    public static void taskHexToBin(Scanner in){

        System.out.println("Enter a hex value:");
        String hexInput = in.nextLine().trim();
        String binaryOutput = hexToBinary(hexInput);
        if(binaryOutput != null){             
            System.out.println("Binary equivalent of " + hexInput + " is: " + binaryOutput);
        }      
        return;
    }

    public static void taskDecToBin(Scanner in){

        System.out.println("Enter a Decimal value:");
        int decInput = in.nextInt();
        String binaryOutput = decimalToBinary(decInput);
        if(binaryOutput != null){             
            System.out.println("Binary equivalent of " + decInput + " is: " + binaryOutput);
        }      
        return;
    }

    public static void taskBinToDec(Scanner in){

        System.out.println("Enter a Binary value:");
        String binInput = in.nextLine().trim();
        int decimalOutput = binaryToDecimal(binInput);           
        System.out.println("Decimal equivalent of " + binInput + " is: " + decimalOutput);     
        return;
    }

    public static void taskBinToHex(Scanner in){

        System.out.println("Enter a Binary value:");
        String binInput = in.nextLine().trim();
        String hexOutput = binaryToHex(binInput);
        if(hexOutput != null){             
            System.out.println("Hexadecimal equivalent of " + binInput + " is: " + hexOutput);
        }      
        return;
    }


}
