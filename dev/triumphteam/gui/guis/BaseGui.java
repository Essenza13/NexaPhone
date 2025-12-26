/*      */ package dev.triumphteam.gui.guis;
/*      */ 
/*      */ import dev.triumphteam.gui.components.GuiAction;
/*      */ import dev.triumphteam.gui.components.GuiType;
/*      */ import dev.triumphteam.gui.components.InteractionModifier;
/*      */ import dev.triumphteam.gui.components.exception.GuiException;
/*      */ import dev.triumphteam.gui.components.util.GuiFiller;
/*      */ import dev.triumphteam.gui.components.util.Legacy;
/*      */ import java.util.ArrayList;
/*      */ import java.util.EnumSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Optional;
/*      */ import java.util.Set;
/*      */ import net.kyori.adventure.text.Component;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.entity.HumanEntity;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.event.inventory.InventoryClickEvent;
/*      */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*      */ import org.bukkit.event.inventory.InventoryDragEvent;
/*      */ import org.bukkit.event.inventory.InventoryOpenEvent;
/*      */ import org.bukkit.inventory.Inventory;
/*      */ import org.bukkit.inventory.InventoryHolder;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.plugin.Plugin;
/*      */ import org.bukkit.plugin.java.JavaPlugin;
/*      */ import org.jetbrains.annotations.Contract;
/*      */ import org.jetbrains.annotations.NotNull;
/*      */ import org.jetbrains.annotations.Nullable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class BaseGui
/*      */   implements InventoryHolder
/*      */ {
/*   67 */   private static final Plugin plugin = (Plugin)JavaPlugin.getProvidingPlugin(BaseGui.class);
/*      */ 
/*      */   
/*      */   static {
/*   71 */     Bukkit.getPluginManager().registerEvents(new GuiListener(), plugin);
/*      */     
/*   73 */     Bukkit.getPluginManager().registerEvents(new InteractionModifierListener(), plugin);
/*      */   }
/*      */ 
/*      */   
/*   77 */   private final GuiFiller filler = new GuiFiller(this);
/*      */   
/*      */   private final Map<Integer, GuiItem> guiItems;
/*      */   
/*      */   private final Map<Integer, GuiAction<InventoryClickEvent>> slotActions;
/*      */   
/*      */   private final Set<InteractionModifier> interactionModifiers;
/*      */   
/*      */   private Inventory inventory;
/*      */   
/*      */   private String title;
/*   88 */   private int rows = 1;
/*      */   
/*   90 */   private GuiType guiType = GuiType.CHEST;
/*      */ 
/*      */   
/*      */   private GuiAction<InventoryClickEvent> defaultClickAction;
/*      */ 
/*      */   
/*      */   private GuiAction<InventoryClickEvent> defaultTopClickAction;
/*      */ 
/*      */   
/*      */   private GuiAction<InventoryClickEvent> playerInventoryAction;
/*      */ 
/*      */   
/*      */   private GuiAction<InventoryDragEvent> dragAction;
/*      */ 
/*      */   
/*      */   private GuiAction<InventoryCloseEvent> closeGuiAction;
/*      */ 
/*      */   
/*      */   private GuiAction<InventoryOpenEvent> openGuiAction;
/*      */ 
/*      */   
/*      */   private GuiAction<InventoryClickEvent> outsideClickAction;
/*      */ 
/*      */   
/*      */   private boolean updating;
/*      */   
/*      */   private boolean runCloseAction = true;
/*      */   
/*      */   private boolean runOpenAction = true;
/*      */ 
/*      */   
/*      */   public BaseGui(int rows, @NotNull String title, @NotNull Set<InteractionModifier> interactionModifiers) {
/*  122 */     int finalRows = rows;
/*  123 */     if (rows < 1 || rows > 6) finalRows = 1; 
/*  124 */     this.rows = finalRows;
/*  125 */     this.interactionModifiers = safeCopyOf(interactionModifiers);
/*  126 */     this.title = title;
/*  127 */     int inventorySize = this.rows * 9;
/*  128 */     this.inventory = Bukkit.createInventory(this, inventorySize, title);
/*  129 */     this.slotActions = new LinkedHashMap<>(inventorySize);
/*  130 */     this.guiItems = new LinkedHashMap<>(inventorySize);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BaseGui(@NotNull GuiType guiType, @NotNull String title, @NotNull Set<InteractionModifier> interactionModifiers) {
/*  142 */     this.guiType = guiType;
/*  143 */     this.interactionModifiers = safeCopyOf(interactionModifiers);
/*  144 */     this.title = title;
/*  145 */     int inventorySize = guiType.getLimit();
/*  146 */     this.inventory = Bukkit.createInventory(this, guiType.getInventoryType(), title);
/*  147 */     this.slotActions = new LinkedHashMap<>(inventorySize);
/*  148 */     this.guiItems = new LinkedHashMap<>(inventorySize);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public BaseGui(int rows, @NotNull String title) {
/*  160 */     int finalRows = rows;
/*  161 */     if (rows < 1 || rows > 6) finalRows = 1; 
/*  162 */     this.rows = finalRows;
/*  163 */     this.interactionModifiers = EnumSet.noneOf(InteractionModifier.class);
/*  164 */     this.title = title;
/*      */     
/*  166 */     this.inventory = Bukkit.createInventory(this, this.rows * 9, title);
/*  167 */     this.slotActions = new LinkedHashMap<>();
/*  168 */     this.guiItems = new LinkedHashMap<>();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public BaseGui(@NotNull GuiType guiType, @NotNull String title) {
/*  180 */     this.guiType = guiType;
/*  181 */     this.interactionModifiers = EnumSet.noneOf(InteractionModifier.class);
/*  182 */     this.title = title;
/*      */     
/*  184 */     this.inventory = Bukkit.createInventory(this, this.guiType.getInventoryType(), title);
/*  185 */     this.slotActions = new LinkedHashMap<>();
/*  186 */     this.guiItems = new LinkedHashMap<>();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   private Set<InteractionModifier> safeCopyOf(@NotNull Set<InteractionModifier> set) {
/*  197 */     if (set.isEmpty()) return EnumSet.noneOf(InteractionModifier.class); 
/*  198 */     return EnumSet.copyOf(set);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @NotNull
/*      */   public String getTitle() {
/*  209 */     return this.title;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Component title() {
/*  219 */     return (Component)Legacy.SERIALIZER.deserialize(this.title);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setItem(int slot, @NotNull GuiItem guiItem) {
/*  229 */     validateSlot(slot);
/*  230 */     this.guiItems.put(Integer.valueOf(slot), guiItem);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeItem(@NotNull GuiItem item) {
/*  242 */     Optional<Map.Entry<Integer, GuiItem>> entry = this.guiItems.entrySet().stream().filter(it -> ((GuiItem)it.getValue()).equals(item)).findFirst();
/*      */     
/*  244 */     entry.ifPresent(it -> {
/*      */           this.guiItems.remove(it.getKey());
/*      */           this.inventory.remove(((GuiItem)it.getValue()).getItemStack());
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeItem(@NotNull ItemStack item) {
/*  259 */     Optional<Map.Entry<Integer, GuiItem>> entry = this.guiItems.entrySet().stream().filter(it -> ((GuiItem)it.getValue()).getItemStack().equals(item)).findFirst();
/*      */     
/*  261 */     entry.ifPresent(it -> {
/*      */           this.guiItems.remove(it.getKey());
/*      */           this.inventory.remove(item);
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeItem(int slot) {
/*  273 */     validateSlot(slot);
/*  274 */     this.guiItems.remove(Integer.valueOf(slot));
/*  275 */     this.inventory.setItem(slot, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeItem(int row, int col) {
/*  285 */     removeItem(getSlotFromRowCol(row, col));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setItem(@NotNull List<Integer> slots, @NotNull GuiItem guiItem) {
/*  295 */     for (Iterator<Integer> iterator = slots.iterator(); iterator.hasNext(); ) { int slot = ((Integer)iterator.next()).intValue();
/*  296 */       setItem(slot, guiItem); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setItem(int row, int col, @NotNull GuiItem guiItem) {
/*  308 */     setItem(getSlotFromRowCol(row, col), guiItem);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addItem(@NotNull GuiItem... items) {
/*  318 */     addItem(false, items);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addItem(boolean expandIfFull, @NotNull GuiItem... items) {
/*  330 */     List<GuiItem> notAddedItems = new ArrayList<>();
/*      */     
/*  332 */     for (GuiItem guiItem : items) {
/*  333 */       for (int slot = 0; slot < this.rows * 9; slot++) {
/*  334 */         if (this.guiItems.get(Integer.valueOf(slot)) != null) {
/*  335 */           if (slot == this.rows * 9 - 1) {
/*  336 */             notAddedItems.add(guiItem);
/*      */           }
/*      */         }
/*      */         else {
/*      */           
/*  341 */           this.guiItems.put(Integer.valueOf(slot), guiItem);
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*  346 */     if (!expandIfFull || this.rows >= 6 || notAddedItems
/*  347 */       .isEmpty() || (this.guiType != null && this.guiType != GuiType.CHEST)) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  352 */     this.rows++;
/*  353 */     this.inventory = Bukkit.createInventory(this, this.rows * 9, this.title);
/*  354 */     update();
/*  355 */     addItem(true, notAddedItems.<GuiItem>toArray(new GuiItem[0]));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addSlotAction(int slot, @Nullable GuiAction<InventoryClickEvent> slotAction) {
/*  366 */     validateSlot(slot);
/*  367 */     this.slotActions.put(Integer.valueOf(slot), slotAction);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addSlotAction(int row, int col, @Nullable GuiAction<InventoryClickEvent> slotAction) {
/*  379 */     addSlotAction(getSlotFromRowCol(row, col), slotAction);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public GuiItem getGuiItem(int slot) {
/*  390 */     return this.guiItems.get(Integer.valueOf(slot));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isUpdating() {
/*  400 */     return this.updating;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUpdating(boolean updating) {
/*  409 */     this.updating = updating;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void open(@NotNull HumanEntity player) {
/*  418 */     if (player.isSleeping())
/*      */       return; 
/*  420 */     this.inventory.clear();
/*  421 */     populateGui();
/*  422 */     player.openInventory(this.inventory);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close(@NotNull HumanEntity player) {
/*  431 */     close(player, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close(@NotNull HumanEntity player, boolean runCloseAction) {
/*  441 */     Bukkit.getScheduler().runTaskLater(plugin, () -> { this.runCloseAction = runCloseAction; player.closeInventory(); this.runCloseAction = true; }2L);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void update() {
/*  452 */     this.inventory.clear();
/*  453 */     populateGui();
/*  454 */     for (HumanEntity viewer : new ArrayList(this.inventory.getViewers())) ((Player)viewer).updateInventory();
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract("_ -> this")
/*      */   @NotNull
/*      */   public BaseGui updateTitle(@NotNull String title) {
/*  467 */     this.updating = true;
/*      */     
/*  469 */     List<HumanEntity> viewers = new ArrayList<>(this.inventory.getViewers());
/*      */     
/*  471 */     this.inventory = Bukkit.createInventory(this, this.inventory.getSize(), title);
/*      */     
/*  473 */     for (HumanEntity player : viewers) {
/*  474 */       open(player);
/*      */     }
/*      */     
/*  477 */     this.updating = false;
/*  478 */     this.title = title;
/*  479 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateItem(int slot, @NotNull ItemStack itemStack) {
/*  489 */     GuiItem guiItem = this.guiItems.get(Integer.valueOf(slot));
/*      */     
/*  491 */     if (guiItem == null) {
/*  492 */       updateItem(slot, new GuiItem(itemStack));
/*      */       
/*      */       return;
/*      */     } 
/*  496 */     guiItem.setItemStack(itemStack);
/*  497 */     updateItem(slot, guiItem);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateItem(int row, int col, @NotNull ItemStack itemStack) {
/*  508 */     updateItem(getSlotFromRowCol(row, col), itemStack);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateItem(int slot, @NotNull GuiItem item) {
/*  518 */     this.guiItems.put(Integer.valueOf(slot), item);
/*  519 */     this.inventory.setItem(slot, item.getItemStack());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateItem(int row, int col, @NotNull GuiItem item) {
/*  530 */     updateItem(getSlotFromRowCol(row, col), item);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   @Contract(" -> this")
/*      */   public BaseGui disableItemPlace() {
/*  543 */     this.interactionModifiers.add(InteractionModifier.PREVENT_ITEM_PLACE);
/*  544 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   @Contract(" -> this")
/*      */   public BaseGui disableItemTake() {
/*  557 */     this.interactionModifiers.add(InteractionModifier.PREVENT_ITEM_TAKE);
/*  558 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   @Contract(" -> this")
/*      */   public BaseGui disableItemSwap() {
/*  571 */     this.interactionModifiers.add(InteractionModifier.PREVENT_ITEM_SWAP);
/*  572 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   @Contract(" -> this")
/*      */   public BaseGui disableItemDrop() {
/*  584 */     this.interactionModifiers.add(InteractionModifier.PREVENT_ITEM_DROP);
/*  585 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   @Contract(" -> this")
/*      */   public BaseGui disableOtherActions() {
/*  598 */     this.interactionModifiers.add(InteractionModifier.PREVENT_OTHER_ACTIONS);
/*  599 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   @Contract(" -> this")
/*      */   public BaseGui disableAllInteractions() {
/*  612 */     this.interactionModifiers.addAll(InteractionModifier.VALUES);
/*  613 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   @Contract(" -> this")
/*      */   public BaseGui enableItemPlace() {
/*  626 */     this.interactionModifiers.remove(InteractionModifier.PREVENT_ITEM_PLACE);
/*  627 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   @Contract(" -> this")
/*      */   public BaseGui enableItemTake() {
/*  640 */     this.interactionModifiers.remove(InteractionModifier.PREVENT_ITEM_TAKE);
/*  641 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   @Contract(" -> this")
/*      */   public BaseGui enableItemSwap() {
/*  654 */     this.interactionModifiers.remove(InteractionModifier.PREVENT_ITEM_SWAP);
/*  655 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   @Contract(" -> this")
/*      */   public BaseGui enableItemDrop() {
/*  667 */     this.interactionModifiers.remove(InteractionModifier.PREVENT_ITEM_DROP);
/*  668 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   @Contract(" -> this")
/*      */   public BaseGui enableOtherActions() {
/*  681 */     this.interactionModifiers.remove(InteractionModifier.PREVENT_OTHER_ACTIONS);
/*  682 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   @Contract(" -> this")
/*      */   public BaseGui enableAllInteractions() {
/*  695 */     this.interactionModifiers.clear();
/*  696 */     return this;
/*      */   }
/*      */   
/*      */   public boolean allInteractionsDisabled() {
/*  700 */     return (this.interactionModifiers.size() == InteractionModifier.VALUES.size());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canPlaceItems() {
/*  711 */     return !this.interactionModifiers.contains(InteractionModifier.PREVENT_ITEM_PLACE);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canTakeItems() {
/*  722 */     return !this.interactionModifiers.contains(InteractionModifier.PREVENT_ITEM_TAKE);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canSwapItems() {
/*  733 */     return !this.interactionModifiers.contains(InteractionModifier.PREVENT_ITEM_SWAP);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canDropItems() {
/*  743 */     return !this.interactionModifiers.contains(InteractionModifier.PREVENT_ITEM_DROP);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean allowsOtherActions() {
/*  753 */     return !this.interactionModifiers.contains(InteractionModifier.PREVENT_OTHER_ACTIONS);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public GuiFiller getFiller() {
/*  763 */     return this.filler;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Map<Integer, GuiItem> getGuiItems() {
/*  773 */     return this.guiItems;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Inventory getInventory() {
/*  784 */     return this.inventory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInventory(@NotNull Inventory inventory) {
/*  793 */     this.inventory = inventory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRows() {
/*  802 */     return this.rows;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public GuiType guiType() {
/*  812 */     return this.guiType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   GuiAction<InventoryClickEvent> getDefaultClickAction() {
/*  820 */     return this.defaultClickAction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDefaultClickAction(@Nullable GuiAction<InventoryClickEvent> defaultClickAction) {
/*  830 */     this.defaultClickAction = defaultClickAction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   GuiAction<InventoryClickEvent> getDefaultTopClickAction() {
/*  838 */     return this.defaultTopClickAction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDefaultTopClickAction(@Nullable GuiAction<InventoryClickEvent> defaultTopClickAction) {
/*  849 */     this.defaultTopClickAction = defaultTopClickAction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   GuiAction<InventoryClickEvent> getPlayerInventoryAction() {
/*  857 */     return this.playerInventoryAction;
/*      */   }
/*      */   
/*      */   public void setPlayerInventoryAction(@Nullable GuiAction<InventoryClickEvent> playerInventoryAction) {
/*  861 */     this.playerInventoryAction = playerInventoryAction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   GuiAction<InventoryDragEvent> getDragAction() {
/*  869 */     return this.dragAction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDragAction(@Nullable GuiAction<InventoryDragEvent> dragAction) {
/*  879 */     this.dragAction = dragAction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   GuiAction<InventoryCloseEvent> getCloseGuiAction() {
/*  887 */     return this.closeGuiAction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCloseGuiAction(@Nullable GuiAction<InventoryCloseEvent> closeGuiAction) {
/*  897 */     this.closeGuiAction = closeGuiAction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   GuiAction<InventoryOpenEvent> getOpenGuiAction() {
/*  905 */     return this.openGuiAction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOpenGuiAction(@Nullable GuiAction<InventoryOpenEvent> openGuiAction) {
/*  915 */     this.openGuiAction = openGuiAction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   GuiAction<InventoryClickEvent> getOutsideClickAction() {
/*  923 */     return this.outsideClickAction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOutsideClickAction(@Nullable GuiAction<InventoryClickEvent> outsideClickAction) {
/*  933 */     this.outsideClickAction = outsideClickAction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   GuiAction<InventoryClickEvent> getSlotAction(int slot) {
/*  943 */     return this.slotActions.get(Integer.valueOf(slot));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void populateGui() {
/*  950 */     for (Map.Entry<Integer, GuiItem> entry : this.guiItems.entrySet()) {
/*  951 */       this.inventory.setItem(((Integer)entry.getKey()).intValue(), ((GuiItem)entry.getValue()).getItemStack());
/*      */     }
/*      */   }
/*      */   
/*      */   boolean shouldRunCloseAction() {
/*  956 */     return this.runCloseAction;
/*      */   }
/*      */   
/*      */   boolean shouldRunOpenAction() {
/*  960 */     return this.runOpenAction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getSlotFromRowCol(int row, int col) {
/*  971 */     return col + (row - 1) * 9 - 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void validateSlot(int slot) {
/*  999 */     int limit = this.guiType.getLimit();
/*      */     
/* 1001 */     if (this.guiType == GuiType.CHEST) {
/* 1002 */       if (slot < 0 || slot >= this.rows * limit) throwInvalidSlot(slot);
/*      */       
/*      */       return;
/*      */     } 
/* 1006 */     if (slot < 0 || slot > limit) throwInvalidSlot(slot);
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void throwInvalidSlot(int slot) {
/* 1015 */     if (this.guiType == GuiType.CHEST) {
/* 1016 */       throw new GuiException("Slot " + slot + " is not valid for the gui type - " + this.guiType.name() + " and rows - " + this.rows + "!");
/*      */     }
/*      */     
/* 1019 */     throw new GuiException("Slot " + slot + " is not valid for the gui type - " + this.guiType.name() + "!");
/*      */   }
/*      */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\dev\triumphteam\gui\guis\BaseGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */