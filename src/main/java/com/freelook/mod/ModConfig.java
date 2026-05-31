package com.freelook.mod;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModConfig
{
    public static Configuration config;

    public static int defaultPerspective = 1;
    public static boolean toggleMode = false;
    public static float maxYaw = 360.0F;

    public static void init(FMLPreInitializationEvent event)
    {
        config = new Configuration(event.getSuggestedConfigurationFile());
        sync();
    }

    public static void sync()
    {
        config.load();

        Property perspectiveProp = config.get("general", "defaultPerspective", 1,
                "Default freelook perspective: 0 = keep current, 1 = third person back, 2 = third person front");
        perspectiveProp.setValidValues(new String[] { "0", "1", "2" });
        defaultPerspective = perspectiveProp.getInt();

        Property toggleProp = config.get("general", "toggleMode", false,
                "Toggle mode: false = hold key, true = press to toggle");
        toggleMode = toggleProp.getBoolean();

        Property yawProp = config.get("general", "maxYaw", 360.0,
                "Maximum horizontal camera rotation in degrees (360 = full rotation)");
        maxYaw = (float) yawProp.getDouble();

        if (config.hasChanged()) config.save();
    }

    public static String getPerspectiveName(int value)
    {
        switch (value)
        {
            case 0: return "Keep Current";
            case 1: return "Third Person (Back)";
            case 2: return "Third Person (Front)";
            default: return "Unknown";
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equals(FreelookMod.MODID))
        {
            sync();
        }
    }
}
