package com.peait.peaituser.validata;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {IsExistNotIdValidata.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@NotNull
public @interface IsExistNotId {

    int id() default 0;
    String tableName();
    String fileName();
    String  entityName();

    String message() default "已存在";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
