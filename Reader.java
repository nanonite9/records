/* 
 * Filename: Reader.java
 * Author: nanonite9
 * Date: November 27, 2017.
 * Description: This program reads a text file and returns the third entry of the records in an array.
 */

import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;

public class Reader {

    public static String [] readEntriesFromFile(String inputFileName) throws FileNotFoundException, IOException {
    // open the input file by the user
        FileReader fileReader = new FileReader(inputFileName);
        BufferedReader reader = new BufferedReader(fileReader);

        int entryOne;
        int entryTwo;
        int entryThree;
        String record = null;
        String [] entries = new String [',']; // prints result
        int line = 0;
        record = reader.readLine();

        while ( record != null ) {

            entryOne = record.indexOf(',') ;
            entryTwo = record.indexOf(',', entryOne + 1) ;
            entryThree = record.indexOf(',', entryTwo + 1) ;
            entries[line++] = record.substring(entryTwo + 1 , entryThree ) ;
            record = reader.readLine();
        }

        reader.close();
        return entries;
    }
}