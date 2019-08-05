package com.codingdojo.ecommerce.validator;
    import org.springframework.stereotype.Component;
    import org.springframework.validation.Errors;
    import org.springframework.validation.Validator;
    import com.codingdojo.ecommerce.models.*;
    @Component
    public class VendorValidator implements Validator {
        
        // 1
        @Override
        public boolean supports(Class<?> clazz) {
            return Vendor.class.equals(clazz);
        }
        
        // 2
        @Override
        public void validate(Object target, Errors errors) {
            Vendor vendor = (Vendor) target;
            
            if (!vendor.getPasswordConfirmation().equals(vendor.getPassword())) {
                // 3
                errors.rejectValue("passwordConfirmation", "Match");
            }         
        }
    }