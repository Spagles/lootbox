package net.xuwu.lootbox;

/** 可由数据包或 KJS 扩展的开箱条件。 */
@FunctionalInterface
public interface LootBoxCondition {
    boolean test(LootBoxContext context);

    default String description(LootBoxContext context) {
        return "";
    }
}
