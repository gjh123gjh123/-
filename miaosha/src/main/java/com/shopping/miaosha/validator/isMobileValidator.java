package com.shopping.miaosha.validator;

import com.alibaba.druid.util.StringUtils;
import com.shopping.miaosha.util.ValidatatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class isMobileValidator implements ConstraintValidator<isMobile,String> {

    private boolean required = false;
    public void initialize(isMobile constraintAnnotation){
        constraintAnnotation.required();
    }

    public boolean isValid(String value, ConstraintValidatorContext context){
        if(required){

        }else {
            if(StringUtils.isEmpty(value)){
                return true;
            }else {
                return ValidatatorUtil.isMobile(value);
            }
        }
        return false;
    }
}
//package com.shopping.miaosha.validator;
//
//        import com.alibaba.druid.util.StringUtils;
//        import com.shopping.miaosha.util.ValidatatorUtil;
//
//        import javax.validation.ConstraintValidator;
//        import javax.validation.ConstraintValidatorContext;
//
//public class isMobileValidator implements ConstraintValidator<isMobile,String> {
//
//    private boolean required;
//
//    @Override
//    public void initialize(isMobile isMobile) {
//        required = isMobile.required();
//    }
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        if (!required && StringUtils.isEmpty(value)) {
//            return true;
//        }
//        return ValidatatorUtil.isMobile(value);
//    }
//}

