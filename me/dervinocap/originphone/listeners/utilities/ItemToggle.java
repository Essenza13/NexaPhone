/*    */ package me.dervinocap.originphone.listeners.utilities;
/*    */ 
/*    */ import me.dervinocap.originphone.commands.chatcommand.subcommands.ChatCommand;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.Call;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerItemHeldEvent;
/*    */ 
/*    */ public class ItemToggle
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onToggle(PlayerItemHeldEvent event) {
/* 16 */     Player player = event.getPlayer();
/*    */     
/* 18 */     if (ChatCommand.toggleChat.containsKey(player.getUniqueId())) {
/* 19 */       ChatCommand.toggleChat.remove(player.getUniqueId());
/* 20 */       BasicsFunction.sendActionBar(player, "§cHai cambiato item, quindi non parlerai più in §lchat.");
/*    */     } 
/*    */     
/* 23 */     if (Call.inCall.containsKey(player.getUniqueId()) || Call.inCall.containsValue(player.getUniqueId())) {
/* 24 */       Call.inCall.remove(player.getUniqueId());
/* 25 */       Call.inCall.inverse().remove(player.getUniqueId());
/* 26 */       BasicsFunction.sendActionBar(player, "§cHai cambiato item, quindi la chiamata è §lterminata.");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\listener\\utilities\ItemToggle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */