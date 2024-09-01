package vectorwing.farmersdelight.common.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.block.RichSoilBlock;
import vectorwing.farmersdelight.common.block.RichSoilFarmlandBlock;
import vectorwing.farmersdelight.common.tag.ModTags;

/**
 * Fabric should <b>really</b> have an event for this...
 * This is the bare minimum to keep parity with Forge.
 */
@Mixin(CactusBlock.class)
public class CactusBlockMixin {
    @ModifyExpressionValue(method = "canSurvive", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    private boolean farmersdelightrefabricated$allowPlantsOnPitcherCrops(boolean original, BlockState state, LevelReader level, BlockPos pos) {
        if (state.getBlock() != (Object)this)
            return original;

        if (level.getBlockState(pos.below()).getBlock() instanceof RichSoilBlock)
            return !((Block)(Object)this).builtInRegistryHolder().is(ModTags.DOES_NOT_SURVIVE_RICH_SOIL);

        if (level.getBlockState(pos.below()).getBlock() instanceof RichSoilFarmlandBlock)
            return ((Block)(Object)this).builtInRegistryHolder().is(ModTags.SURVIVES_RICH_SOIL_FARMLAND);

        return original;
    }
}
