package com.hochoy.spark;

import org.roaringbitmap.RoaringBitmap;

/**
 * Describe:
 *
 * @author hochoy <hochoy18@sina.com>
 * @version V1.0.0
 * @date 2019/1/10
 */
public class Basic {
    public static void main(String[] args) {
        RoaringBitmap rb = RoaringBitmap.bitmapOf(1, 2, 3, 1000);
        RoaringBitmap rb2 = new RoaringBitmap();
        rb2.add(4000, 4101);
        rb2.add(300, 401);
        rb2.add(10, 20);

        RoaringBitmap rbor = RoaringBitmap.or(rb, rb2);
        rb.or(rb2);
        boolean eq = rbor.equals(rb);
        if (!eq) throw new RuntimeException("bug");
        int cardinality = rb.getCardinality();
        System.out.println(cardinality);
        for (int i : rb) {
            System.out.println(i);
        }

    }
}
