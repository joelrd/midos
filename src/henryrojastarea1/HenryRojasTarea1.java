/*
 * @author Henry Rojas Douglas 111490839
 * Compiladores
 * Grupo 01
 */
package henryrojastarea1;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author joel
 */
public class HenryRojasTarea1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int memory = 256;
        List<String> directories = new ArrayList<>();
        try {
            File memoryFile = new File("C:\\MIDOSFRE.txt");
            File directoryFile = new File("C:\\MIDOSTRE.txt");
            if (memoryFile.exists()) {
                FileInputStream memoryInput = new FileInputStream("C:\\MIDOSFRE.txt");
                ObjectInputStream memoryProperties = new ObjectInputStream(memoryInput);
                memory = (int) memoryProperties.readObject();
                memoryProperties.close();
            }
            if (directoryFile.exists()) {
                FileInputStream directoryInput = new FileInputStream("C:\\MIDOSTRE.txt");
                ObjectInputStream directoryProperties = new ObjectInputStream(directoryInput);
                directories = (List<String>) directoryProperties.readObject();
                directoryProperties.close();
            }
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
        boolean isExit = true;
        String value;
        System.out.println("MINGOSOFT ® MIDOS");
        System.out.println("© Copyright MINGOSOFT CORPORATION 2018");
        System.out.println("Versión 1.0 Memoria libre: "+memory+"K");
        System.out.println("Autor: Henry Rojas Douglas - 111490839");
        while ( isExit ) {
            System.out.print("M:\\");
            Scanner command = new Scanner(System.in);
            value = command.nextLine();
            if ( value.startsWith("MD")) {
                String directoryCommand = value.substring(3, value.length());
                memory = memory - 8;
                String convertIntoCapital = directoryCommand.toUpperCase();
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
                    directories.add(convertIntoCapital);
                }
            } else {
                switch (value) {
                case "CLS": System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    break;  
                case "EXIT": System.out.println("¿Está seguro de salir de MIDOS?");
                    System.out.print("M:\\");
                    String wantToExit;
                    boolean isExiting = true;
                    while ( isExiting ) {
                        wantToExit = command.nextLine();
                        switch (wantToExit) {
                            case "S": isExit = false;
                                isExiting = false;
                                break;
                            case "N": isExiting = false;
                                break;
                            default: System.out.println("Solo puede ser S o N");
                                System.out.print("M:\\");
                            break;
                        }
                    }
                    break;
                case "VER": System.out.println("MINGOSOFT ® MIDOS");
                    System.out.println("© Copyright MINGOSOFT CORPORATION 2018");
                    System.out.println("Versión 1.0 Memoria libre: "+memory+"K");
                    System.out.println("Autor: Henry Rojas Douglas - 111490839");
                    break;
                case "DATE": Calendar calendar = Calendar.getInstance();
                    Calendar gregorianCalendar = new GregorianCalendar();
                    DecimalFormat mFormat= new DecimalFormat("00");
                    String day = mFormat.format(Double.valueOf(gregorianCalendar.get(calendar.DATE)));
                    String month = mFormat.format(Double.valueOf(gregorianCalendar.get(calendar.MONTH)));
                    String year = mFormat.format(Double.valueOf(gregorianCalendar.get(calendar.YEAR)));
                    System.out.println("La fecha actual es: "+day+"/"+month+"/"+year);
                    break;
                case "TIME": String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
                    System.out.println("La hora actual es: "+time);
                    break;              
                default:  System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("ERROR: 001 Comando inválido.");
                    break;
                }
            }
        }
        try {
            FileOutputStream memoryOut = new FileOutputStream("C:\\MIDOSFRE.txt");
            FileOutputStream directoryOut = new FileOutputStream("C:\\MIDOSTRE.txt");
            ObjectOutputStream memoryObjectOut = new ObjectOutputStream(memoryOut);
            ObjectOutputStream directoryObjectOut = new ObjectOutputStream(directoryOut); 
            memoryObjectOut.writeObject(memory);
            directoryObjectOut.writeObject(directories);
            memoryObjectOut.close();
            directoryObjectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }
}
