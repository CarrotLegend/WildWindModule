package org.polaris2023.wildwind.wilderness.client.tooltip;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.polaris2023.wildwind.wilderness.component.OmniClawTools;

/**
 * 全能蟹钳工具列表的客户端提示组件喵~
 * <p>
 * 26.2 迁移说明：ClientTooltipComponent 的 renderImage 改为 extractImage，
 * GuiGraphics 重构为 GuiGraphicsExtractor，原版 bundle/background、bundle/slot
 * 与 item/empty_slot_* 精灵图已移除，改用 gui 图集的 bundle/slot_background
 * 与 container/slot/* 占位图标。槽位高亮改为 slot_highlight 精灵图。
 */
public class ClientOmniClawTooltip implements ClientTooltipComponent {
	private static final Identifier BACKGROUND_SPRITE = Identifier.withDefaultNamespace("container/bundle/slot_background");
	private static final Identifier SLOT_SPRITE = Identifier.withDefaultNamespace("container/bundle/slot_background");
	private static final Identifier SLOT_HIGHLIGHT_FRONT_SPRITE = Identifier.withDefaultNamespace("container/slot_highlight_front");
	private static final Identifier[] EMPTY_SLOT_TOOLS_TEXTURE = new Identifier[] {
			Identifier.withDefaultNamespace("container/slot/pickaxe"),
			Identifier.withDefaultNamespace("container/slot/axe"),
			Identifier.withDefaultNamespace("container/slot/shovel"),
			Identifier.withDefaultNamespace("container/slot/hoe")
	};
	private static final int SLOT_WIDTH = 18;
	private static final int SLOT_HEIGHT = 20;
	private static final int SLOT_COUNT = 4;
	private static final int MARGIN = 1;
	private final OmniClawTools tools;

	/**
	 * 创建全能蟹钳客户端提示组件喵~
	 *
	 * @param tools 全能蟹钳内保存的工具列表喵~
	 */
	public ClientOmniClawTooltip(OmniClawTools tools) {
		this.tools = tools;
	}

	/**
	 * 获取提示组件总高度喵~
	 *
	 * @param font 当前字体渲染器喵~
	 * @return 提示组件高度喵~
	 */
	@Override
	public int getHeight(Font font) {
		return getBackgroundHeight() + 4;
	}

	/**
	 * 获取提示组件总宽度喵~
	 *
	 * @param font 当前字体渲染器喵~
	 * @return 提示组件宽度喵~
	 */
	@Override
	public int getWidth(Font font) {
		return SLOT_WIDTH * SLOT_COUNT + MARGIN * 2;
	}

	private int getBackgroundHeight() {
		return SLOT_HEIGHT + MARGIN * 2;
	}

	/**
	 * 渲染全能蟹钳工具提示中的图标网格喵~
	 *
	 * @param font 当前字体渲染器喵~
	 * @param x 左上角横坐标喵~
	 * @param y 左上角纵坐标喵~
	 * @param w 组件宽度喵~
	 * @param h 组件高度喵~
	 * @param graphics 图形提取上下文喵~
	 */
	@Override
	public void extractImage(Font font, int x, int y, int w, int h, GuiGraphicsExtractor graphics) {
		graphics.blitSprite(RenderPipelines.GUI_TEXTURED, BACKGROUND_SPRITE, x, y, this.getWidth(font), this.getBackgroundHeight());
		y += MARGIN;
		x += MARGIN;
		for (int i = 0; i < SLOT_COUNT; i++) {
			graphics.blitSprite(RenderPipelines.GUI_TEXTURED, SLOT_SPRITE, x, y, SLOT_WIDTH, SLOT_HEIGHT);

			ItemStack stack = this.tools.getTools().get(i);
			if (stack.isEmpty()) {
				graphics.blitSprite(RenderPipelines.GUI_TEXTURED, EMPTY_SLOT_TOOLS_TEXTURE[i], x + 1, y + 1, 16, 16);
			} else {
				graphics.item(stack, x + 1, y + 1);
				graphics.itemDecorations(font, stack, x + 1, y + 1);
				if (i == this.tools.getSelectedToolIndex()) {
					graphics.blitSprite(RenderPipelines.GUI_TEXTURED, SLOT_HIGHLIGHT_FRONT_SPRITE, x + 1 - 4, y + 1 - 4, 24, 24);
				}
			}

			x += SLOT_WIDTH;
		}
	}
}