/*     */ package dev.triumphteam.gui.guis;
/*     */ 
/*     */ import dev.triumphteam.gui.components.InteractionModifier;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StorageGui
/*     */   extends BaseGui
/*     */ {
/*     */   public StorageGui(int rows, @NotNull String title, @NotNull Set<InteractionModifier> interactionModifiers) {
/*  51 */     super(rows, title, interactionModifiers);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public StorageGui(int rows, @NotNull String title) {
/*  62 */     super(rows, title);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public StorageGui(@NotNull String title) {
/*  72 */     super(1, title);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Map<Integer, ItemStack> addItem(@NotNull ItemStack... items) {
/*  83 */     return Collections.unmodifiableMap(getInventory().addItem(items));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, ItemStack> addItem(@NotNull List<ItemStack> items) {
/*  93 */     return addItem(items.<ItemStack>toArray(new ItemStack[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void open(@NotNull HumanEntity player) {
/* 103 */     if (player.isSleeping())
/* 104 */       return;  populateGui();
/* 105 */     player.openInventory(getInventory());
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\dev\triumphteam\gui\guis\StorageGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */