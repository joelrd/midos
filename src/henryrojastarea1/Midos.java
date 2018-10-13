/*
 * @author Henry Rojas Douglas
 * @version 1.0.1
 * @copyright Henry Rojas Douglas
 * @license MIT
 * @package henrojastarea1
 */
package henryrojastarea1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class Midos {
    /**
     * Memory Path
     * @var String
     * @since 1.0.1
     */
    final static String memoryPath = "C:\\MIDOSFRE.txt";
    /**
     * Directory path
     * @var String
     * @since 1.0.1
     */
    final static String dictoriesPath = "C:\\MIDOSTRE.txt";
    /**
     * Load a file
     * @param fileName String
     * @return Object
     */
    public static Object loadAFile(String fileName) {
        Object object = null;
        try {
            File file = new File(fileName);
            if (file.exists()) {
                FileInputStream fileInput = new FileInputStream(fileName);
                ObjectInputStream fileProperties = new ObjectInputStream(fileInput);
                object = fileProperties.readObject();
                fileProperties.close();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return object;
    }
    
    /**
     * Print the header 
     * @since 1.0.1
     * @param memory Integer
     */
    public static void header(int memory) {
        System.out.println("MINGOSOFT ® MIDOS");
        System.out.println("© Copyright MINGOSOFT CORPORATION 2018");
        System.out.println("Versión 1.0 Memoria libre: "+memory+"K");
        System.out.println("Autor: Henry Rojas Douglas - 111490839");
    }
    /**
     * Make a Directory
     * @since 1.0.1
     * @param name
     * @param memory
     * @param directories
     * @return
     */
    public static String makeDirectory (String name, int memory, List<String> directories) { 
        try {
            String convertIntoCapital = name.toUpperCase();
            if ( memory < 0 || memory == 0 ) {
                    System.out.println("No hay memoria disponible");
                } else if ( convertIntoCapital.isEmpty() ) {
                    System.out.println("DirectoriOs deben tener nombre");
                } else if ( directories.size() > 8 ) {
                    System.out.println("No se pueden agregar mas de 8 directorios");
                } else if ( convertIntoCapital.length() > 8 ) {
                    System.out.println("Los directories no pueden exceder los 8 caracteres");
                } else if ( convertIntoCapital.substring(0, 1).matches(".*\\d+.*")) {
                    System.out.println("Primer caracter no puede ser un numero");
                } else if ( convertIntoCapital.matches("[-/@#$%^&_+=()]") ) {
                    System.out.println("No se permiten caracteres especiales");
                } else if ( directories.contains(convertIntoCapital) ) {
                    System.out.println("Diretorio ya ha sido creado");
                } else {
                    return convertIntoCapital;
                } 
        } catch ( Exception ex) {
            ex.printStackTrace();  
        }
        return null;
    }
    /**
     * Clear the Screen
     * @since 1.0.1
     */
    public static void clearScreen() {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
    /**
     * Exit Midos
     * @since 1.0.1
     * @return 
     */
    public static Boolean exitMidos() {
        try {
            System.out.println("¿Está seguro de salir de MIDOS?");
            System.out.print("M:\\");
            String wantToExit;
            boolean isExiting = true;
            while ( isExiting ) {
                Scanner command = new Scanner(System.in);
                wantToExit = command.nextLine().toUpperCase();
                switch (wantToExit) {
                    case "S": return false;
                    case "N": isExiting = false;
                        break;
                    default: System.out.println("Solo puede ser S o N");
                        System.out.print("M:\\");
                    break;
                }
            }
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    /**
     * Get the date
     * @since 1.0.1
     */
    public static void showDate() {
        try {
            Calendar calendar = Calendar.getInstance();
            Calendar gregorianCalendar = new GregorianCalendar();
            DecimalFormat mFormat= new DecimalFormat("00");
            String day = mFormat.format(Double.valueOf(gregorianCalendar.get(calendar.DATE)));
            String month = mFormat.format(Double.valueOf(gregorianCalendar.get(calendar.MONTH)));
            String year = mFormat.format(Double.valueOf(gregorianCalendar.get(calendar.YEAR)));
            System.out.println("La fecha actual es: "+day+"/"+month+"/"+year);
        } catch( Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Gets the time
     * @since 1.0.1
     */
    public static void showTime() {
        try {
            String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
            System.out.println("La hora actual es: "+time);
        } catch( Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Invalid Command
     * @since 1.0.1
     */
    public static void invalidCommand() {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("ERROR: 001 Comando inválido.");
    }
    /**
     * Save values in the file
     * @param value
     * @param path 
     */
    public static void saveFile(Object value, String path) {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream fileObjectOut = new ObjectOutputStream(fileOut);
            fileObjectOut.writeObject(value);
            fileObjectOut.close();
        } catch( Exception ex) {
            ex.printStackTrace();
        }
    }
}
