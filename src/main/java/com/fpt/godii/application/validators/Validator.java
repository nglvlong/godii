package com.fpt.godii.application.validators;

import com.fpt.godii.application.exception.FOBadRequestException;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.function.*;
import java.util.regex.Pattern;

public class Validator<T> {
    private final T value;

    private Validator(T value) {
        this.value = value;
    }

    public static <T> Validator<T> of(T value) {
        return new Validator<>(value);
    }

    public Validator<T> requireNonNull(String messageCode) {
        if (Objects.isNull(value)) {
            throw new FOBadRequestException(messageCode);
        }

        return this;
    }

    public Validator<T> requireNonBlank(Function<T, String> apply, String messageCode) {
        if (ObjectUtils.isEmpty(apply.apply(value))) {
            throw new FOBadRequestException(messageCode);
        }

        return this;
    }

    public Validator<T> requireNonDefaultInteger(Function<T, Integer> apply, String messageCode) {
        Integer number = apply.apply(value);
        if (number == null || number == 0) {
            throw new FOBadRequestException(messageCode);
        }

        return this;
    }

    public Validator<T> requireNonDefaultInt(ToIntFunction<T> apply, String messageCode) {
        if (apply.applyAsInt(value) == 0) {
            throw new FOBadRequestException(messageCode);
        }

        return this;
    }

    public Validator<T> requireNonDefaultLong(Function<T, Long> apply, String messageCode) {
        Long number = apply.apply(value);
        if (number == null || number == 0) {
            throw new FOBadRequestException(messageCode);
        }

        return this;
    }

    public Validator<T> requireNonDefaultLong(ToLongFunction<T> apply, String messageCode) {
        if (apply.applyAsLong(value) == 0) {
            throw new FOBadRequestException(messageCode);
        }

        return this;
    }

    public Validator<T> requireValidEmail(Function<T, String> apply, String messageCode) {
        String email = apply.apply(value);
        if (!isValidEmail(email)) {
            throw new FOBadRequestException(messageCode);
        }
        return this;
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.compile(regex).matcher(email).matches();
    }

    public Validator<T> requireNonDefaultDouble(Function<T, Double> apply, String messageCode) {
        Double number = apply.apply(value);
        if (number == null || number == 0) {
            throw new FOBadRequestException(messageCode);
        }

        return this;
    }

    public Validator<T> requireNonDefaultDouble(ToDoubleFunction<T> apply, String messageCode) {
        if (apply.applyAsDouble(value) == 0) {
            throw new FOBadRequestException(messageCode);
        }

        return this;
    }

    public Validator<T> requireNonDefaultFloat(Function<T, Float> apply, String messageCode) {
        Float number = apply.apply(value);
        if (number == null || number == 0) {
            throw new FOBadRequestException(messageCode);
        }

        return this;
    }

    public Validator<T> requireNonDefaultDate(Function<T, Timestamp> apply, String messageCode) {
        Timestamp date = apply.apply(value);
        if (date == null) {
            throw new FOBadRequestException(messageCode);
        }

        return this;
    }

    public <P> Validator<T> validate(Function<T, P> apply, Predicate<P> condition, String messageCode) {
        if (condition.test(apply.apply(value))) {
            throw new FOBadRequestException(messageCode);
        }

        return this;
    }

//    public <P> Validator<T> validate(Function<T, P> apply, Predicate<P> condition, Supplier<? extends RuntimeException> exception) {
//        if (condition.test(apply.apply(value))) {
//            throw exception.get();
//        }
//
//        return this;
//    }

    public T get() {
        return value;
    }


}
