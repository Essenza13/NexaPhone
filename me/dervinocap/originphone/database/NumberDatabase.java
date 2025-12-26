/*     */ package me.dervinocap.originphone.database;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.UUID;
/*     */ import me.dervinocap.originphone.RealityPhone;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class NumberDatabase {
/*     */   public static void playerFolder() {
/*  19 */     if (!RealityPhone.getInstance().getDataFolder().exists()) {
/*  20 */       RealityPhone.getInstance().getDataFolder().mkdir();
/*     */     }
/*  22 */     File playerdataFolder = new File(RealityPhone.getInstance().getDataFolder(), "numbers");
/*  23 */     if (!playerdataFolder.exists())
/*  24 */       playerdataFolder.mkdirs(); 
/*     */   }
/*     */   
/*     */   public static void createPlayerData(Player player) {
/*  28 */     UUID playerName = player.getUniqueId();
/*  29 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers");
/*  30 */     File f = new File(userdata, File.separator + playerName + ".yml");
/*  31 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(f);
/*  32 */     if (!f.exists())
/*     */       try {
/*  34 */         yamlConfiguration.createSection(player.getName());
/*  35 */         yamlConfiguration.set("player." + player.getUniqueId() + ".number", null);
/*  36 */         yamlConfiguration.set("player." + player.getUniqueId() + ".number-formatted", "Nessuno");
/*  37 */         yamlConfiguration.set("player." + player.getUniqueId() + ".abbonamento", "Nessuno");
/*  38 */         yamlConfiguration.set("player." + player.getUniqueId() + ".abbonamento-expire", "---");
/*  39 */         yamlConfiguration.set("player." + player.getUniqueId() + ".abbonamento-expire-formatted", "---");
/*  40 */         yamlConfiguration.set("player." + player.getUniqueId() + ".minutes", Integer.valueOf(0));
/*  41 */         yamlConfiguration.set("player." + player.getUniqueId() + ".sms", Integer.valueOf(0));
/*  42 */         yamlConfiguration.save(f);
/*  43 */       } catch (IOException exception) {
/*  44 */         exception.printStackTrace();
/*     */       }  
/*     */   }
/*     */   
/*     */   public static String getValue(Player getPlayer, String section) {
/*  49 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + getPlayer.getUniqueId() + ".yml");
/*  50 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/*  51 */     return yamlConfiguration.getString("player." + getPlayer.getUniqueId() + "." + section);
/*     */   }
/*     */   
/*     */   public static void setValue(Player getPlayer, String section, String value) {
/*  55 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + getPlayer.getUniqueId() + ".yml");
/*  56 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/*  57 */     yamlConfiguration.set("player." + getPlayer.getUniqueId() + "." + section, value);
/*     */     try {
/*  59 */       yamlConfiguration.save(userdata);
/*  60 */     } catch (IOException e) {
/*  61 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String getAbbonamento(Player getPlayer) {
/*  66 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + getPlayer.getUniqueId() + ".yml");
/*  67 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/*  68 */     return yamlConfiguration.getString("player." + getPlayer.getUniqueId() + ".abbonamento");
/*     */   }
/*     */   
/*     */   public static String getAbbonamentoExpire(Player getPlayer) {
/*  72 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + getPlayer.getUniqueId() + ".yml");
/*  73 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/*  74 */     return yamlConfiguration.getString("player." + getPlayer.getUniqueId() + ".abbonamento-expire");
/*     */   }
/*     */   
/*     */   public static String getAbbonamentoExpireFormatted(Player getPlayer) {
/*  78 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + getPlayer.getUniqueId() + ".yml");
/*  79 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/*  80 */     return yamlConfiguration.getString("player." + getPlayer.getUniqueId() + ".abbonamento-expire-formatted");
/*     */   }
/*     */   
/*     */   public static Integer getSms(Player getPlayer) {
/*  84 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + getPlayer.getUniqueId() + ".yml");
/*  85 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/*  86 */     return Integer.valueOf(yamlConfiguration.getInt("player." + getPlayer.getUniqueId() + ".sms"));
/*     */   }
/*     */   
/*     */   public static Integer getMinutes(Player getPlayer) {
/*  90 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + getPlayer.getUniqueId() + ".yml");
/*  91 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/*  92 */     return Integer.valueOf(yamlConfiguration.getInt("player." + getPlayer.getUniqueId() + ".minutes"));
/*     */   }
/*     */   
/*     */   public static String getPhoneNumber(Player getPlayer) {
/*  96 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + getPlayer.getUniqueId() + ".yml");
/*  97 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/*  98 */     return yamlConfiguration.getString("player." + getPlayer.getUniqueId() + ".number");
/*     */   }
/*     */   
/*     */   public static String getPhoneNumberFormatted(Player getPlayer) {
/* 102 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + getPlayer.getUniqueId() + ".yml");
/* 103 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/* 104 */     return yamlConfiguration.getString("player." + getPlayer.getUniqueId() + ".number-formatted");
/*     */   }
/*     */   
/*     */   public static String getPhoneNumberOfflineFormatted(OfflinePlayer getPlayer) {
/* 108 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + getPlayer.getUniqueId() + ".yml");
/*     */     
/* 110 */     File userdata2 = new File(RealityPhone.getInstance().getDataFolder(), "numbers");
/* 111 */     File f = new File(userdata2, File.separator + getPlayer.getUniqueId() + ".yml");
/*     */     
/* 113 */     if (f.exists()) {
/* 114 */       YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/* 115 */       return yamlConfiguration.getString("player." + getPlayer.getUniqueId() + ".number-formatted");
/*     */     } 
/* 117 */     return null;
/*     */   }
/*     */   
/*     */   public static String getPhoneNumberOffline(OfflinePlayer getPlayer) {
/* 121 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + getPlayer.getUniqueId() + ".yml");
/*     */     
/* 123 */     File userdata2 = new File(RealityPhone.getInstance().getDataFolder(), "numbers");
/* 124 */     File f = new File(userdata2, File.separator + getPlayer.getUniqueId() + ".yml");
/*     */     
/* 126 */     if (f.exists()) {
/* 127 */       YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/* 128 */       return yamlConfiguration.getString("player." + getPlayer.getUniqueId() + ".number");
/*     */     } 
/* 130 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Player getPhoneNumberOwner(String phoneNumber) {
/* 135 */     for (Iterator<Player> iterator = Bukkit.getOnlinePlayers().iterator(); iterator.hasNext(); ) { Player onlinePlayer = iterator.next();
/*     */       
/* 137 */       File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + onlinePlayer.getUniqueId() + ".yml");
/* 138 */       YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/*     */       
/* 140 */       if (yamlConfiguration.getConfigurationSection("player.").getKeys(false).stream().filter(string -> Objects.equals(yamlConfiguration.getString("player." + string + ".number"), phoneNumber)).findFirst().orElse(null) != null) {
/* 141 */         Player player = Bukkit.getPlayer(UUID.fromString(Objects.<String>requireNonNull(((ConfigurationSection)Objects.<ConfigurationSection>requireNonNull(yamlConfiguration.getConfigurationSection("player."))).getKeys(false).stream().filter(string -> Objects.equals(yamlConfiguration.getString("player." + string + ".number"), phoneNumber)).findFirst().orElse(null))));
/*     */         
/* 143 */         if (player != null) {
/* 144 */           return player;
/*     */         }
/*     */       }  }
/*     */ 
/*     */ 
/*     */     
/* 150 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static OfflinePlayer getPhoneNumberOwnerOffline(String phoneNumber) {
/* 155 */     for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
/*     */       
/* 157 */       File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + offlinePlayer.getUniqueId() + ".yml");
/* 158 */       YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/* 159 */       File userdata2 = new File(RealityPhone.getInstance().getDataFolder(), "numbers");
/* 160 */       File f = new File(userdata2, File.separator + offlinePlayer.getUniqueId() + ".yml");
/*     */       
/* 162 */       if (f.exists())
/*     */       {
/* 164 */         if (yamlConfiguration.getConfigurationSection("player.").getKeys(false).stream().filter(string -> yamlConfiguration.getString("player." + string + ".number").equals(phoneNumber)).findFirst().map(string -> string).orElse(null) != null)
/*     */         {
/* 166 */           return RealityPhone.getInstance().getServer().getOfflinePlayer(UUID.fromString(Objects.<String>requireNonNull(yamlConfiguration.getConfigurationSection("player.").getKeys(false).stream().filter(string -> yamlConfiguration.getString("player." + string + ".number").equals(phoneNumber)).findFirst().orElse(null))));
/*     */         }
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 173 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void createPhoneNumber(Player player) {
/* 179 */     String num1 = "351";
/* 180 */     String num2 = BasicsFunction.generate3();
/* 181 */     String num3 = BasicsFunction.generate4();
/*     */     
/* 183 */     String number = num1 + num2 + num3;
/* 184 */     String numberFormatted = num1 + " " + num2 + " " + num3;
/*     */     
/* 186 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + player.getUniqueId() + ".yml");
/* 187 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/* 188 */     yamlConfiguration.set("player." + player.getUniqueId() + ".number", number);
/* 189 */     yamlConfiguration.set("player." + player.getUniqueId() + ".number-formatted", numberFormatted);
/* 190 */     yamlConfiguration.set("player." + player.getUniqueId() + ".minutes", Integer.valueOf(0));
/* 191 */     yamlConfiguration.set("player." + player.getUniqueId() + ".sms", Integer.valueOf(0));
/* 192 */     yamlConfiguration.set("player." + player.getUniqueId() + ".abbonamento", "Nessuno");
/*     */     try {
/* 194 */       yamlConfiguration.save(userdata);
/* 195 */     } catch (IOException e) {
/* 196 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void deletePhoneNumber(Player player) {
/* 203 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + player.getUniqueId() + ".yml");
/* 204 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/* 205 */     yamlConfiguration.set("player." + player.getUniqueId() + ".number", null);
/* 206 */     yamlConfiguration.set("player." + player.getUniqueId() + ".number-formatted", "Nessuno");
/* 207 */     yamlConfiguration.set("player." + player.getUniqueId() + ".abbonamento", "Nessuno");
/* 208 */     yamlConfiguration.set("player." + player.getUniqueId() + ".abbonamento-expire", "---");
/* 209 */     yamlConfiguration.set("player." + player.getUniqueId() + ".abbonamento-expire-formatted", "---");
/* 210 */     yamlConfiguration.set("player." + player.getUniqueId() + ".minutes", Integer.valueOf(0));
/* 211 */     yamlConfiguration.set("player." + player.getUniqueId() + ".sms", Integer.valueOf(0));
/*     */     try {
/* 213 */       yamlConfiguration.save(userdata);
/* 214 */     } catch (IOException e) {
/* 215 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setAbbonamento(Player player, String abbonamento) {
/* 222 */     int noOfDays = 14;
/* 223 */     Calendar cal = Calendar.getInstance();
/* 224 */     Date cdate = cal.getTime();
/* 225 */     cal.add(6, noOfDays);
/* 226 */     Date date = cal.getTime();
/*     */     
/* 228 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
/*     */     
/* 230 */     simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
/*     */     
/* 232 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + player.getUniqueId() + ".yml");
/* 233 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/* 234 */     yamlConfiguration.set("player." + player.getUniqueId() + ".abbonamento", abbonamento);
/* 235 */     yamlConfiguration.set("player." + player.getUniqueId() + ".abbonamento-expire", date.toString());
/* 236 */     yamlConfiguration.set("player." + player.getUniqueId() + ".abbonamento-expire-formatted", simpleDateFormat.format(date));
/*     */     try {
/* 238 */       yamlConfiguration.save(userdata);
/* 239 */     } catch (IOException e) {
/* 240 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void resetAbbonamentoExpire(Player player) {
/* 248 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + player.getUniqueId() + ".yml");
/* 249 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/* 250 */     yamlConfiguration.set("player." + player.getUniqueId() + ".abbonamento-expire", "---");
/* 251 */     yamlConfiguration.set("player." + player.getUniqueId() + ".abbonamento-expire-formatted", "---");
/*     */     try {
/* 253 */       yamlConfiguration.save(userdata);
/* 254 */     } catch (IOException e) {
/* 255 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setSms(Player player, int sms) {
/* 262 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + player.getUniqueId() + ".yml");
/* 263 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/* 264 */     yamlConfiguration.set("player." + player.getUniqueId() + ".sms", Integer.valueOf(sms));
/*     */     try {
/* 266 */       yamlConfiguration.save(userdata);
/* 267 */     } catch (IOException e) {
/* 268 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setMinutes(Player player, int minutes) {
/* 275 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + player.getUniqueId() + ".yml");
/* 276 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/* 277 */     yamlConfiguration.set("player." + player.getUniqueId() + ".minutes", Integer.valueOf(minutes));
/*     */     try {
/* 279 */       yamlConfiguration.save(userdata);
/* 280 */     } catch (IOException e) {
/* 281 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkIfPlayerHasNumber(Player player) {
/* 288 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + player.getUniqueId() + ".yml");
/* 289 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/*     */     
/* 291 */     if (yamlConfiguration.getString("player." + player.getUniqueId() + ".number") != null)
/* 292 */       return true; 
/* 293 */     return false;
/*     */   }
/*     */   
/*     */   public static List<String> getAllPhoneNumber() {
/* 297 */     List<String> phoneNumbers = new ArrayList<>();
/*     */     
/* 299 */     for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
/* 300 */       File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + onlinePlayer.getUniqueId() + ".yml");
/* 301 */       YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/*     */       
/* 303 */       phoneNumbers.add(yamlConfiguration.getString("player." + onlinePlayer.getUniqueId() + ".number"));
/*     */     } 
/*     */     
/* 306 */     for (OfflinePlayer offlinePlayer : RealityPhone.getInstance().getServer().getOfflinePlayers()) {
/*     */       
/* 308 */       File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers" + File.separator + offlinePlayer.getUniqueId() + ".yml");
/* 309 */       YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userdata);
/*     */       
/* 311 */       phoneNumbers.add(yamlConfiguration.getString("player." + offlinePlayer.getUniqueId() + ".number"));
/*     */     } 
/*     */ 
/*     */     
/* 315 */     return phoneNumbers;
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\database\NumberDatabase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */