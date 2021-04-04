package io.github.crucible.grimoire.mixins.gadomancy;

import makeo.gadomancy.common.aura.AuraEffectHandler;
import makeo.gadomancy.common.aura.AuraEffects;
import org.spongepowered.asm.mixin.Mixin;
import thaumcraft.api.aspects.Aspect;

@Mixin(value = AuraEffects.class, remap = false)
public abstract class MixinAuraEffects {

  static{
    AuraEffectHandler.registeredEffects.remove(Aspect.TREE);
    AuraEffectHandler.registeredEffects.remove(Aspect.PLANT);
  }

}
