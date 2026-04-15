package net.nekoclient;

import net.fabricmc.api.ModInitializer;
import net.nekoclient.config.ConfigManager;
import net.nekoclient.modules.ModuleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NekoClient implements ModInitializer {
    public static final String MOD_ID = "nekoclient";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    private static NekoClient instance;
    private ConfigManager configManager;
    private ModuleManager moduleManager;
    
    @Override
    public void onInitialize() {
        instance = this;
        
        LOGGER.info("Initializing Neko Client v1.0.0");
        
        // Initialize configuration first
        configManager = new ConfigManager();
        configManager.load();
        
        // Initialize modules
        moduleManager = new ModuleManager();
        moduleManager.init();
        
        LOGGER.info("Neko Client initialized successfully!");
    }
    
    public static NekoClient getInstance() {
        return instance;
    }
    
    public ConfigManager getConfigManager() {
        return configManager;
    }
    
    public ModuleManager getModuleManager() {
        return moduleManager;
    }
}
