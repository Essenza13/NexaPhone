/*    */ package me.dervinocap.originphone.gui.app;
/*    */ 
/*    */ import com.live.bemmamin.gps.api.GPSAPI;
/*    */ import com.live.bemmamin.gps.logic.Point;
/*    */ import dev.triumphteam.gui.builder.gui.PaginatedBuilder;
/*    */ import dev.triumphteam.gui.builder.item.ItemBuilder;
/*    */ import dev.triumphteam.gui.guis.Gui;
/*    */ import dev.triumphteam.gui.guis.GuiItem;
/*    */ import dev.triumphteam.gui.guis.PaginatedGui;
/*    */ import me.dervinocap.originphone.RealityPhone;
/*    */ import me.dervinocap.originphone.utils.Icon;
/*    */ import net.kyori.adventure.text.Component;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class DestinationGUI {
/*    */   public static void openGui(Player player) {
/* 22 */     PaginatedGui gui = ((PaginatedBuilder)((PaginatedBuilder)Gui.paginated().title((Component)Component.text("§f§lGPS"))).rows(6)).create();
/*    */     
/* 24 */     gui.getFiller().fillBorder(ItemBuilder.from(new ItemStack(Icon.filler())).asGuiItem());
/*    */     
/* 26 */     gui.setItem(6, 4, ((ItemBuilder)((ItemBuilder)ItemBuilder.from(Icon.left()).setName("§4» §cIndietro")).setLore(new String[] { "§8• §fClicca per andare avanti" })).asGuiItem(event -> gui.previous()));
/* 27 */     gui.setItem(6, 6, ((ItemBuilder)((ItemBuilder)ItemBuilder.from(Icon.right()).setName("§2» §aAvanti")).setLore(new String[] { "§8• §fClicca per tornare indietro" })).asGuiItem(event -> gui.next()));
/*    */     
/* 29 */     gui.setDefaultClickAction(event -> event.setCancelled(true));
/*    */ 
/*    */ 
/*    */     
/* 33 */     GPSAPI gpsapi = new GPSAPI((Plugin)RealityPhone.getInstance());
/*    */     
/* 35 */     GuiItem stopGPS = ((ItemBuilder)ItemBuilder.from(Icon.blockedThin()).setName("§cFerma il §lGPS")).asGuiItem(event -> gpsapi.stopGPS(player));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 41 */     gpsapi.getAllPoints().forEach(point -> {
/*    */           GuiItem guiPoint = ((ItemBuilder)ItemBuilder.from(Material.MAP).setName("§a" + point.getName())).asGuiItem(());
/*    */ 
/*    */ 
/*    */           
/*    */           gui.addItem(guiPoint);
/*    */ 
/*    */ 
/*    */           
/*    */           gui.setItem(6, 5, stopGPS);
/*    */         });
/*    */ 
/*    */     
/* 54 */     gui.open((HumanEntity)player);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\gui\app\DestinationGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */