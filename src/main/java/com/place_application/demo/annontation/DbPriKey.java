package com.place_application.demo.annontation;

import java.lang.annotation.*;

/**
 * @author Florence
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DbPriKey {
}
