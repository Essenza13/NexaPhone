/*     */ package me.dervinocap.originphone.gui.phone;
/*     */ 
/*     */ import de.tr7zw.nbtapi.NBTItem;
/*     */ import dev.triumphteam.gui.builder.gui.SimpleBuilder;
/*     */ import dev.triumphteam.gui.builder.item.ItemBuilder;
/*     */ import dev.triumphteam.gui.guis.Gui;
/*     */ import dev.triumphteam.gui.guis.GuiItem;
/*     */ import java.util.Arrays;
/*     */ import me.dervinocap.originphone.gui.app.EmergencyGUI;
/*     */ import me.dervinocap.originphone.gui.telefonia.TelefoniaGUI;
/*     */ import me.dervinocap.originphone.utils.Icon;
/*     */ import net.kyori.adventure.text.Component;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Nokia
/*     */ {
/*     */   public static void open(Player player, NBTItem nbtItem) {
/*  27 */     Gui gui = ((SimpleBuilder)((SimpleBuilder)Gui.gui().title((Component)Component.text("§8§lNokia 3310"))).rows(3)).create();
/*     */     
/*  29 */     gui.setDefaultClickAction(event -> event.setCancelled(true));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  34 */     GuiItem batteria = ((ItemBuilder)((ItemBuilder)ItemBuilder.from(Icon.bolt()).setName("§e§lBatteria")).setLore(Arrays.asList(new String[] { "§8• §7Carica Rimanente: §e" + nbtItem.getInteger("Carica") + "%" }))).asGuiItem(event -> event.setCancelled(true));
/*     */ 
/*     */ 
/*     */     
/*  38 */     GuiItem telefoniaItem = ((ItemBuilder)((ItemBuilder)ItemBuilder.from(Icon.glassLend()).setName("§b§lTelefonia")).setLore(Arrays.asList(new String[] { "§7Apri questa app per chiamare", "§7o messaggiare un tuo contatto" }))).asGuiItem(event -> TelefoniaGUI.open(player));
/*     */ 
/*     */ 
/*     */     
/*  42 */     GuiItem emergencyItem = ((ItemBuilder)((ItemBuilder)ItemBuilder.from(Icon.alert()).setName("§4§lEmergenze")).setLore(Arrays.asList(new String[] { "§7Apri questa app per chiedere", "§7aiuto alle forze dell'ordine" }))).asGuiItem(event -> EmergencyGUI.open(player));
/*     */ 
/*     */ 
/*     */     
/*  46 */     GuiItem chatItem = ((ItemBuilder)ItemBuilder.from(Icon.blockedThin()).setName("§cApp non §lDisponibile")).asGuiItem(event -> player.closeInventory());
/*     */ 
/*     */ 
/*     */     
/*  50 */     GuiItem twitter = ((ItemBuilder)ItemBuilder.from(Icon.blockedThin()).setName("§cApp non §lDisponibile")).asGuiItem(event -> player.closeInventory());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     gui.getFiller().fill(new GuiItem(ItemBuilder.from(Icon.filler()).asGuiItem().getItemStack()));
/*     */     
/* 122 */     gui.setItem(2, 8, batteria);
/* 123 */     gui.setItem(2, 2, telefoniaItem);
/* 124 */     gui.setItem(2, 3, chatItem);
/* 125 */     gui.setItem(2, 4, emergencyItem);
/* 126 */     gui.setItem(2, 5, chatItem);
/* 127 */     gui.setItem(2, 6, twitter);
/*     */     
/* 129 */     gui.open((HumanEntity)player);
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\gui\phone\Nokia.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */