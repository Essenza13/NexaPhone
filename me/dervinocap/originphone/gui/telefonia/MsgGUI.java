/*     */ package me.dervinocap.originphone.gui.telefonia;
/*     */ 
/*     */ import dev.triumphteam.gui.builder.gui.PaginatedBuilder;
/*     */ import dev.triumphteam.gui.builder.item.ItemBuilder;
/*     */ import dev.triumphteam.gui.guis.Gui;
/*     */ import dev.triumphteam.gui.guis.GuiItem;
/*     */ import dev.triumphteam.gui.guis.PaginatedGui;
/*     */ import it.realityrp.realitygeneral.database.ImpattoSilenziosoDB;
/*     */ import java.util.UUID;
/*     */ import me.dervinocap.originphone.anvilgui.AggiungiContattoGUI;
/*     */ import me.dervinocap.originphone.database.ContactDatabase;
/*     */ import me.dervinocap.originphone.database.NumberDatabase;
/*     */ import me.dervinocap.originphone.utils.BasicsFunction;
/*     */ import me.dervinocap.originphone.utils.Icon;
/*     */ import me.dervinocap.originphone.utils.ItemManager;
/*     */ import me.dervinocap.originphone.utils.MSG;
/*     */ import net.kyori.adventure.text.Component;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MsgGUI
/*     */ {
/*     */   public static void open(Player player) {
/*  29 */     PaginatedGui paginatedGui = ((PaginatedBuilder)((PaginatedBuilder)Gui.paginated().title((Component)Component.text("§8§lTelefonia"))).rows(3)).create();
/*     */     
/*  31 */     paginatedGui.getFiller().fillBottom(ItemBuilder.from(Icon.filler()).asGuiItem());
/*     */     
/*  33 */     paginatedGui.setItem(3, 4, ((ItemBuilder)((ItemBuilder)ItemBuilder.from(Icon.left()).setName("§4» §cIndietro")).setLore(new String[] { "§8• §fClicca per andare avanti" })).asGuiItem(event -> paginatedGui.next()));
/*  34 */     paginatedGui.setItem(3, 6, ((ItemBuilder)((ItemBuilder)ItemBuilder.from(Icon.right()).setName("§2» §aAvanti")).setLore(new String[] { "§8• §fClicca per tornare indietro" })).asGuiItem(event -> paginatedGui.previous()));
/*     */     
/*  36 */     GuiItem aggiungiContatto = ((ItemBuilder)ItemBuilder.from(Icon.plus()).setName("§7Aggiungi un §lContatto")).asGuiItem(event -> AggiungiContattoGUI.open(player));
/*     */ 
/*     */ 
/*     */     
/*  40 */     paginatedGui.setItem(3, 5, aggiungiContatto);
/*     */     
/*  42 */     paginatedGui.setDefaultClickAction(event -> event.setCancelled(true));
/*     */ 
/*     */ 
/*     */     
/*  46 */     ContactDatabase.getPlayerContactsUUID(player).forEach(string -> {
/*     */           if (Bukkit.getPlayer(string) != null) {
/*     */             Player contact = Bukkit.getPlayer(string);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             GuiItem guiContact = ItemBuilder.from(ItemManager.contactItem(contact)).asGuiItem(());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             paginatedGui.addItem(guiContact);
/*     */           } else {
/*     */             OfflinePlayer contact = Bukkit.getOfflinePlayer(string);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             GuiItem guiContact = ItemBuilder.from(ItemManager.contactItemOffline(contact)).asGuiItem(());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             paginatedGui.addItem(guiContact);
/*     */           } 
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     GuiItem messageAction = ((ItemBuilder)ItemBuilder.from(ItemManager.guiMessageContact()).glow()).asGuiItem(event -> TelefoniaGUI.open(player));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     GuiItem callAction = ItemBuilder.from(ItemManager.guiCallContact()).asGuiItem(event -> CallGUI.open(player));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     paginatedGui.setItem(3, 1, callAction);
/* 105 */     paginatedGui.setItem(3, 9, messageAction);
/*     */     
/* 107 */     paginatedGui.open((HumanEntity)player);
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\gui\telefonia\MsgGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */