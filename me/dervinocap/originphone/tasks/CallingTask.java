/*    */ package me.dervinocap.originphone.tasks;
/*    */ 
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.Call;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CallingTask
/*    */   extends BukkitRunnable
/*    */ {
/*    */   public void run() {
/* 20 */     for (Player player : Bukkit.getOnlinePlayers()) {
/*    */       
/* 22 */       if (Call.callList.contains(player.getUniqueId())) {
/*    */         
/* 24 */         BasicsFunction.sendActionBar(player, "§eTi stanno chiamando! Apri il telefono per rispondere.");
/* 25 */         player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0F, 2.0F);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\tasks\CallingTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */