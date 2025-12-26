/*     */ package net.kyori.adventure.sound;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import java.util.OptionalLong;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Supplier;
/*     */ import net.kyori.adventure.builder.AbstractBuilder;
/*     */ import net.kyori.adventure.key.Key;
/*     */ import net.kyori.adventure.key.Keyed;
/*     */ import net.kyori.adventure.util.Index;
/*     */ import net.kyori.examination.Examinable;
/*     */ import org.jetbrains.annotations.ApiStatus.NonExtendable;
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
/*     */ @NonExtendable
/*     */ public interface Sound
/*     */   extends Examinable
/*     */ {
/*     */   @NotNull
/*     */   static Builder sound() {
/*  69 */     return new SoundImpl.BuilderImpl();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   static Builder sound(@NotNull Sound existing) {
/*  80 */     return new SoundImpl.BuilderImpl(existing);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   static Sound sound(@NotNull Consumer<Builder> configurer) {
/*  91 */     return (Sound)AbstractBuilder.configureAndBuild(sound(), configurer);
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
/*     */   @NotNull
/*     */   static Sound sound(@NotNull Key name, @NotNull Source source, float volume, float pitch) {
/* 105 */     return (Sound)sound().type(name).source(source).volume(volume).pitch(pitch).build();
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
/*     */   @NotNull
/*     */   static Sound sound(@NotNull Type type, @NotNull Source source, float volume, float pitch) {
/* 119 */     Objects.requireNonNull(type, "type");
/* 120 */     return sound(type.key(), source, volume, pitch);
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
/*     */   @NotNull
/*     */   static Sound sound(@NotNull Supplier<? extends Type> type, @NotNull Source source, float volume, float pitch) {
/* 134 */     return (Sound)sound().type(type).source(source).volume(volume).pitch(pitch).build();
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
/*     */   @NotNull
/*     */   static Sound sound(@NotNull Key name, Source.Provider source, float volume, float pitch) {
/* 148 */     return sound(name, source.soundSource(), volume, pitch);
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
/*     */   @NotNull
/*     */   static Sound sound(@NotNull Type type, Source.Provider source, float volume, float pitch) {
/* 162 */     return sound(type, source.soundSource(), volume, pitch);
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
/*     */   @NotNull
/*     */   static Sound sound(@NotNull Supplier<? extends Type> type, Source.Provider source, float volume, float pitch) {
/* 176 */     return sound(type, source.soundSource(), volume, pitch);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   Key name();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   Source source();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float volume();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float pitch();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   OptionalLong seed();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   SoundStop asStop();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface Provider
/*     */   {
/*     */     @NotNull
/*     */     Sound.Source soundSource();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Source
/*     */   {
/* 235 */     MASTER("master"),
/* 236 */     MUSIC("music"),
/* 237 */     RECORD("record"),
/* 238 */     WEATHER("weather"),
/* 239 */     BLOCK("block"),
/* 240 */     HOSTILE("hostile"),
/* 241 */     NEUTRAL("neutral"),
/* 242 */     PLAYER("player"),
/* 243 */     AMBIENT("ambient"),
/* 244 */     VOICE("voice");
/*     */     
/*     */     public static final Index<String, Source> NAMES;
/*     */     
/*     */     private final String name;
/*     */     
/*     */     static {
/* 251 */       NAMES = Index.create(Source.class, source -> source.name);
/*     */     }
/*     */     
/*     */     Source(String name) {
/* 255 */       this.name = name;
/*     */     }
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
/*     */     public static interface Provider
/*     */     {
/*     */       @NotNull
/*     */       Sound.Source soundSource();
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface Type
/*     */     extends Keyed
/*     */   {
/*     */     @NotNull
/*     */     Key key();
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
/*     */   public static interface Emitter
/*     */   {
/*     */     @NotNull
/*     */     static Emitter self() {
/* 306 */       return SoundImpl.EMITTER_SELF;
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface Builder extends AbstractBuilder<Sound> {
/*     */     @NotNull
/*     */     Builder type(@NotNull Key param1Key);
/*     */     
/*     */     @NotNull
/*     */     Builder type(@NotNull Sound.Type param1Type);
/*     */     
/*     */     @NotNull
/*     */     Builder type(@NotNull Supplier<? extends Sound.Type> param1Supplier);
/*     */     
/*     */     @NotNull
/*     */     Builder source(@NotNull Sound.Source param1Source);
/*     */     
/*     */     @NotNull
/*     */     Builder source(Sound.Source.Provider param1Provider);
/*     */     
/*     */     @NotNull
/*     */     Builder volume(float param1Float);
/*     */     
/*     */     @NotNull
/*     */     Builder pitch(float param1Float);
/*     */     
/*     */     @NotNull
/*     */     Builder seed(long param1Long);
/*     */     
/*     */     @NotNull
/*     */     Builder seed(@NotNull OptionalLong param1OptionalLong);
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\sound\Sound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */