/*    */ package me.dervinocap.originphone.tasks;
/*    */ 
/*    */ import de.tr7zw.nbtapi.NBTItem;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BatteriaTask
/*    */   extends BukkitRunnable
/*    */ {
/*    */   public void run() {
/* 19 */     for (Player player : Bukkit.getOnlinePlayers()) {
/*    */       
/* 21 */       if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().getAmount() != 0 && !player.getInventory().getItemInMainHand().getType().equals(Material.AIR) && 
/* 22 */         BasicsFunction.isPhone(player.getInventory().getItemInMainHand())) {
/*    */ 
/*    */         
/* 25 */         NBTItem nbtItem = new NBTItem(player.getInventory().getItemInMainHand(), true);
/*    */         
/* 27 */         if (nbtItem.getInteger("Carica").intValue() > 0)
/* 28 */           nbtItem.setInteger("Carica", Integer.valueOf(nbtItem.getInteger("Carica").intValue() - 1)); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\tasks\BatteriaTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */