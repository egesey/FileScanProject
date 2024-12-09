
package SecondComp2112Project;


import java.util.*;

public class FileProperty {

    private final String name;
    private String extension;
    private Date lastModified;
    private int size; 
    private String accessLevel;

    public FileProperty(String name, String extension, Date lastModified, int size, String accessLevel) {
        this.name = name;
        this.extension = extension;
        this.lastModified = lastModified;
        this.size = size;
        this.accessLevel = accessLevel;
    }

    public String getName() {
        return name;
    }
    

    public String getExtension() {
        return extension;
    }
    
    public String setExtension(String extensionSet){
        return this.extension = extensionSet;
    }

    public Date getLastModified() {
        return lastModified;
    }

    
    public int getSize() {
        return size;
    }
    
 public int setSize(int sizeSet){
        return this.size = sizeSet;
    }
    
    public String getAccessLevel() {
        return accessLevel;
    }
    
    public String setGetAccessLevel(String SetLvl){
        return this.accessLevel = SetLvl;
    }

    public void updateLastModified(Date newDate) {
        this.lastModified = newDate;
    }
 
    public void displayDetails() {
        System.out.println("File Name: " + name);
        System.out.println("Extension: " + extension);
        System.out.println("Last Modified: " + lastModified);
        System.out.println("Size: " + size + " bytes");
        System.out.println("Access Level: " + accessLevel);
    }
}