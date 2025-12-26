/*     */ package net.kyori.adventure.text.serializer.gson;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonElement;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.UnaryOperator;
/*     */ import net.kyori.adventure.builder.AbstractBuilder;
/*     */ import net.kyori.adventure.text.Component;
/*     */ import net.kyori.adventure.text.serializer.json.JSONComponentSerializer;
/*     */ import net.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer;
/*     */ import net.kyori.adventure.util.Buildable;
/*     */ import net.kyori.adventure.util.PlatformAPI;
/*     */ import org.jetbrains.annotations.ApiStatus.Internal;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface GsonComponentSerializer
/*     */   extends JSONComponentSerializer, Buildable<GsonComponentSerializer, GsonComponentSerializer.Builder>
/*     */ {
/*     */   @NotNull
/*     */   static GsonComponentSerializer gson() {
/*  59 */     return GsonComponentSerializerImpl.Instances.INSTANCE;
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
/*     */   @NotNull
/*     */   static GsonComponentSerializer colorDownsamplingGson() {
/*  72 */     return GsonComponentSerializerImpl.Instances.LEGACY_INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Builder builder() {
/*  82 */     return new GsonComponentSerializerImpl.BuilderImpl();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   Gson serializer();
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   UnaryOperator<GsonBuilder> populator();
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   Component deserializeFromTree(@NotNull JsonElement paramJsonElement);
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   JsonElement serializeToTree(@NotNull Component paramComponent);
/*     */ 
/*     */   
/*     */   @PlatformAPI
/*     */   @Internal
/*     */   public static interface Provider
/*     */   {
/*     */     @PlatformAPI
/*     */     @Internal
/*     */     @NotNull
/*     */     GsonComponentSerializer gson();
/*     */ 
/*     */     
/*     */     @PlatformAPI
/*     */     @Internal
/*     */     @NotNull
/*     */     GsonComponentSerializer gsonLegacy();
/*     */ 
/*     */     
/*     */     @PlatformAPI
/*     */     @Internal
/*     */     @NotNull
/*     */     Consumer<GsonComponentSerializer.Builder> builder();
/*     */   }
/*     */ 
/*     */   
/*     */   public static interface Builder
/*     */     extends AbstractBuilder<GsonComponentSerializer>, Buildable.Builder<GsonComponentSerializer>, JSONComponentSerializer.Builder
/*     */   {
/*     */     @NotNull
/*     */     GsonComponentSerializer build();
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     Builder emitLegacyHoverEvent();
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     Builder legacyHoverEventSerializer(LegacyHoverEventSerializer param1LegacyHoverEventSerializer);
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     @NotNull
/*     */     default Builder legacyHoverEventSerializer(@Nullable LegacyHoverEventSerializer serializer) {
/* 146 */       return legacyHoverEventSerializer(serializer);
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     Builder downsampleColors();
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\text\serializer\gson\GsonComponentSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */