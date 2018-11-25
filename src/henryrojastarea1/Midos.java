/*
 * @author Henry Rojas Douglas
 * @version 1.0.3
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
     * Load a file
     * @since 1.0.1
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
     * Make a Archive
     * @since 1.0.1
     * @since 1.0.2 Error management
     * @param parent
     * @param name
     * @param memory
     * @param directories
     * @return
     */
    public static List<Archive> makeDirectory (String name, int memory, List<Archive> directories, Archive parent) { 
        try {
            String parentName = null;
            int parentPossition = 0;
            if (parent != null) {
                parentPossition = parent.possition + 1;
                parentName = parent.name;
            }
            String convertIntoCapital = name.toUpperCase();
            if (memory < 8) {
                    invalidCommand(002, "No hay memoria disponible");
                } else if ( convertIntoCapital.isEmpty() ) {
                    invalidCommand(003, "DirectoriOs deben tener nombre");
                } else if ( parent != null && parent.numberOfChildren > 8 ) {
                    System.out.println("");
                    invalidCommand(004, "No se pueden agregar mas de 8 directorios");
                } else if ( convertIntoCapital.length() > 8 ) {
                    invalidCommand(005, "Los directories no pueden exceder los 8 caracteres");
                } else if ( convertIntoCapital.substring(0, 1).matches(".*\\d+.*")) {
                    invalidCommand(006, "Primer caracter no puede ser un numero");
                } else if ( convertIntoCapital.matches("[-/@#$%^&_+=()]") ) {
                    invalidCommand(007, "No se permiten caracteres especiales");
                } else if (Archive.numberOfRootDirectories(directories) > 8) {
                    invalidCommand(8, "No se pueden agregar mas de 8 directorios");
                } else if (Archive.isDuplicated(name, directories, parentPossition)) {
                    invalidCommand(9, "Ya se agrego el directorio");
                } else {
                    if (parent != null) {
                        directories = saveParentDirectory(directories, parent, true, parent.numberOfChildren + 1);
                    }
                    Archive directory  = new Archive(name, parentName, false, 0, parentPossition, parent == null ? false : true, false, null);
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
     * @param path
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
                    default: invalidCommand(10, "Solo puede ser S o N");
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
     * @since 1.0.3 Receive parameters
     */
    public static void invalidCommand(int errorNumber, String errorMessage) {
        String spacer = "     ";
        String zeros = errorNumber > 9 ? "0" : "00";
        System.out.println(spacer+"ERROR: "+ zeros + errorNumber+ " "+errorMessage);
    }
    /**
     * Save values in the file
     * @since 1.0.1
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
     * @since 1.0.1
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
     * @since 1.0.1
     * @param path 
     * @param directories
     * @param name
     * @return 

     */
    public static List<String> callDirectory(List<String> path, List<Archive> directories, String name) {
        try {
            int size = path.size();
            int index = size - 1;
            if ((name.startsWith("..") && name.length() == 2 ) || (name.startsWith(" ..") && name.length() == 3)  ) {
                if (size == 2) {
                    invalidCommand(11, "Se encuentra en el directorio raiz");
                } else {
                    path.remove(index - 1);
                    return path;
                }
            } else if (name.startsWith(" \\") && name.length() == 2) {
                if (size == 2) {
                    invalidCommand(11, "Se encuentra en el directorio raiz");
                } else {
                   for (int i = index - 1; i > 0; i--) {
                       path.remove(i);
                   }
                   return path;
                }
            } else if (name.startsWith(" ") && !name.startsWith(" \\") && !name.startsWith("..") && !name.startsWith(" ..")) {
                String sanatizedName = name.substring(1, name.length());
                for (Archive directory : directories ) {
                    if ( directory.possition == index - 1 && directory.name.contentEquals(sanatizedName) && !directory.isText ) {
                        if (!directory.isText) {
                            String slashedName = "\\";
                            if (size == 2) {
                                path.add(index, directory.name);
                            } else {
                                path.add(index, slashedName+directory.name);
                            }    
                            return path;
                        } else {                            
                            invalidCommand(12, "El archivo que trata de accesar no es un directory");
                            return path;
                        }    
                    } 
                }
                invalidCommand(13, "No existe un directorio con ese nombre en ese directorio");
            } else {
                invalidCommand(1, "Comando invalido");
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
    public static Archive getParentByPath(List<String> path, List<Archive> directories) {
        try {
            if ( path.size() == 2 || directories.isEmpty() ) {
                return null;
            } else {
                int index = path.size() - 2;
                String name = path.get(index);
                if (name.startsWith("\\")) {
                    name = name.substring(1, name.length());
                }
                for (Archive directory : directories ) {
                    if (directory.name.equals(name)) {
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
     * @since 1.0.1
     * @param directories
     * @param parent
     * @param hasChildren
     * @param numberOfChildren
     * @return 
     */
    public static List<Archive> saveParentDirectory(List<Archive> directories, Archive parent, boolean hasChildren, int numberOfChildren) {
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
    public static List<Archive> removeDirectory (String name, List<Archive> directories, Archive parent) { 
        try {
            Archive directory = Archive.getDirectory(name, directories);
             if (directory == null) {
                 invalidCommand(14, "Caracter invalido");
                 return directories;
                } else if ( parent != null && directory.parent != parent.name) {
                    invalidCommand(15, "No se encuentra el archivo deseado");
                    return directories;
                } else if ( directory.hasChildren ) {
                    invalidCommand(16, "No se puede borrar carpetas con contenido");
                    return directories;
                } else if ( parent == null && directory.possition != 0 ) {
                    invalidCommand(15, "No se encuentra el archivo deseado");
                    return directories;
                } else if ( directory.isText ) {
                    System.out.println("No se puede borrar archivos");
                    invalidCommand(16, "No se puede borrar carpetas con contenido");
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
    /**
     * Sort the command line
     * @since 1.0.1
     * @param path
     * @param name
     * @return 
     */
    public static List<String> prompt(List<String> path, String name) {
        try {
            int index = path.size() - 1;
            if (name.isEmpty() || name.equals("$P $G")) {
                path.set(0, "M:\\");
                path.set(index, ">");
                return path;
            } else if (name.equals("$P")) {
                path.set(0, "M:\\");
                path.set(index, "");
            } else if (name.equals("$G")) {
                path.set(0, "");
                path.set(index, ">");
            } else if (name.equals("$G $P")) {
                path.set(index, "M:\\");
                path.set(0, ">");
            } else {
                invalidCommand(001, "Comando invalido");
            }
            return path;
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
        return path;
    }
    /**
     * Create Directories
     * @since 1.0.1
     * @param directories
     * @return 
     */
    public static List<Archive> createContent(List<Archive> directories) {
        try {
            Archive midos = new Archive("MIDOS", null, true, 1, 0, false, false, null);
            Archive document = new Archive("DOCUMENT", midos.name, true, 1, midos.possition + 1, true, false, null);
            Archive tareas = new Archive("TAREAS", document.name, false, 0, document.possition + 1, true, true, "Testing out type command");
            directories.add(midos);
            directories.add(document);
            directories.add(tareas);
            return directories;
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
        return directories;
    }
    /**
     * Display files within a directory
     * @since 1.0.1
     * @param directories
     * @param parent
     * @param memory 
     */
    public static void directories(List<Archive> directories, Archive parent, int memory){
        try {
            if (parent == null) {
                System.out.println("\\");
            } else {
                System.out.println(parent.name);
            }
            List<String> folders = new ArrayList<>();
            List<String> archives = new ArrayList<>(); 
            for (Archive directory : directories) {
                if ( parent == null ) {
                    if (directory.parent == null) {
                        if ( directory.isText ) {
                            archives.add(directory.name+" "+ "arch" );
                        } else {
                            folders.add(directory.name+" "+ "<DIR>");
                        }
                    }
                } else {
                    if (directory.parent != null && directory.parent.equals(parent.name)) {
                        if ( directory.isText ) {
                            archives.add(directory.name+" "+ "arch" );
                        } else {
                            folders.add(directory.name+" "+ "<DIR>");
                        }
                    }
                } 
            }
            String folderAmount = folders.size() != 1 ? "directorios" : "directorio";
            String archiveAmount = archives.size() != 1 ? "archivos" : "archivo";
            System.out.println("El directorio posee: "+folders.size()+" "+ folderAmount +" y "+archives.size()+" "+ archiveAmount +" con una memoria disponible de: "+memory+"K");
            archives.sort(String::compareToIgnoreCase);
            folders.sort(String::compareToIgnoreCase);
            for (String archive : archives) {
                System.out.println(archive);
            }
            for (String folder : folders) {
                System.out.println(folder);
            }
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Copy content to Archive
     * @since 1.0.2
     * @param name
     * @param memory
     * @param directories
     * @param parent
     * @return 
     */
    public static List<Archive> copyCon (String name, int memory, List<Archive> directories, Archive parent) { 
        try {
            Scanner e = new Scanner(System.in);
            String parentName = null;
            int parentPossition = 0;
            if (parent != null) {
                parentPossition = parent.possition + 1;
                parentName = parent.name;
            }
            String convertIntoCapital = name.toUpperCase();
            if (memory < 4) {
                    invalidCommand(002, "No hay memoria");
                } else if ( convertIntoCapital.isEmpty() ) {
                    invalidCommand(003, "DirectoriOs deben tener nombre");
                } else if ( parent != null && parent.numberOfChildren > 8 ) {
                    invalidCommand(004, "No se pueden agregar mas de 8 archivos");
                } else if ( convertIntoCapital.length() > 8 ) {
                    invalidCommand(005, "Los directories no pueden exceder los 8 caracteres");
                } else if (convertIntoCapital.substring(0, 1).matches(".*\\d+.*")) {
                    invalidCommand(006, "Primer caracter no puede ser un numero");
                } else if ( convertIntoCapital.matches("[-/@#$%^&_+=()]") ) {
                    invalidCommand(007, "No se permiten caracteres especiales");
                } else if (Archive.numberOfRootDirectories(directories) > 8) {
                    invalidCommand(17, "No se pueden agregar mas de 8 archivos");
                } else if (Archive.isDuplicated(name, directories, parentPossition)) {
                    invalidCommand(18, "Ya se agrego el archivo");
                } else {
                    String content = "";
                    String betweenLines;
                    boolean start = true;
                    System.out.print(">");
                    while(start) {
                        betweenLines = e.next();
                        if (betweenLines.contains("^Z")) {
                            content += betweenLines.substring(betweenLines.indexOf("^Z") + 2, betweenLines.length());
                            start = false;
                        } else {
                            content += betweenLines + "\n";
                        }
                        System.out.print(">");
                    }
                    Archive archive = new Archive(name, parentName, false, 0, parentPossition, parent == null ? false : true, true, content);
                    if (parent != null) {
                        directories = saveParentDirectory(directories, parent, true, parent.numberOfChildren + 1);
                    }
                    directories.add(archive);
                    return directories;
                } 
        } catch ( Exception ex) {
            ex.printStackTrace();  
        }
        return directories;
    }
    /**
     * Return the content of a text file
     * @since 1.0.2
     * @param path
     * @param directories
     * @param name 
     */
    public static void type(List<String> path, List<Archive> directories, String name) {
        try {
            int size = path.size();
            int index = size - 1;
            if (name.isEmpty()) {
                invalidCommand(18, "Favor ingresar un nombre");
            } else {
                for (Archive directory : directories ) {
                    if ( directory.possition == index - 1 && directory.name.contentEquals(name)) {
                        
                        if (directory.isText) {
                            if (directory.content.isEmpty()) {
                                invalidCommand(19, "El archivo esta vacio");
                                return;
                            }   
                            System.out.println(directory.content);
                            return;
                        } else {
                            invalidCommand(20, "El archivo es un directory");
                            return;
                        }    
                        
                    } else if (directory.possition == index - 1 && directory.name.contentEquals(name) && !directory.isText) {
                        
                    }
                }
                invalidCommand(21, "No existe un archivo de texto con ese nombre en ese directorio");
            }
        } catch( Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Deletes archives
     * @since 1.0.2
     * @param name
     * @param directories
     * @param parent
     * @return 
     */
    public static List<Archive> delete (String name, List<Archive> directories, Archive parent) { 
        try {
            Archive directory = Archive.getDirectory(name, directories);
             if (directory == null) {
                 invalidCommand(14, "Caracter invalido");
                 return directories;
                } else if ( parent != null && directory.parent != parent.name) {
                    invalidCommand(15, "No se encuentra el archivo deseado");
                    return directories;
                } else if ( directory.hasChildren ) {
                    invalidCommand(16, "No se puede borrar carpetas con contenido");
                    return directories;
                } else if ( parent == null && directory.possition != 0 ) {
                    invalidCommand(15, "No se encuentra el archivo deseado");
                    return directories;
                } else if ( !directory.isText ) {
                    invalidCommand(22, "No se puede borrar directorios");
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
        } catch (Exception ex) {
            ex.printStackTrace();  
        }
        return directories;
    }
    /**
     * Rename old file with new name
     * @since 1.0.2
     * @param names
     * @param directories
     * @param parent
     * @return 
     */
    public static List<Archive> rename(String names, List<Archive> directories,  Archive parent) {
        try {
            if (!names.isEmpty()) {
                if (names.startsWith(" ")) {
                    invalidCommand(23, "No puede empezar con espacio");
                    return directories;
                }
                String[] entryNames = names.split("\\s+");
                System.out.println(entryNames.length);
                if (entryNames.length > 2) {
                    return directories;
                }
                Archive directory = Archive.getDirectory(entryNames[0], directories);
                if (directory == null) {
                    invalidCommand(21, "No existe un archivo de texto con ese nombre en ese directorio");
                    return directories;
                } else if ( parent != null && directory.parent != parent.name) {
                    invalidCommand(15, "No se encuentra el archivo deseado");
                    return directories;
                } else if ( parent == null && directory.possition != 0 ) {
                    invalidCommand(15, "No se encuentra el archivo deseado");
                    return directories;
                } else if ( entryNames.length == 0 ) {
                    invalidCommand(22, "No se puede borrar directorios");
                    return directories;
                } else {
                    int getIndex = directories.indexOf(directory);
                    directory.name = entryNames[1];
                    directories.set(getIndex, directory);
                    for (Archive childDirectory : directories) {
                        if (childDirectory.parent != null && childDirectory.parent.equals(entryNames[0])) {
                            int childIndex = directories.indexOf(childDirectory);
                            childDirectory.parent = entryNames[1];
                            directories.set(childIndex, childDirectory);
                        }
                    }
                    return directories;
                }
            } else {
                invalidCommand(24, "Favor ingresar los respectivos archivos");
                return directories;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return directories;
    }
    /**
     * Returns all the folder in the system
     * @since 1.0.3
     * @param directories 
     */
    public static void tree( List<Archive> directories ) {
        try {
            for( Archive directory : directories ) {
                if ( directory.possition == 0 ) {
                    System.out.println( directory.name );
                    if ( directory.hasChildren ) {
                        final String spacer = "    ";
                        directories.stream().forEach( element -> { 
                            if ( directory.name.equals( element.getParent() ) ) {
                                System.out.println(spacer + element.name);
                                if( element.hasChildren ) {
                                    recursiveTree( directories, element, spacer + spacer );
                                }
                            }
                        });
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Iterates within the directorise
     * @since 1.0.3
     * @param directories
     * @param parent
     * @param spacer 
     */
    public static void recursiveTree( List<Archive> directories, Archive parent, String spacer ) {
        if ( directories.size() > 0 && parent != null ) {
            directories.stream().forEach( element -> {
                if ( parent.name.equals( element.getParent() ) ) {
                    System.out.println(spacer + element.name );
                    if (element.hasChildren) {
                        recursiveTree( directories, element, spacer + spacer );
                    }
                }
            });
        }
    }
}
