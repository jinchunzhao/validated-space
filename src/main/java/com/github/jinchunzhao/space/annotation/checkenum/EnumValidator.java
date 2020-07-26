
package com.github.jinchunzhao.space.annotation.checkenum;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 枚举参数校验处理类
 *
 * @author jinchunzhao
 * @version 1.0
 * @date 2020-05-14 15:31
 */
public class EnumValidator implements ConstraintValidator<EnumValid, String> {

    // 枚举校验注解
    private EnumValid annotation;

    @Override
    public void initialize(EnumValid constraintAnnotation) {

        annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = false;

        Class<?> cls = annotation.target();
        boolean ignoreEmpty = annotation.ignoreEmpty();

        // target为枚举，并且value有值，或者不忽视空值，才进行校验
        if (cls.isEnum() && (StringUtils.isNotEmpty(value) || !ignoreEmpty)) {
            Object[] objects = cls.getEnumConstants();
            try {
                Method method = cls.getMethod("getCode");
                for (Object obj : objects) {
                    Object code = method.invoke(obj);
                    if (value.equalsIgnoreCase(code.toString())) {
                        result = true;
                        break;
                    }
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                log.warn("EnumValidator call isValid() method exception.");
                result = false;
            }
        } else {
            result = true;
        }
        return result;
    }
}