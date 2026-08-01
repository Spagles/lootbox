package net.xuwu.lootbox;

import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = LootBoxMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class LootBoxClientEvents {
    private LootBoxClientEvents() {}

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((ItemStack stack, int tintIndex) -> {
            LootBoxDefinition definition = LootBoxItem.getDefinition(stack);
            return tintIndex == 0 && definition != null ? 0xFF000000 | definition.color() : 0xFFFFFFFF;
        }, LootBoxMod.LOOT_BOX.get());
    }
}
