
package com.github.jinchunzhao.space.annotation.checknumber;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 数字参数校验处理类
 *
 * @author jinchunzhao
 * @version 1.0
 * @date 2020-05-14 14:28
 */
public class CheckNumberValidator implements ConstraintValidator<CheckNumber, Object> {

    private CheckNumber checkNumber;

    @Override
    public void initialize(CheckNumber constraintAnnotation) {
        this.checkNumber = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null || StringUtils.isBlank(value.toString())) {
            //当参数值为null或者length = 0时，不做校验
            return true;
        }
        if (!StringUtils.isNumeric(value.toString())) {
            return false;
        }
        try {
            int max = checkNumber.max();
            int min = checkNumber.min();
            int curValue = Integer.parseInt(value.toString());
            if (curValue < min || curValue > max) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}