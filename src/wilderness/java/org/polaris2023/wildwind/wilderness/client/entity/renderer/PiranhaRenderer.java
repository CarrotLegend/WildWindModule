package org.polaris2023.wildwind.wilderness.client.entity.renderer;

import com.geckolib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.polaris2023.wildwind.wilderness.client.entity.model.PiranhaModel;
import org.polaris2023.wildwind.wilderness.client.entity.renderstate.PiranhaRenderState;
import org.polaris2023.wildwind.wilderness.entity.animal.Piranha;

import javax.annotation.Nullable;

/**
 * 食人鱼渲染器
 * @author huimanman
 */
public class PiranhaRenderer extends GeoEntityRenderer<Piranha, PiranhaRenderState> {
	public PiranhaRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new PiranhaModel());
	}

	@Override
	public PiranhaRenderState createRenderState(Piranha animatable, @Nullable Void relatedObject) {
		return new PiranhaRenderState();
	}
}