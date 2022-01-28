class Rectangle {
    protected int width , height;//เห็นเฉพาะใน class มันเอง ที่เป็น subclass (ถูก inherit)
    public Rectangle() {}
    
    public Rectangle(int width , int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth() {return width;}
    public void setWidth(int width) { this.width = width;}

    public int getHeight() {return height;}
    public void setHeight(int height) { this.height = height;}
    public int getArea(){
    return width*height;
    }
    @Override
    public String toString(){
        return "Retangle(" +
        "width = " + width +
        ", height = " + height +
        "}";
    }
}

class Square extends Rectangle {
    public Square(){}

    public Square(int side){
        width = height = side;
    }

    //เปลี่ยนแปลงจากต้นฉบับได้
    @Override
    public void setWidth (int width){
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight (int height){
        super.setHeight(height);
        super.setWidth(height);
    }
}

class RectangleFactory {
    public static Rectangle newRectangle(int width,int height){
        return new Rectangle(width,height);
    }

    public static Rectangle newSquare(int side){
    return new Rectangle(side, side);
    }
}

public class SOLID_L {
    public static void main (String[] args){
        Rectangle rc = new Rectangle(2, 3);
        useIt(rc);

        Rectangle sp = RectangleFactory.newSquare(5);
        useIt(sp);
    }
    static void useIt(Rectangle r){
        int width = r.getWidth();

        r.setHeight(10);

        System.out.println("Excepted Area " + (width * 10) + ". got "+ r.getArea());
    }
}
