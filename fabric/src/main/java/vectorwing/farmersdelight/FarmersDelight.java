package vectorwing.farmersdelight;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.fabricators_of_create.porting_lib.config.ConfigRegistry;
import io.github.fabricators_of_create.porting_lib.config.ConfigType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.RecipeBookType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vectorwing.farmersdelight.common.CommonSetup;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.block.entity.CuttingBoardBlockEntity;
import vectorwing.farmersdelight.common.crafting.ingredient.ToolActionIngredient;
import vectorwing.farmersdelight.common.event.CommonEvents;
import vectorwing.farmersdelight.common.event.VillagerEvents;
import vectorwing.farmersdelight.common.item.DogFoodItem;
import vectorwing.farmersdelight.common.item.HorseFeedItem;
import vectorwing.farmersdelight.common.registry.*;
import vectorwing.farmersdelight.common.world.VillageStructures;


public class FarmersDelight implements ModInitializer
{
	public static final String MODID = "farmersdelight";
	public static final Logger LOGGER = LogManager.getLogger();
	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

	public static final RecipeBookType RECIPE_TYPE_COOKING = RecipeBookType.create("COOKING");

	public static ResourceLocation res(String name) {
		return new ResourceLocation(MODID, name);
	}

	@Override
	public void onInitialize() {

		ConfigRegistry.registerConfig(MODID, ConfigType.COMMON, Configuration.COMMON_CONFIG);
		ConfigRegistry.registerConfig(MODID, ConfigType.CLIENT, Configuration.CLIENT_CONFIG);

		ModSounds.SOUNDS.register();
		ModBlocks.BLOCKS.register();
		ModEffects.EFFECTS.register();
		ModParticleTypes.PARTICLE_TYPES.register();
		ModItems.ITEMS.register();
		ModEntityTypes.ENTITIES.register();
		ModEnchantments.ENCHANTMENTS.register();
		ModBlockEntityTypes.TILES.register();
		ModMenuTypes.MENU_TYPES.register();
		ModRecipeTypes.RECIPE_TYPES.register();
		ModRecipeSerializers.RECIPE_SERIALIZERS.register();
		ModBiomeFeatures.FEATURES.register();
		ModCreativeTabs.CREATIVE_TABS.register();
		ModPlacementModifiers.PLACEMENT_MODIFIERS.register();
		//ModBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register();
		ModLootFunctions.LOOT_FUNCTIONS.register();
		ModLootModifiers.LOOT_MODIFIERS.register();

		VillageStructures.init();
		CommonEvents.init();
		VillagerEvents.addTrades();


		CommonSetup.init();

		// new stuff
		ModBiomeModifiers.init();
		DogFoodItem.init();
		HorseFeedItem.init();

		ItemStorage.SIDED.registerForBlockEntity(CookingPotBlockEntity::getStorage, ModBlockEntityTypes.COOKING_POT.get());
		ItemStorage.SIDED.registerForBlockEntity(CuttingBoardBlockEntity::getStorage, ModBlockEntityTypes.CUTTING_BOARD.get());
	}
}
