/*
 * Archive Class
 * @author Henry Rojas Douglas
 * @version 1.0.2
 * @copyright Henry Rojas Douglas
 * @license MIT
 * @package henryrojastarea1
 */
package henryrojastarea1;

import java.util.List;

public class Archive implements java.io.Serializable {
    /**
     * Archive name
     * @var
     * @since 1.0.1
     */
    protected String name;
    /**
     * Archive parent
     * @var
     * @since 1.0.1
     */
    protected String parent;
    /**
     * Archive hasChildren
     * @var
     * @since 1.0.1
     */
    protected boolean hasChildren;
    /**
     * Archive numberOfChildren
     * @var
     * @since 1.0.1
     */
    protected int numberOfChildren;
    /**
     * Archive possition
     * @var
     * @since 1.0.1
     */
    protected int possition;
    /**
     * Archive hasParent
     * @var
     * @since 1.0.1
     */
    protected boolean hasParent;
    /**
     * Text file
     * @var
     * @since 1.0.2
     */
    protected boolean isText;
    /**
     * Content
     * @var
     * @since 1.0.2
     */
    protected String content;
    /**
     * Constructor
     * @since 1.0.2
     * @param name
     * @param parent
     * @param hasChildren
     * @param numberOfChildren
     * @param possition
     * @param hasParent
     * @param isText
     * @param content 
     */
    public Archive(String name, String parent, boolean hasChildren, int numberOfChildren, int possition, boolean hasParent, boolean isText, String content) {
        this.name = name;
        this.parent = parent;
        this.hasChildren = hasChildren;
        this.numberOfChildren = numberOfChildren;
        this.possition = possition;
        this.hasParent = hasParent;
        this.isText = isText;
        this.content = content;
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
     * @since 1.0.2
     * @return 
     */
    public boolean isIsText() {
        return isText;
    }
    /**
     * @since 1.0.2
     * @param isText 
     */
    public void setIsText(boolean isText) {
        this.isText = isText;
    }
    /**
     * @since 1.0.2
     * @return 
     */
    public String getContent() {
        return content;
    }
    /**
     * @since 1.0.2
     * @param content 
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * Gets a directory from given name
     * @since 1.0.1
     * @param name
     * @param directories
     * @return 
     */
    public static Archive getDirectory(String name, List<Archive> directories) {
        try {
            if (directories.size() > 0 ) {
                for ( Archive directory : directories) {
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
     * Counts the number of null in a directory
     * @since 1.0.1
     * @param directories
     * @return 
     */
    public static int numberOfRootDirectories(List<Archive> directories) {
        try {
            int counter = 0;
            for ( Archive directory : directories ) {
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
    public static boolean isDuplicated(String name, List<Archive> directories, int possition) {
        try {
            if ( directories.isEmpty() ) {
                return false;
            }
            for ( Archive directory : directories ) {
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
