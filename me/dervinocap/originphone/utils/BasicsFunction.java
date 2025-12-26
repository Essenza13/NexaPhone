/*     */ package me.dervinocap.originphone.utils;
/*     */ 
/*     */ import de.tr7zw.nbtapi.NBTItem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import me.dervinocap.originphone.database.ChatDatabase;
/*     */ import me.dervinocap.originphone.database.ChatNamesDatabase;
/*     */ import me.dervinocap.originphone.database.TwitterDatabase;
/*     */ import net.md_5.bungee.api.ChatMessageType;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.md_5.bungee.api.chat.ClickEvent;
/*     */ import net.md_5.bungee.api.chat.ComponentBuilder;
/*     */ import net.md_5.bungee.api.chat.HoverEvent;
/*     */ import net.md_5.bungee.api.chat.TextComponent;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ public class BasicsFunction
/*     */ {
/*     */   public static String generate3() {
/*  27 */     int min = 100;
/*  28 */     int max = 999;
/*  29 */     int num = (int)Math.floor(Math.random() * (max - min + 1) + min);
/*  30 */     return String.valueOf(num);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendActionBar(Player player, String message) {
/*  35 */     player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String generate4() {
/*  40 */     int min = 1000;
/*  41 */     int max = 9999;
/*  42 */     int num = (int)Math.floor(Math.random() * (max - min + 1) + min);
/*  43 */     return String.valueOf(num);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String generatePhoneNumber() {
/*  48 */     String num1 = "351";
/*  49 */     String num2 = generate3();
/*  50 */     String num3 = generate4();
/*     */     
/*  52 */     String number = num1 + num2 + num3;
/*     */     
/*  54 */     return number;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String generatePhoneNumberFormatted() {
/*  60 */     String num1 = "351";
/*  61 */     String num2 = generate3();
/*  62 */     String num3 = generate4();
/*     */     
/*  64 */     String number = num1 + " " + num2 + " " + num3;
/*     */     
/*  66 */     return number;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendChatMessage(Player player, String chatName, String message) {
/*     */     String nome;
/*  74 */     if (ChatNamesDatabase.getName(player, chatName) == null) {
/*  75 */       nome = player.getName();
/*     */     } else {
/*  77 */       nome = ChatNamesDatabase.getName(player, chatName);
/*     */     } 
/*     */     
/*  80 */     for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
/*     */       
/*  82 */       if (ChatDatabase.checkChatOwner(chatName).equalsIgnoreCase(onlinePlayer.getUniqueId().toString()) || ChatDatabase.getChatMembers(chatName).contains(onlinePlayer.getUniqueId()) || onlinePlayer.hasPermission("realityphone.chat." + chatName) || onlinePlayer.hasPermission("realityphone.chatadmin")) {
/*     */         
/*  84 */         if (hasPhone(onlinePlayer)) {
/*  85 */           onlinePlayer.sendMessage(hex(ChatDatabase.getChatChatFormat(chatName).replace("%player%", nome).replace("%message%", message))); continue;
/*  86 */         }  if (onlinePlayer.hasPermission("realityphone.chatadmin")) {
/*  87 */           onlinePlayer.sendMessage(hex(ChatDatabase.getChatChatFormat(chatName).replace("%player%", nome).replace("%message%", message)));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPhone(ItemStack itemStack) {
/*  97 */     NBTItem nbtItem = new NBTItem(itemStack);
/*     */     
/*  99 */     if (nbtItem.getKeys().contains("Phone"))
/* 100 */       return true; 
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isYourPhone(ItemStack itemStack, Player player) {
/* 106 */     NBTItem nbtItem = new NBTItem(itemStack);
/*     */     
/* 108 */     if (nbtItem.getKeys().contains("Phone") && nbtItem.getString("Player").equalsIgnoreCase(player.getName()))
/* 109 */       return true; 
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isPowerBank(ItemStack itemStack) {
/* 115 */     NBTItem nbtItem = new NBTItem(itemStack);
/*     */     
/* 117 */     if (nbtItem.getKeys().contains("PowerBank"))
/* 118 */       return true; 
/* 119 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendEmergency(Player player, String permission, String emergency, String motivo) {
/* 124 */     sendActionBar(player, "§eHai chiamato " + emergency);
/*     */     
/* 126 */     TextComponent textPolice = new TextComponent("§3[Clicca per avviare il GPS]");
/* 127 */     textPolice.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§3[Clicca per avviare il GPS]")).create()));
/* 128 */     textPolice.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/compass start " + player.getLocation().getBlockX() + " " + player.getLocation().getBlockY() + " " + player.getLocation().getBlockZ()));
/*     */     
/* 130 */     TextComponent textPolice2 = new TextComponent(" §a[Rispondi]");
/* 131 */     textPolice2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§a[Rispondi]")).create()));
/* 132 */     textPolice2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/emergency-respond Polizia " + player.getName()));
/*     */     
/* 134 */     TextComponent textAmbulance = new TextComponent("§c[Clicca per avviare il GPS]");
/* 135 */     textAmbulance.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§c[Clicca per avviare il GPS]")).create()));
/* 136 */     textAmbulance.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/compass start " + player.getLocation().getBlockX() + " " + player.getLocation().getBlockY() + " " + player.getLocation().getBlockZ()));
/*     */     
/* 138 */     TextComponent textAmbulance2 = new TextComponent(" §a[Rispondi]");
/* 139 */     textAmbulance2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§a[Rispondi]")).create()));
/* 140 */     textAmbulance2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/emergency-respond Ambulanza " + player.getName()));
/*     */     
/* 142 */     TextComponent textTaxi = new TextComponent("§e[Clicca per avviare il GPS]");
/* 143 */     textTaxi.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§e[Clicca per avviare il GPS]")).create()));
/* 144 */     textTaxi.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/compass start " + player.getLocation().getBlockX() + " " + player.getLocation().getBlockY() + " " + player.getLocation().getBlockZ()));
/*     */     
/* 146 */     TextComponent textVDF = new TextComponent("§4[Clicca per avviare il GPS]");
/* 147 */     textVDF.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§4[Clicca per avviare il GPS]")).create()));
/* 148 */     textVDF.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/compass start " + player.getLocation().getBlockX() + " " + player.getLocation().getBlockY() + " " + player.getLocation().getBlockZ()));
/*     */     
/* 150 */     TextComponent textVDF2 = new TextComponent(" §a[Rispondi]");
/* 151 */     textVDF2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§a[Rispondi]")).create()));
/* 152 */     textVDF2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/emergency-respond VDF " + player.getName()));
/*     */     
/* 154 */     textPolice.addExtra((BaseComponent)textPolice2);
/* 155 */     textAmbulance.addExtra((BaseComponent)textAmbulance2);
/* 156 */     textVDF.addExtra((BaseComponent)textVDF2);
/*     */     
/* 158 */     for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
/*     */       
/* 160 */       if (onlinePlayer.hasPermission(permission)) {
/*     */         
/* 162 */         if (emergency.equalsIgnoreCase("Polizia")) {
/* 163 */           onlinePlayer.sendMessage("");
/* 164 */           onlinePlayer.sendMessage(" §3§lALLERTA CENTRALE");
/* 165 */           onlinePlayer.sendMessage(" §b" + player.getName() + " §7ha inviato un allerta!");
/* 166 */           onlinePlayer.sendMessage(" §7Coordinate: §bx " + player.getLocation().getBlockX() + " y " + player.getLocation().getBlockY() + " z " + player.getLocation().getBlockZ());
/* 167 */           onlinePlayer.sendMessage(" §7Motivo: §b" + motivo);
/* 168 */           onlinePlayer.sendMessage("");
/* 169 */           onlinePlayer.spigot().sendMessage((BaseComponent)textPolice);
/*     */         } 
/*     */         
/* 172 */         if (emergency.equalsIgnoreCase("Taxi")) {
/* 173 */           onlinePlayer.sendMessage("");
/* 174 */           onlinePlayer.sendMessage(" §6§lRICHIESTA TAXI");
/* 175 */           onlinePlayer.sendMessage(" §e" + player.getName() + " §7ha richiesto un taxi!");
/* 176 */           onlinePlayer.sendMessage(" §7Coordinate: §bx " + player.getLocation().getBlockX() + " y " + player.getLocation().getBlockY() + " z " + player.getLocation().getBlockZ());
/* 177 */           onlinePlayer.sendMessage("");
/* 178 */           onlinePlayer.spigot().sendMessage((BaseComponent)textTaxi);
/*     */         } 
/*     */         
/* 181 */         if (emergency.equalsIgnoreCase("Ospedale")) {
/* 182 */           onlinePlayer.sendMessage("");
/* 183 */           onlinePlayer.sendMessage(" §c§lALLERTA OSPEDALE");
/* 184 */           onlinePlayer.sendMessage(" §c" + player.getName() + " §7ha richiesto un ambulanza!");
/* 185 */           onlinePlayer.sendMessage(" §7Coordinate: §cx " + player.getLocation().getBlockX() + " y " + player.getLocation().getBlockY() + " z " + player.getLocation().getBlockZ());
/* 186 */           onlinePlayer.sendMessage(" §7Motivo: §c" + motivo);
/* 187 */           onlinePlayer.sendMessage("");
/*     */           
/* 189 */           onlinePlayer.spigot().sendMessage((BaseComponent)textAmbulance);
/*     */         } 
/* 191 */         if (emergency.equalsIgnoreCase("Vigili del Fuoco")) {
/* 192 */           onlinePlayer.sendMessage("");
/* 193 */           onlinePlayer.sendMessage(" §4§lALLERTA VIGILI DEL FUOCO");
/* 194 */           onlinePlayer.sendMessage(" §c" + player.getName() + " §7ha richiesto una volante dei vdf!");
/* 195 */           onlinePlayer.sendMessage(" §7Coordinate: §cx " + player.getLocation().getBlockX() + " y " + player.getLocation().getBlockY() + " z " + player.getLocation().getBlockZ());
/* 196 */           onlinePlayer.sendMessage(" §7Motivo: §c" + motivo);
/* 197 */           onlinePlayer.sendMessage("");
/*     */           
/* 199 */           onlinePlayer.spigot().sendMessage((BaseComponent)textVDF);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getFormattedString(String str) {
/* 209 */     return ChatColor.translateAlternateColorCodes('&', str);
/*     */   }
/*     */   
/*     */   public static List<String> getFormattedStringList(List<String> stringList) {
/* 213 */     List<String> formattedLore = new ArrayList<>();
/* 214 */     stringList.forEach(s -> formattedLore.add(getFormattedString(s)));
/* 215 */     return formattedLore;
/*     */   }
/*     */   
/*     */   public static String hex(String message) {
/* 219 */     Pattern pattern = Pattern.compile("(#[a-fA-F0-9]{6})");
/* 220 */     Matcher matcher = pattern.matcher(message);
/* 221 */     while (matcher.find()) {
/* 222 */       String hexCode = message.substring(matcher.start(), matcher.end());
/* 223 */       String replaceSharp = hexCode.replace('#', 'x');
/*     */       
/* 225 */       char[] ch = replaceSharp.toCharArray();
/* 226 */       StringBuilder builder = new StringBuilder("");
/* 227 */       for (char c : ch) {
/* 228 */         builder.append("&" + c);
/*     */       }
/*     */       
/* 231 */       message = message.replace(hexCode, builder.toString());
/* 232 */       matcher = pattern.matcher(message);
/*     */     } 
/* 234 */     return ChatColor.translateAlternateColorCodes('&', message).replace('&', '§');
/*     */   }
/*     */   
/*     */   public static boolean hasPhone(Player p) {
/* 238 */     for (ItemStack item : p.getInventory().getContents()) {
/* 239 */       if (item != null && 
/* 240 */         isYourPhone(item, p)) return true; 
/*     */     } 
/* 242 */     return false;
/*     */   }
/*     */   
/*     */   public static int getNextId() {
/* 246 */     int nextNumber = 1;
/* 247 */     if (TwitterDatabase.getChatsData().isSet(".tweets.")) {
/* 248 */       Set<String> receipt = TwitterDatabase.getChatsData().getConfigurationSection(".tweets.").getKeys(false);
/* 249 */       nextNumber = receipt.size() + 1;
/*     */     } 
/* 251 */     return nextNumber;
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphon\\utils\BasicsFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */