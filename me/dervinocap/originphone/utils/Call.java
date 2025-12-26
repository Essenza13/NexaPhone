/*    */ package me.dervinocap.originphone.utils;
/*    */ 
/*    */ import com.google.common.collect.HashBiMap;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class Call
/*    */ {
/* 10 */   public static HashBiMap<UUID, UUID> calling = HashBiMap.create();
/* 11 */   public static HashBiMap<UUID, UUID> inCall = HashBiMap.create();
/* 12 */   public static List<UUID> inCallArray = new ArrayList<>();
/*    */   
/* 14 */   public static List<UUID> callList = new ArrayList<>();
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphon\\utils\Call.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */