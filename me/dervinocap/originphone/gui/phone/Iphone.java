/*    */ package me.dervinocap.originphone.gui.phone;
/*    */ 
/*    */ import de.tr7zw.nbtapi.NBTItem;
/*    */ import dev.triumphteam.gui.builder.gui.SimpleBuilder;
/*    */ import dev.triumphteam.gui.builder.item.ItemBuilder;
/*    */ import dev.triumphteam.gui.guis.Gui;
/*    */ import dev.triumphteam.gui.guis.GuiItem;
/*    */ import java.util.Arrays;
/*    */ import me.dervinocap.originphone.gui.app.ChatsGUI;
/*    */ import me.dervinocap.originphone.gui.app.DestinationGUI;
/*    */ import me.dervinocap.originphone.gui.app.EmergencyGUI;
/*    */ import me.dervinocap.originphone.gui.app.MainAppGUI;
/*    */ import me.dervinocap.originphone.gui.telefonia.TelefoniaGUI;
/*    */ import me.dervinocap.originphone.utils.Icon;
/*    */ import net.kyori.adventure.text.Component;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Iphone
/*    */ {
/*    */   public static void open(Player player, NBTItem nbtItem) {
/* 35 */     Gui gui = ((SimpleBuilder)((SimpleBuilder)Gui.gui().title((Component)Component.text("§8§liPhone 14"))).rows(3)).create();
/*    */     
/* 37 */     gui.setDefaultClickAction(event -> event.setCancelled(true));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 42 */     GuiItem batteria = ((ItemBuilder)((ItemBuilder)ItemBuilder.from(Icon.bolt()).setName("§e§lBatteria")).setLore(Arrays.asList(new String[] { "§8• §7Carica Rimanente: §e" + nbtItem.getInteger("Carica") + "%" }))).asGuiItem(event -> event.setCancelled(true));
/*    */ 
/*    */ 
/*    */     
/* 46 */     GuiItem telefoniaItem = ((ItemBuilder)((ItemBuilder)ItemBuilder.from(Icon.glassLend()).setName("§b§lTelefonia")).setLore(Arrays.asList(new String[] { "§7Apri questa app per chiamare", "§7o messaggiare un tuo contatto" }))).asGuiItem(event -> TelefoniaGUI.open(player));
/*    */ 
/*    */ 
/*    */     
/* 50 */     GuiItem gps = ((ItemBuilder)((ItemBuilder)ItemBuilder.from(Icon.globe()).setName("§a§lGPS")).setLore(Arrays.asList(new String[] { "§7Apri questa app per aprire", "§7il GPS" }))).asGuiItem(event -> DestinationGUI.openGui(player));
/*    */ 
/*    */ 
/*    */     
/* 54 */     GuiItem emergencyItem = ((ItemBuilder)((ItemBuilder)ItemBuilder.from(Icon.alert()).setName("§4§lEmergenze")).setLore(Arrays.asList(new String[] { "§7Apri questa app per chiedere", "§7aiuto alle forze dell'ordine" }))).asGuiItem(event -> EmergencyGUI.open(player));
/*    */ 
/*    */ 
/*    */     
/* 58 */     GuiItem chatItem = ((ItemBuilder)((ItemBuilder)ItemBuilder.from(Icon.text()).setName("§7§lChat")).setLore(Arrays.asList(new String[] { "§7Apri questa app per vedere", "§7la lista delle tue chat" }))).asGuiItem(event -> ChatsGUI.open(player));
/*    */ 
/*    */ 
/*    */     
/* 62 */     GuiItem applicazioni = ((ItemBuilder)((ItemBuilder)ItemBuilder.from(Material.PAPER).model(10379)).setName("§a§lApplicazioni")).asGuiItem(event -> MainAppGUI.open(player));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 67 */     gui.getFiller().fill(new GuiItem(ItemBuilder.from(Icon.filler()).asGuiItem().getItemStack()));
/*    */     
/* 69 */     gui.setItem(2, 8, batteria);
/* 70 */     gui.setItem(2, 2, telefoniaItem);
/* 71 */     gui.setItem(2, 3, gps);
/* 72 */     gui.setItem(2, 4, emergencyItem);
/* 73 */     gui.setItem(2, 5, chatItem);
/* 74 */     gui.setItem(2, 6, applicazioni);
/*    */     
/* 76 */     gui.open((HumanEntity)player);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\gui\phone\Iphone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */