package com.skrewbar.parryPlugin

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class SettingCommand : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        when (args.size) {
            1 -> sender.sendMessage(when (args[0]) {
                    "cooldown" -> ParryPlugin.cooldown.toString()
                    "parryTime" -> ParryPlugin.parryTime.toString()
                    else -> "No such property exists."
                })
            2 -> {
                try {
                    val value = args[1].toInt()
                    if (value < 0) throw NumberFormatException()
                    when (args[0]) {
                        "cooldown" -> ParryPlugin.cooldown = value
                        "parryTime" -> ParryPlugin.parryTime = value
                        else -> {
                            sender.sendMessage("No such property exists.")
                            return true
                        }
                    }
                    sender.sendMessage("Value has been successfully set.")
                } catch (e: NumberFormatException) {
                    sender.sendMessage("Second argument must be a positive integer.")
                }
            }
            else -> return false
        }
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String>? {
        return when (args.size) {
            1 -> mutableListOf("cooldown", "parryTime")
            else -> null
        }
    }
}