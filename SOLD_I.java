class Document {

}

interface Machine {
    void print(Document d) throws Exception;

    void fax(Document d) throws Exception;

    void scan(Document d) throws Exception;
}

class MulipleFunctionPrinter implements Machine , Printer , Scanner , Fax{

    @Override
    public void print(Document d) throws Exception {

    }

    @Override
    public void fax(Document d) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void scan(Document d) throws Exception {
        // TODO Auto-generated method stub

    }

}

class OldFashionPrinter implements Machine {

    @Override
    public void fax(Document d) throws Exception {
        throw new Exception();
    }

    @Override
    public void print(Document d) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void scan(Document d) throws Exception {
        throw new Exception();

    }

}

interface Printer {
    void print(Document d)throws Exception;
}

interface Scanner {
    void scan(Document d)throws Exception;
}

interface Fax {
    void fax(Document d)throws Exception;
}

class JustAPrinter implements Printer {
    @Override
    public void print(Document d) {
    }
}

class Photocopier implements Printer, Scanner {
    @Override
    public void print(Document d) {
    }

    @Override
    public void scan(Document d) {
    }
}

public class SOLD_I {
    public static void main(String[] args) {

    }
}
