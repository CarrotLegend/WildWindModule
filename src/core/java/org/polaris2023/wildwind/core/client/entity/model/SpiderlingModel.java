package org.polaris2023.wildwind.core.client.entity.model;

import com.geckolib.model.DefaultedEntityGeoModel;
import com.geckolib.renderer.base.GeoRenderState;
import net.minecraft.resources.Identifier;
import org.polaris2023.wildwind.core.WildWindCore;
import org.polaris2023.wildwind.core.entity.monster.Spiderling;

/**
 * 幼蛛实体的几何模型定义喵~
 */
public class SpiderlingModel extends DefaultedEntityGeoModel<Spiderling> {

    public SpiderlingModel() {
        super(WildWindCore.id("spiderling"));
    }

    @Override
    public Identifier getTextureResource(GeoRenderState state) {
        return WildWindCore.id("textures/entity/spiderling.png");
    }
}
