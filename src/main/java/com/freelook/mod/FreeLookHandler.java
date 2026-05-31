package com.freelook.mod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;

@SideOnly(Side.CLIENT)
public class FreeLookHandler
{
    private final Minecraft mc = Minecraft.getMinecraft();

    private boolean isFreeLooking = false;
    private float cameraYaw;
    private float cameraPitch;
    private float frozenPlayerYaw;
    private float frozenPlayerPitch;
    private int freelookPerspective;
    private int originalPerspective;
    private boolean wasKeyDown = false;

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event)
    {
        if (!isFreeLooking) return;
        if (mc.thePlayer == null) return;

        mc.gameSettings.thirdPersonView = freelookPerspective;

        int dX = Mouse.getDX();
        int dY = Mouse.getDY();

        float sensitivity = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
        float factor = sensitivity * sensitivity * sensitivity * 8.0F * 0.15F;

        cameraYaw += dX * factor;

        float pitchChange = dY * factor;
        if (mc.gameSettings.invertMouse) pitchChange = -pitchChange;
        cameraPitch += pitchChange;

        float yawOffset = cameraYaw - frozenPlayerYaw;
        while (yawOffset > 180) yawOffset -= 360;
        while (yawOffset < -180) yawOffset += 360;
        float maxYaw = ModConfig.maxYaw;
        if (maxYaw < 360.0F)
        {
            if (yawOffset > maxYaw) yawOffset = maxYaw;
            if (yawOffset < -maxYaw) yawOffset = -maxYaw;
            cameraYaw = frozenPlayerYaw + yawOffset;
        }

        mc.thePlayer.rotationYaw = cameraYaw;
        mc.thePlayer.rotationPitch = cameraPitch;
        mc.thePlayer.prevRotationYaw = cameraYaw;
        mc.thePlayer.prevRotationPitch = cameraPitch;
        mc.thePlayer.rotationYawHead = frozenPlayerYaw;
        mc.thePlayer.prevRotationYawHead = frozenPlayerYaw;
        mc.thePlayer.renderYawOffset = frozenPlayerYaw;
        mc.thePlayer.prevRenderYawOffset = frozenPlayerYaw;
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        if (event.phase != Phase.START) return;
        if (mc.thePlayer == null) return;

        if (isFreeLooking)
        {
            mc.thePlayer.rotationYaw = frozenPlayerYaw;
            mc.thePlayer.rotationPitch = frozenPlayerPitch;
            mc.thePlayer.prevRotationYaw = frozenPlayerYaw;
            mc.thePlayer.prevRotationPitch = frozenPlayerPitch;
            mc.thePlayer.rotationYawHead = frozenPlayerYaw;
            mc.thePlayer.prevRotationYawHead = frozenPlayerYaw;
            mc.thePlayer.renderYawOffset = frozenPlayerYaw;
            mc.thePlayer.prevRenderYawOffset = frozenPlayerYaw;
        }

        boolean keyDown = FreelookMod.freeLookKey.isKeyDown();

        if (ModConfig.toggleMode)
        {
            if (keyDown && !wasKeyDown)
            {
                if (isFreeLooking) stopFreeLooking();
                else startFreeLooking();
            }
        }
        else
        {
            if (keyDown && !isFreeLooking) startFreeLooking();
            if (!keyDown && isFreeLooking) stopFreeLooking();
        }

        wasKeyDown = keyDown;
    }

    private void startFreeLooking()
    {
        isFreeLooking = true;
        cameraYaw = mc.thePlayer.rotationYaw;
        cameraPitch = 0.0F;
        frozenPlayerYaw = mc.thePlayer.rotationYaw;
        frozenPlayerPitch = mc.thePlayer.rotationPitch;

        originalPerspective = mc.gameSettings.thirdPersonView;
        if (ModConfig.defaultPerspective > 0)
        {
            freelookPerspective = ModConfig.defaultPerspective;
        }
        else
        {
            freelookPerspective = originalPerspective > 0 ? originalPerspective : 1;
        }
        mc.gameSettings.thirdPersonView = freelookPerspective;
    }

    private void stopFreeLooking()
    {
        isFreeLooking = false;
        mc.thePlayer.rotationYaw = frozenPlayerYaw;
        mc.thePlayer.rotationPitch = frozenPlayerPitch;
        mc.thePlayer.prevRotationYaw = frozenPlayerYaw;
        mc.thePlayer.prevRotationPitch = frozenPlayerPitch;
        mc.thePlayer.rotationYawHead = frozenPlayerYaw;
        mc.thePlayer.prevRotationYawHead = frozenPlayerYaw;
        mc.thePlayer.renderYawOffset = frozenPlayerYaw;
        mc.thePlayer.prevRenderYawOffset = frozenPlayerYaw;

        mc.gameSettings.thirdPersonView = originalPerspective;
    }

    @SubscribeEvent
    public void onCameraSetup(EntityViewRenderEvent.CameraSetup event)
    {
        if (!isFreeLooking) return;
        if (!(event.entity instanceof EntityPlayerSP)) return;

        EntityPlayerSP player = (EntityPlayerSP) event.entity;

        event.yaw = cameraYaw;
        event.pitch = cameraPitch;
        event.roll = 0.0F;

        player.rotationYaw = frozenPlayerYaw;
        player.rotationPitch = frozenPlayerPitch;
        player.prevRotationYaw = frozenPlayerYaw;
        player.prevRotationPitch = frozenPlayerPitch;
        player.rotationYawHead = frozenPlayerYaw;
        player.prevRotationYawHead = frozenPlayerYaw;
        player.renderYawOffset = frozenPlayerYaw;
        player.prevRenderYawOffset = frozenPlayerYaw;
    }
}
