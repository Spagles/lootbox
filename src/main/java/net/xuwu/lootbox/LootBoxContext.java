package net.xuwu.lootbox;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

/** 条件和 KJS 回调可读取的开箱上下文。 */
public record LootBoxContext(ServerPlayer player, Level level, float luck) {
    public boolean hasPlayer() {
        return player != null;
    }
}
