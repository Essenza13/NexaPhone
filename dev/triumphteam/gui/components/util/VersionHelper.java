/*     */ package dev.triumphteam.gui.components.util;
/*     */ 
/*     */ import com.google.common.primitives.Ints;
/*     */ import dev.triumphteam.gui.components.exception.GuiException;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.bukkit.Bukkit;
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
/*     */ 
/*     */ public final class VersionHelper
/*     */ {
/*  40 */   private static final String NMS_VERSION = getNmsVersion();
/*     */ 
/*     */   
/*     */   private static final int V1_11 = 1110;
/*     */   
/*     */   private static final int V1_13 = 1130;
/*     */   
/*     */   private static final int V1_14 = 1140;
/*     */   
/*     */   private static final int V1_16_5 = 1165;
/*     */   
/*     */   private static final int V1_12_1 = 1121;
/*     */   
/*     */   private static final int V1_20_1 = 1201;
/*     */   
/*  55 */   private static final int CURRENT_VERSION = getCurrentVersion();
/*     */   
/*  57 */   private static final boolean IS_PAPER = checkPaper();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public static final boolean IS_COMPONENT_LEGACY = (CURRENT_VERSION < 1165);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public static final boolean IS_ITEM_LEGACY = (CURRENT_VERSION < 1130);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   public static final boolean IS_UNBREAKABLE_LEGACY = (CURRENT_VERSION < 1110);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public static final boolean IS_PDC_VERSION = (CURRENT_VERSION >= 1140);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public static final boolean IS_SKULL_OWNER_LEGACY = (CURRENT_VERSION < 1121);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public static final boolean IS_CUSTOM_MODEL_DATA = (CURRENT_VERSION >= 1140);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public static final boolean IS_PLAYER_PROFILE_API = (CURRENT_VERSION >= 1201);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean checkPaper() {
/*     */     try {
/* 104 */       Class.forName("com.destroystokyo.paper.PaperConfig");
/* 105 */       return true;
/* 106 */     } catch (ClassNotFoundException ignored) {
/* 107 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getCurrentVersion() {
/* 118 */     Matcher matcher = Pattern.compile("(?<version>\\d+\\.\\d+)(?<patch>\\.\\d+)?").matcher(Bukkit.getBukkitVersion());
/*     */     
/* 120 */     StringBuilder stringBuilder = new StringBuilder();
/* 121 */     if (matcher.find()) {
/* 122 */       stringBuilder.append(matcher.group("version").replace(".", ""));
/* 123 */       String patch = matcher.group("patch");
/* 124 */       if (patch == null) { stringBuilder.append("0"); }
/* 125 */       else { stringBuilder.append(patch.replace(".", "")); }
/*     */     
/*     */     } 
/*     */     
/* 129 */     Integer version = Ints.tryParse(stringBuilder.toString());
/*     */ 
/*     */     
/* 132 */     if (version == null) throw new GuiException("Could not retrieve server version!");
/*     */     
/* 134 */     return version.intValue();
/*     */   }
/*     */   
/*     */   private static String getNmsVersion() {
/* 138 */     String version = Bukkit.getServer().getClass().getPackage().getName();
/* 139 */     return version.substring(version.lastIndexOf('.') + 1);
/*     */   }
/*     */   
/*     */   public static Class<?> craftClass(@NotNull String name) throws ClassNotFoundException {
/* 143 */     return Class.forName("org.bukkit.craftbukkit." + NMS_VERSION + "." + name);
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\dev\triumphteam\gui\component\\util\VersionHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */