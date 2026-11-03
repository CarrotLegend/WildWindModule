package org.polaris2023.wildwind.core.client.entity.renderer;

import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderer;
import com.geckolib.renderer.layer.GeoRenderLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.polaris2023.wildwind.core.client.entity.model.SpiderlingModel;
import org.polaris2023.wildwind.core.client.entity.renderer.layer.SpiderlingEyesLayer;
import org.polaris2023.wildwind.core.client.entity.renderstate.SpiderlingRenderState;
import org.polaris2023.wildwind.core.entity.monster.Spiderling;

import javax.annotation.Nullable;

/**
 * 幼蛛实体渲染器喵~
 */
public class SpiderlingRenderer extends GeoEntityRenderer<Spiderling, SpiderlingRenderState> {

    /** GeoRenderState 只在运行时由 mixin 注入 EntityRenderState，编译期需一次等价强转。 */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public SpiderlingRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SpiderlingModel());
        this.withRenderLayer(renderer -> (GeoRenderLayer) new SpiderlingEyesLayer((GeoRenderer) renderer));
    }

    @Override
    public SpiderlingRenderState createRenderState(Spiderling animatable, @Nullable Void relatedObject) {
        return new SpiderlingRenderState();
    }
}
