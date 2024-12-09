
package SecondComp2112Project;

import java.util.*;

class Node {
    String name;
    String type; 
    int size;
    Date lastModified;
    String accessLevel;
    List<Node> children;

    public Node(String name, String type, int size, Date lastModified, String accessLevel) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.lastModified = lastModified;
        this.accessLevel = accessLevel;
        this.children = new ArrayList<>();
    }
    
        public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }
 
    public Date getLastModified() {
        return lastModified;
    }

    public String getAccessLevel() {
        return accessLevel;
    }
        public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
} 