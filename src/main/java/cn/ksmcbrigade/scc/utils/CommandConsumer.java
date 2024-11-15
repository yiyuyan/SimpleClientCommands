package cn.ksmcbrigade.scc.utils;

@FunctionalInterface
public interface CommandConsumer<T,U,V> {
    void accept(T t, U u, V v) throws IllegalAccessException;
}
