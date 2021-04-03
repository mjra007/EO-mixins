package io.github.crucible.grimoire.mixins.arsmagica2;

import io.github.crucible.grimoire.mixins.cooldown.Cooldown;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class Util {

  public static <T> void checkIfSpellIsOnCooldown(EntityLivingBase caster,
      CallbackInfoReturnable<T> ci, T returnValue) {
    if(Cooldown.getCooldown("arsMagica") != null && Cooldown.isInCooldown("arsMagica")){
      if(caster instanceof EntityPlayerMP)
        ((EntityPlayerMP)caster).addChatMessage(new ChatComponentText("Spell is on a global cooldown! "+Cooldown.getTimeLeft("arsMagica") +" seconds left"));
      ci.setReturnValue(returnValue);
      ci.cancel();
    }else{
      Cooldown.start("arsMagica", 240);
    }
  }

}
