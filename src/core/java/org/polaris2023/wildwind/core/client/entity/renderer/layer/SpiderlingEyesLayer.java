package org.polaris2023.wildwind.core.client.entity.renderer.layer;

import com.geckolib.renderer.base.GeoRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer;
import net.minecraft.resources.Identifier;
import org.polaris2023.wildwind.core.WildWindCore;
import org.polaris2023.wildwind.core.entity.monster.Spiderling;

/** 幼蛛发光眼睛图层喵~；R 用 GeoRenderState（编译期 LivingEntityRenderState 不满足其边界）。 */
public class SpiderlingEyesLayer extends AutoGlowingGeoLayer<Spiderling, Void, GeoRenderState> {
    private static final Identifier EYES = WildWindCore.id("textures/entity/spiderling_eyes.png");

    public SpiderlingEyesLayer(GeoRenderer<Spiderling, Void, GeoRenderState> renderer) {
        super(renderer);
    }

    @Override
    protected Identifier getTextureResource(GeoRenderState renderState) {
        return EYES;
    }
}
