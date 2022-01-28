import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

enum Color { 
    RED , GREEN , BLUE
}

enum Size {
    SMALL , MEDIUM , LARGE
}

enum Price {
    LOW , MEDIUM ,HIGH
}

class Product{
    public String name;
    public Color color;
    public Size size;

    public Product(String name , Color color , Size size){
        this.name = name;
        this.color = color;
        this.size = size;
    }
}

class ProductFilter{
    public Stream<Product> filterByColor (List<Product> products , Color color){
        return products.stream().filter(p -> p.color == color);
    }
    public Stream<Product> filterBySize (List<Product> products , Size size){
        return products.stream().filter(p -> p.size == size);
    }

    public Stream<Product> filterByColorAndSize (List<Product> products , Color color , Size size){
        return products.stream().filter(p -> p.color == color && p.size == size);
    }

}

interface Specification<T>{
    boolean isSatisfied(T item);
}


class ColorSpecification implements Specification<Product>{
    private Color color;
    public ColorSpecification(Color color){
        this.color = color;
    }

    @Override //check ตัวมันเองตรงามคุณสมบัติที่ส่งมามั้ย
    public boolean isSatisfied(Product item){
        return item.color == color;
    }
}

class SizeSpecification implements Specification<Product>{
    private Size size;
    public SizeSpecification(Size size){
        this.size = size;
    }

    @Override //check ตัวมันเองตรงามคุณสมบัติที่ส่งมามั้ย
    public boolean isSatisfied(Product item){
        return item.size == size;
    }
}

interface Filter<T>{
    Stream<T> filter(List<T> items, Specification<T> spec);
}

class BetterFilter implements Filter<Product> {
    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec){
        return items.stream().filter(p -> spec.isSatisfied(p));
    }
}

class AndSpecification<T> implements Specification<T>{
    private Specification first , second;
    public AndSpecification(Specification<T> first , Specification<T> second){
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item){
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}

public class SOLID_O {
    public static void main (String[] args) throws Exception{
    Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
    Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
    Product house = new Product("House", Color.BLUE, Size.LARGE);

    List<Product> product = List.of(apple,tree,house);

    ProductFilter pf = new ProductFilter();
    System.out.println("Green product (old): ");
    pf.filterByColor(product , Color.GREEN).forEach(p -> System.out.println(" - " + p.name + " is green"));

    System.out.println("Large product (old): ");
    pf.filterBySize(product , Size.LARGE).forEach(p -> System.out.println(" - " + p.name + " is large"));

    System.out.println("Green & Large product (old): ");
    pf.filterByColorAndSize(product , Color.GREEN, Size.LARGE).forEach(p -> System.out.println(" - " + p.name + " is green & large"));
    
        BetterFilter bf = new BetterFilter();
        System.out.println("Green Product (new) : ");
        bf.filter(product, new ColorSpecification(Color.GREEN))
        .forEach(p -> System.out.println(" - " + p.name + " is green"));

        System.out.println("Green and Large Product (new) : ");
        bf.filter(product, new AndSpecification<>(
            new ColorSpecification(Color.BLUE), // add ได้เรื่อยๆ
            new SizeSpecification(Size.LARGE)))
            .forEach(p -> System.out.println(" - "+ p.name +" is blue and large")); 
        


    }
}
