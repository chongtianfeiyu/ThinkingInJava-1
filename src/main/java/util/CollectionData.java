package util;

import java.util.ArrayList;

/**
 * User: ViaPro
 * Date: 11/13/13
 * Time: 1:55 PM
 *
 * 适配器模式：将Generator适配到CollectionData的构造器中
 *
 */
public class CollectionData<T> extends ArrayList<T> {

    public CollectionData(Generator<T> gen, int quantity) {
        for (int i = 0; i < quantity; i++)
            add(gen.next());
    }
    //便利方法
    public static <T> CollectionData<T> list(Generator<T> gen, int quantity) {
        return new CollectionData<T>(gen, quantity);
    }
}
