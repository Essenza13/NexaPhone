/*     */ package dev.triumphteam.gui.components.util;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.Base64;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SkullUtil
/*     */ {
/*  40 */   private static final Material SKULL = getSkullMaterial();
/*  41 */   private static final Gson GSON = new Gson();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Material getSkullMaterial() {
/*  49 */     if (VersionHelper.IS_ITEM_LEGACY) {
/*  50 */       return Material.valueOf("SKULL_ITEM");
/*     */     }
/*     */     
/*  53 */     return Material.PLAYER_HEAD;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemStack skull() {
/*  63 */     return VersionHelper.IS_ITEM_LEGACY ? new ItemStack(SKULL, 1, (short)3) : new ItemStack(SKULL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPlayerSkull(@NotNull ItemStack item) {
/*  74 */     if (VersionHelper.IS_ITEM_LEGACY) {
/*  75 */       return (item.getType() == SKULL && item.getDurability() == 3);
/*     */     }
/*     */     
/*  78 */     return (item.getType() == SKULL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getSkinUrl(String base64Texture) {
/*  93 */     String decoded = new String(Base64.getDecoder().decode(base64Texture));
/*  94 */     JsonObject object = (JsonObject)GSON.fromJson(decoded, JsonObject.class);
/*     */     
/*  96 */     JsonElement textures = object.get("textures");
/*     */     
/*  98 */     if (textures == null) {
/*  99 */       return null;
/*     */     }
/*     */     
/* 102 */     JsonElement skin = textures.getAsJsonObject().get("SKIN");
/*     */     
/* 104 */     if (skin == null) {
/* 105 */       return null;
/*     */     }
/*     */     
/* 108 */     JsonElement url = skin.getAsJsonObject().get("url");
/* 109 */     return (url == null) ? null : url.getAsString();
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\dev\triumphteam\gui\component\\util\SkullUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */