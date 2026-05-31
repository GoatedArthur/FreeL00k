package com.freelook.mod;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class FreelookConfigGui extends GuiScreen
{
    private GuiScreen parent;
    private GuiButton perspectiveButton;
    private GuiButton toggleButton;
    private GuiButton yawButton;

    public FreelookConfigGui(GuiScreen parent)
    {
        this.parent = parent;
    }

    @Override
    public void initGui()
    {
        int centerX = width / 2;
        int y = height / 2 - 60;

        perspectiveButton = new GuiButton(0, centerX - 100, y, 200, 20,
                "Perspective: " + ModConfig.getPerspectiveName(ModConfig.defaultPerspective));
        buttonList.add(perspectiveButton);

        toggleButton = new GuiButton(1, centerX - 100, y + 30, 200, 20,
                "Mode: " + (ModConfig.toggleMode ? "Toggle" : "Hold"));
        buttonList.add(toggleButton);

        yawButton = new GuiButton(2, centerX - 100, y + 60, 200, 20,
                "Max Yaw: " + (ModConfig.maxYaw >= 360 ? "360 (Full)" : (int) ModConfig.maxYaw + "\u00b0"));
        buttonList.add(yawButton);

        buttonList.add(new GuiButton(100, centerX - 100, height - 40, 200, 20, "Done"));
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        switch (button.id)
        {
            case 0:
                ModConfig.defaultPerspective = (ModConfig.defaultPerspective + 1) % 3;
                ModConfig.config.get("general", "defaultPerspective", 1).set(ModConfig.defaultPerspective);
                perspectiveButton.displayString = "Perspective: " + ModConfig.getPerspectiveName(ModConfig.defaultPerspective);
                break;
            case 1:
                ModConfig.toggleMode = !ModConfig.toggleMode;
                ModConfig.config.get("general", "toggleMode", false).set(ModConfig.toggleMode);
                toggleButton.displayString = "Mode: " + (ModConfig.toggleMode ? "Toggle" : "Hold");
                break;
            case 2:
                float[] values = { 90, 180, 360 };
                int idx = 0;
                for (int i = 0; i < values.length; i++)
                {
                    if (Math.abs(values[i] - ModConfig.maxYaw) < 1) idx = i;
                }
                idx = (idx + 1) % values.length;
                ModConfig.maxYaw = values[idx];
                ModConfig.config.get("general", "maxYaw", 360.0).set(ModConfig.maxYaw);
                yawButton.displayString = "Max Yaw: " + (ModConfig.maxYaw >= 360 ? "360 (Full)" : (int) ModConfig.maxYaw + "\u00b0");
                break;
            case 100:
                ModConfig.config.save();
                mc.displayGuiScreen(parent);
                break;
        }
    }

    @Override
    public void onGuiClosed()
    {
        ModConfig.config.save();
    }
}
