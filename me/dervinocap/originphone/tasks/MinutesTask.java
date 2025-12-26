/*    */ package me.dervinocap.originphone.tasks;
/*    */ 
/*    */ import me.dervinocap.originphone.database.NumberDatabase;
/*    */ import me.dervinocap.originphone.utils.Call;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ public class MinutesTask
/*    */   extends BukkitRunnable
/*    */ {
/*    */   public void run() {
/* 13 */     for (Player player : Bukkit.getOnlinePlayers()) {
/*    */       
/* 15 */       if (Call.inCallArray.contains(player.getUniqueId()))
/*    */       {
/* 17 */         NumberDatabase.setMinutes(player, NumberDatabase.getMinutes(player).intValue() - 1);
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\tasks\MinutesTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */