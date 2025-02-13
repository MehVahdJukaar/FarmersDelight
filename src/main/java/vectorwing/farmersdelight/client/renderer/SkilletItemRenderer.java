package vectorwing.farmersdelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.registry.ModDataComponents;

public class SkilletItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    public SkilletItemRenderer() {
    }

    @Override
    public void render(ItemStack stack, ItemDisplayContext mode, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        //render block
        BlockItem item = ((BlockItem) stack.getItem());
        BlockState state = item.getBlock().defaultBlockState();


        Minecraft mc = Minecraft.getInstance();

        ItemStackWrapper stackWrapper = stack.getOrDefault(ModDataComponents.SKILLET_INGREDIENT.get(), ItemStackWrapper.EMPTY);
        ItemStack ingredientStack = stackWrapper.getStack();

        float animation = 0;

        if (!ingredientStack.isEmpty()) {
            poseStack.pushPose();
            poseStack.translate(0.5, 1 / 16f, 0.5);

            long gameTime = mc.level.getGameTime();
            if (stack.has(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get()) && mode != ItemDisplayContext.GUI) {
                long time = stack.get(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get());
                float partialTicks = mc.getTimer().getGameTimeDeltaPartialTick(false);
                animation = ((gameTime - time) + partialTicks) / SkilletItem.FLIP_TIME;
                animation = Mth.clamp(animation, 0, 1);
                float maxH = 0.4F;
                poseStack.translate(0, maxH * Mth.sin(animation * Mth.PI), 0);
                float rotationAnimation = stack.getOrDefault(ModDataComponents.SKILLET_FLIPPED.get(), false) ? animation + 1.0F : animation;
                poseStack.mulPose(Axis.XP.rotationDegrees(180 * rotationAnimation));
            } else {
                poseStack.mulPose(Axis.XP.rotationDegrees(stack.getOrDefault(ModDataComponents.SKILLET_FLIPPED.get(), false) ? 180 : 0));
            }

            poseStack.mulPose(Axis.XP.rotationDegrees(90));
            poseStack.scale(0.5F, 0.5F, 0.5F);

            if (mode != ItemDisplayContext.GUI) {
                var itemRenderer = mc.getItemRenderer();
                itemRenderer.renderStatic(ingredientStack, ItemDisplayContext.FIXED, packedLight,
                        packedOverlay, poseStack, buffer, null, 0);
            }

            poseStack.popPose();
        }

        poseStack.pushPose();

        if (animation != 0 && mode.firstPerson()) {
            poseStack.translate(0, 0, 1);
            poseStack.mulPose(Axis.XN.rotationDegrees(Mth.sin(animation * Mth.TWO_PI) * 15));
            poseStack.translate(0F, 0, -1);
            poseStack.translate(0, 0, -Mth.sin(animation * Mth.PI) * 0.2);
        }
        mc.getBlockRenderer().renderSingleBlock(state, poseStack, buffer, packedLight, packedOverlay);

        poseStack.popPose();

    }
}
