/*     */ package me.dervinocap.originphone.gui.app;
/*     */ 
/*     */ import dev.triumphteam.gui.builder.gui.SimpleBuilder;
/*     */ import dev.triumphteam.gui.builder.item.ItemBuilder;
/*     */ import dev.triumphteam.gui.guis.Gui;
/*     */ import dev.triumphteam.gui.guis.GuiItem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import me.dervinocap.originphone.RealityPhone;
/*     */ import me.dervinocap.originphone.gui.app.twitter.TweetsGUI;
/*     */ import me.dervinocap.originphone.utils.BasicsFunction;
/*     */ import me.dervinocap.originphone.utils.Icon;
/*     */ import me.dervinocap.originphone.utils.Tweets;
/*     */ import me.dervinocap.realityposte.database.PayPalDatabase;
/*     */ import me.dervinocap.realityscuola.gui.RegistroElettronicoGUI;
/*     */ import net.kyori.adventure.text.Component;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.ClickType;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class MainAppGUI
/*     */ {
/*  29 */   public static List<UUID> activePaypal = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void open(final Player player) {
/*  36 */     Gui gui = ((SimpleBuilder)((SimpleBuilder)Gui.gui().title((Component)Component.text(BasicsFunction.hex("&8Applicazioni")))).rows(3)).create();
/*     */     
/*  38 */     gui.setDefaultClickAction(event -> event.setCancelled(true));
/*     */ 
/*     */ 
/*     */     
/*  42 */     GuiItem twitter = ((ItemBuilder)((ItemBuilder)((ItemBuilder)ItemBuilder.from(Material.PAPER).model(10369)).setName("§bTwitter")).setLore(Arrays.asList(new String[] { BasicsFunction.hex("&7Tasto-Destro per creare un tweet") }))).asGuiItem(event -> {
/*     */           if (event.getClick() == ClickType.RIGHT) {
/*     */             if (Tweets.cooldown.contains(player.getUniqueId())) {
/*     */               player.sendMessage(BasicsFunction.hex("&f &#DE2727Devi aspettare prima di postare un altro tweet!"));
/*     */               
/*     */               player.closeInventory();
/*     */               
/*     */               return;
/*     */             } 
/*     */             
/*     */             if (!Tweets.titolo.containsKey(player.getUniqueId())) {
/*     */               Tweets.sendingTitolo.add(player.getUniqueId());
/*     */               
/*     */               player.sendMessage(BasicsFunction.hex("&f &#F59F0BScrivi in chat il titolo del post, scrivi &7'Annulla'&#F59F0B per annullare."));
/*     */               
/*     */               player.closeInventory();
/*     */               
/*     */               return;
/*     */             } 
/*     */             return;
/*     */           } 
/*     */           TweetsGUI.openReal(player, BasicsFunction.getNextId() - 1);
/*     */         });
/*  65 */     GuiItem registroElet = ((ItemBuilder)((ItemBuilder)((ItemBuilder)ItemBuilder.from(Material.PAPER).model(10391)).setName("§aRegistro §lElettronico")).setLore(Arrays.asList(new String[] { BasicsFunction.hex("&7") }))).asGuiItem(event -> RegistroElettronicoGUI.open(player, player));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     if (PayPalDatabase.checkPayPal(player)) {
/*  74 */       Material itemStack = Material.PAPER;
/*     */     } else {
/*  76 */       Material itemStack = Material.BARRIER;
/*     */     } 
/*     */     
/*  79 */     GuiItem paypal = ((ItemBuilder)((ItemBuilder)((ItemBuilder)ItemBuilder.from(Material.PAPER).model(10392)).setName("§9§lPayPal")).setLore(Arrays.asList(new String[] { BasicsFunction.hex("&7Usalo per mandare pagamenti rapidi!") }))).asGuiItem(event -> {
/*     */           if (!PayPalDatabase.checkPayPal(player)) {
/*     */             player.sendMessage(BasicsFunction.hex("&f &#DE2727Non possiedi un account PayPal attivo!"));
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/*     */           player.sendMessage(BasicsFunction.hex(""));
/*     */           
/*     */           player.sendMessage(BasicsFunction.hex(" &b&lPAGAMENTO PAYPAL"));
/*     */           
/*     */           player.sendMessage(BasicsFunction.hex(" &7Scrivi in chat questo formato per"));
/*     */           player.sendMessage(BasicsFunction.hex(" &7inviare un pagamento veloce."));
/*     */           player.sendMessage(BasicsFunction.hex(" &7&o(( [Nome Cittadino] [Soldi] [Motivazione] ))"));
/*     */           player.sendMessage(BasicsFunction.hex(""));
/*     */           activePaypal.add(player.getUniqueId());
/*     */           Bukkit.getScheduler().runTaskLater((Plugin)RealityPhone.getInstance(), new Runnable()
/*     */               {
/*     */                 public void run()
/*     */                 {
/*  99 */                   if (!MainAppGUI.activePaypal.contains(player.getUniqueId()))
/*     */                     return; 
/* 101 */                   MainAppGUI.activePaypal.remove(player.getUniqueId());
/* 102 */                   player.sendMessage(BasicsFunction.hex("&f &#DE2727Pagamento annullato."));
/*     */                 }
/*     */               },  200L);
/*     */         });
/*     */ 
/*     */     
/* 108 */     gui.getFiller().fill(ItemBuilder.from(Icon.filler()).asGuiItem());
/*     */     
/* 110 */     gui.setItem(2, 3, twitter);
/* 111 */     gui.setItem(2, 5, paypal);
/* 112 */     gui.setItem(2, 7, registroElet);
/*     */     
/* 114 */     gui.open((HumanEntity)player);
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\gui\app\MainAppGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */