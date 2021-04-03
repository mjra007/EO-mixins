package io.github.crucible.grimoire.mixins.arsmagica2;

import am2.spell.components.Daylight;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Daylight.class, remap = false)
public abstract class MixinDaylightCooldown {

  @Inject(method = "applyEffectBlock", at = @At("HEAD"), cancellable = true)
  public void applyEffectBlock(ItemStack stack, World world, int blockx, int blocky, int blockz, int blockFace, double impactX, double impactY, double impactZ, EntityLivingBase caster,  CallbackInfoReturnable<Boolean> ci) {
    Util.checkIfSpellIsOnCooldown(caster, ci, false);
  }

  @Inject(method = "applyEffectEntity", at = @At("HEAD"), cancellable = true)
  public void applyEffectEntity(ItemStack stack, World world, EntityLivingBase caster, Entity target,  CallbackInfoReturnable<Boolean> ci) {
    Util.checkIfSpellIsOnCooldown(caster, ci, false);
  }
}
