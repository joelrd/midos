/*
 * @author Henry Rojas Douglas 111490839
 * @version 1.0.3 
 * @copyright MIT
 * @license Henry Rojas
 * @package MIDOS
 */
package henryrojastarea1;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main execution
 * @version 1.0.1
 */
public class HenryRojasTarea1 {
    /**
     * Memory Path
     * @var String
     * @since 1.0.1
     */
    static String memoryPath = "C:\\MIDOSFRE.txt";
    /**
     * Archive path
     * @var String
     * @since 1.0.1
     */
    static String dictoriesPath = "C:\\MIDOSTRE.txt";
    /**
     * @param args the command line arguments
     * @since 1.0.0
     * @Since 1.0.1 Refactoring, and added CD, Prompt, dir
     * @since 1.0.2 Copy Con, type, del, ren
     * @since 1.0.3 Tree
     */
    public static void main(String[] args) {
        int memory = 256;
        List<Archive> directories = new ArrayList<Archive>();
        if (Midos.loadAFile(memoryPath) != null) {
            memory = (int) Midos.loadAFile(memoryPath);
        }
        if (Midos.loadAFile(dictoriesPath) != null) {
            directories = (List<Archive>) Midos.loadAFile(dictoriesPath);
        } else {
            directories = Midos.createContent(directories);
            memory = 256 - 8*3;
        }
        boolean isExit = true;
        String value;
        Midos.header(memory);
        List<String> path = new ArrayList<String>();
        path.add("M:\\");
        path.add(">");
        while ( isExit ) {
            int directoryCount = directories.size();
            Midos.displayPath(path);
            Scanner command = new Scanner(System.in);
            value = command.nextLine().toUpperCase();
            if (value.startsWith("MD ")) {
                directories = Midos.makeDirectory(value.substring(3, value.length()), memory, directories, Midos.getParentByPath(path, directories));
                if (directoryCount < directories.size()) {
                    memory = memory - 8;
                }      
            } else if (value.startsWith("CD")) {
                path = Midos.callDirectory(path, directories, value.substring(2, value.length()));
            } else if (value.startsWith("RD ")) {
                directories = Midos.removeDirectory(value.substring(3, value.length()), directories, Midos.getParentByPath(path, directories));
                if (directoryCount > directories.size()) {
                    memory = memory + 8;
                }  
            } else if (value.startsWith("PROMPT ") || value.startsWith("PROMPT")) {
                String name = "";
                if ( value.length() > 6 ) {
                  name = value.substring(7, value.length());   
                } 
                path = Midos.prompt(path, name);
            } else if (value.equals("DIR")) {
                Midos.directories(directories, Midos.getParentByPath(path, directories), memory);
            } else if (value.startsWith("COPY CON ")) {
                directories = Midos.copyCon(value.substring(9, value.length()), memory, directories, Midos.getParentByPath(path, directories));
                if (directoryCount < directories.size()) {
                    memory = memory - 4;
                }
            } else if (value.startsWith("TYPE ")) {
                Midos.type(path, directories, value.substring(5, value.length()));
            } else if (value.startsWith("DEL ")) {
                directories = Midos.delete(value.substring(4, value.length()), directories, Midos.getParentByPath(path, directories));
                if (directoryCount > directories.size()) {
                    memory = memory + 4;
                }  
            }else if (value.startsWith("REN ")) {
                directories = Midos.rename(value.substring(4, value.length()), directories, Midos.getParentByPath(path, directories));
                if (directoryCount > directories.size()) {
                    memory = memory + 4;
                }  
            }else {
                switch (value) {
                case "CLS": Midos.clearScreen();
                    break;  
                case "EXIT": isExit = Midos.exitMidos(path);
                    break;
                case "VER": Midos.header(memory);
                    break;
                case "DATE": Midos.showDate();
                    break;
                case "TIME": Midos.showTime();
                    break;
                case "CD": Midos.showTime();
                    break;
                case "TREE": Midos.tree(directories, path);
                    break;
                default:  Midos.invalidCommand(001, "Comando Invalido");
                    break;
                }
            }
        }
        try {
            Midos.saveFile(memory, memoryPath);
            Midos.saveFile(directories, dictoriesPath);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }
}
