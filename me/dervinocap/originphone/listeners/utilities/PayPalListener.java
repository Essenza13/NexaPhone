/*     */ package me.dervinocap.originphone.listeners.utilities;
/*     */ 
/*     */ import de.tr7zw.nbtapi.NBTItem;
/*     */ import me.dervinocap.originphone.gui.app.MainAppGUI;
/*     */ import me.dervinocap.originphone.utils.BasicsFunction;
/*     */ import me.dervinocap.originphone.utils.customloader.PluginCustomLoader;
/*     */ import me.dervinocap.realityposte.database.PayPalDatabase;
/*     */ import net.milkbowl.vault.economy.EconomyResponse;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PayPalListener
/*     */   implements Listener
/*     */ {
/*     */   public static boolean isDouble(String str) {
/*     */     try {
/*  24 */       Double.parseDouble(str);
/*  25 */       return true;
/*  26 */     } catch (NumberFormatException e) {
/*  27 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onChat(AsyncPlayerChatEvent event) {
/*  34 */     Player player = event.getPlayer();
/*     */     
/*  36 */     if (!MainAppGUI.activePaypal.contains(player.getUniqueId()))
/*     */       return; 
/*  38 */     event.setCancelled(true);
/*     */     
/*  40 */     String[] parts = event.getMessage().split(" ");
/*     */     
/*  42 */     String t = parts[0];
/*  43 */     String m = parts[1];
/*  44 */     String mm = parts[2];
/*     */     
/*  46 */     OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(t);
/*     */     
/*  48 */     if (!isDouble(m)) {
/*  49 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727I soldi devono essere un numero!"));
/*  50 */       MainAppGUI.activePaypal.remove(player.getUniqueId());
/*     */       
/*     */       return;
/*     */     } 
/*  54 */     if (player.getInventory().getItemInMainHand() == null)
/*  55 */       return;  if (player.getInventory().getItemInMainHand().getType() == null)
/*  56 */       return;  if (player.getInventory().getItemInMainHand().getAmount() == 0)
/*  57 */       return;  if (player.getInventory().getItemInMainHand().getType() == Material.AIR)
/*     */       return; 
/*  59 */     if (!BasicsFunction.isPhone(player.getInventory().getItemInMainHand()))
/*     */       return; 
/*  61 */     NBTItem nbtItem = new NBTItem(player.getInventory().getItemInMainHand());
/*     */     
/*  63 */     double money = Double.parseDouble(m);
/*     */     
/*  65 */     double maxMoney = 0.0D;
/*     */     
/*  67 */     if (nbtItem.getString("Phone").equalsIgnoreCase("iPadPro")) {
/*  68 */       maxMoney = 2500.0D;
/*     */     }
/*  70 */     if (nbtItem.getString("Phone").equalsIgnoreCase("iPhone14")) {
/*  71 */       maxMoney = 5000.0D;
/*     */     }
/*     */     
/*  74 */     if (money > maxMoney) {
/*  75 */       MainAppGUI.activePaypal.remove(player.getUniqueId());
/*  76 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Puoi mandare un massimo di " + maxMoney));
/*     */       
/*     */       return;
/*     */     } 
/*  80 */     if (!PayPalDatabase.checkPayPal((Player)offlinePlayer)) {
/*  81 */       MainAppGUI.activePaypal.remove(player.getUniqueId());
/*  82 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Questo cittadino non ha un account PayPal!"));
/*     */       
/*     */       return;
/*     */     } 
/*  86 */     PluginCustomLoader.getInstance(); EconomyResponse r = PluginCustomLoader.getEcon().withdrawPlayer((OfflinePlayer)player, money);
/*     */     
/*  88 */     if (r.transactionSuccess()) {
/*     */       
/*  90 */       MainAppGUI.activePaypal.remove(player.getUniqueId());
/*     */       
/*  92 */       player.sendMessage(BasicsFunction.hex("&f &#84CC17Hai inviato " + money + " a " + offlinePlayer.getName()));
/*  93 */       if (offlinePlayer != null)
/*     */       {
/*  95 */         offlinePlayer.getPlayer().sendMessage(BasicsFunction.hex("&b&lPAYPAL &8→ &7Hai ricevuto &b" + money + "€ &7da &b" + player.getName() + " &7con la motivazione &b" + mm));
/*     */       }
/*     */ 
/*     */       
/*  99 */       PluginCustomLoader.getEcon().depositPlayer(offlinePlayer, money);
/*     */     }
/* 101 */     else if (r.errorMessage == "Loan was not permitted!") {
/* 102 */       player.sendMessage(BasicsFunction.hex("&f &#F59F0BNon hai abbastanza soldi per questa transizione!"));
/*     */     } else {
/* 104 */       player.sendMessage(String.format("§cSi è verificato un' errore:", new Object[] { r.errorMessage }));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\listener\\utilities\PayPalListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */