/*     */ package me.dervinocap.originphone.database;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.dervinocap.originphone.RealityPhone;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TwitterDatabase
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
/*  39 */     dataFile = new File(plugin.getDataFolder(), "tweets.yml");
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
/*     */   public static void createTweet(String titolo, String contenuto, int id, Player player) {
/*  52 */     getChatsData().set(".tweets." + id + "..scrittore", player.getName());
/*  53 */     getChatsData().set(".tweets." + id + "..titolo", titolo);
/*  54 */     getChatsData().set(".tweets." + id + "..contenuto", contenuto);
/*  55 */     getChatsData().set(".tweets." + id + "..like", Integer.valueOf(0));
/*  56 */     getChatsData().set(".tweets." + id + "..dislike", Integer.valueOf(0));
/*  57 */     saveChatsData();
/*     */   }
/*     */   
/*     */   public static void addLike(int id, List<String> players) {
/*  61 */     getChatsData().set(".tweets." + id + "..like", Integer.valueOf(getLikes(id) + 1));
/*  62 */     getChatsData().set(".tweets." + id + "..like-player", players);
/*  63 */     saveChatsData();
/*     */   }
/*     */   
/*     */   public static void addDislike(int id, List<String> strings) {
/*  67 */     getChatsData().set(".tweets." + id + "..dislike", Integer.valueOf(getDislikes(id) + 1));
/*  68 */     getChatsData().set(".tweets." + id + "..dislike-player", strings);
/*  69 */     saveChatsData();
/*     */   }
/*     */   
/*     */   public static List<String> getLikesPlayer(int id) {
/*  73 */     return getChatsData().getStringList(".tweets." + id + "..like-player");
/*     */   }
/*     */   
/*     */   public static List<String> getDislikesPlayer(int id) {
/*  77 */     return getChatsData().getStringList(".tweets." + id + "..dislike-player");
/*     */   }
/*     */   
/*     */   public static String getScrittore(int id) {
/*  81 */     return getChatsData().getString(".tweets." + id + "..scrittore");
/*     */   }
/*     */   
/*     */   public static int getLikes(int id) {
/*  85 */     return getChatsData().getInt(".tweets." + id + "..like");
/*     */   }
/*     */   public static int getDislikes(int id) {
/*  88 */     return getChatsData().getInt(".tweets." + id + "..dislike");
/*     */   }
/*     */   
/*     */   public static String getTitolo(int id) {
/*  92 */     return getChatsData().getString(".tweets." + id + "..titolo");
/*     */   }
/*     */   
/*     */   public static String getContenuto(int id) {
/*  96 */     return getChatsData().getString(".tweets." + id + "..contenuto");
/*     */   }
/*     */   
/*     */   public static void removeTweet(int id) {
/* 100 */     getChatsData().set(".tweets." + id, null);
/* 101 */     saveChatsData();
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<Integer> getTweet() {
/* 106 */     List<Integer> receipt = new ArrayList<>();
/* 107 */     if (getChatsData().isSet(".tweets."))
/* 108 */       for (String s : getChatsData().getConfigurationSection(".tweets.").getKeys(false))
/* 109 */         receipt.add(Integer.valueOf(s));  
/* 110 */     return receipt;
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\database\TwitterDatabase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */