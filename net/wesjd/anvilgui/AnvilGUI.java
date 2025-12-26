/*     */ package net.wesjd.anvilgui;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.logging.Level;
/*     */ import net.wesjd.anvilgui.version.VersionMatcher;
/*     */ import net.wesjd.anvilgui.version.VersionWrapper;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.ClickType;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*     */ import org.bukkit.event.inventory.InventoryDragEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnvilGUI
/*     */ {
/*  43 */   private static final VersionWrapper WRAPPER = (new VersionMatcher()).match();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   private static final ItemStack AIR = new ItemStack(Material.AIR);
/*     */   
/*     */   private final Plugin plugin;
/*     */   
/*     */   private final Player player;
/*     */   private final Executor mainThreadExecutor;
/*     */   
/*     */   private static ItemStack itemNotNull(ItemStack stack) {
/*  57 */     return (stack == null) ? AIR : stack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Object titleComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final ItemStack[] initialContents;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean preventClose;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Set<Integer> interactableSlots;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Consumer<StateSnapshot> closeListener;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean concurrentClickHandlerExecution;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final ClickHandler clickHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int containerId;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Inventory inventory;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   private final ListenUp listener = new ListenUp();
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
/*     */   private boolean open;
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
/*     */   private VersionWrapper.AnvilContainerWrapper container;
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
/*     */   private AnvilGUI(Plugin plugin, Player player, Executor mainThreadExecutor, Object titleComponent, ItemStack[] initialContents, boolean preventClose, Set<Integer> interactableSlots, Consumer<StateSnapshot> closeListener, boolean concurrentClickHandlerExecution, ClickHandler clickHandler) {
/* 147 */     this.plugin = plugin;
/* 148 */     this.player = player;
/* 149 */     this.mainThreadExecutor = mainThreadExecutor;
/* 150 */     this.titleComponent = titleComponent;
/* 151 */     this.initialContents = initialContents;
/* 152 */     this.preventClose = preventClose;
/* 153 */     this.interactableSlots = Collections.unmodifiableSet(interactableSlots);
/* 154 */     this.closeListener = closeListener;
/* 155 */     this.concurrentClickHandlerExecution = concurrentClickHandlerExecution;
/* 156 */     this.clickHandler = clickHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void openInventory() {
/* 163 */     WRAPPER.handleInventoryCloseEvent(this.player);
/* 164 */     WRAPPER.setActiveContainerDefault(this.player);
/*     */     
/* 166 */     Bukkit.getPluginManager().registerEvents(this.listener, this.plugin);
/*     */     
/* 168 */     this.container = WRAPPER.newContainerAnvil(this.player, this.titleComponent);
/*     */     
/* 170 */     this.inventory = this.container.getBukkitInventory();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 175 */     for (int i = 0; i < this.initialContents.length; i++) {
/* 176 */       this.inventory.setItem(i, this.initialContents[i]);
/*     */     }
/*     */     
/* 179 */     this.containerId = WRAPPER.getNextContainerId(this.player, this.container);
/* 180 */     WRAPPER.sendPacketOpenWindow(this.player, this.containerId, this.titleComponent);
/* 181 */     WRAPPER.setActiveContainer(this.player, this.container);
/* 182 */     WRAPPER.setActiveContainerId(this.container, this.containerId);
/* 183 */     WRAPPER.addActiveContainerSlotListener(this.container, this.player);
/*     */     
/* 185 */     this.open = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeInventory() {
/* 192 */     closeInventory(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void closeInventory(boolean sendClosePacket) {
/* 201 */     if (!this.open) {
/*     */       return;
/*     */     }
/*     */     
/* 205 */     this.open = false;
/*     */     
/* 207 */     HandlerList.unregisterAll(this.listener);
/*     */     
/* 209 */     if (sendClosePacket) {
/* 210 */       WRAPPER.handleInventoryCloseEvent(this.player);
/* 211 */       WRAPPER.setActiveContainerDefault(this.player);
/* 212 */       WRAPPER.sendPacketCloseWindow(this.player, this.containerId);
/*     */     } 
/*     */     
/* 215 */     if (this.closeListener != null) {
/* 216 */       this.closeListener.accept(StateSnapshot.fromAnvilGUI(this));
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
/*     */   public void setTitle(String literalTitle, boolean preserveRenameText) {
/* 229 */     Validate.notNull(literalTitle, "literalTitle cannot be null");
/* 230 */     setTitle(WRAPPER.literalChatComponent(literalTitle), preserveRenameText);
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
/*     */   public void setJsonTitle(String json, boolean preserveRenameText) {
/* 242 */     Validate.notNull(json, "json cannot be null");
/* 243 */     setTitle(WRAPPER.jsonChatComponent(json), preserveRenameText);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setTitle(Object title, boolean preserveRenameText) {
/* 253 */     if (!WRAPPER.isCustomTitleSupported()) {
/*     */       return;
/*     */     }
/* 256 */     String renameText = this.container.getRenameText();
/* 257 */     WRAPPER.sendPacketOpenWindow(this.player, this.containerId, title);
/* 258 */     if (preserveRenameText)
/*     */     {
/* 260 */       this.container.setRenameText((renameText == null) ? "" : renameText);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Inventory getInventory() {
/* 270 */     return this.inventory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class ListenUp
/*     */     implements Listener
/*     */   {
/*     */     private boolean clickHandlerRunning = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @EventHandler
/*     */     public void onInventoryClick(InventoryClickEvent event) {
/* 287 */       if (!event.getInventory().equals(AnvilGUI.this.inventory)) {
/*     */         return;
/*     */       }
/*     */       
/* 291 */       Player clicker = (Player)event.getWhoClicked();
/*     */       
/* 293 */       Inventory clickedInventory = event.getClickedInventory();
/* 294 */       if (clickedInventory != null && clickedInventory
/* 295 */         .equals(clicker.getInventory()) && event
/* 296 */         .getClick().equals(ClickType.DOUBLE_CLICK)) {
/* 297 */         event.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/* 301 */       int rawSlot = event.getRawSlot();
/* 302 */       if (rawSlot < 3 || event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
/* 303 */         event.setCancelled(!AnvilGUI.this.interactableSlots.contains(Integer.valueOf(rawSlot)));
/* 304 */         if (this.clickHandlerRunning && !AnvilGUI.this.concurrentClickHandlerExecution) {
/*     */           return;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 310 */         CompletableFuture<List<AnvilGUI.ResponseAction>> actionsFuture = AnvilGUI.this.clickHandler.apply(Integer.valueOf(rawSlot), AnvilGUI.StateSnapshot.fromAnvilGUI(AnvilGUI.this));
/*     */         
/* 312 */         Consumer<List<AnvilGUI.ResponseAction>> actionsConsumer = actions -> {
/*     */             for (AnvilGUI.ResponseAction action : actions) {
/*     */               action.accept(AnvilGUI.this, clicker);
/*     */             }
/*     */           };
/*     */         
/* 318 */         if (actionsFuture.isDone()) {
/*     */ 
/*     */           
/* 321 */           actionsFuture.thenAccept(actionsConsumer).join();
/*     */         } else {
/* 323 */           this.clickHandlerRunning = true;
/*     */ 
/*     */           
/* 326 */           actionsFuture
/* 327 */             .thenAcceptAsync(actionsConsumer, AnvilGUI.this.mainThreadExecutor)
/* 328 */             .handle((results, exception) -> {
/*     */                 if (exception != null) {
/*     */                   AnvilGUI.this.plugin.getLogger().log(Level.SEVERE, "An exception occurred in the AnvilGUI clickHandler", exception);
/*     */                 }
/*     */                 this.clickHandlerRunning = false;
/*     */                 return null;
/*     */               });
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @EventHandler
/*     */     public void onInventoryDrag(InventoryDragEvent event) {
/* 346 */       if (event.getInventory().equals(AnvilGUI.this.inventory)) {
/* 347 */         for (int slot : AnvilGUI.Slot.values()) {
/* 348 */           if (event.getRawSlots().contains(Integer.valueOf(slot))) {
/* 349 */             event.setCancelled(!AnvilGUI.this.interactableSlots.contains(Integer.valueOf(slot)));
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     }
/*     */     
/*     */     @EventHandler
/*     */     public void onInventoryClose(InventoryCloseEvent event) {
/* 358 */       if (AnvilGUI.this.open && event.getInventory().equals(AnvilGUI.this.inventory)) {
/* 359 */         AnvilGUI.this.closeInventory(false);
/* 360 */         if (AnvilGUI.this.preventClose) {
/* 361 */           AnvilGUI.this.mainThreadExecutor.execute(() -> rec$.openInventory());
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private ListenUp() {}
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Builder
/*     */   {
/*     */     private Executor mainThreadExecutor;
/*     */     
/*     */     private Consumer<AnvilGUI.StateSnapshot> closeListener;
/*     */     
/*     */     private boolean concurrentClickHandlerExecution = false;
/*     */     
/*     */     private AnvilGUI.ClickHandler clickHandler;
/*     */     private boolean preventClose = false;
/* 381 */     private Set<Integer> interactableSlots = Collections.emptySet();
/*     */     
/*     */     private Plugin plugin;
/*     */     
/* 385 */     private Object titleComponent = AnvilGUI.WRAPPER.literalChatComponent("Repair & Name");
/*     */ 
/*     */ 
/*     */     
/*     */     private String itemText;
/*     */ 
/*     */     
/*     */     private ItemStack itemLeft;
/*     */ 
/*     */     
/*     */     private ItemStack itemRight;
/*     */ 
/*     */     
/*     */     private ItemStack itemOutput;
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder mainThreadExecutor(Executor executor) {
/* 403 */       Validate.notNull(executor, "Executor cannot be null");
/* 404 */       this.mainThreadExecutor = executor;
/* 405 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder preventClose() {
/* 414 */       this.preventClose = true;
/* 415 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder interactableSlots(int... slots) {
/* 426 */       Set<Integer> newValue = new HashSet<>();
/* 427 */       for (int slot : slots) {
/* 428 */         newValue.add(Integer.valueOf(slot));
/*     */       }
/* 430 */       this.interactableSlots = newValue;
/* 431 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder onClose(Consumer<AnvilGUI.StateSnapshot> closeListener) {
/* 442 */       Validate.notNull(closeListener, "closeListener cannot be null");
/* 443 */       this.closeListener = closeListener;
/* 444 */       return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder onClickAsync(AnvilGUI.ClickHandler clickHandler) {
/* 462 */       Validate.notNull(clickHandler, "click function cannot be null");
/* 463 */       this.clickHandler = clickHandler;
/* 464 */       return this;
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
/*     */     public Builder allowConcurrentClickHandlerExecution() {
/* 477 */       this.concurrentClickHandlerExecution = true;
/* 478 */       return this;
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
/*     */     
/*     */     public Builder onClick(BiFunction<Integer, AnvilGUI.StateSnapshot, List<AnvilGUI.ResponseAction>> clickHandler) {
/* 493 */       Validate.notNull(clickHandler, "click function cannot be null");
/* 494 */       this.clickHandler = ((slot, stateSnapshot) -> CompletableFuture.completedFuture(clickHandler.apply(slot, stateSnapshot)));
/*     */       
/* 496 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder plugin(Plugin plugin) {
/* 507 */       Validate.notNull(plugin, "Plugin cannot be null");
/* 508 */       this.plugin = plugin;
/* 509 */       return this;
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
/*     */     public Builder text(String text) {
/* 523 */       Validate.notNull(text, "Text cannot be null");
/* 524 */       this.itemText = text;
/* 525 */       return this;
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
/*     */     public Builder title(String title) {
/* 538 */       Validate.notNull(title, "title cannot be null");
/* 539 */       this.titleComponent = AnvilGUI.WRAPPER.literalChatComponent(title);
/* 540 */       return this;
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
/*     */     public Builder jsonTitle(String json) {
/* 554 */       Validate.notNull(json, "json cannot be null");
/* 555 */       this.titleComponent = AnvilGUI.WRAPPER.jsonChatComponent(json);
/* 556 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder itemLeft(ItemStack item) {
/* 567 */       Validate.notNull(item, "item cannot be null");
/* 568 */       this.itemLeft = item;
/* 569 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder itemRight(ItemStack item) {
/* 579 */       this.itemRight = item;
/* 580 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder itemOutput(ItemStack item) {
/* 590 */       this.itemOutput = item;
/* 591 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public AnvilGUI open(Player player) {
/* 602 */       Validate.notNull(this.plugin, "Plugin cannot be null");
/* 603 */       Validate.notNull(this.clickHandler, "click handler cannot be null");
/* 604 */       Validate.notNull(player, "Player cannot be null");
/*     */       
/* 606 */       if (this.itemText != null) {
/* 607 */         if (this.itemLeft == null) {
/* 608 */           this.itemLeft = new ItemStack(Material.PAPER);
/*     */         }
/*     */         
/* 611 */         ItemMeta paperMeta = this.itemLeft.getItemMeta();
/* 612 */         paperMeta.setDisplayName(this.itemText);
/* 613 */         this.itemLeft.setItemMeta(paperMeta);
/*     */       } 
/*     */ 
/*     */       
/* 617 */       if (this.mainThreadExecutor == null) {
/* 618 */         this.mainThreadExecutor = (task -> Bukkit.getScheduler().runTask(this.plugin, task));
/*     */       }
/*     */       
/* 621 */       AnvilGUI anvilGUI = new AnvilGUI(this.plugin, player, this.mainThreadExecutor, this.titleComponent, new ItemStack[] { this.itemLeft, this.itemRight, this.itemOutput }, this.preventClose, this.interactableSlots, this.closeListener, this.concurrentClickHandlerExecution, this.clickHandler);
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
/* 632 */       anvilGUI.openInventory();
/* 633 */       return anvilGUI;
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
/*     */   @FunctionalInterface
/*     */   public static interface ResponseAction
/*     */     extends BiConsumer<AnvilGUI, Player>
/*     */   {
/*     */     static ResponseAction replaceInputText(String text) {
/* 664 */       Validate.notNull(text, "text cannot be null");
/* 665 */       return (anvilgui, player) -> {
/*     */           ItemStack item = anvilgui.getInventory().getItem(2);
/*     */           if (item == null) {
/*     */             item = anvilgui.getInventory().getItem(0);
/*     */           }
/*     */           if (item == null) {
/*     */             throw new IllegalStateException("replaceInputText can only be used if slots OUTPUT or INPUT_LEFT are not empty");
/*     */           }
/*     */           ItemStack cloned = item.clone();
/*     */           ItemMeta meta = cloned.getItemMeta();
/*     */           meta.setDisplayName(text);
/*     */           cloned.setItemMeta(meta);
/*     */           anvilgui.getInventory().setItem(0, cloned);
/*     */         };
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
/*     */     
/*     */     static ResponseAction updateTitle(String literalTitle, boolean preserveRenameText) {
/* 693 */       Validate.notNull(literalTitle, "literalTitle cannot be null");
/* 694 */       return (anvilGUI, player) -> anvilGUI.setTitle(literalTitle, preserveRenameText);
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
/*     */     static ResponseAction updateJsonTitle(String json, boolean preserveRenameText) {
/* 706 */       Validate.notNull(json, "json cannot be null");
/* 707 */       return (anvilGUI, player) -> anvilGUI.setJsonTitle(json, preserveRenameText);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static ResponseAction openInventory(Inventory otherInventory) {
/* 717 */       Validate.notNull(otherInventory, "otherInventory cannot be null");
/* 718 */       return (anvilgui, player) -> player.openInventory(otherInventory);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static ResponseAction close() {
/* 726 */       return (anvilgui, player) -> anvilgui.closeInventory();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static ResponseAction run(Runnable runnable) {
/* 736 */       Validate.notNull(runnable, "runnable cannot be null");
/* 737 */       return (anvilgui, player) -> runnable.run();
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
/*     */   @Deprecated
/*     */   public static class Response
/*     */   {
/*     */     public static List<AnvilGUI.ResponseAction> close() {
/* 753 */       return Arrays.asList(new AnvilGUI.ResponseAction[] { AnvilGUI.ResponseAction.close() });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static List<AnvilGUI.ResponseAction> text(String text) {
/* 764 */       return Arrays.asList(new AnvilGUI.ResponseAction[] { AnvilGUI.ResponseAction.replaceInputText(text) });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static List<AnvilGUI.ResponseAction> openInventory(Inventory inventory) {
/* 775 */       return Arrays.asList(new AnvilGUI.ResponseAction[] { AnvilGUI.ResponseAction.openInventory(inventory) });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Slot
/*     */   {
/* 784 */     private static final int[] values = new int[] { 0, 1, 2 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final int INPUT_LEFT = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final int INPUT_RIGHT = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final int OUTPUT = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static int[] values() {
/* 807 */       return values;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class StateSnapshot
/*     */   {
/*     */     private final ItemStack leftItem;
/*     */     private final ItemStack rightItem;
/*     */     private final ItemStack outputItem;
/*     */     private final Player player;
/*     */     
/*     */     private static StateSnapshot fromAnvilGUI(AnvilGUI anvilGUI) {
/* 820 */       Inventory inventory = anvilGUI.getInventory();
/* 821 */       return new StateSnapshot(AnvilGUI
/* 822 */           .itemNotNull(inventory.getItem(0)).clone(), AnvilGUI
/* 823 */           .itemNotNull(inventory.getItem(1)).clone(), AnvilGUI
/* 824 */           .itemNotNull(inventory.getItem(2)).clone(), anvilGUI
/* 825 */           .player);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public StateSnapshot(ItemStack leftItem, ItemStack rightItem, ItemStack outputItem, Player player) {
/* 846 */       this.leftItem = leftItem;
/* 847 */       this.rightItem = rightItem;
/* 848 */       this.outputItem = outputItem;
/* 849 */       this.player = player;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ItemStack getLeftItem() {
/* 858 */       return this.leftItem;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ItemStack getRightItem() {
/* 867 */       return this.rightItem;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ItemStack getOutputItem() {
/* 877 */       return this.outputItem;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Player getPlayer() {
/* 886 */       return this.player;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getText() {
/* 895 */       return this.outputItem.hasItemMeta() ? this.outputItem.getItemMeta().getDisplayName() : "";
/*     */     }
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface ClickHandler extends BiFunction<Integer, StateSnapshot, CompletableFuture<List<ResponseAction>>> {}
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\wesjd\anvilgui\AnvilGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */