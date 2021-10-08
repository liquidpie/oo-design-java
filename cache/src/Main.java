import com.vivek.cache.Cache;
import com.vivek.cache.factories.CacheFactory;

public class Main {

    public static void main(String[] args) {
        Cache<Integer, Integer> cache = new CacheFactory<Integer, Integer>().lfuCache(3);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.get(2);
        cache.get(1);
        cache.put(4, 4);
    }

}
