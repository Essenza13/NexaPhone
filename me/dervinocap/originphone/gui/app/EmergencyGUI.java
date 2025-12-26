/*    */ package me.dervinocap.originphone.gui.app;
/*    */ 
/*    */ import dev.triumphteam.gui.builder.gui.SimpleBuilder;
/*    */ import dev.triumphteam.gui.builder.item.ItemBuilder;
/*    */ import dev.triumphteam.gui.components.GuiType;
/*    */ import dev.triumphteam.gui.guis.Gui;
/*    */ import dev.triumphteam.gui.guis.GuiItem;
/*    */ import it.realityrp.realitygeneral.database.ImpattoSilenziosoDB;
/*    */ import java.util.Arrays;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.EmergencyCall;
/*    */ import me.dervinocap.originphone.utils.ItemManager;
/*    */ import net.kyori.adventure.text.Component;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EmergencyGUI
/*    */ {
/*    */   public static void open(Player player) {
/* 26 */     Gui gui = ((SimpleBuilder)Gui.gui().title((Component)Component.text("§f§lEmergenze"))).type(GuiType.HOPPER).create();
/*    */     
/* 28 */     gui.setDefaultClickAction(event -> event.setCancelled(true));
/*    */ 
/*    */ 
/*    */     
/* 32 */     GuiItem ambulance = ((ItemBuilder)((ItemBuilder)ItemBuilder.from(ItemManager.ambulanza()).setName("§cChiama un §lAmbulanza")).setLore(Arrays.asList(new String[] { "§8• §7Clicca per inviare una", "§8• §7segnalazione all'ospedale" }))).asGuiItem(event -> {
/*    */           if (ImpattoSilenziosoDB.checkIfEnabled()) {
/*    */             player.sendMessage(BasicsFunction.hex("&f &#DE2727Segnale non trovato, riprova piu' tardi."));
/*    */             
/*    */             return;
/*    */           } 
/*    */           
/*    */           if (!EmergencyCall.callingAmbulance.contains(player.getUniqueId())) {
/*    */             EmergencyCall.callingAmbulance.add(player.getUniqueId());
/*    */           }
/*    */           
/*    */           EmergencyCall.callingVDF.remove(player.getUniqueId());
/*    */           
/*    */           EmergencyCall.callingPolice.remove(player.getUniqueId());
/*    */           
/*    */           player.sendMessage(BasicsFunction.hex("&f &#84CC17Scrivi in chat la motivazione, scrivi &7'Annulla'&#84CC17 per annullare."));
/*    */           
/*    */           player.closeInventory();
/*    */         });
/* 51 */     GuiItem police = ((ItemBuilder)((ItemBuilder)ItemBuilder.from(ItemManager.polizia()).setName("§9Chiama la §lPolizia")).setLore(Arrays.asList(new String[] { "§8• §7Clicca per inviare una", "§8• §7segnalazione alla centrale" }))).asGuiItem(event -> {
/*    */           if (ImpattoSilenziosoDB.checkIfEnabled()) {
/*    */             player.sendMessage(BasicsFunction.hex("&f &#DE2727Segnale non trovato, riprova piu' tardi."));
/*    */             
/*    */             return;
/*    */           } 
/*    */           
/*    */           if (!EmergencyCall.callingPolice.contains(player.getUniqueId())) {
/*    */             EmergencyCall.callingPolice.add(player.getUniqueId());
/*    */           }
/*    */           
/*    */           EmergencyCall.callingVDF.remove(player.getUniqueId());
/*    */           
/*    */           EmergencyCall.callingAmbulance.remove(player.getUniqueId());
/*    */           
/*    */           player.sendMessage(BasicsFunction.hex("&f &#84CC17Scrivi in chat la motivazione, scrivi &7'Annulla'&#84CC17 per annullare."));
/*    */           
/*    */           player.closeInventory();
/*    */         });
/* 70 */     GuiItem vdf = ((ItemBuilder)((ItemBuilder)ItemBuilder.from(ItemManager.vdf()).setName("§4Chiama i §lVigili del Fuoco")).setLore(Arrays.asList(new String[] { "§8• §7Clicca per inviare una", "§8• §7segnalazione ai VDF" }))).asGuiItem(event -> {
/*    */           if (ImpattoSilenziosoDB.checkIfEnabled()) {
/*    */             player.sendMessage(BasicsFunction.hex("&f &#DE2727Segnale non trovato, riprova piu' tardi."));
/*    */             
/*    */             return;
/*    */           } 
/*    */           
/*    */           if (!EmergencyCall.callingVDF.contains(player.getUniqueId())) {
/*    */             EmergencyCall.callingVDF.add(player.getUniqueId());
/*    */           }
/*    */           
/*    */           EmergencyCall.callingAmbulance.remove(player.getUniqueId());
/*    */           
/*    */           EmergencyCall.callingPolice.remove(player.getUniqueId());
/*    */           
/*    */           player.sendMessage(BasicsFunction.hex("&f &#84CC17Scrivi in chat la motivazione, scrivi &7'Annulla'&#84CC17 per annullare."));
/*    */           
/*    */           player.closeInventory();
/*    */         });
/* 89 */     gui.setItem(0, ambulance);
/* 90 */     gui.setItem(2, police);
/* 91 */     gui.setItem(4, vdf);
/*    */     
/* 93 */     gui.open((HumanEntity)player);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\gui\app\EmergencyGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */