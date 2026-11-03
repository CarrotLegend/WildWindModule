package org.polaris2023.wildwind.wilderness.client.entity.model;


import com.geckolib.model.DefaultedEntityGeoModel;
import com.geckolib.renderer.base.GeoRenderState;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.resources.Identifier;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.client.entity.renderstate.MudcrabRenderState;
import org.polaris2023.wildwind.wilderness.entity.animal.Mudcrab;

/**
 * 泥沼蟹实体的几何模型定义喵~
 */
public class MudcrabModel extends DefaultedEntityGeoModel<Mudcrab> {

	/**
	 * 创建泥沼蟹模型实例喵~
	 */
	public MudcrabModel() {
		super(WildWindWilderness.id("mudcrab"));
	}

	/**
	 * 获取当前泥沼蟹变种对应的材质资源喵~
	 * <p>
	 */
	@Override
	public Identifier getTextureResource(GeoRenderState state) {
		return ((MudcrabRenderState) state).variant.assetInfo().texturePath();
	}
}
