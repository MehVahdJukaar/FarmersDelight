package vectorwing.farmersdelight.common.loot.modifier;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.fabricators_of_create.porting_lib.loot.IGlobalLootModifier;
import io.github.fabricators_of_create.porting_lib.loot.LootModifier;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.Configuration;

import java.util.function.Supplier;

import static net.minecraft.world.level.storage.loot.LootTable.createStackSplitter;

/**
 * Credits to Commoble for this implementation!
 */
public class AddLootTableModifier extends LootModifier
{
	public static final Supplier<Codec<AddLootTableModifier>> CODEC = Suppliers.memoize(() ->
			RecordCodecBuilder.create(inst -> LootModifier.codecStart(inst)
					.and(ResourceLocation.CODEC.fieldOf("lootTable").forGetter((m) -> m.lootTable))
					.apply(inst, AddLootTableModifier::new)));

	private final ResourceLocation lootTable;

	protected AddLootTableModifier(LootItemCondition[] conditionsIn, ResourceLocation lootTable) {
		super(conditionsIn);
		this.lootTable = lootTable;
	}

	@NotNull
	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		if (Configuration.GENERATE_FD_CHEST_LOOT.get()) {
			LootTable extraTable = context.getResolver().getLootTable(this.lootTable);
			extraTable.getRandomItemsRaw(context, LootTable.createStackSplitter(context.getLevel(), generatedLoot::add));
		}
		return generatedLoot;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC.get();
	}
}
