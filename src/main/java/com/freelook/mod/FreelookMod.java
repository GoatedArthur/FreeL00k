package com.freelook.mod;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

@Mod(modid = FreelookMod.MODID, version = FreelookMod.VERSION,
     name = "FreeL00k", clientSideOnly = true,
     guiFactory = "com.freelook.mod.FreelookGuiFactory")
public class FreelookMod
{
    public static final String MODID = "freelook";
    public static final String VERSION = "1.0.0";

    public static Logger logger;

    public static KeyBinding freeLookKey;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        ModConfig.init(event);
        MinecraftForge.EVENT_BUS.register(new ModConfig());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        freeLookKey = new KeyBinding("key.freelook.toggle", Keyboard.KEY_LMENU, "key.categories.freelook");
        ClientRegistry.registerKeyBinding(freeLookKey);

        MinecraftForge.EVENT_BUS.register(new FreeLookHandler());
    }
}
