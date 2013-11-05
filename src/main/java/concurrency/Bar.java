package concurrency;

/**
 * User: ViaPro
 * Date: 11/5/13
 * Time: 7:42 PM
 */
class Foo {
    public int a;
    public Foo() { a = 3; }
    public void addFive() { a += 5; }
    public void superAddFive(){}
}

public class Bar extends Foo{
    public int a;
    public Bar() { a = 8; }
    public void addFive() { a +=5; }
    public void superAddFive() { super.a +=5;}

    public static void main(String[] args) {

        Foo foo = new Bar();
        Bar bar = new Bar();
        System.out.println("foo: "+ foo.a);
        System.out.println("bar: "+ bar.a);

        foo.addFive();
        bar.superAddFive();
        System.out.println("foo: "+ foo.a);
        System.out.println("bar: "+ bar.a);

        foo.superAddFive();
        bar.addFive();
        System.out.println("foo: "+ foo.a);
        System.out.println("bar: "+ bar.a);
    }

}