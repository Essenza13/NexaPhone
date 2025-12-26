/*    */ package me.dervinocap.originphone.gui;
/*    */ 
/*    */ import dev.triumphteam.gui.builder.gui.SimpleBuilder;
/*    */ import dev.triumphteam.gui.builder.item.ItemBuilder;
/*    */ import dev.triumphteam.gui.guis.Gui;
/*    */ import dev.triumphteam.gui.guis.GuiItem;
/*    */ import me.dervinocap.originphone.utils.ItemManager;
/*    */ import net.kyori.adventure.text.Component;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class ItemGUI
/*    */ {
/*    */   public static void open(Player player) {
/* 19 */     Gui gui = ((SimpleBuilder)((SimpleBuilder)Gui.gui().title((Component)Component.text("Phone Item"))).rows(3)).create();
/*    */     
/* 21 */     gui.setDefaultClickAction(event -> event.setCancelled(true));
/*    */ 
/*    */ 
/*    */     
/* 25 */     GuiItem iphone14 = ItemBuilder.from(ItemManager.iphone14()).asGuiItem(event -> player.getInventory().addItem(new ItemStack[] { ItemManager.iphone14() }));
/*    */ 
/*    */ 
/*    */     
/* 29 */     GuiItem ipadpro = ItemBuilder.from(ItemManager.ipadPro()).asGuiItem(event -> player.getInventory().addItem(new ItemStack[] { ItemManager.ipadPro() }));
/*    */ 
/*    */ 
/*    */     
/* 33 */     GuiItem s22 = ItemBuilder.from(ItemManager.s22()).asGuiItem(event -> player.getInventory().addItem(new ItemStack[] { ItemManager.s22() }));
/*    */ 
/*    */ 
/*    */     
/* 37 */     GuiItem nokia = ItemBuilder.from(ItemManager.nokia()).asGuiItem(event -> player.getInventory().addItem(new ItemStack[] { ItemManager.nokia() }));
/*    */ 
/*    */ 
/*    */     
/* 41 */     GuiItem powerBank100 = ItemBuilder.from(ItemManager.powerBank100()).asGuiItem(event -> player.getInventory().addItem(new ItemStack[] { ItemManager.powerBank100() }));
/*    */ 
/*    */ 
/*    */     
/* 45 */     GuiItem powerBank200 = ItemBuilder.from(ItemManager.powerBank200()).asGuiItem(event -> player.getInventory().addItem(new ItemStack[] { ItemManager.powerBank200() }));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 50 */     GuiItem powerBank500 = ItemBuilder.from(ItemManager.powerBank500()).asGuiItem(event -> player.getInventory().addItem(new ItemStack[] { ItemManager.powerBank500() }));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 55 */     gui.getFiller().fillBorder(ItemBuilder.from(new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1, (short)3)).asGuiItem());
/*    */     
/* 57 */     gui.addItem(new GuiItem[] { iphone14 });
/* 58 */     gui.addItem(new GuiItem[] { ipadpro });
/* 59 */     gui.addItem(new GuiItem[] { s22 });
/* 60 */     gui.addItem(new GuiItem[] { nokia });
/* 61 */     gui.addItem(new GuiItem[] { powerBank100 });
/* 62 */     gui.addItem(new GuiItem[] { powerBank200 });
/* 63 */     gui.addItem(new GuiItem[] { powerBank500 });
/*    */     
/* 65 */     gui.open((HumanEntity)player);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\gui\ItemGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */