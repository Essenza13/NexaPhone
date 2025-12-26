/*    */ package me.dervinocap.originphone.gui.app;
/*    */ 
/*    */ import dev.triumphteam.gui.builder.gui.PaginatedBuilder;
/*    */ import dev.triumphteam.gui.builder.item.ItemBuilder;
/*    */ import dev.triumphteam.gui.guis.Gui;
/*    */ import dev.triumphteam.gui.guis.GuiItem;
/*    */ import dev.triumphteam.gui.guis.PaginatedGui;
/*    */ import it.realityrp.realitygeneral.database.ImpattoSilenziosoDB;
/*    */ import me.dervinocap.originphone.commands.chatcommand.subcommands.ChatCommand;
/*    */ import me.dervinocap.originphone.database.ChatDatabase;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.Icon;
/*    */ import net.kyori.adventure.text.Component;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatsGUI
/*    */ {
/*    */   public static void open(Player player) {
/* 24 */     PaginatedGui paginatedGui = ((PaginatedBuilder)((PaginatedBuilder)Gui.paginated().title((Component)Component.text("§f§lChats"))).rows(5)).create();
/*    */     
/* 26 */     paginatedGui.getFiller().fillBorder(ItemBuilder.from(new ItemStack(Icon.filler())).asGuiItem());
/*    */     
/* 28 */     paginatedGui.setItem(5, 4, ((ItemBuilder)((ItemBuilder)ItemBuilder.from(Icon.left()).setName("§4» §cIndietro")).setLore(new String[] { "§8• §fClicca per andare avanti" })).asGuiItem(event -> paginatedGui.previous()));
/* 29 */     paginatedGui.setItem(5, 6, ((ItemBuilder)((ItemBuilder)ItemBuilder.from(Icon.right()).setName("§2» §aAvanti")).setLore(new String[] { "§8• §fClicca per tornare indietro" })).asGuiItem(event -> paginatedGui.next()));
/*    */     
/* 31 */     paginatedGui.setDefaultClickAction(event -> event.setCancelled(true));
/*    */ 
/*    */ 
/*    */     
/* 35 */     if (ChatDatabase.getChatChats() == null) {
/* 36 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Non sei membro di nessuna chat!"));
/*    */       
/*    */       return;
/*    */     } 
/* 40 */     ChatDatabase.getPlayerChats(player.getUniqueId()).forEach(string -> {
/*    */           GuiItem guiChat = ((ItemBuilder)ItemBuilder.from(Icon.chat()).setName("§bChat §l" + string.toUpperCase())).asGuiItem(());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/*    */           paginatedGui.addItem(guiChat);
/*    */         });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 61 */     paginatedGui.open((HumanEntity)player);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\gui\app\ChatsGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */