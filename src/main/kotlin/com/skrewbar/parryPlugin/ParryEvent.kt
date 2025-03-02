package com.skrewbar.parryPlugin

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent

class ParryEvent : Listener {
    @EventHandler
    fun onGuard(event: PlayerInteractEvent) {
        if (!ParryPlugin.isEnabled) return
        ParryPlugin.remainParry[event.player.uniqueId] = ParryPlugin.parryTime
    }

    @EventHandler
    fun onParry(event: EntityDamageByEntityEvent) {
        if (event.entity !is Player) return
        val player = event.entity as Player
        if (!ParryPlugin.remainParry.contains(player.uniqueId)) return
        if (ParryPlugin.remainParry[player.uniqueId]!! > 0) {
            event.isCancelled = true
            player.sendMessage("parry lol")
        }
    }
}