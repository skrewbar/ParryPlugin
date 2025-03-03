package com.skrewbar.parryPlugin

import com.skrewbar.parryPlugin.runnables.DecreaseRemainParry
import com.skrewbar.parryPlugin.runnables.IncreaseParryTime
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class SettingCommand : CommandExecutor, TabCompleter {
    private val decreaseRemainParry = DecreaseRemainParry()
    private val increaseParryTime = IncreaseParryTime()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isNotEmpty()) return false
        if (isEnabled) {
            isEnabled = false
            sender.sendMessage("Parry has been disabled.")

            decreaseRemainParry.cancel()
            increaseParryTime.cancel()
        } else {
            isEnabled = true
            sender.sendMessage("Parry has been enabled.")

            decreaseRemainParry.runTaskTimer(ParryPlugin.instance, 0, 1)
            increaseParryTime.runTaskTimer(ParryPlugin.instance, 0, 10)
        }
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String>? {
        return null
    }
}