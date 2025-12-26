/*     */ package me.dervinocap.originphone.database;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import me.dervinocap.originphone.RealityPhone;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ public class ChatDatabase
/*     */ {
/*  16 */   public static final RealityPhone plugin = RealityPhone.getInstance();
/*     */   
/*     */   public static File dataFile;
/*     */   
/*     */   public static YamlConfiguration chatsData;
/*     */   
/*     */   public File getDataFile() {
/*  23 */     this; return dataFile;
/*     */   }
/*     */   
/*     */   public static YamlConfiguration getChatsData() {
/*  27 */     return chatsData;
/*     */   }
/*     */   
/*     */   public static void saveChatsData() {
/*     */     try {
/*  32 */       chatsData.save(dataFile);
/*  33 */     } catch (IOException e) {
/*  34 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setupChatsData() {
/*  39 */     dataFile = new File(plugin.getDataFolder(), "chats.yml");
/*  40 */     if (!plugin.getDataFolder().exists())
/*  41 */       plugin.getDataFolder().mkdir(); 
/*  42 */     if (!dataFile.exists())
/*     */       try {
/*  44 */         dataFile.createNewFile();
/*  45 */       } catch (IOException e) {
/*  46 */         throw new RuntimeException(e);
/*     */       }  
/*  48 */     chatsData = YamlConfiguration.loadConfiguration(dataFile);
/*     */   }
/*     */   
/*     */   public static void createChat(String chatName, UUID chatOwner, String chatFormat) {
/*  52 */     getChatsData().set(".chats." + chatName + "..owner", chatOwner.toString());
/*  53 */     getChatsData().set(".chats." + chatName + "..chat-format", chatFormat);
/*  54 */     saveChatsData();
/*     */   }
/*     */   
/*     */   public static void setOwner(String chatName, UUID chatOwner) {
/*  58 */     getChatsData().set(".chats." + chatName + "..owner", chatOwner.toString());
/*  59 */     saveChatsData();
/*     */   }
/*     */   
/*     */   public static void removeChat(String chatName) {
/*  63 */     getChatsData().set(".chats." + chatName, null);
/*  64 */     saveChatsData();
/*     */   }
/*     */   
/*     */   public static String getChatChatFormat(String chatName) {
/*  68 */     return getChatsData().getString(".chats.." + chatName + "..chat-format");
/*     */   }
/*     */   
/*     */   public static String getChatChats() {
/*  72 */     return getChatsData().getString(".chats");
/*     */   }
/*     */   
/*     */   public static String checkChatOwner(String chatName) {
/*  76 */     return getChatsData().getString(".chats.." + chatName + "..owner");
/*     */   }
/*     */   
/*     */   public static String checkChatMember(String chatName) {
/*  80 */     return getChatsData().getString(".chats." + chatName + "..members");
/*     */   }
/*     */   
/*     */   public static List<UUID> getChatMembers(String chatName) {
/*  84 */     List<UUID> chatMembers = new ArrayList<>();
/*     */     
/*  86 */     for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
/*  87 */       if (onlinePlayer.hasPermission("realityphone.chat." + chatName))
/*  88 */         chatMembers.add(onlinePlayer.getUniqueId()); 
/*     */     } 
/*  90 */     for (String memberUUID : getChatsData().getStringList(".chats." + chatName + "..members")) {
/*  91 */       UUID member = UUID.fromString(memberUUID);
/*  92 */       chatMembers.add(member);
/*     */     } 
/*  94 */     return chatMembers;
/*     */   }
/*     */   
/*     */   public static boolean checkIfChatsExist(String chatName) {
/*  98 */     if (getChatsData().isSet(".chats." + chatName) || getChatsData().isSet(".chats." + chatName.toLowerCase()))
/*  99 */       return true; 
/* 100 */     return false;
/*     */   }
/*     */   
/*     */   public static List<String> getPlayerChats(UUID playerUUID) {
/* 104 */     List<String> playerChats = new ArrayList<>();
/*     */     
/* 106 */     Player player = Bukkit.getPlayer(playerUUID);
/*     */     
/* 108 */     for (String chat : getChatsData().getConfigurationSection(".chats").getKeys(false)) {
/* 109 */       if (getChatMembers(chat).contains(playerUUID) || checkChatOwner(chat).equals(playerUUID.toString()) || player.hasPermission("realityphone.chat." + chat))
/* 110 */         playerChats.add(chat); 
/*     */     } 
/* 112 */     return playerChats;
/*     */   }
/*     */   
/*     */   public static void addChatMember(String chatName, UUID playerUUID) {
/* 116 */     List<String> list = getChatsData().getStringList(".chats." + chatName + "..members");
/* 117 */     list.add(playerUUID.toString());
/* 118 */     getChatsData().set(".chats." + chatName + "..members", list);
/* 119 */     saveChatsData();
/*     */   }
/*     */   
/*     */   public static void removeChatMember(String chatName, UUID playerUUID) {
/* 123 */     List<String> list = getChatsData().getStringList(".chats." + chatName + "..members");
/* 124 */     list.remove(playerUUID.toString());
/* 125 */     getChatsData().set(".chats." + chatName + "..members", list);
/* 126 */     saveChatsData();
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\database\ChatDatabase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */