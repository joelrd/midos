/*
 * @author Henry Rojas Douglas 111490839
 * @version 1.0.1 
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
     * Directory path
     * @var String
     * @since 1.0.1
     */
    static String dictoriesPath = "C:\\MIDOSTRE.txt";

    /**
     * @param args the command line arguments
     * @since 1.0.0
     * @Since 1.0.1 Refactoring, and added CD, Prompt, dir
     */
    public static void main(String[] args) {
        int memory = 256;
        List<Directory> directories = new ArrayList<Directory>();
        if (Midos.loadAFile(memoryPath) != null) {
            memory = (int) Midos.loadAFile(memoryPath);
        }
        if (Midos.loadAFile(dictoriesPath) != null) {
            directories = (List<Directory>) Midos.loadAFile(dictoriesPath);
        }
        boolean isExit = true;
        String value;
        Midos.header(memory);
        while ( isExit ) {
            int directoryCount = directories.size();
            System.out.print("M:\\");
            Scanner command = new Scanner(System.in);
            value = command.nextLine();
            if ( value.startsWith("MD ") || value.startsWith("md ")) {
                directories = Midos.makeDirectory(value.substring(3, value.length()), memory, directories, null);
                if (directoryCount < directories.size()) {
                    memory = memory - 8;
                }      
            } else {
                switch (value.toUpperCase()) {
                case "CLS": Midos.clearScreen();
                    break;  
                case "EXIT": isExit = Midos.exitMidos();
                    break;
                case "VER": Midos.header(memory);
                    break;
                case "DATE": Midos.showDate();
                    break;
                case "TIME": Midos.showTime();
                    break;              
                default:  Midos.invalidCommand();
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
