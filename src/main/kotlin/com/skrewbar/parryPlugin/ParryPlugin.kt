package com.skrewbar.parryPlugin

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.math.min

var isEnabled = false

const val DEFAULT_PARRY_TIME = 10
val parryTime: MutableMap<UUID, Int> = mutableMapOf()
// parryTime increases over time
val remainParry: MutableMap<UUID, Int> = mutableMapOf()
// player can parry while remainParry is greater than zero.

class ParryPlugin : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        logger.info("Parry Plugin has enabled.")

        server.pluginManager.registerEvents(ParryEvent(), this)
        val settingCommand = SettingCommand()
        server.getPluginCommand("parry")?.setExecutor(settingCommand)
        server.getPluginCommand("parry")?.tabCompleter = settingCommand

        val decreaseRemainParry = Bukkit.getScheduler().runTaskLater(this, Runnable {
            for ((id) in remainParry) {
                remainParry[id] = remainParry[id]!! - 1
                if (remainParry[id] == 0) remainParry.remove(id)
            }
        }, 1)

        val increaseParryTime = Bukkit.getScheduler().runTaskLater(this, Runnable {
            for ((id) in parryTime) {
                parryTime[id] = min(parryTime[id]!! + 1, 10)
            }
        }, 10)
    }
}
