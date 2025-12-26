/*     */ package dev.triumphteam.gui.builder.item;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import dev.triumphteam.gui.components.exception.GuiException;
/*     */ import dev.triumphteam.gui.components.util.SkullUtil;
/*     */ import dev.triumphteam.gui.components.util.VersionHelper;
/*     */ import java.lang.reflect.Field;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ import org.bukkit.profile.PlayerProfile;
/*     */ import org.bukkit.profile.PlayerTextures;
/*     */ import org.jetbrains.annotations.Contract;
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
/*     */ public final class SkullBuilder
/*     */   extends BaseItemBuilder<SkullBuilder>
/*     */ {
/*     */   private static final Field PROFILE_FIELD;
/*     */   
/*     */   static {
/*     */     Field field;
/*     */     try {
/*  57 */       SkullMeta skullMeta = (SkullMeta)SkullUtil.skull().getItemMeta();
/*  58 */       field = skullMeta.getClass().getDeclaredField("profile");
/*  59 */       field.setAccessible(true);
/*  60 */     } catch (NoSuchFieldException e) {
/*  61 */       e.printStackTrace();
/*  62 */       field = null;
/*     */     } 
/*     */     
/*  65 */     PROFILE_FIELD = field;
/*     */   }
/*     */   
/*     */   SkullBuilder() {
/*  69 */     super(SkullUtil.skull());
/*     */   }
/*     */   
/*     */   SkullBuilder(@NotNull ItemStack itemStack) {
/*  73 */     super(itemStack);
/*  74 */     if (!SkullUtil.isPlayerSkull(itemStack)) {
/*  75 */       throw new GuiException("SkullBuilder requires the material to be a PLAYER_HEAD/SKULL_ITEM!");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   @Contract("_, _ -> this")
/*     */   public SkullBuilder texture(@NotNull String texture, @NotNull UUID profileId) {
/*  89 */     if (!SkullUtil.isPlayerSkull(getItemStack())) return this;
/*     */     
/*  91 */     if (VersionHelper.IS_PLAYER_PROFILE_API) {
/*  92 */       String textureUrl = SkullUtil.getSkinUrl(texture);
/*     */       
/*  94 */       if (textureUrl == null) {
/*  95 */         return this;
/*     */       }
/*     */       
/*  98 */       SkullMeta skullMeta1 = (SkullMeta)getMeta();
/*  99 */       PlayerProfile playerProfile = Bukkit.createPlayerProfile(profileId, "");
/* 100 */       PlayerTextures textures = playerProfile.getTextures();
/*     */       
/*     */       try {
/* 103 */         textures.setSkin(new URL(textureUrl));
/* 104 */       } catch (MalformedURLException e) {
/* 105 */         e.printStackTrace();
/* 106 */         return this;
/*     */       } 
/*     */       
/* 109 */       playerProfile.setTextures(textures);
/* 110 */       skullMeta1.setOwnerProfile(playerProfile);
/* 111 */       setMeta((ItemMeta)skullMeta1);
/* 112 */       return this;
/*     */     } 
/*     */     
/* 115 */     if (PROFILE_FIELD == null) {
/* 116 */       return this;
/*     */     }
/*     */     
/* 119 */     SkullMeta skullMeta = (SkullMeta)getMeta();
/* 120 */     GameProfile profile = new GameProfile(profileId, "");
/* 121 */     profile.getProperties().put("textures", new Property("textures", texture));
/*     */     
/*     */     try {
/* 124 */       PROFILE_FIELD.set(skullMeta, profile);
/* 125 */     } catch (IllegalArgumentException|IllegalAccessException ex) {
/* 126 */       ex.printStackTrace();
/*     */     } 
/*     */     
/* 129 */     setMeta((ItemMeta)skullMeta);
/* 130 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   @Contract("_ -> this")
/*     */   public SkullBuilder texture(@NotNull String texture) {
/* 142 */     return texture(texture, UUID.randomUUID());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   @Contract("_ -> this")
/*     */   public SkullBuilder owner(@NotNull OfflinePlayer player) {
/* 154 */     if (!SkullUtil.isPlayerSkull(getItemStack())) return this;
/*     */     
/* 156 */     SkullMeta skullMeta = (SkullMeta)getMeta();
/*     */     
/* 158 */     if (VersionHelper.IS_SKULL_OWNER_LEGACY) {
/* 159 */       skullMeta.setOwner(player.getName());
/*     */     } else {
/* 161 */       skullMeta.setOwningPlayer(player);
/*     */     } 
/*     */     
/* 164 */     setMeta((ItemMeta)skullMeta);
/* 165 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\dev\triumphteam\gui\builder\item\SkullBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */