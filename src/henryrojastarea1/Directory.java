/*
 * Directory Class
 * @author Henry Rojas Douglas
 * @version 1.0.1
 * @copyright Henry Rojas Douglas
 * @license MIT
 * @package henryrojastarea1
 */
package henryrojastarea1;

import java.util.List;

public class Directory implements java.io.Serializable {
    /**
     * Directory name
     * @var
     * @since 1.0.1
     */
    protected String name;
    /**
     * Directory parent
     * @var
     * @since 1.0.1
     */
    protected String parent;
    /**
     * Directory hasChildren
     * @var
     * @since 1.0.1
     */
    protected boolean hasChildren;
    /**
     * Directory numberOfChildren
     * @var
     * @since 1.0.1
     */
    protected int numberOfChildren;
    /**
     * Directory possition
     * @var
     * @since 1.0.1
     */
    protected int possition;
    /**
     * Directory hasParent
     * @var
     * @since 1.0.1
     */
    protected boolean hasParent;
    /**
     * Constructor
     * @since 1.0.1
     * @param name
     * @param parent
     * @param hasChildren
     * @param numberOfChildren
     * @param possition 
     */
    public Directory(String name, String parent, boolean hasChildren, int numberOfChildren, int possition) {
        this.name = name;
        this.parent = parent;
        this.hasChildren = hasChildren;
        this.numberOfChildren = numberOfChildren;
        this.possition = possition;
    }

    /**
     * @since 1.0.1
     * @return 
     */
    public String getName() {
        return name;
    }
    /**
     * @since 1.0.1
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @since 1.0.1
     * @return 
     */
    public String getParent() {
        return parent;
    }
    /**
     * @since 1.0.1
     * @param parent 
     */
    public void setParent(String parent) {
        this.parent = parent;
    }
    /**
     * @since 1.0.1
     * @return 
     */
    public boolean isHasChildren() {
        return hasChildren;
    }
    /**
     * @since 1.0.1
     * @param hasChildren 
     */
    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }
    /**
     * @since 1.0.1  
     * @return 
     */
    public int getNumberOfChildren() {
        return numberOfChildren;
    }
    /**
     * @since 1.0.1
     * @param numberOfChildren 
     */
    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }
    /**
     * @since 1.0.1
     * @return 
     */
    public int getPossition() {
        return possition;
    }
    /**
     * @since 1.0.1
     * @param possition 
     */
    public void setPossition(int possition) {
        this.possition = possition;
    }
    /**
     * @since 1.0.1
     * @return 
     */
    public boolean isHasParent() {
        return hasParent;
    }
    /**
     * @since 1.0.1
     * @param hasParent 
     */
    public void setHasParent(boolean hasParent) {
        this.hasParent = hasParent;
    }
    /**
     * Gets a directory from given name
     * @since 1.0.1
     * @param name
     * @param directories
     * @return 
     */
    public static Directory getDirectory(String name, List<Directory> directories) {
        try {
            if (directories.size() > 0 ) {
                for ( Directory directory : directories) {
                    if (directory.name.contentEquals(name)) {
                        return directory;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    /**
     * Save Directories in the ArrayList
     * @since 1.0.1
     * @param directories
     * @param hasChildren
     * @param numberOfChildren
     * @return 
     */
    public static List<Directory> saveInDirectories(List<Directory> directories, boolean hasChildren, int numberOfChildren) {
        try {
            
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
        return directories;
    }
    /**
     * Counts the number of null in a directory
     * @since 1.0.1
     * @param directories
     * @return 
     */
    public static int numberOfRootDirectories(List<Directory> directories) {
        try {
            int counter = 0;
            for ( Directory directory : directories ) {
                if (directory.parent == null) {
                    counter++;
                }
            }
            return counter;
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    /**
     * Checks if directory is duplicated
     * @since 1.0.1
     * @param name
     * @param directories
     * @param possition
     * @return 
     */
    public static boolean isDuplicated(String name, List<Directory> directories, int possition) {
        try {
            if ( directories.size() == 0 ) {
                return false;
            }
            for ( Directory directory : directories ) {
                if (directory.name.contentEquals(name) && directory.possition == possition) {
                    return true;
                }
            }
            return false;
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
}
