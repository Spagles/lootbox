package net.xuwu.lootbox;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public final class LootBoxClientHooks {
    private LootBoxClientHooks() {}

    public static boolean isShiftDown() {
        return Screen.hasShiftDown();
    }

    public static float currentLuck() {
        var player = Minecraft.getInstance().player;
        return player == null ? 0.0F : player.getLuck();
    }
}
