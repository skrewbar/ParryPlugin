package com.skrewbar.parryPlugin

import org.bukkit.plugin.java.JavaPlugin
import java.util.*

var isEnabled = false

const val DEFAULT_PARRY_TIME = 10
val parryTime: MutableMap<UUID, Int> = mutableMapOf()
// parryTime increases over time
val remainParry: MutableMap<UUID, Int> = mutableMapOf()
// player can parry while remainParry is greater than zero.

class ParryPlugin : JavaPlugin() {
    companion object {
        lateinit var instance: ParryPlugin
    }

    override fun onEnable() {
        // Plugin startup logic
        logger.info("Parry Plugin has enabled.")

        instance = this

        server.pluginManager.registerEvents(ParryEvent(), this)
        val settingCommand = SettingCommand()
        server.getPluginCommand("parry")?.setExecutor(settingCommand)
        server.getPluginCommand("parry")?.tabCompleter = settingCommand
    }
}
