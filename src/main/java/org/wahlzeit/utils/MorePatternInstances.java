package org.wahlzeit.utils;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
public @interface MorePatternInstances {
    PatternInstance[] value();
}