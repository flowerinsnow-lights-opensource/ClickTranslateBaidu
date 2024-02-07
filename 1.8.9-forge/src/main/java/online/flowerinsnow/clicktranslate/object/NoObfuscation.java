package online.flowerinsnow.clicktranslate.object;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NoObfuscation
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SideOnly(Side.CLIENT)
public @interface NoObfuscation {
}
