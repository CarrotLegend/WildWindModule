package org.polaris2023.wildwind.core.config;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.polaris2023.wildwind.core.WildWindCore;

/**
 * 模组配置类
 * 用于控制模组行为的各种配置选项
 *
 * @author baka4n
 * @since 2026/04/15
 */
@EventBusSubscriber(modid = WildWindCore.MOD_ID)
public class ModCommonConfig {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // ==================== 世界生成配置 ====================

    /**
     * 是否启用原版制箭台替换为模组制箭台
     * 当设置为 false 时，世界生成时不会替换原版制箭台
     */
    private static final ModConfigSpec.BooleanValue REPLACE_VANILLA_FLETCHING_TABLE = BUILDER
            .comment("是否启用原版制箭台替换为模组制箭台",
                    "当设置为 false 时，世界生成时不会替换原版制箭台",
                    "Enable replacement of vanilla fletching table with mod fletching table",
                    "When set to false, vanilla fletching tables will not be replaced during world generation")
            .define("worldgen.replaceVanillaFletchingTable", true);

    // ==================== 构建配置规范 ====================

    public static final ModConfigSpec SPEC = BUILDER.build();

    // ==================== 运行时配置值 ====================

    private static boolean replaceVanillaFletchingTable = true;

    /**
     * 获取是否启用原版制箭台替换
     * @return true 表示启用替换，false 表示不替换
     */
    public static boolean shouldReplaceVanillaFletchingTable() {
        return replaceVanillaFletchingTable;
    }

    /**
     * 配置加载事件处理
     * 当配置文件被加载或重新加载时调用
     */
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        // 更新运行时配置值
        replaceVanillaFletchingTable = REPLACE_VANILLA_FLETCHING_TABLE.get();
    }
}
