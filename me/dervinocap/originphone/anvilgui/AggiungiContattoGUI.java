/*    */ package me.dervinocap.originphone.anvilgui;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import me.dervinocap.originphone.RealityPhone;
/*    */ import me.dervinocap.originphone.database.ContactDatabase;
/*    */ import me.dervinocap.originphone.database.NumberDatabase;
/*    */ import me.dervinocap.originphone.utils.Icon;
/*    */ import net.wesjd.anvilgui.AnvilGUI;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class AggiungiContattoGUI
/*    */ {
/*    */   public static void open(Player myPlayer) {
/* 19 */     (new AnvilGUI.Builder())
/*    */       
/* 21 */       .onClick((slot, stateSnapshot) -> {
/*    */           if (slot.intValue() != 2) {
/*    */             return Collections.emptyList();
/*    */           }
/*    */           
/*    */           String number = stateSnapshot.getText();
/*    */           
/*    */           if (!NumberDatabase.getAllPhoneNumber().contains(number)) {
/*    */             stateSnapshot.getPlayer().sendMessage("§cQuesto numero non esiste!");
/*    */             
/*    */             return Collections.emptyList();
/*    */           } 
/*    */           
/*    */           if (number.equals(NumberDatabase.getPhoneNumber(stateSnapshot.getPlayer()))) {
/*    */             return Collections.emptyList();
/*    */           }
/*    */           
/*    */           Player contact = NumberDatabase.getPhoneNumberOwner(number);
/*    */           
/*    */           if (contact == null) {
/*    */             OfflinePlayer offlinePlayerContact = NumberDatabase.getPhoneNumberOwnerOffline(number);
/*    */             
/*    */             if (ContactDatabase.getPlayerContactsUUID(stateSnapshot.getPlayer()).contains(((OfflinePlayer)Objects.<OfflinePlayer>requireNonNull(offlinePlayerContact)).getUniqueId())) {
/*    */               stateSnapshot.getPlayer().sendMessage("§cQuesto contatto esiste già!");
/*    */               
/*    */               return Collections.emptyList();
/*    */             } 
/*    */             
/*    */             ContactDatabase.addOfflineContact(stateSnapshot.getPlayer(), offlinePlayerContact);
/*    */             
/*    */             stateSnapshot.getPlayer().sendMessage("§aHai aggiunto §7" + offlinePlayerContact.getName() + " §anella lista dei tuoi contatti.");
/*    */             
/*    */             stateSnapshot.getPlayer().closeInventory();
/*    */           } 
/*    */           
/*    */           if (ContactDatabase.getPlayerContactsUUID(stateSnapshot.getPlayer()).contains(contact.getUniqueId())) {
/*    */             stateSnapshot.getPlayer().sendMessage("§cQuesto contatto esiste già!");
/*    */             
/*    */             return Collections.emptyList();
/*    */           } 
/*    */           
/*    */           ContactDatabase.addContact(stateSnapshot.getPlayer(), contact);
/*    */           stateSnapshot.getPlayer().sendMessage("§aHai aggiunto §7" + contact.getName() + " §anella lista dei tuoi contatti.");
/*    */           stateSnapshot.getPlayer().closeInventory();
/*    */           return AnvilGUI.Response.text("Fatto");
/* 66 */         }).text("Numero")
/* 67 */       .itemLeft(new ItemStack(Icon.filler()))
/* 68 */       .itemRight(new ItemStack(Icon.filler()))
/* 69 */       .itemOutput(new ItemStack(Icon.check()))
/* 70 */       .plugin((Plugin)RealityPhone.getInstance())
/* 71 */       .open(myPlayer);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\anvilgui\AggiungiContattoGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */