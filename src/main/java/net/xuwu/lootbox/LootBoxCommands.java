package net.xuwu.lootbox;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

/** /lootbox give 命令，方便测试和 KJS/datapack 服务器发放组件化箱子。 */
public final class LootBoxCommands {
    private LootBoxCommands() {}

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("lootbox")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("give")
                        .then(Commands.argument("target", EntityArgument.player())
                                .then(Commands.argument("id", ResourceLocationArgument.id())
                                        .executes(context -> give(context.getSource(), EntityArgument.getPlayer(context, "target"),
                                                ResourceLocationArgument.getId(context, "id"), 1))
                                        .then(Commands.argument("count", IntegerArgumentType.integer(1, 64))
                                                .executes(context -> give(context.getSource(), EntityArgument.getPlayer(context, "target"),
                                                        ResourceLocationArgument.getId(context, "id"), IntegerArgumentType.getInteger(context, "count"))))))));
    }

    private static int give(CommandSourceStack source, ServerPlayer target, ResourceLocation id, int count) {
        if (LootBoxApi.getDefinition(id) == null) {
            source.sendFailure(Component.translatable("commands.lootbox.unknown_box", id.toString()));
            return 0;
        }
        ItemStack stack = LootBoxItem.createStack(id.toString());
        stack.setCount(count);
        target.getInventory().placeItemBackInInventory(stack);
        source.sendSuccess(() -> Component.translatable("commands.lootbox.give", count, id.toString(), target.getName()), true);
        return count;
    }
}
