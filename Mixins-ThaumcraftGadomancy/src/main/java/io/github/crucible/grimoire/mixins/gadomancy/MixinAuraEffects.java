package io.github.crucible.grimoire.mixins.gadomancy;

import java.util.Map;
import makeo.gadomancy.api.AuraEffect;
import makeo.gadomancy.common.aura.AuraEffectHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import thaumcraft.api.aspects.Aspect;

@Mixin(value = AuraEffectHandler.class, remap = false)
public abstract class MixinAuraEffects {

  @Shadow
  public static Map<Aspect, AuraEffect> registeredEffects;

  static{
    registeredEffects.remove(Aspect.TREE);
    registeredEffects.remove(Aspect.PLANT);
  }

}
