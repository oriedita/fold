package fold.custom;

/**
 * Helper interface to provide a contract to classes which can convert from T to V and back.
 * <p>
 * Users of this interface provide an instance of both objects. The converter class will fill the second object using the first object and vice versa.
 *
 * @param <T> First class.
 * @param <V> Second class.
 */
public interface Adapter<T, V> {
    /**
     * Convert from type T to type V.
     *
     * @param from Input value of type T.
     * @param to   Output value of type V.
     * @return Output value of type V.
     */
    V convert(T from, V to);

    /**
     * Convert from type V to type T.
     *
     * @param from Input value of type V.
     * @param to   Output value of type T.
     * @return Output value of type T.
     */
    T convertBack(V from, T to);
}
