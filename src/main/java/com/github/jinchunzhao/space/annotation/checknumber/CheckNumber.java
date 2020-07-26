
package com.github.jinchunzhao.space.annotation.checknumber;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 枚举校验注解
 *
 * @author jinchunzhao
 * @version 1.0
 * @date 2020-05-14 14:49
 */
@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckNumberValidator.class)
public @interface CheckNumber {

    /**
     * @return size the element must be higher or equal to
     */
    int min() default 0;

    /**
     * @return size the element must be lower or equal to
     */
    int max() default Integer.MAX_VALUE;

    String message() default "当前字符串必须为数字类型";

    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};
}