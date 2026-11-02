package org.polaris2023.wildwind.core.client.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import org.polaris2023.wildwind.core.menu.FletchingTableMenu;

/**
 * 制箭台界面。
 * <p>
 * 直接引用原版锻造台界面贴图（不复制进模组 jar，避免再分发 Mojang 资源），
 * 槽位布局与之对齐：三个输入 (8,48)/(26,48)/(44,48)，产物 (98,48)，背包 (8,84) 起。
 * <p>
 * 锻造台贴图左上角自带一个锤子装饰，这里用底色盖掉，换成光灵箭图标。
 */
public class FletchingTableScreen extends AbstractContainerScreen<FletchingTableMenu> {
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath("minecraft", "textures/gui/container/smithing.png");
    private static final Identifier ARROW_ICON =
            Identifier.fromNamespaceAndPath("minecraft", "textures/item/spectral_arrow.png");

    private static final int BACKGROUND_COLOR = 0xFFC6C6C6;
    /** 原版锤子装饰在贴图上的范围（含 1px 余量） */
    private static final int HAMMER_X = 6;
    private static final int HAMMER_Y = 7;
    private static final int HAMMER_SIZE = 32;
    /** 光灵箭图标的绘制尺寸（放大到 24×24 后居中于被遮盖的 32×32 区域） */
    private static final int ICON_SIZE = 24;
    private static final int ICON_X = HAMMER_X + (HAMMER_SIZE - ICON_SIZE) / 2;
    private static final int ICON_Y = HAMMER_Y + (HAMMER_SIZE - ICON_SIZE) / 2;

    public FletchingTableScreen(FletchingTableMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 176, 166);
        this.titleLabelX = 40;
        this.titleLabelY = 15;
        this.inventoryLabelX = 8;
        this.inventoryLabelY = 72;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractBackground(graphics, mouseX, mouseY, partialTick);
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE,
                this.leftPos, this.topPos, 0.0F, 0.0F,
                this.imageWidth, this.imageHeight, 256, 256);

        // 盖掉原版锻造台的锤子，原位画光灵箭
        graphics.fill(this.leftPos + HAMMER_X, this.topPos + HAMMER_Y,
                this.leftPos + HAMMER_X + HAMMER_SIZE, this.topPos + HAMMER_Y + HAMMER_SIZE,
                BACKGROUND_COLOR);
        graphics.blit(RenderPipelines.GUI_TEXTURED, ARROW_ICON,
                this.leftPos + ICON_X, this.topPos + ICON_Y, 0.0F, 0.0F,
                ICON_SIZE, ICON_SIZE, 16, 16, 16, 16);
    }
}
