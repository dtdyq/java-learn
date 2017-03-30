package thread;

/**
 * Created by Admin on 2017/3/25.
 * 如果程序中有多个线程访问不安全的集合类，可以使用Collections提供的类方法把这些
 * 集合包装成线程安全的集合：
 *      synchronizedCollection(Collection<T> c):返回指定collection对应的线程
 *          安全的Collection
 *      其他对应静态方法：
 *      synchronizedSet
 *      synchrinizedList
 *      synchronizedMap
 *      synchronizedSortedSet
 *
 * 线程安全的集合类：
 *      以Concurrent开头的集合类：并发写入时有较好的性能
 *      以CopyOnWrite开头的集合类：读取操作较频繁的场景中，有较好的性能
 */
public class TestCollection {
    public static void main(String[] args){

    }
}
