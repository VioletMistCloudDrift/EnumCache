package neko.violetmist.enumcache;

import neko.violetmist.enumcache.api.ICacheableEnum;

public class EnumCache {
    public static <T extends Enum<T>> boolean clearCacheForEnum(Class<T> clazz) {
        if (!clazz.isEnum()) return false;
        T[] values = clazz.getEnumConstants();
        if (values == null || values.length == 0) return false;
        T instance = values[0];
        if (instance instanceof ICacheableEnum cacheable) {
            cacheable.enumCache$clearValuesCache();
            return true;
        }
        return false;
    }
}
