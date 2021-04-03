package io.github.crucible.grimoire.mixins.arsmagica2;

import am2.api.spell.enums.SpellModifiers;
import am2.spell.modifiers.Lunar;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Lunar.class, remap = false)
public abstract class MixinLunarSpellCooldown {

    @Inject(method = "getModifier", at = @At("HEAD"), cancellable = true)
    private void getModifier(SpellModifiers type, EntityLivingBase caster, Entity target, World world, byte[] metadata, CallbackInfoReturnable<Float> ci)
    {
      Util.checkIfSpellIsOnCooldown(caster,ci, 1f );
    }
}
