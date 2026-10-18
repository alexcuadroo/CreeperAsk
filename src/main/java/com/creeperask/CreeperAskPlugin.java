package com.creeperask;

import com.creeperask.command.CreeperAskCommand;
import com.creeperask.listener.CreeperExplosionListener;
import com.creeperask.listener.CreeperSessionListener;
import com.creeperask.session.SessionManager;
import com.creeperask.task.CreeperProximityTask;
import java.util.Objects;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class CreeperAskPlugin extends JavaPlugin {
   private SessionManager sessionManager;
   private boolean globallyEnabled = true;

   @Override
   public void onEnable() {
      this.sessionManager = new SessionManager(this);
      this.getServer().getPluginManager().registerEvents(new CreeperSessionListener(this, this.sessionManager), this);
      this.getServer().getPluginManager().registerEvents(new CreeperExplosionListener(this.sessionManager), this);
       PluginCommand command = Objects.requireNonNull(this.getCommand("creeperask"), "creeperask command missing from plugin.yml");
      CreeperAskCommand executor = new CreeperAskCommand(this, this.sessionManager);
      command.setExecutor(executor);
      command.setTabCompleter(executor);
      (new CreeperProximityTask(this, this.sessionManager)).runTaskTimer(this, 10L, 10L);
   }

   @Override
   public void onDisable() {
      if (this.sessionManager != null) {
         this.sessionManager.releaseAll();
      }
   }

   public boolean isGloballyEnabled() {
      return this.globallyEnabled;
   }

   public void setGloballyEnabled(boolean globallyEnabled) {
      this.globallyEnabled = globallyEnabled;
   }
}
