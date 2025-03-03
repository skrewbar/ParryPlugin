package com.skrewbar.parryPlugin.runnables

import com.skrewbar.parryPlugin.remainParry
import org.bukkit.scheduler.BukkitRunnable

class DecreaseRemainParry : BukkitRunnable() {
    override fun run() {
        for ((id) in remainParry) {
            remainParry[id] = remainParry[id]!! - 1
            if (remainParry[id] == 0) remainParry.remove(id)
        }
    }
}