package online.flowerinsnow.clicktranslatebaidu.client.object;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NoObfuscation
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Environment(EnvType.CLIENT)
public @interface NoObfuscation {
}
