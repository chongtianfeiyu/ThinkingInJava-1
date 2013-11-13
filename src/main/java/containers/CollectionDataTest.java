package containers;

import util.CollectionData;
import util.Generator;

import java.util.LinkedHashSet;
import java.util.Set;

import static util.Print.*;

/**
 * User: ViaPro
 * Date: 11/13/13
 * Time: 3:14 PM
 */

class StrGen implements Generator<String> {
    private static final String[] STRS = "The overriding design goal for Markdown's formatting syntax is to make it as readable as possible. The idea is that a Markdown-formatted document should be publishable as-is, as plain text, without looking like it's been marked up with tags or formatting instructions."
            .split(" ");
    private static final int LEN = STRS.length;
    private int index = 0;

    @Override
    public String next() {
        return STRS[index++ % LEN];
    }
}

public class CollectionDataTest {

    public static void main(String[] args) {
        Generator<String> gen = new StrGen();
        Set<String> set = new LinkedHashSet<String>(new CollectionData<String>(gen, 9));
        print(set);
        set.addAll(CollectionData.list(gen,9));
        print(set);
    }

}
