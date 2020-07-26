
package com.github.jinchunzhao.space.annotation.checkstrarray;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字符串数组检验注解
 *
 * @author JinChunZhao
 * @version 1.0
 * @date 2020-07-03 11:05
 */
@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckArrayValidator.class)
public @interface CheckStrArray {

    /**
     * @return size the item must be higher or equal to
     */
    int itemLength() default 20;

    /**
     * @return size the element must be lower or equal to
     */
    int max() default 10;

    String message() default "当前字符串必须用逗号分隔，单个字符串不超过20字，字符串个数不能超过10个";

    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};
}