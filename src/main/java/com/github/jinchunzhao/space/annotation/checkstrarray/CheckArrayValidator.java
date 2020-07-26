
package com.github.jinchunzhao.space.annotation.checkstrarray;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * 字符串数组校验类
 *
 * @author JinChunZhao
 * @version 1.0
 * @date 2020-07-03 11:06
 */
public class CheckArrayValidator implements ConstraintValidator<CheckStrArray, Object> {

    private CheckStrArray checkStrArray;

    @Override
    public void initialize(CheckStrArray constraintAnnotation) {
        this.checkStrArray = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (Objects.isNull(value) || StringUtils.isBlank(value.toString())) {
            //当参数值为null或者length = 0时，不做校验
            return true;
        }
        try {
            int max = checkStrArray.max();
            int itemLength = checkStrArray.itemLength();
            String arrayStr = value.toString();
            String[] split = arrayStr.split(",");
            if(Objects.nonNull(split) || split.length == 0){
                return true;
            }
            //字符串数组长度大于默认值
            if (split.length > max){
                return false;
            }
            //字符串数组，分割后每一项长度大于默认值
            for (int i = 0,len = split.length; i < len;i++){
                String item = split[i];
                if(item.length() > itemLength){
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}