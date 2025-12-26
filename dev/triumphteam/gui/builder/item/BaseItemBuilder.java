/*     */ package dev.triumphteam.gui.builder.item;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import dev.triumphteam.gui.components.GuiAction;
/*     */ import dev.triumphteam.gui.components.exception.GuiException;
/*     */ import dev.triumphteam.gui.components.util.ItemNbt;
/*     */ import dev.triumphteam.gui.components.util.Legacy;
/*     */ import dev.triumphteam.gui.components.util.VersionHelper;
/*     */ import dev.triumphteam.gui.guis.GuiItem;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.stream.Collectors;
/*     */ import net.kyori.adventure.text.Component;
/*     */ import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.LeatherArmorMeta;
/*     */ import org.bukkit.persistence.PersistentDataContainer;
/*     */ import org.jetbrains.annotations.Contract;
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
/*     */ public abstract class BaseItemBuilder<B extends BaseItemBuilder<B>>
/*     */ {
/*  67 */   private static final EnumSet<Material> LEATHER_ARMOR = EnumSet.of(Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS);
/*     */ 
/*     */ 
/*     */   
/*  71 */   private static final GsonComponentSerializer GSON = GsonComponentSerializer.gson();
/*     */   private static final Field DISPLAY_NAME_FIELD;
/*     */   private static final Field LORE_FIELD;
/*     */   
/*     */   static {
/*     */     try {
/*  77 */       Class<?> metaClass = VersionHelper.craftClass("inventory.CraftMetaItem");
/*     */       
/*  79 */       DISPLAY_NAME_FIELD = metaClass.getDeclaredField("displayName");
/*  80 */       DISPLAY_NAME_FIELD.setAccessible(true);
/*     */       
/*  82 */       LORE_FIELD = metaClass.getDeclaredField("lore");
/*  83 */       LORE_FIELD.setAccessible(true);
/*  84 */     } catch (NoSuchFieldException|ClassNotFoundException exception) {
/*  85 */       exception.printStackTrace();
/*  86 */       throw new GuiException("Could not retrieve displayName nor lore field for ItemBuilder.");
/*     */     } 
/*     */   }
/*     */   
/*     */   private ItemStack itemStack;
/*     */   private ItemMeta meta;
/*     */   
/*     */   protected BaseItemBuilder(@NotNull ItemStack itemStack) {
/*  94 */     Preconditions.checkNotNull(itemStack, "Item can't be null!");
/*     */     
/*  96 */     this.itemStack = itemStack;
/*  97 */     this.meta = itemStack.hasItemMeta() ? itemStack.getItemMeta() : Bukkit.getItemFactory().getItemMeta(itemStack.getType());
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
/*     */   @Contract("_ -> this")
/*     */   public B name(@NotNull Component name) {
/* 110 */     if (this.meta == null) return (B)this;
/*     */     
/* 112 */     if (VersionHelper.IS_COMPONENT_LEGACY) {
/* 113 */       this.meta.setDisplayName(Legacy.SERIALIZER.serialize(name));
/* 114 */       return (B)this;
/*     */     } 
/*     */     
/*     */     try {
/* 118 */       DISPLAY_NAME_FIELD.set(this.meta, GSON.serialize(name));
/* 119 */     } catch (IllegalAccessException exception) {
/* 120 */       exception.printStackTrace();
/*     */     } 
/*     */     
/* 123 */     return (B)this;
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
/*     */   @Contract("_ -> this")
/*     */   public B amount(int amount) {
/* 136 */     this.itemStack.setAmount(amount);
/* 137 */     return (B)this;
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
/*     */   @Contract("_ -> this")
/*     */   public B lore(@Nullable Component... lore) {
/* 150 */     return lore(Arrays.asList(lore));
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
/*     */   @Contract("_ -> this")
/*     */   public B lore(@NotNull List<Component> lore) {
/* 163 */     if (this.meta == null) return (B)this;
/*     */     
/* 165 */     if (VersionHelper.IS_COMPONENT_LEGACY) {
/* 166 */       Objects.requireNonNull(Legacy.SERIALIZER); this.meta.setLore((List)lore.stream().filter(Objects::nonNull).map(Legacy.SERIALIZER::serialize).collect(Collectors.toList()));
/* 167 */       return (B)this;
/*     */     } 
/*     */     
/* 170 */     Objects.requireNonNull(GSON); List<String> jsonLore = (List<String>)lore.stream().filter(Objects::nonNull).map(GSON::serialize).collect(Collectors.toList());
/*     */     
/*     */     try {
/* 173 */       LORE_FIELD.set(this.meta, jsonLore);
/* 174 */     } catch (IllegalAccessException exception) {
/* 175 */       exception.printStackTrace();
/*     */     } 
/*     */     
/* 178 */     return (B)this;
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
/*     */   public B lore(@NotNull Consumer<List<Component>> lore) {
/*     */     List<Component> components;
/* 191 */     if (this.meta == null) return (B)this;
/*     */ 
/*     */     
/* 194 */     if (VersionHelper.IS_COMPONENT_LEGACY) {
/* 195 */       List<String> stringLore = this.meta.getLore();
/* 196 */       Objects.requireNonNull(Legacy.SERIALIZER); components = (stringLore == null) ? new ArrayList<>() : (List<Component>)stringLore.stream().map(Legacy.SERIALIZER::deserialize).collect(Collectors.toList());
/*     */     } else {
/*     */       try {
/* 199 */         List<String> jsonLore = (List<String>)LORE_FIELD.get(this.meta);
/*     */         
/* 201 */         Objects.requireNonNull(GSON); components = (jsonLore == null) ? new ArrayList<>() : (List<Component>)jsonLore.stream().map(GSON::deserialize).collect(Collectors.toList());
/* 202 */       } catch (IllegalAccessException exception) {
/* 203 */         components = new ArrayList<>();
/* 204 */         exception.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/* 208 */     lore.accept(components);
/* 209 */     return lore(components);
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
/*     */   @Contract("_, _, _ -> this")
/*     */   public B enchant(@NotNull Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
/* 224 */     this.meta.addEnchant(enchantment, level, ignoreLevelRestriction);
/* 225 */     return (B)this;
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
/*     */   @Contract("_, _ -> this")
/*     */   public B enchant(@NotNull Enchantment enchantment, int level) {
/* 239 */     return enchant(enchantment, level, true);
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
/*     */   @Contract("_ -> this")
/*     */   public B enchant(@NotNull Enchantment enchantment) {
/* 252 */     return enchant(enchantment, 1, true);
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
/*     */   @Contract("_, _ -> this")
/*     */   public B enchant(@NotNull Map<Enchantment, Integer> enchantments, boolean ignoreLevelRestriction) {
/* 267 */     enchantments.forEach((enchantment, level) -> enchant(enchantment, level.intValue(), ignoreLevelRestriction));
/* 268 */     return (B)this;
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
/*     */   @Contract("_ -> this")
/*     */   public B enchant(@NotNull Map<Enchantment, Integer> enchantments) {
/* 282 */     return enchant(enchantments, true);
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
/*     */   @Contract("_ -> this")
/*     */   public B disenchant(@NotNull Enchantment enchantment) {
/* 295 */     this.itemStack.removeEnchantment(enchantment);
/* 296 */     return (B)this;
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
/*     */   @Contract("_ -> this")
/*     */   public B flags(@NotNull ItemFlag... flags) {
/* 309 */     this.meta.addItemFlags(flags);
/* 310 */     return (B)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   @Contract(" -> this")
/*     */   public B unbreakable() {
/* 322 */     return unbreakable(true);
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
/*     */   public B unbreakable(boolean unbreakable) {
/* 334 */     if (VersionHelper.IS_UNBREAKABLE_LEGACY) {
/* 335 */       return setNbt("Unbreakable", unbreakable);
/*     */     }
/*     */     
/* 338 */     this.meta.setUnbreakable(unbreakable);
/* 339 */     return (B)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   @Contract(" -> this")
/*     */   public B glow() {
/* 351 */     return glow(true);
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
/*     */   public B glow(boolean glow) {
/* 363 */     if (glow) {
/* 364 */       this.meta.addEnchant(Enchantment.LURE, 1, false);
/* 365 */       this.meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
/* 366 */       return (B)this;
/*     */     } 
/*     */     
/* 369 */     for (Enchantment enchantment : this.meta.getEnchants().keySet()) {
/* 370 */       this.meta.removeEnchant(enchantment);
/*     */     }
/*     */     
/* 373 */     return (B)this;
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
/*     */   @Contract("_ -> this")
/*     */   public B pdc(@NotNull Consumer<PersistentDataContainer> consumer) {
/* 387 */     consumer.accept(this.meta.getPersistentDataContainer());
/* 388 */     return (B)this;
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
/*     */   @Contract("_ -> this")
/*     */   public B model(int modelData) {
/* 402 */     if (VersionHelper.IS_CUSTOM_MODEL_DATA) {
/* 403 */       this.meta.setCustomModelData(Integer.valueOf(modelData));
/*     */     }
/*     */     
/* 406 */     return (B)this;
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
/*     */   @Contract("_ -> this")
/*     */   public B color(@NotNull Color color) {
/* 421 */     if (LEATHER_ARMOR.contains(this.itemStack.getType())) {
/* 422 */       LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta)getMeta();
/*     */       
/* 424 */       leatherArmorMeta.setColor(color);
/* 425 */       setMeta((ItemMeta)leatherArmorMeta);
/*     */     } 
/*     */     
/* 428 */     return (B)this;
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
/*     */   public B setNbt(@NotNull String key, @NotNull String value) {
/* 441 */     this.itemStack.setItemMeta(this.meta);
/* 442 */     this.itemStack = ItemNbt.setString(this.itemStack, key, value);
/* 443 */     this.meta = this.itemStack.getItemMeta();
/* 444 */     return (B)this;
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
/*     */   public B setNbt(@NotNull String key, boolean value) {
/* 457 */     this.itemStack.setItemMeta(this.meta);
/* 458 */     this.itemStack = ItemNbt.setBoolean(this.itemStack, key, value);
/* 459 */     this.meta = this.itemStack.getItemMeta();
/* 460 */     return (B)this;
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
/*     */   public B removeNbt(@NotNull String key) {
/* 472 */     this.itemStack.setItemMeta(this.meta);
/* 473 */     this.itemStack = ItemNbt.removeTag(this.itemStack, key);
/* 474 */     this.meta = this.itemStack.getItemMeta();
/* 475 */     return (B)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack build() {
/* 485 */     this.itemStack.setItemMeta(this.meta);
/* 486 */     return this.itemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   @Contract(" -> new")
/*     */   public GuiItem asGuiItem() {
/* 497 */     return new GuiItem(build());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   @Contract("_ -> new")
/*     */   public GuiItem asGuiItem(@NotNull GuiAction<InventoryClickEvent> action) {
/* 509 */     return new GuiItem(build(), action);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected ItemStack getItemStack() {
/* 519 */     return this.itemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setItemStack(@NotNull ItemStack itemStack) {
/* 528 */     this.itemStack = itemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected ItemMeta getMeta() {
/* 538 */     return this.meta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setMeta(@NotNull ItemMeta meta) {
/* 547 */     this.meta = meta;
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
/*     */   @Deprecated
/*     */   public B setName(@NotNull String name) {
/* 562 */     getMeta().setDisplayName(name);
/* 563 */     return (B)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public B setAmount(int amount) {
/* 575 */     getItemStack().setAmount(amount);
/* 576 */     return (B)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public B addLore(@NotNull String... lore) {
/* 588 */     return addLore(Arrays.asList(lore));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public B addLore(@NotNull List<String> lore) {
/* 600 */     List<String> newLore = getMeta().hasLore() ? getMeta().getLore() : new ArrayList<>();
/*     */     
/* 602 */     newLore.addAll(lore);
/* 603 */     return setLore(newLore);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public B setLore(@NotNull String... lore) {
/* 615 */     return setLore(Arrays.asList(lore));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public B setLore(@NotNull List<String> lore) {
/* 627 */     getMeta().setLore(lore);
/* 628 */     return (B)this;
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
/*     */   @Deprecated
/*     */   public B addEnchantment(@NotNull Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
/* 642 */     getMeta().addEnchant(enchantment, level, ignoreLevelRestriction);
/* 643 */     return (B)this;
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
/*     */   @Deprecated
/*     */   public B addEnchantment(@NotNull Enchantment enchantment, int level) {
/* 656 */     return addEnchantment(enchantment, level, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public B addEnchantment(@NotNull Enchantment enchantment) {
/* 668 */     return addEnchantment(enchantment, 1, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public B removeEnchantment(@NotNull Enchantment enchantment) {
/* 680 */     getItemStack().removeEnchantment(enchantment);
/* 681 */     return (B)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public B addItemFlags(@NotNull ItemFlag... flags) {
/* 693 */     getMeta().addItemFlags(flags);
/* 694 */     return (B)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public B setUnbreakable(boolean unbreakable) {
/* 706 */     return unbreakable(unbreakable);
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\dev\triumphteam\gui\builder\item\BaseItemBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */