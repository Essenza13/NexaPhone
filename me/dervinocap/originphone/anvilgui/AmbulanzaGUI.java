/*    */ package me.dervinocap.originphone.anvilgui;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import me.dervinocap.originphone.RealityPhone;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.Icon;
/*    */ import net.wesjd.anvilgui.AnvilGUI;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ public class AmbulanzaGUI
/*    */ {
/*    */   public static void open(Player myPlayer) {
/* 17 */     (new AnvilGUI.Builder())
/*    */       
/* 19 */       .onClick((slot, stateSnapshot) -> {
/*    */           if (slot.intValue() != 2) {
/*    */             return Collections.emptyList();
/*    */           }
/*    */ 
/*    */           
/*    */           BasicsFunction.sendEmergency(stateSnapshot.getPlayer(), "realityphone.hospital", "Ospedale", stateSnapshot.getText());
/*    */           
/*    */           stateSnapshot.getPlayer().chat("*Sta chiamando un Ambulanza*");
/*    */           
/*    */           stateSnapshot.getPlayer().closeInventory();
/*    */           
/*    */           return AnvilGUI.Response.text("Fatto");
/* 32 */         }).text("Motivo")
/* 33 */       .itemLeft(new ItemStack(Icon.filler()))
/* 34 */       .itemRight(new ItemStack(Icon.filler()))
/* 35 */       .itemOutput(new ItemStack(Icon.check()))
/* 36 */       .plugin((Plugin)RealityPhone.getInstance())
/* 37 */       .open(myPlayer);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\anvilgui\AmbulanzaGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */