/*     */ package me.dervinocap.originphone.gui.app.twitter;
/*     */ 
/*     */ import dev.triumphteam.gui.builder.gui.PaginatedBuilder;
/*     */ import dev.triumphteam.gui.builder.item.ItemBuilder;
/*     */ import dev.triumphteam.gui.guis.Gui;
/*     */ import dev.triumphteam.gui.guis.GuiItem;
/*     */ import dev.triumphteam.gui.guis.PaginatedGui;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import me.dervinocap.originphone.database.TwitterDatabase;
/*     */ import me.dervinocap.originphone.utils.BasicsFunction;
/*     */ import me.dervinocap.originphone.utils.Icon;
/*     */ import me.dervinocap.originphone.utils.ItemManager;
/*     */ import net.kyori.adventure.text.Component;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.ClickType;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TweetsGUI
/*     */ {
/*     */   public static void open(Player player, int id) {
/*  29 */     PaginatedGui paginatedGui = ((PaginatedBuilder)((PaginatedBuilder)Gui.paginated().title((Component)Component.text("§8§lTweets"))).rows(3)).create();
/*     */     
/*  31 */     paginatedGui.getFiller().fillBorder(ItemBuilder.from(new ItemStack(Icon.filler())).asGuiItem());
/*     */     
/*  33 */     paginatedGui.setItem(3, 9, ((ItemBuilder)((ItemBuilder)((ItemBuilder)ItemBuilder.from(Material.PAPER).model(10276)).setName("§2» §aAvanti")).setLore(new String[] { "§8• §fClicca per andare avanti" })).asGuiItem(event -> openReal(player, id - 1)));
/*  34 */     paginatedGui.setItem(1, 9, ((ItemBuilder)((ItemBuilder)((ItemBuilder)ItemBuilder.from(Material.PAPER).model(10282)).setName("§4» §cIndietro")).setLore(new String[] { "§8• §fClicca per tornare indietro" })).asGuiItem(event -> openReal(player, id + 1)));
/*     */     
/*  36 */     paginatedGui.setDefaultClickAction(event -> event.setCancelled(true));
/*     */ 
/*     */ 
/*     */     
/*  40 */     if (TwitterDatabase.getTweet().isEmpty()) {
/*  41 */       player.sendMessage("§cNon c'è nessun tweet al momento.");
/*  42 */       player.sendMessage(BasicsFunction.hex("&f &#F59F0BNon c'è nessun tweet al momento."));
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  47 */     GuiItem skullTweet = ((ItemBuilder)((ItemBuilder)((ItemBuilder)ItemBuilder.from(ItemManager.playerSkull(TwitterDatabase.getScrittore(id))).setLore(Arrays.asList(new String[] { BasicsFunction.hex("&8" + id) }))).model(1)).setName(TwitterDatabase.getScrittore(id))).asGuiItem(event -> {
/*     */           if (event.getClick() == ClickType.RIGHT) {
/*     */             if (player.hasPermission("realityphone.admin")) {
/*     */               TwitterDatabase.removeTweet(id);
/*     */             }
/*     */ 
/*     */             
/*     */             player.sendMessage(BasicsFunction.hex("&f &#84CC17Hai rimosso il tweet " + id));
/*     */           } 
/*     */ 
/*     */           
/*     */           player.closeInventory();
/*     */         });
/*     */ 
/*     */     
/*  62 */     GuiItem paperItem = ((ItemBuilder)((ItemBuilder)ItemBuilder.from(Material.MAP).setName(BasicsFunction.hex(TwitterDatabase.getTitolo(id)))).setLore(Arrays.asList(new String[] { BasicsFunction.hex("&8• &7Premi per visualizzare il contenuto") }))).asGuiItem(event -> {
/*     */           player.sendMessage(BasicsFunction.hex("&f            "));
/*     */           
/*     */           player.sendMessage(BasicsFunction.hex("&f"));
/*     */           
/*     */           player.sendMessage(BasicsFunction.hex("&f"));
/*     */           
/*     */           player.sendMessage(BasicsFunction.hex("&f"));
/*     */           
/*     */           player.sendMessage(BasicsFunction.hex("&f"));
/*     */           player.sendMessage(BasicsFunction.hex("&b&l" + TwitterDatabase.getTitolo(id)));
/*     */           player.sendMessage(BasicsFunction.hex("&7&o" + TwitterDatabase.getContenuto(id)));
/*     */           player.sendMessage(BasicsFunction.hex("&f"));
/*     */           player.closeInventory();
/*     */         });
/*  77 */     GuiItem like = ((ItemBuilder)((ItemBuilder)((ItemBuilder)ItemBuilder.from(Material.PAPER).setName(BasicsFunction.hex("&aMetti un &lLike"))).model(10396)).setLore(Arrays.asList(new String[] { BasicsFunction.hex("&8• &7Premi per mettere like al post"), BasicsFunction.hex("&8» &7Likes: &a" + TwitterDatabase.getLikes(id)) }))).asGuiItem(event -> {
/*     */           List<String> strings = TwitterDatabase.getLikesPlayer(id);
/*     */           
/*     */           if (strings.contains(player.getName())) {
/*     */             player.sendMessage(BasicsFunction.hex("&f &#F59F0BHai già messo like a questo post!"));
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/*     */           openReal(player, id);
/*     */           
/*     */           player.sendMessage(BasicsFunction.hex("&f &#84CC17Hai messo like al post di " + TwitterDatabase.getScrittore(id)));
/*     */           
/*     */           strings.add(player.getName());
/*     */           
/*     */           TwitterDatabase.addLike(id, strings);
/*     */         });
/*     */     
/*  95 */     GuiItem dislike = ((ItemBuilder)((ItemBuilder)((ItemBuilder)ItemBuilder.from(Material.PAPER).setName(BasicsFunction.hex("&cMetti un &lDislike"))).model(10397)).setLore(Arrays.asList(new String[] { BasicsFunction.hex("&8• &7Premi per mettere dislike al post"), BasicsFunction.hex("&8» &7Dislikes: &c" + TwitterDatabase.getDislikes(id)) }))).asGuiItem(event -> {
/*     */           List<String> strings = TwitterDatabase.getDislikesPlayer(id);
/*     */           
/*     */           if (strings.contains(player.getName())) {
/*     */             player.sendMessage(BasicsFunction.hex("&f &#F59F0BHai già messo dislike a questo post!"));
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/*     */           openReal(player, id);
/*     */           
/*     */           player.sendMessage(BasicsFunction.hex("&f &#84CC17Hai messo dislike al post di " + TwitterDatabase.getScrittore(id)));
/*     */           
/*     */           strings.add(player.getName());
/*     */           
/*     */           TwitterDatabase.addDislike(id, strings);
/*     */         });
/*     */     
/* 113 */     paginatedGui.setItem(2, 2, skullTweet);
/*     */     
/* 115 */     paginatedGui.setItem(2, 4, ((ItemBuilder)ItemBuilder.from(Material.BARRIER).setName(BasicsFunction.hex("&f"))).asGuiItem());
/* 116 */     paginatedGui.setItem(2, 5, ((ItemBuilder)ItemBuilder.from(Material.BARRIER).setName(BasicsFunction.hex("&f"))).asGuiItem());
/*     */     
/* 118 */     paginatedGui.setItem(2, 6, paperItem);
/*     */     
/* 120 */     paginatedGui.setItem(2, 7, ((ItemBuilder)ItemBuilder.from(Material.BARRIER).setName(BasicsFunction.hex("&f"))).asGuiItem());
/* 121 */     paginatedGui.setItem(2, 8, ((ItemBuilder)ItemBuilder.from(Material.BARRIER).setName(BasicsFunction.hex("&f"))).asGuiItem());
/*     */     
/* 123 */     paginatedGui.setItem(2, 9, ((ItemBuilder)((ItemBuilder)ItemBuilder.from(Material.PAPER).model(10278)).setName(BasicsFunction.hex("&3» &bPagina: &f" + id))).asGuiItem());
/*     */     
/* 125 */     paginatedGui.setItem(3, 5, like);
/* 126 */     paginatedGui.setItem(3, 7, dislike);
/*     */     
/* 128 */     paginatedGui.setItem(2, 3, ItemBuilder.from(Icon.filler()).asGuiItem());
/*     */ 
/*     */     
/* 131 */     paginatedGui.open((HumanEntity)player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void openReal(Player player, int id) {
/* 137 */     open(player, id);
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\gui\app\twitter\TweetsGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */