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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class Midos {
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
    public static List<Directory> makeDirectory (String name, int memory, List<Directory> directories, Directory parent) { 
        try {
            String parentName = null;
            int parentPossition = 0;
            if (parent != null) {
                parentPossition = parent.possition + 1;
                parentName = parent.name;
            }
            String convertIntoCapital = name.toUpperCase();
            if ( memory < 0 || memory == 0 ) {
                    System.out.println("No hay memoria disponible");
                } else if ( convertIntoCapital.isEmpty() ) {
                    System.out.println("DirectoriOs deben tener nombre");
                } else if ( parent != null && parent.numberOfChildren > 8 ) {
                    System.out.println("No se pueden agregar mas de 8 directorios");
                } else if ( convertIntoCapital.length() > 8 ) {
                    System.out.println("Los directories no pueden exceder los 8 caracteres");
                } else if ( convertIntoCapital.substring(0, 1).matches(".*\\d+.*")) {
                    System.out.println("Primer caracter no puede ser un numero");
                } else if ( convertIntoCapital.matches("[-/@#$%^&_+=()]") ) {
                    System.out.println("No se permiten caracteres especiales");
                } else if (Directory.numberOfRootDirectories(directories) > 8) {
                    System.out.println("No se pueden agregar mas de 8 directorios");
                } else if (Directory.isDuplicated(name, directories, parentPossition)) {
                    System.out.println("Ya se agrego el directorio");
                } else {
                    if (parent != null) {
                        directories = saveParentDirectory(directories, parent, true, parent.numberOfChildren + 1);
                    }
                    Directory directory  = new Directory(
                        name, 
                        parentName, 
                        false, 
                        0, 
                        parentPossition
                    );
                    directories.add(directory);
                    return directories;
                } 
        } catch ( Exception ex) {
            ex.printStackTrace();  
        }
        return directories;
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
    public static Boolean exitMidos(List<String> path) {
        try {
            System.out.println("¿Está seguro de salir de MIDOS?");
            displayPath(path);
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
                        displayPath(path);
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
    /**
     * Returns the path
     * @param path 
     */
    public static void displayPath(List<String> path) {
        try {
            for( String element : path ) {
                System.out.print(element);
            }
        } catch( Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Save values in the file
     * @param value
     * @param path 
     */
    public static List<String> callDirectory(List<String> path, List<Directory> directories, String name) {
        try {
            int size = path.size();
            int index = size - 1;
            if ((name.startsWith("..") && name.length() == 2 ) || (name.startsWith(" ..") && name.length() == 3)  ) {
                if (size == 2) {
                    System.out.println("Se encuentra en el directorio raiz");
                } else {
                    path.remove(index - 1);
                    return path;
                }
            } else if (name.startsWith(" \\") && name.length() == 2) {
                if (size == 2) {
                    System.out.println("Se encuentra en el directorio raiz");
                } else {
                   for (int i = index - 1; i > 0; i--) {
                       path.remove(i);
                   }
                   return path;
                }
            } else if (name.startsWith(" ") && !name.startsWith(" \\") && !name.startsWith("..") && !name.startsWith(" ..")) {
                String sanatizedName = name.substring(1, name.length());
                for (Directory directory : directories ) {
                    if ( directory.possition == index - 1 && directory.name.contentEquals(sanatizedName) ) {
                        String slashedName = "\\";
                        if (size == 2) {
                            path.add(index, directory.name);
                        } else {
                            path.add(index, slashedName+directory.name);
                        }    
                        return path;
                    }
                }
                System.out.println("No existe un directorio con ese nombre en ese directorio");
            } else {
                
                System.out.println("Comando invalido al llamar el directorio");
            }
        } catch( Exception ex) {
            ex.printStackTrace();
        }
        return path;
    }
    /**
     * Gets the parent directory from the path
     * @since 1.0.1
     * @param path
     * @param directories
     * @return 
     */
    public static Directory getParentByPath(List<String> path, List<Directory> directories) {
        try {
            if ( path.size() == 2 || directories.size() == 0 ) {
                return null;
            } else {
                int index = path.size() - 2;
                String name = path.get(index);
                if (name.startsWith("\\")) {
                    name = name.substring(1, name.length());
                }
                for (Directory directory : directories ) {
                    if (directory.name.contains(name)) {
                        return directory;
                    }
                }
            }
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    /**
     * Updates parent directory
     * @param directories
     * @param parent
     * @param hasChildren
     * @param numberOfChildren
     * @return 
     */
    public static List<Directory> saveParentDirectory(List<Directory> directories, Directory parent, boolean hasChildren, int numberOfChildren) {
        try {
            int index = directories.indexOf(parent);
            parent.hasChildren = hasChildren;
            parent.numberOfChildren = numberOfChildren;
            directories.set(index, parent);
            return directories;
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
        return directories;
    }
    /**
     * Remove a directory
     * @since 1.0.1
     * @param name
     * @param directories
     * @param parent
     * @return 
     */
    public static List<Directory> removeDirectory (String name, List<Directory> directories, Directory parent) { 
        try {
            Directory directory = Directory.getDirectory(name, directories);
             if (directory == null) {
                 System.out.println("Caracter invalido");
                 return directories;
                } else if ( parent != null && directory.parent != parent.name) {
                    System.out.println("No se encuentra el archivo deseado");
                    return directories;
                } else if ( directory.hasChildren ) {
                    System.out.println("No se puede borrar carpetas con contenido");
                    return directories;
                } else if ( parent == null && directory.possition != 0 ) {
                    System.out.println("No se encuentra el archivo deseado");
                    return directories;
                } else {
                    if (parent != null) {
                        boolean hasChildren = true;
                        if( parent.numberOfChildren == 1 ) {
                            hasChildren = false;
                        }
                        directories = saveParentDirectory(directories, parent, hasChildren, parent.numberOfChildren - 1);
                    }
                    directories.remove(directory);
                    return directories;
                } 
        } catch ( Exception ex) {
            ex.printStackTrace();  
        }
        return directories;
    }
}
