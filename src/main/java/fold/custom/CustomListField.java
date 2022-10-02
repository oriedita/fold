package fold.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Use this class to define a custom list field in your extension of fold.
 *
 * @param <T> The type of the value
 * @param <V> An enum denoting the available fields that make up this extension.
 */
public class CustomListField<T, V extends Enum<V>> {
    private final Class<V> names;
    private final String namespace;
    private final Adapter<Map<V, Object>, T> factory;

    /**
     * Create a new CustomFieldList instance.
     *
     * @param namespace The namespace in the custom properties map
     * @param names     A list of valid names for fields in the fold file.
     * @param factory   A Factor which can convert a Map to T and back.
     */
    public CustomListField(String namespace, Class<V> names, Adapter<Map<V, Object>, T> factory) {
        this.names = names;
        this.namespace = namespace;
        this.factory = factory;
    }

    /**
     * Obtain a list of T from the custom properties map in a fold object.
     *
     * @param customMap Custom properties map in a fold object.
     * @return A list of T
     */
    public List<T> getValue(Map<String, Object> customMap) {
        Map<V, List<?>> vals = new HashMap<>();
        for (V name : names.getEnumConstants()) {
            vals.put(name, (List<?>) customMap.get(getKey(name)));
        }

        int size = 0;

        for (List<?> val : vals.values()) {
            if (val == null) continue;
            if (val.size() > size) {
                size = val.size();
            }
        }

        List<T> out = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Map<V, Object> constructorMap = new HashMap<>();

            for (V v : vals.keySet()) {
                List<?> val = vals.get(v);

                if (val == null) continue;

                Object value = vals.get(v).size() > i ? vals.get(v).get(i) : null;
                constructorMap.put(v, value);
            }

            T apply = null;
            try {
                apply = factory.convert(constructorMap, null);
            } catch (Exception e) {
                // ignore
            }

            if (apply != null) {
                out.add(apply);
            }
        }

        return out;
    }

    private String getKey(V name) {
        return namespace + ":" + name;
    }

    /**
     * Set the value in the custom properties map of a fold file.
     *
     * @param customMap The custom properties map of a fold file.
     * @param val       The value to set.
     */
    public void setValue(Map<String, Object> customMap, List<T> val) {
        Map<String, List<Object>> tempMap = new HashMap<>();
        for (V v : names.getEnumConstants()) {
            tempMap.put(getKey(v), new ArrayList<>());
        }

        for (T t : val) {
            Map<V, Object> map = factory.convertBack(t, new HashMap<>());

            for (V v : map.keySet()) {
                tempMap.get(getKey(v)).add(map.get(v));
            }
        }

        for (Map.Entry<String, List<Object>> entry : tempMap.entrySet()) {
            if (entry.getValue().size() > 0) {
                customMap.put(entry.getKey(), entry.getValue());
            } else {
                customMap.remove(entry.getKey());
            }
        }
    }
}
