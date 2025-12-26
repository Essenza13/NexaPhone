/*    */ package me.dervinocap.originphone.tasks;
/*    */ 
/*    */ import de.tr7zw.nbtapi.NBTItem;
/*    */ import java.util.Arrays;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PowerBankTask
/*    */   extends BukkitRunnable
/*    */ {
/*    */   public void run() {
/* 22 */     for (Player player : Bukkit.getOnlinePlayers()) {
/*    */       
/* 24 */       if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().getAmount() != 0 && !player.getInventory().getItemInMainHand().getType().equals(Material.AIR) && player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().getAmount() != 0 && !player.getInventory().getItemInOffHand().getType().equals(Material.AIR) && 
/* 25 */         BasicsFunction.isPhone(player.getInventory().getItemInMainHand()) && BasicsFunction.isPowerBank(player.getInventory().getItemInOffHand())) {
/*    */ 
/*    */         
/* 28 */         NBTItem nbtItem = new NBTItem(player.getInventory().getItemInMainHand(), true);
/* 29 */         NBTItem offhand = new NBTItem(player.getInventory().getItemInOffHand(), true);
/*    */         
/* 31 */         if (offhand.getInteger("PowerCarica").intValue() > 0) {
/* 32 */           if (nbtItem.getInteger("Carica").intValue() >= 0 && nbtItem.getInteger("Carica").intValue() < 100) {
/* 33 */             nbtItem.setInteger("Carica", Integer.valueOf(nbtItem.getInteger("Carica").intValue() + 1));
/* 34 */             offhand.setInteger("PowerCarica", Integer.valueOf(offhand.getInteger("PowerCarica").intValue() - 1));
/* 35 */             BasicsFunction.sendActionBar(player, BasicsFunction.hex("&eIl powerbank sta ricaricando il telefono..."));
/* 36 */             player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1.0F, 2.0F);
/*    */             
/* 38 */             ItemStack item = player.getInventory().getItemInOffHand();
/* 39 */             ItemMeta meta = item.getItemMeta();
/*    */             
/* 41 */             meta.setLore(Arrays.asList(new String[] { BasicsFunction.hex("&8• &7Carica rimanente: &e⚡ " + offhand.getInteger("PowerCarica") + "%") }));
/*    */             
/* 43 */             item.setItemMeta(meta);
/*    */             continue;
/*    */           } 
/* 46 */           BasicsFunction.sendActionBar(player, BasicsFunction.hex("&cIl telefono ha raggiunto il massimo della carica!"));
/*    */           continue;
/*    */         } 
/* 49 */         BasicsFunction.sendActionBar(player, BasicsFunction.hex("&cIl powerbank è scarico!"));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\tasks\PowerBankTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */