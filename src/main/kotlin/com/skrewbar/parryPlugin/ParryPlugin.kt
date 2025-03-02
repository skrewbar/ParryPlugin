package com.skrewbar.parryPlugin

import org.bukkit.plugin.java.JavaPlugin
import java.util.UUID

class ParryPlugin : JavaPlugin() {
    companion object {
        var isEnabled = false

        var cooldown = 4
        var remainCool: MutableMap<UUID, Int> = mutableMapOf()

        var parryTime = 10
        var remainParry: MutableMap<UUID, Int> = mutableMapOf()
    }

    override fun onEnable() {
        // Plugin startup logic
        logger.info("Parry Plugin has enabled.")

        server.pluginManager.registerEvents(ParryEvent(), this)
        val settingCommand = SettingCommand()
        server.getPluginCommand("parry")?.setExecutor(settingCommand)
        server.getPluginCommand("parry")?.tabCompleter = settingCommand
    }
}
