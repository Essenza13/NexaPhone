package dev.triumphteam.gui.components.nbt;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface NbtWrapper {
  ItemStack setString(@NotNull ItemStack paramItemStack, String paramString1, String paramString2);
  
  ItemStack removeTag(@NotNull ItemStack paramItemStack, String paramString);
  
  ItemStack setBoolean(@NotNull ItemStack paramItemStack, String paramString, boolean paramBoolean);
  
  @Nullable
  String getString(@NotNull ItemStack paramItemStack, String paramString);
}


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\dev\triumphteam\gui\components\nbt\NbtWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */