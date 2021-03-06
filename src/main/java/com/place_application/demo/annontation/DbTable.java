package com.place_application.demo.annontation;

import java.lang.annotation.*;

/**
 * @author Florence
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DbTable {
    String value();
}
