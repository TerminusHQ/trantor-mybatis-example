package io.terminus.trantor.demo.myBatis;

import io.terminus.trantor.api.ModuleDefinition;
import org.jetbrains.annotations.NotNull;

/**
 * Created by hedy on 2021/9/6.
 */
public class Module extends ModuleDefinition {
    @Override
    protected @NotNull String getKey() {
        return "trantor_demo_mybatis";
    }
}
