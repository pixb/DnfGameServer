package com.dnfm.common.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingUniqueQueue<E> extends LinkedBlockingQueue<E> {
   private static final long serialVersionUID = 8351632564634804122L;
   private final Set<E> datas = new ConcurrentHashSet<E>(1200);

   public boolean contains(Object o) {
      return this.datas.contains(o);
   }

   public boolean containsAll(Collection<?> c) {
      return this.datas.containsAll(c);
   }

   public E take() {
      E head = null;

      try {
         head = (E)super.take();
      } catch (Exception var3) {
      }

      this.datas.remove(head);
      return head;
   }

   public boolean add(E e) {
      if (this.contains(e)) {
         return false;
      } else {
         this.datas.add(e);
         return super.add(e);
      }
   }

   public boolean addAll(Collection<? extends E> c) {
      boolean modified = false;

      for(E e : c) {
         if (this.add(e)) {
            modified = true;
         }
      }

      return modified;
   }

   public boolean remove(Object o) {
      this.datas.remove(o);
      return super.remove(o);
   }

   public boolean removeAll(Collection<?> c) {
      for(Object e : c) {
         this.datas.remove(e);
      }

      return super.removeAll(c);
   }

   public Iterator<E> iterator() {
      return new Itr(super.iterator());
   }

   private class Itr implements Iterator<E> {
      private final Iterator<E> it;
      private E curr;

      Itr(Iterator<E> it) {
         this.it = it;
      }

      public boolean hasNext() {
         return this.it.hasNext();
      }

      public E next() {
         this.curr = (E)this.it.next();
         return this.curr;
      }

      public void remove() {
         this.it.remove();
         BlockingUniqueQueue.this.datas.remove(this.curr);
      }
   }
}
