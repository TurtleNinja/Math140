package hw4;

public class Rectangle {

    private int width;
    private int length;

    public Rectangle(int width, int length) {
        this.width = width;
        this.length = length;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getLength(){
        return length;
    }
    
    public void setWidth(int width){
        this.width = width;
    }
    
    public void setLength(int length){
        this.length = length;
    }

    public boolean equals(Object o) {
        boolean flag = false;
        
        // return false if o is null or o isn't a Rectangle object
        if (o != null || getClass() != o.getClass()) {
            if (this == o) { // this and o refer to the same object
                flag = true;
            } 
            else {  // different objects, return true if they contain 
                    // the same content
                Rectangle r = (Rectangle) o;
                if (r.width == width && r.length == length){
                    flag = true;
                }
            }
        }
        return flag;
    }
    
    public String toString(){
        return "width: " + width + " length: " + length;
    }
}

