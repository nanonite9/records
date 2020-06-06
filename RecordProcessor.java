/* 
 * Filename: processor.java
 * Author: nanonite9
 * Date: November 27, 2017
 * Description: This program reads the specified amount of entries of all records in the input file and proceeds to return the amount of occurences of the characters A, D and W in the output file.
 */

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

class processor {

    public static void process(String[] entries) throws IOException {

        final int SUBSECTION_LENGTH = 6; 
        final char[] chars = {'A', 'D', 'W'}; // specified characters to count
        final String OUTPUT_FILE_NAME = "output.txt"; // name of output file

        int numLine = 0;
        char[] fieldChars;
        boolean endReached = (entries[0] == null);
        int[] subsection = new int[3]; // chars.length is 3

        while (numLine <= 50 && !endReached) { // MAX_LINE_NUM = 50
            // number of lines
            numLine++;
            endReached = (entries[numLine] == null);
        }

        int[][] count = new int[numLine][];

        // output file
        FileWriter fileWriter = new FileWriter (OUTPUT_FILE_NAME);
        PrintWriter writer = new PrintWriter (fileWriter);

        for (int i = 0; i < numLine; i++) {
        // each line
            count[i] = new int[chars.length];
            fieldChars = entries[i].toCharArray();
            for (int j = 0; j < fieldChars.length; j++) {
            // each character
                for (int k = 0; k < chars.length; k++) {
                // matches specified letters and increases count
                    if (fieldChars[j] == chars[k] ) {
                        count[i][k]++;
                    }
                }
            }
        }
        int i = 0;
        while (i < numLine ) {
        // first part of each line
            writer.print(i + 1);
            for (int k = 0; k < chars.length; k++) {
            	
                writer.printf(", %d", count[i][k]); 
                subsection[k] += count[i][k];
            }
            if (i + SUBSECTION_LENGTH < numLine) {
            // second part of each line
                writer.printf(",      %d", i + SUBSECTION_LENGTH + 1);
                for (int k = 0; k < chars.length; k++) {
                    subsection[k] += count[i + SUBSECTION_LENGTH][k];
                    writer.printf(", %d", count[i + SUBSECTION_LENGTH][k]);
                }
            } else {
            	
                writer.printf(",      %d , 0 , 0 , 0", i + SUBSECTION_LENGTH + 1);
            }
            writer.println();

            if ((i + 1) % (2 * SUBSECTION_LENGTH) == SUBSECTION_LENGTH) {
            // prints lines in format
                writer.printf("For the above %d lines, ", SUBSECTION_LENGTH);
                for (int k = 0; k < chars.length; k++) {
                    writer.printf("number of %c: %d", chars[k], subsection[k]);
                    if (k < chars.length - 1) {
                        writer.
                                print(" **** ");
                    }
                }
                writer.println();
                subsection = new int[chars.length];
                i += SUBSECTION_LENGTH + 1;

            } else {
            	
                i++;
            }
        }
        writer.close () ;
    }
    public static void main(String args[]) throws IOException {

        Scanner scan = new Scanner(System.in);
        String input;
        String output;
        boolean inputValid; // boolean if input is valid
        String[] entries;

		System.out.println("Welcome to The Record Processor\n-----------------");

        inputValid = false;
        while (!inputValid) {
            try {
            // prompts for user input
                System.out.print("Please input the input file name: ");
                input = scan.next();
                // reads file
                entries = reader.readEntriesFromFile(input);
                inputValid = true;
                process(entries);
            }
            catch (IOException e) {
            // tries until user enters valid input file
            	System.out.println("Sorry, cannot open the file for reading!");
            }
        }
        scan.close();
    }
}