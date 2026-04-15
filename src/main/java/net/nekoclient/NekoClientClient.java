package net.nekoclient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.nekoclient.gui.ClickGUI;
import net.nekoclient.hud.HudManager;
import net.nekoclient.recorder.GameRecorder;
import net.nekoclient.utils.KeyBindManager;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class NekoClientClient implements ClientModInitializer {
    
    private static NekoClientClient instance;
    private HudManager hudManager;
    private ClickGUI clickGUI;
    private GameRecorder gameRecorder;
    
    @Override
    public void onInitializeClient() {
        instance = this;
        
        NekoClient.LOGGER.info("Initializing Neko Client Client-side");
        
        // Initialize HUD
        hudManager = new HudManager();
        hudManager.init();
        
        // Initialize GUI
        clickGUI = new ClickGUI();
        
        // Initialize recorder
        gameRecorder = new GameRecorder();
        
        // Register keybinds
        KeyBindManager.registerKeyBind(
            "key.nekoclient.clickgui",
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "category.nekoclient.general",
            () -> clickGUI.toggle()
        );
        
        KeyBindManager.registerKeyBind(
            "key.nekoclient.hudeditor",
            GLFW.GLFW_KEY_H,
            "category.nekoclient.general",
            () -> hudManager.toggleEditor()
        );
        
        KeyBindManager.registerKeyBind(
            "key.nekoclient.recorder",
            GLFW.GLFW_KEY_F6,
            "category.nekoclient.recording",
            () -> gameRecorder.toggleRecording()
        );
        
        // Register tick event for module updates
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (NekoClient.getInstance().getModuleManager() != null) {
                NekoClient.getInstance().getModuleManager().update();
            }
            if (hudManager != null) {
                hudManager.tick();
            }
        });
    }
    
    public static NekoClientClient getInstance() {
        return instance;
    }
    
    public HudManager getHudManager() {
        return hudManager;
    }
    
    public ClickGUI getClickGUI() {
        return clickGUI;
    }
    
    public GameRecorder getGameRecorder() {
        return gameRecorder;
    }
}
