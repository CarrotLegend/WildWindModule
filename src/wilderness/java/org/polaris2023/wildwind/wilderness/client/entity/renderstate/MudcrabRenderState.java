package org.polaris2023.wildwind.wilderness.client.entity.renderstate;

import net.minecraft.core.Direction;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.polaris2023.wildwind.wilderness.entity.animal.MudcrabVariant;

public class MudcrabRenderState extends LivingEntityRenderState {
    public MudcrabVariant variant;
    public float modelYRotOffset;
    public float modelXRotOffset;
    public Direction motionDirection = Direction.NORTH;
}