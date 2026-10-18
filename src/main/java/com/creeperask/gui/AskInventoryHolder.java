package com.creeperask.gui;

import java.util.Objects;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public final class AskInventoryHolder implements InventoryHolder {
   private Inventory inventory;

   void setInventory(@NotNull Inventory inventory) {
      this.inventory = inventory;
   }

   @Override
   public @NotNull Inventory getInventory() {
      return Objects.requireNonNull(this.inventory, "Inventory has not been initialized");
   }
}
