/*     */ package com.google.gson.internal;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InvalidObjectException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Comparator;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
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
/*     */ public final class LinkedTreeMap<K, V>
/*     */   extends AbstractMap<K, V>
/*     */   implements Serializable
/*     */ {
/*  44 */   private static final Comparator<Comparable> NATURAL_ORDER = new Comparator<Comparable>() {
/*     */       public int compare(Comparable<Comparable> a, Comparable b) {
/*  46 */         return a.compareTo(b);
/*     */       }
/*     */     };
/*     */   
/*     */   private final Comparator<? super K> comparator;
/*     */   private final boolean allowNullValues;
/*     */   Node<K, V> root;
/*  53 */   int size = 0;
/*  54 */   int modCount = 0;
/*     */ 
/*     */   
/*     */   final Node<K, V> header;
/*     */   
/*     */   private EntrySet entrySet;
/*     */   
/*     */   private KeySet keySet;
/*     */ 
/*     */   
/*     */   public LinkedTreeMap() {
/*  65 */     this((Comparator)NATURAL_ORDER, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LinkedTreeMap(boolean allowNullValues) {
/*  76 */     this((Comparator)NATURAL_ORDER, allowNullValues);
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
/*     */   public LinkedTreeMap(Comparator<? super K> comparator, boolean allowNullValues) {
/*  89 */     this
/*     */       
/*  91 */       .comparator = (comparator != null) ? comparator : (Comparator)NATURAL_ORDER;
/*  92 */     this.allowNullValues = allowNullValues;
/*  93 */     this.header = new Node<>(allowNullValues);
/*     */   }
/*     */   
/*     */   public int size() {
/*  97 */     return this.size;
/*     */   }
/*     */   
/*     */   public V get(Object key) {
/* 101 */     Node<K, V> node = findByObject(key);
/* 102 */     return (node != null) ? node.value : null;
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 106 */     return (findByObject(key) != null);
/*     */   }
/*     */   
/*     */   public V put(K key, V value) {
/* 110 */     if (key == null) {
/* 111 */       throw new NullPointerException("key == null");
/*     */     }
/* 113 */     if (value == null && !this.allowNullValues) {
/* 114 */       throw new NullPointerException("value == null");
/*     */     }
/* 116 */     Node<K, V> created = find(key, true);
/* 117 */     V result = created.value;
/* 118 */     created.value = value;
/* 119 */     return result;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 123 */     this.root = null;
/* 124 */     this.size = 0;
/* 125 */     this.modCount++;
/*     */ 
/*     */     
/* 128 */     Node<K, V> header = this.header;
/* 129 */     header.next = header.prev = header;
/*     */   }
/*     */   
/*     */   public V remove(Object key) {
/* 133 */     Node<K, V> node = removeInternalByKey(key);
/* 134 */     return (node != null) ? node.value : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Node<K, V> find(K key, boolean create) {
/*     */     Node<K, V> created;
/* 144 */     Comparator<? super K> comparator = this.comparator;
/* 145 */     Node<K, V> nearest = this.root;
/* 146 */     int comparison = 0;
/*     */     
/* 148 */     if (nearest != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 153 */       Comparable<Object> comparableKey = (comparator == NATURAL_ORDER) ? (Comparable<Object>)key : null;
/*     */ 
/*     */ 
/*     */       
/*     */       while (true) {
/* 158 */         comparison = (comparableKey != null) ? comparableKey.compareTo(nearest.key) : comparator.compare(key, nearest.key);
/*     */ 
/*     */         
/* 161 */         if (comparison == 0) {
/* 162 */           return nearest;
/*     */         }
/*     */ 
/*     */         
/* 166 */         Node<K, V> child = (comparison < 0) ? nearest.left : nearest.right;
/* 167 */         if (child == null) {
/*     */           break;
/*     */         }
/*     */         
/* 171 */         nearest = child;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 176 */     if (!create) {
/* 177 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 181 */     Node<K, V> header = this.header;
/*     */     
/* 183 */     if (nearest == null) {
/*     */       
/* 185 */       if (comparator == NATURAL_ORDER && !(key instanceof Comparable)) {
/* 186 */         throw new ClassCastException(key.getClass().getName() + " is not Comparable");
/*     */       }
/* 188 */       created = new Node<>(this.allowNullValues, nearest, key, header, header.prev);
/* 189 */       this.root = created;
/*     */     } else {
/* 191 */       created = new Node<>(this.allowNullValues, nearest, key, header, header.prev);
/* 192 */       if (comparison < 0) {
/* 193 */         nearest.left = created;
/*     */       } else {
/* 195 */         nearest.right = created;
/*     */       } 
/* 197 */       rebalance(nearest, true);
/*     */     } 
/* 199 */     this.size++;
/* 200 */     this.modCount++;
/*     */     
/* 202 */     return created;
/*     */   }
/*     */ 
/*     */   
/*     */   Node<K, V> findByObject(Object key) {
/*     */     try {
/* 208 */       return (key != null) ? find((K)key, false) : null;
/* 209 */     } catch (ClassCastException e) {
/* 210 */       return null;
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
/*     */   Node<K, V> findByEntry(Map.Entry<?, ?> entry) {
/* 224 */     Node<K, V> mine = findByObject(entry.getKey());
/* 225 */     boolean valuesEqual = (mine != null && equal(mine.value, entry.getValue()));
/* 226 */     return valuesEqual ? mine : null;
/*     */   }
/*     */   
/*     */   private boolean equal(Object a, Object b) {
/* 230 */     return (a == b || (a != null && a.equals(b)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeInternal(Node<K, V> node, boolean unlink) {
/* 240 */     if (unlink) {
/* 241 */       node.prev.next = node.next;
/* 242 */       node.next.prev = node.prev;
/*     */     } 
/*     */     
/* 245 */     Node<K, V> left = node.left;
/* 246 */     Node<K, V> right = node.right;
/* 247 */     Node<K, V> originalParent = node.parent;
/* 248 */     if (left != null && right != null) {
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
/* 259 */       Node<K, V> adjacent = (left.height > right.height) ? left.last() : right.first();
/* 260 */       removeInternal(adjacent, false);
/*     */       
/* 262 */       int leftHeight = 0;
/* 263 */       left = node.left;
/* 264 */       if (left != null) {
/* 265 */         leftHeight = left.height;
/* 266 */         adjacent.left = left;
/* 267 */         left.parent = adjacent;
/* 268 */         node.left = null;
/*     */       } 
/*     */       
/* 271 */       int rightHeight = 0;
/* 272 */       right = node.right;
/* 273 */       if (right != null) {
/* 274 */         rightHeight = right.height;
/* 275 */         adjacent.right = right;
/* 276 */         right.parent = adjacent;
/* 277 */         node.right = null;
/*     */       } 
/*     */       
/* 280 */       adjacent.height = Math.max(leftHeight, rightHeight) + 1;
/* 281 */       replaceInParent(node, adjacent); return;
/*     */     } 
/* 283 */     if (left != null) {
/* 284 */       replaceInParent(node, left);
/* 285 */       node.left = null;
/* 286 */     } else if (right != null) {
/* 287 */       replaceInParent(node, right);
/* 288 */       node.right = null;
/*     */     } else {
/* 290 */       replaceInParent(node, null);
/*     */     } 
/*     */     
/* 293 */     rebalance(originalParent, false);
/* 294 */     this.size--;
/* 295 */     this.modCount++;
/*     */   }
/*     */   
/*     */   Node<K, V> removeInternalByKey(Object key) {
/* 299 */     Node<K, V> node = findByObject(key);
/* 300 */     if (node != null) {
/* 301 */       removeInternal(node, true);
/*     */     }
/* 303 */     return node;
/*     */   }
/*     */   
/*     */   private void replaceInParent(Node<K, V> node, Node<K, V> replacement) {
/* 307 */     Node<K, V> parent = node.parent;
/* 308 */     node.parent = null;
/* 309 */     if (replacement != null) {
/* 310 */       replacement.parent = parent;
/*     */     }
/*     */     
/* 313 */     if (parent != null) {
/* 314 */       if (parent.left == node) {
/* 315 */         parent.left = replacement;
/*     */       } else {
/* 317 */         assert parent.right == node;
/* 318 */         parent.right = replacement;
/*     */       } 
/*     */     } else {
/* 321 */       this.root = replacement;
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
/*     */   private void rebalance(Node<K, V> unbalanced, boolean insert) {
/* 333 */     for (Node<K, V> node = unbalanced; node != null; node = node.parent) {
/* 334 */       Node<K, V> left = node.left;
/* 335 */       Node<K, V> right = node.right;
/* 336 */       int leftHeight = (left != null) ? left.height : 0;
/* 337 */       int rightHeight = (right != null) ? right.height : 0;
/*     */       
/* 339 */       int delta = leftHeight - rightHeight;
/* 340 */       if (delta == -2) {
/* 341 */         Node<K, V> rightLeft = right.left;
/* 342 */         Node<K, V> rightRight = right.right;
/* 343 */         int rightRightHeight = (rightRight != null) ? rightRight.height : 0;
/* 344 */         int rightLeftHeight = (rightLeft != null) ? rightLeft.height : 0;
/*     */         
/* 346 */         int rightDelta = rightLeftHeight - rightRightHeight;
/* 347 */         if (rightDelta == -1 || (rightDelta == 0 && !insert)) {
/* 348 */           rotateLeft(node);
/*     */         } else {
/* 350 */           assert rightDelta == 1;
/* 351 */           rotateRight(right);
/* 352 */           rotateLeft(node);
/*     */         } 
/* 354 */         if (insert) {
/*     */           break;
/*     */         }
/*     */       }
/* 358 */       else if (delta == 2) {
/* 359 */         Node<K, V> leftLeft = left.left;
/* 360 */         Node<K, V> leftRight = left.right;
/* 361 */         int leftRightHeight = (leftRight != null) ? leftRight.height : 0;
/* 362 */         int leftLeftHeight = (leftLeft != null) ? leftLeft.height : 0;
/*     */         
/* 364 */         int leftDelta = leftLeftHeight - leftRightHeight;
/* 365 */         if (leftDelta == 1 || (leftDelta == 0 && !insert)) {
/* 366 */           rotateRight(node);
/*     */         } else {
/* 368 */           assert leftDelta == -1;
/* 369 */           rotateLeft(left);
/* 370 */           rotateRight(node);
/*     */         } 
/* 372 */         if (insert) {
/*     */           break;
/*     */         }
/*     */       }
/* 376 */       else if (delta == 0) {
/* 377 */         node.height = leftHeight + 1;
/* 378 */         if (insert) {
/*     */           break;
/*     */         }
/*     */       } else {
/*     */         
/* 383 */         assert delta == -1 || delta == 1;
/* 384 */         node.height = Math.max(leftHeight, rightHeight) + 1;
/* 385 */         if (!insert) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void rotateLeft(Node<K, V> root) {
/* 396 */     Node<K, V> left = root.left;
/* 397 */     Node<K, V> pivot = root.right;
/* 398 */     Node<K, V> pivotLeft = pivot.left;
/* 399 */     Node<K, V> pivotRight = pivot.right;
/*     */ 
/*     */     
/* 402 */     root.right = pivotLeft;
/* 403 */     if (pivotLeft != null) {
/* 404 */       pivotLeft.parent = root;
/*     */     }
/*     */     
/* 407 */     replaceInParent(root, pivot);
/*     */ 
/*     */     
/* 410 */     pivot.left = root;
/* 411 */     root.parent = pivot;
/*     */ 
/*     */     
/* 414 */     root.height = Math.max((left != null) ? left.height : 0, 
/* 415 */         (pivotLeft != null) ? pivotLeft.height : 0) + 1;
/* 416 */     pivot.height = Math.max(root.height, 
/* 417 */         (pivotRight != null) ? pivotRight.height : 0) + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void rotateRight(Node<K, V> root) {
/* 424 */     Node<K, V> pivot = root.left;
/* 425 */     Node<K, V> right = root.right;
/* 426 */     Node<K, V> pivotLeft = pivot.left;
/* 427 */     Node<K, V> pivotRight = pivot.right;
/*     */ 
/*     */     
/* 430 */     root.left = pivotRight;
/* 431 */     if (pivotRight != null) {
/* 432 */       pivotRight.parent = root;
/*     */     }
/*     */     
/* 435 */     replaceInParent(root, pivot);
/*     */ 
/*     */     
/* 438 */     pivot.right = root;
/* 439 */     root.parent = pivot;
/*     */ 
/*     */     
/* 442 */     root.height = Math.max((right != null) ? right.height : 0, 
/* 443 */         (pivotRight != null) ? pivotRight.height : 0) + 1;
/* 444 */     pivot.height = Math.max(root.height, 
/* 445 */         (pivotLeft != null) ? pivotLeft.height : 0) + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/* 452 */     EntrySet result = this.entrySet;
/* 453 */     return (result != null) ? result : (this.entrySet = new EntrySet());
/*     */   }
/*     */   
/*     */   public Set<K> keySet() {
/* 457 */     KeySet result = this.keySet;
/* 458 */     return (result != null) ? result : (this.keySet = new KeySet());
/*     */   }
/*     */   
/*     */   static final class Node<K, V>
/*     */     implements Map.Entry<K, V> {
/*     */     Node<K, V> parent;
/*     */     Node<K, V> left;
/*     */     Node<K, V> right;
/*     */     Node<K, V> next;
/*     */     Node<K, V> prev;
/*     */     final K key;
/*     */     final boolean allowNullValue;
/*     */     V value;
/*     */     int height;
/*     */     
/*     */     Node(boolean allowNullValue) {
/* 474 */       this.key = null;
/* 475 */       this.allowNullValue = allowNullValue;
/* 476 */       this.next = this.prev = this;
/*     */     }
/*     */ 
/*     */     
/*     */     Node(boolean allowNullValue, Node<K, V> parent, K key, Node<K, V> next, Node<K, V> prev) {
/* 481 */       this.parent = parent;
/* 482 */       this.key = key;
/* 483 */       this.allowNullValue = allowNullValue;
/* 484 */       this.height = 1;
/* 485 */       this.next = next;
/* 486 */       this.prev = prev;
/* 487 */       prev.next = this;
/* 488 */       next.prev = this;
/*     */     }
/*     */     
/*     */     public K getKey() {
/* 492 */       return this.key;
/*     */     }
/*     */     
/*     */     public V getValue() {
/* 496 */       return this.value;
/*     */     }
/*     */     
/*     */     public V setValue(V value) {
/* 500 */       if (value == null && !this.allowNullValue) {
/* 501 */         throw new NullPointerException("value == null");
/*     */       }
/* 503 */       V oldValue = this.value;
/* 504 */       this.value = value;
/* 505 */       return oldValue;
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 509 */       if (o instanceof Map.Entry) {
/* 510 */         Map.Entry<?, ?> other = (Map.Entry<?, ?>)o;
/* 511 */         return (((this.key == null) ? (other.getKey() == null) : this.key.equals(other.getKey())) && ((this.value == null) ? (other
/* 512 */           .getValue() == null) : this.value.equals(other.getValue())));
/*     */       } 
/* 514 */       return false;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 518 */       return ((this.key == null) ? 0 : this.key.hashCode()) ^ (
/* 519 */         (this.value == null) ? 0 : this.value.hashCode());
/*     */     }
/*     */     
/*     */     public String toString() {
/* 523 */       return (new StringBuilder()).append(this.key).append("=").append(this.value).toString();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Node<K, V> first() {
/* 530 */       Node<K, V> node = this;
/* 531 */       Node<K, V> child = node.left;
/* 532 */       while (child != null) {
/* 533 */         node = child;
/* 534 */         child = node.left;
/*     */       } 
/* 536 */       return node;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Node<K, V> last() {
/* 543 */       Node<K, V> node = this;
/* 544 */       Node<K, V> child = node.right;
/* 545 */       while (child != null) {
/* 546 */         node = child;
/* 547 */         child = node.right;
/*     */       } 
/* 549 */       return node;
/*     */     }
/*     */   }
/*     */   
/*     */   private abstract class LinkedTreeMapIterator<T> implements Iterator<T> {
/* 554 */     LinkedTreeMap.Node<K, V> next = LinkedTreeMap.this.header.next;
/* 555 */     LinkedTreeMap.Node<K, V> lastReturned = null;
/* 556 */     int expectedModCount = LinkedTreeMap.this.modCount;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public final boolean hasNext() {
/* 562 */       return (this.next != LinkedTreeMap.this.header);
/*     */     }
/*     */     
/*     */     final LinkedTreeMap.Node<K, V> nextNode() {
/* 566 */       LinkedTreeMap.Node<K, V> e = this.next;
/* 567 */       if (e == LinkedTreeMap.this.header) {
/* 568 */         throw new NoSuchElementException();
/*     */       }
/* 570 */       if (LinkedTreeMap.this.modCount != this.expectedModCount) {
/* 571 */         throw new ConcurrentModificationException();
/*     */       }
/* 573 */       this.next = e.next;
/* 574 */       return this.lastReturned = e;
/*     */     }
/*     */     
/*     */     public final void remove() {
/* 578 */       if (this.lastReturned == null) {
/* 579 */         throw new IllegalStateException();
/*     */       }
/* 581 */       LinkedTreeMap.this.removeInternal(this.lastReturned, true);
/* 582 */       this.lastReturned = null;
/* 583 */       this.expectedModCount = LinkedTreeMap.this.modCount;
/*     */     }
/*     */   }
/*     */   
/*     */   class EntrySet extends AbstractSet<Map.Entry<K, V>> {
/*     */     public int size() {
/* 589 */       return LinkedTreeMap.this.size;
/*     */     }
/*     */     
/*     */     public Iterator<Map.Entry<K, V>> iterator() {
/* 593 */       return new LinkedTreeMap<K, V>.LinkedTreeMapIterator<Map.Entry<K, V>>() {
/*     */           public Map.Entry<K, V> next() {
/* 595 */             return nextNode();
/*     */           }
/*     */         };
/*     */     }
/*     */     
/*     */     public boolean contains(Object o) {
/* 601 */       return (o instanceof Map.Entry && LinkedTreeMap.this.findByEntry((Map.Entry<?, ?>)o) != null);
/*     */     }
/*     */     
/*     */     public boolean remove(Object o) {
/* 605 */       if (!(o instanceof Map.Entry)) {
/* 606 */         return false;
/*     */       }
/*     */       
/* 609 */       LinkedTreeMap.Node<K, V> node = LinkedTreeMap.this.findByEntry((Map.Entry<?, ?>)o);
/* 610 */       if (node == null) {
/* 611 */         return false;
/*     */       }
/* 613 */       LinkedTreeMap.this.removeInternal(node, true);
/* 614 */       return true;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 618 */       LinkedTreeMap.this.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   final class KeySet extends AbstractSet<K> {
/*     */     public int size() {
/* 624 */       return LinkedTreeMap.this.size;
/*     */     }
/*     */     
/*     */     public Iterator<K> iterator() {
/* 628 */       return new LinkedTreeMap<K, V>.LinkedTreeMapIterator<K>() {
/*     */           public K next() {
/* 630 */             return (nextNode()).key;
/*     */           }
/*     */         };
/*     */     }
/*     */     
/*     */     public boolean contains(Object o) {
/* 636 */       return LinkedTreeMap.this.containsKey(o);
/*     */     }
/*     */     
/*     */     public boolean remove(Object key) {
/* 640 */       return (LinkedTreeMap.this.removeInternalByKey(key) != null);
/*     */     }
/*     */     
/*     */     public void clear() {
/* 644 */       LinkedTreeMap.this.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object writeReplace() throws ObjectStreamException {
/* 655 */     return new LinkedHashMap<>(this);
/*     */   }
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException {
/* 660 */     throw new InvalidObjectException("Deserialization is unsupported");
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\com\google\gson\internal\LinkedTreeMap.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */