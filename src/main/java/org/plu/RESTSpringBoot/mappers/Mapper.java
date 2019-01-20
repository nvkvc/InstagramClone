package org.plu.RESTSpringBoot.mappers;

import java.util.function.Function;

public interface Mapper<F, T> extends Function<F, T> {
    T map(F from);

    @Override
    default T apply(F from){ return map(from); }
}
