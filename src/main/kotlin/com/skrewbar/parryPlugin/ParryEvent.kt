package com.skrewbar.parryPlugin

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import kotlin.math.max

class ParryEvent : Listener {
    @EventHandler
    fun onReady(event: PlayerInteractEvent) {
        if (!isEnabled) return
        if ((event.action != Action.RIGHT_CLICK_AIR) and (event.action != Action.RIGHT_CLICK_BLOCK)) return

        val id = event.player.uniqueId

        if (parryTime.contains(id)) {
            parryTime[id] = max(parryTime[id]!! / 2, 1) // parrying during parrying decreases its duration
        } else parryTime[id] = DEFAULT_PARRY_TIME
        remainParry[id] = parryTime[id]!!
    }

    @EventHandler
    fun onParry(event: EntityDamageByEntityEvent) {
        if (event.entity !is Player) return
        val player = event.entity as Player
        if (!remainParry.contains(player.uniqueId)) return
        if (remainParry[player.uniqueId]!! > 0) {
            event.isCancelled = true
            remainParry.remove(player.uniqueId)
            player.sendMessage("parry lol")
        }
    }
}