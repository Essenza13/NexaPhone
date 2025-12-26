/*     */ package dev.triumphteam.gui.guis;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Set;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryDragEvent;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.inventory.Inventory;
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
/*     */ public final class InteractionModifierListener
/*     */   implements Listener
/*     */ {
/*     */   @EventHandler
/*     */   public void onGuiClick(InventoryClickEvent event) {
/*  57 */     if (!(event.getInventory().getHolder() instanceof BaseGui)) {
/*     */       return;
/*     */     }
/*  60 */     BaseGui gui = (BaseGui)event.getInventory().getHolder();
/*     */     
/*  62 */     if (gui.allInteractionsDisabled()) {
/*  63 */       event.setCancelled(true);
/*  64 */       event.setResult(Event.Result.DENY);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  69 */     if ((!gui.canPlaceItems() && isPlaceItemEvent(event)) || (!gui.canTakeItems() && isTakeItemEvent(event)) || (!gui.canSwapItems() && isSwapItemEvent(event)) || (!gui.canDropItems() && isDropItemEvent(event)) || (!gui.allowsOtherActions() && isOtherEvent(event))) {
/*  70 */       event.setCancelled(true);
/*  71 */       event.setResult(Event.Result.DENY);
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
/*     */   @EventHandler
/*     */   public void onGuiDrag(InventoryDragEvent event) {
/*  84 */     if (!(event.getInventory().getHolder() instanceof BaseGui)) {
/*     */       return;
/*     */     }
/*  87 */     BaseGui gui = (BaseGui)event.getInventory().getHolder();
/*     */     
/*  89 */     if (gui.allInteractionsDisabled()) {
/*  90 */       event.setCancelled(true);
/*  91 */       event.setResult(Event.Result.DENY);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  96 */     if (gui.canPlaceItems() || !isDraggingOnGui(event)) {
/*     */       return;
/*     */     }
/*  99 */     event.setCancelled(true);
/* 100 */     event.setResult(Event.Result.DENY);
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
/*     */   private boolean isTakeItemEvent(InventoryClickEvent event) {
/* 112 */     Preconditions.checkNotNull(event, "event cannot be null");
/*     */     
/* 114 */     Inventory inventory = event.getInventory();
/* 115 */     Inventory clickedInventory = event.getClickedInventory();
/* 116 */     InventoryAction action = event.getAction();
/*     */ 
/*     */     
/* 119 */     if ((clickedInventory != null && clickedInventory.getType() == InventoryType.PLAYER) || inventory.getType() == InventoryType.PLAYER) {
/* 120 */       return false;
/*     */     }
/*     */     
/* 123 */     return (action == InventoryAction.MOVE_TO_OTHER_INVENTORY || isTakeAction(action));
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
/*     */   private boolean isPlaceItemEvent(InventoryClickEvent event) {
/* 135 */     Preconditions.checkNotNull(event, "event cannot be null");
/*     */     
/* 137 */     Inventory inventory = event.getInventory();
/* 138 */     Inventory clickedInventory = event.getClickedInventory();
/* 139 */     InventoryAction action = event.getAction();
/*     */ 
/*     */     
/* 142 */     if (action == InventoryAction.MOVE_TO_OTHER_INVENTORY && clickedInventory != null && clickedInventory
/* 143 */       .getType() == InventoryType.PLAYER && inventory
/* 144 */       .getType() != clickedInventory.getType()) {
/* 145 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 149 */     return (isPlaceAction(action) && (clickedInventory == null || clickedInventory
/* 150 */       .getType() != InventoryType.PLAYER) && inventory
/* 151 */       .getType() != InventoryType.PLAYER);
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
/*     */   private boolean isSwapItemEvent(InventoryClickEvent event) {
/* 163 */     Preconditions.checkNotNull(event, "event cannot be null");
/*     */     
/* 165 */     Inventory inventory = event.getInventory();
/* 166 */     Inventory clickedInventory = event.getClickedInventory();
/* 167 */     InventoryAction action = event.getAction();
/*     */     
/* 169 */     return (isSwapAction(action) && (clickedInventory == null || clickedInventory
/* 170 */       .getType() != InventoryType.PLAYER) && inventory
/* 171 */       .getType() != InventoryType.PLAYER);
/*     */   }
/*     */   
/*     */   private boolean isDropItemEvent(InventoryClickEvent event) {
/* 175 */     Preconditions.checkNotNull(event, "event cannot be null");
/*     */     
/* 177 */     Inventory inventory = event.getInventory();
/* 178 */     Inventory clickedInventory = event.getClickedInventory();
/* 179 */     InventoryAction action = event.getAction();
/*     */     
/* 181 */     return (isDropAction(action) && (clickedInventory != null || inventory
/* 182 */       .getType() != InventoryType.PLAYER));
/*     */   }
/*     */   
/*     */   private boolean isOtherEvent(InventoryClickEvent event) {
/* 186 */     Preconditions.checkNotNull(event, "event cannot be null");
/*     */     
/* 188 */     Inventory inventory = event.getInventory();
/* 189 */     Inventory clickedInventory = event.getClickedInventory();
/* 190 */     InventoryAction action = event.getAction();
/*     */     
/* 192 */     return (isOtherAction(action) && (clickedInventory != null || inventory
/* 193 */       .getType() != InventoryType.PLAYER));
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
/*     */   private boolean isDraggingOnGui(InventoryDragEvent event) {
/* 205 */     Preconditions.checkNotNull(event, "event cannot be null");
/* 206 */     int topSlots = event.getView().getTopInventory().getSize();
/*     */     
/* 208 */     return event.getRawSlots().stream().anyMatch(slot -> (slot.intValue() < topSlots));
/*     */   }
/*     */   
/*     */   private boolean isTakeAction(InventoryAction action) {
/* 212 */     Preconditions.checkNotNull(action, "action cannot be null");
/* 213 */     return ITEM_TAKE_ACTIONS.contains(action);
/*     */   }
/*     */   
/*     */   private boolean isPlaceAction(InventoryAction action) {
/* 217 */     Preconditions.checkNotNull(action, "action cannot be null");
/* 218 */     return ITEM_PLACE_ACTIONS.contains(action);
/*     */   }
/*     */   
/*     */   private boolean isSwapAction(InventoryAction action) {
/* 222 */     Preconditions.checkNotNull(action, "action cannot be null");
/* 223 */     return ITEM_SWAP_ACTIONS.contains(action);
/*     */   }
/*     */   
/*     */   private boolean isDropAction(InventoryAction action) {
/* 227 */     Preconditions.checkNotNull(action, "action cannot be null");
/* 228 */     return ITEM_DROP_ACTIONS.contains(action);
/*     */   }
/*     */   
/*     */   private boolean isOtherAction(InventoryAction action) {
/* 232 */     Preconditions.checkNotNull(action, "action cannot be null");
/* 233 */     return (action == InventoryAction.CLONE_STACK || action == InventoryAction.UNKNOWN);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 239 */   private static final Set<InventoryAction> ITEM_TAKE_ACTIONS = Collections.unmodifiableSet(EnumSet.of(InventoryAction.PICKUP_ONE, new InventoryAction[] { InventoryAction.PICKUP_SOME, InventoryAction.PICKUP_HALF, InventoryAction.PICKUP_ALL, InventoryAction.COLLECT_TO_CURSOR, InventoryAction.HOTBAR_SWAP, InventoryAction.MOVE_TO_OTHER_INVENTORY }));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   private static final Set<InventoryAction> ITEM_PLACE_ACTIONS = Collections.unmodifiableSet(EnumSet.of(InventoryAction.PLACE_ONE, InventoryAction.PLACE_SOME, InventoryAction.PLACE_ALL));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   private static final Set<InventoryAction> ITEM_SWAP_ACTIONS = Collections.unmodifiableSet(EnumSet.of(InventoryAction.HOTBAR_SWAP, InventoryAction.SWAP_WITH_CURSOR, InventoryAction.HOTBAR_MOVE_AND_READD));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 254 */   private static final Set<InventoryAction> ITEM_DROP_ACTIONS = Collections.unmodifiableSet(EnumSet.of(InventoryAction.DROP_ONE_SLOT, InventoryAction.DROP_ALL_SLOT, InventoryAction.DROP_ONE_CURSOR, InventoryAction.DROP_ALL_CURSOR));
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\dev\triumphteam\gui\guis\InteractionModifierListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */