package com.test.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LimitedCacheMap<K, V> {
   private LinkedHashMap<K, LimitedCacheMap<K, V>.Element<V>> data;
   private int capacity;
   private long aliveTime;
   private boolean useLru;
   private ReentrantReadWriteLock lock;
   private ReentrantReadWriteLock.ReadLock readLock;
   private ReentrantReadWriteLock.WriteLock writeLock;

   public LimitedCacheMap(int capacity, long aliveTime) {
      this(capacity, aliveTime, Boolean.FALSE);
   }

   public LimitedCacheMap(final int capacity, final long aliveTime, boolean useLru) {
      this.lock = new ReentrantReadWriteLock();
      this.readLock = this.lock.readLock();
      this.writeLock = this.lock.writeLock();
      this.capacity = capacity;
      this.aliveTime = aliveTime;
      this.useLru = useLru;
      this.data = new LinkedHashMap<K, LimitedCacheMap<K, V>.Element<V>>(capacity, 1.0F, useLru) {
         private static final long serialVersionUID = 1L;

         protected boolean removeEldestEntry(Map.Entry<K, LimitedCacheMap<K, V>.Element<V>> eldest) {
            if (this.size() > capacity) {
               return true;
            } else {
               long eleAliveTime = System.currentTimeMillis() - ((Element)eldest.getValue()).bornTime;
               return eleAliveTime > aliveTime;
            }
         }
      };
   }

   public V put(K key, V value) {
      V preValue = null;
      LimitedCacheMap<K, V>.Element<V> newValue = new Element<V>(value);
      this.readLock.lock();

      Object var5;
      try {
         if (this.data.containsKey(key)) {
            preValue = ((Element<V>)this.data.get(key)).value;
         }

         this.data.put(key, newValue);
         var5 = preValue;
      } finally {
         this.readLock.unlock();
      }

      return (V)var5;
   }

   public V get(K key) {
      Lock lock = (Lock)(this.useLru ? this.writeLock : this.readLock);
      lock.lock();
      LimitedCacheMap<K, V>.Element<V> target = null;

      try {
         target = (Element)this.data.get(key);
         if (target == null) {
            Object var4 = null;
            return (V)var4;
         }
      } finally {
         lock.unlock();
      }

      long eleAlive = System.currentTimeMillis() - target.bornTime;
      if (eleAlive < this.aliveTime) {
         return target.value;
      } else {
         this.remove(key);
         return null;
      }
   }

   public int size() {
      this.readLock.lock();

      int var1;
      try {
         var1 = this.data.size();
      } finally {
         this.readLock.unlock();
      }

      return var1;
   }

   public void remove(K key) {
      this.writeLock.lock();

      try {
         this.data.remove(key);
      } finally {
         this.writeLock.unlock();
      }

   }

   public void clear() {
      this.writeLock.lock();

      try {
         this.data.clear();
      } finally {
         this.writeLock.unlock();
      }

   }

   public static void main(String[] args) throws Exception {
      LimitedCacheMap<Integer, Integer> map = new LimitedCacheMap<Integer, Integer>(3, 1500L);
      map.put(1, 1);
      System.out.println("-----");
      Thread.sleep(1200L);
      System.out.println(map.get(1));
   }

   public String toString() {
      return "LinkedCacheMap [data=" + this.data + ", capacity=" + this.capacity + ", aliveTime=" + this.aliveTime + ", useLru=" + this.useLru + "]";
   }

   class Element<V> {
      V value;
      long bornTime;

      Element(V v) {
         this.value = v;
         this.bornTime = System.currentTimeMillis();
      }

      public String toString() {
         return "Element [value=" + this.value + "]";
      }
   }
}
