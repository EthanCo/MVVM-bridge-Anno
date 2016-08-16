package com.ethanco.mvvm_bridge_anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by YOLANDA on 2016-01-21.
 */
@Retention(RetentionPolicy.SOURCE)
//@Target(ElementType.METHOD)
public @interface MVVM {

    //    Class value();
    Class View();

    Class ViewModel();
}
