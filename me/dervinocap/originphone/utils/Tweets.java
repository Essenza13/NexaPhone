/*    */ package me.dervinocap.originphone.utils;
/*    */ 
/*    */ import com.google.common.collect.HashBiMap;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ 
/*    */ 
/*    */ public class Tweets
/*    */ {
/* 11 */   public static HashBiMap<UUID, String> titolo = HashBiMap.create();
/* 12 */   public static HashBiMap<UUID, String> contenuto = HashBiMap.create();
/*    */   
/* 14 */   public static List<UUID> sendingTitolo = new ArrayList<>();
/* 15 */   public static List<UUID> sendingContenuto = new ArrayList<>();
/*    */   
/* 17 */   public static List<UUID> cooldown = new ArrayList<>();
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphon\\utils\Tweets.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */