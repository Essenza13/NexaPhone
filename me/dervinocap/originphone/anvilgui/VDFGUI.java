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
/*    */ public class VDFGUI
/*    */ {
/*    */   public static void open(Player myPlayer) {
/* 16 */     (new AnvilGUI.Builder())
/*    */       
/* 18 */       .onClick((slot, stateSnapshot) -> {
/*    */           if (slot.intValue() != 2) {
/*    */             return Collections.emptyList();
/*    */           }
/*    */ 
/*    */           
/*    */           BasicsFunction.sendEmergency(stateSnapshot.getPlayer(), "realityphone.vdf", "Vigili del Fuoco", stateSnapshot.getText());
/*    */           
/*    */           stateSnapshot.getPlayer().chat("*Sta chiamando i Vigili del Fuoco*");
/*    */           
/*    */           stateSnapshot.getPlayer().closeInventory();
/*    */           
/*    */           return AnvilGUI.Response.text("Fatto");
/* 31 */         }).text("Motivo")
/* 32 */       .itemLeft(new ItemStack(Icon.filler()))
/* 33 */       .itemRight(new ItemStack(Icon.filler()))
/* 34 */       .itemOutput(new ItemStack(Icon.check()))
/* 35 */       .plugin((Plugin)RealityPhone.getInstance())
/* 36 */       .open(myPlayer);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\anvilgui\VDFGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */