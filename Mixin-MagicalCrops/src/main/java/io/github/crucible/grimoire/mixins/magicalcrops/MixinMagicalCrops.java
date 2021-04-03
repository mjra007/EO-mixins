package io.github.crucible.grimoire.mixins.magicalcrops;

import com.mark719.magicalcrops.blocks.BlockCropBooster;
import com.mark719.magicalcrops.blocks.BlockMagicalCrops;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.BlockBush;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value= BlockMagicalCrops.class, remap = false)
public abstract class MixinMagicalCrops extends BlockBush {

  Random random = new Random();

  private final float chanceOfDroppingEssence16Pulsars = 0.60f;
  private final float chanceOfDroppingEssence25Pulsars = 0.30f;
  private final float chanceOfDroppingSeeds25Pulsars = 0.95f;
  private final float chanceOfDroppingEssenceMoreThan25Pulsars = 0.30f;
  private final float chanceOfDroppingSeedMoreThan25Pulsars = 0.90f;

  @Shadow
  protected abstract Item func_149866_i();

  @Override
  public Item getItemDropped(int meta, Random random, int p_149650_3_){
    return null;
  }

  @Shadow protected abstract Item func_149865_P();

  @Override
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);
    if(metadata>=7){
      int numberOfPulsars = getNumberOfPulsars(world,x,y-2,z);//first block is dirt, second would be the pulsar

      if(numberOfPulsars <=13){
        ret.add(new ItemStack(this.func_149866_i(), 1, 0));//crop seed
        ret.add(new ItemStack(this.func_149865_P(), 1, 0));//crop essence
      }else if(numberOfPulsars <= 16){
        double chance = random.nextFloat();
        if(chance<=chanceOfDroppingEssence16Pulsars){
          ret.add(new ItemStack(this.func_149865_P(), 1, 0));
        }
        ret.add(new ItemStack(this.func_149866_i(), 1, 0));
      }else if (numberOfPulsars <=25){
        double chance = random.nextFloat();
        if(chance<=chanceOfDroppingEssence25Pulsars){
          ret.add(new ItemStack(this.func_149865_P(), 1, 0));
        }
        if(chance<=chanceOfDroppingSeeds25Pulsars){
          world.spawnParticle("witchMagic", x, y,z, 0f, 0f, 0f);
          world.spawnParticle("witchMagic", x, y,z, 0.3f, 0.5f, 0.2f);
          world.spawnParticle("witchMagic", x, y,z, 0.3f, 0f, 0.8f);
          ret.add(new ItemStack(this.func_149866_i(), 1, 0));
        }
      }else{
        double chance = random.nextFloat();
        if(chance<=chanceOfDroppingEssenceMoreThan25Pulsars){
          ret.add(new ItemStack(this.func_149865_P(), 1, 0));
        }
        if(chance<=chanceOfDroppingSeedMoreThan25Pulsars){
          world.spawnParticle("witchMagic", x, y,z, 0f, 0f, 0f);
          world.spawnParticle("witchMagic", x, y,z, 0.3f, 0.5f, 0.2f);
          world.spawnParticle("witchMagic", x, y,z, 0.3f, 0f, 0.8f);
          ret.add(new ItemStack(this.func_149866_i(), 1, 0));
        }
      }
    }else{
      ret.add(new ItemStack(this.func_149866_i(), 1, 0));
    }

    return ret;
  }

  public int getNumberOfPulsars(World world, int x, int y, int z){
    int counter = 0;

    while(world.blockExists(x, y-counter, z)  &&  world.getBlock(x,y-counter,z) instanceof BlockCropBooster ){
        counter++;
    }
    return counter;
  }

}
