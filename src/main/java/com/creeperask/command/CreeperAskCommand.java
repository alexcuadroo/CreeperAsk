package com.creeperask.command;

import com.creeperask.CreeperAskPlugin;
import com.creeperask.session.SessionManager;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class CreeperAskCommand implements CommandExecutor, TabCompleter {
   private final CreeperAskPlugin plugin;
   private final SessionManager sessionManager;

   public CreeperAskCommand(CreeperAskPlugin plugin, SessionManager sessionManager) {
      this.plugin = plugin;
      this.sessionManager = sessionManager;
   }

   public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
      if (args.length == 1 && (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("off"))) {
         boolean enabled = args[0].equalsIgnoreCase("on");
         this.plugin.setGloballyEnabled(enabled);
         if (!enabled) {
            this.sessionManager.releaseAll();
         }

         sender.sendMessage("CreeperAsk " + (enabled ? "activado." : "desactivado."));
         return true;
      } else {
         sender.sendMessage("Uso: /creeperask <on|off>");
         return true;
      }
   }

   public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
      return args.length == 1 ? List.of("on", "off") : List.of();
   }
}
