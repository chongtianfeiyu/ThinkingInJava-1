package containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static util.Print.*;

/**
 * User: ViaPro
 * Date: 11/13/13
 * Time: 11:55 AM
 *
 * 证明在Collections中的
 * List<T> nCopies(int n, T o) 是返回一个java.util.Collections$CopiesList子类，复制的是同一对象的地址
 * fill(List<? super T> list, T obj) 使用obj对特定的list的所有元素进行替换
 *
 */
class StringAddress {
    private String s;

    public StringAddress(String str) {
        s = str;
    }

    @Override
    public String toString() {
        return super.toString() + " " + s;   //   Object.toString()用于获得该类的名字
    }
}

public class FillingLists {
    public static void main(String[] args) {
        List<StringAddress> list = new ArrayList<StringAddress>(Collections.nCopies(2, new StringAddress("Hello")));
        print(Collections.nCopies(1, new StringAddress("Hello")).getClass());
        print(list);
        Collections.fill(list, new StringAddress("World! "));
        print(list);
    }
}

/*
[containers.StringAddress@4c56291a Hello, containers.StringAddress@4c56291a Hello, containers.StringAddress@4c56291a Hello, containers.StringAddress@4c56291a Hello]
[containers.StringAddress@2ca4911d World! , containers.StringAddress@2ca4911d World! , containers.StringAddress@2ca4911d World! , containers.StringAddress@2ca4911d World! ]
 */
